package com.example.demo.Menu.Game;

import com.example.demo.LifeStage;
import com.example.demo.TamagotchiState;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.canvas.Canvas;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Random;

public class TetrisPane extends Pane {

  private static final int TILE_SIZE = 7;
  private static final int ROWS = 11;
  private static final int COLS = 10;

  private final Canvas canvas;
  private final GraphicsContext gc;
  private final int[][] grid = new int[ROWS][COLS];

  private Tetromino currentPiece;
  private Tetromino nextPiece;
  private final Random random = new Random();
  private int tick = 0;
  private int score = 0;
  private MediaPlayer mediaPlayer;
  public boolean gameOver = false;
  private ImageView tamagotchiView;
  private final Image tamagotchiBabyGif = new Image(getClass().getResource("/gifs/Baby.gif").toExternalForm());
  private final Image tamagotchiChildGif = new Image(getClass().getResource("/gifs/Child.gif").toExternalForm());
  private final Image tamagotchiTeenagerGif = new Image(getClass().getResource("/gifs/Teenager.gif").toExternalForm());
  private final Image tamagotchiAdultGif = new Image(getClass().getResource("/gifs/Adult.gif").toExternalForm());
  private final Image tamagotchiDeadEmptyScreen = new Image(getClass().getResource("/images/EmptyScreen.png").toExternalForm());
  private final Image tamagotchiEggImage = new Image(getClass().getResource("/images/Ei.png").toExternalForm());
  private final TamagotchiState state;


  public TetrisPane(TamagotchiState tamagotchiState) {
    this.state = tamagotchiState;
    canvas = new Canvas((COLS + 10) * TILE_SIZE, ROWS * TILE_SIZE);
    gc = canvas.getGraphicsContext2D();
    this.getChildren().add(canvas);
    try {
      Font pixelFont = Font.loadFont(getClass().getResource("/fonts/PressStart2P-Regular.ttf").toExternalForm(), 8);
      gc.setFont(pixelFont);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private Image selectGif(LifeStage stage) {
    return switch (stage) {
      case EGG -> tamagotchiEggImage;
      case BABY -> tamagotchiBabyGif;
      case CHILD -> tamagotchiChildGif;
      case TEEN -> tamagotchiTeenagerGif;
      case ADULT -> tamagotchiAdultGif;
      case DEAD -> tamagotchiDeadEmptyScreen;
    };
  }

  public void startGame() {
    state.happiness += 10;
    if (!gameOver) {
      score = 0;
      tick = 0;
      clearGrid();
      spawnNewPiece();
      playMusic("/music/Tetris.mp3");
      gameLoop.start();
      tamagotchiView = new ImageView(selectGif(state.getCurrentStage()));
      tamagotchiView.setLayoutX((COLS + 5) * TILE_SIZE); // position right of game
      tamagotchiView.setLayoutY(45);                     // adjust Y as needed
      tamagotchiView.setFitWidth(30);                   // resize if needed
      tamagotchiView.setPreserveRatio(true);
      this.getChildren().add(tamagotchiView);
    }
  }

  public void stopGame() {
    gameLoop.stop();
    stopMusic();
    gameOver = false;
    clearGrid();
    render(); // force screen to update
    this.getChildren().remove(tamagotchiView);
  }

  private void clearGrid() {
    for (int y = 0; y < ROWS; y++) {
      for (int x = 0; x < COLS; x++) {
        grid[y][x] = 0;
      }
    }
  }

  private void playMusic(String path) {
    try {
      Media sound = new Media(getClass().getResource(path).toExternalForm());
      mediaPlayer = new MediaPlayer(sound);
      mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // loop forever
      mediaPlayer.setVolume(0.2);
      mediaPlayer.play();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void playOneShot(String path) {
    try {
      Media sound = new Media(getClass().getResource(path).toExternalForm());
      MediaPlayer oneShot = new MediaPlayer(sound);
      oneShot.setVolume(0.3);
      oneShot.play();
      oneShot.setOnEndOfMedia(oneShot::dispose);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void stopMusic() {
    if (mediaPlayer != null) {
      mediaPlayer.stop();
    }
  }

  private final AnimationTimer gameLoop = new AnimationTimer() {
    @Override
    public void handle(long now) {
      if (!gameOver) {
        tick++;
        if (tick % 30 == 0) {
          moveDown();
        }
        render();
      }
    }
  };

  private void moveDown() {
    if (canMove(currentPiece, currentPiece.x, currentPiece.y + 1)) {
      currentPiece.y++;
    } else {
      placePiece(currentPiece);
      spawnNewPiece();
    }
  }

  private void spawnNewPiece() {
    if (nextPiece == null) {
      currentPiece = new Tetromino(Tetromino.SHAPES[random.nextInt(Tetromino.SHAPES.length)]);
    } else {
      currentPiece = nextPiece;
    }

    nextPiece = new Tetromino(Tetromino.SHAPES[random.nextInt(Tetromino.SHAPES.length)]);

    // Game Over check
    if (!canMove(currentPiece, currentPiece.x, currentPiece.y)) {
      gameOver = true;
      stopMusic();
      playOneShot("/music/GameOver.mp3");
    }
  }

  private boolean canMove(Tetromino t, int newX, int newY) {
    for (int y = 0; y < t.shape.length; y++) {
      for (int x = 0; x < t.shape[y].length; x++) {
        if (t.shape[y][x] == 1) {
          int gx = newX + x;
          int gy = newY + y;
          if (gx < 0 || gx >= COLS || gy >= ROWS || grid[gy][gx] == 1) {
            return false;
          }
        }
      }
    }
    return true;
  }

  private void placePiece(Tetromino t) {
    for (int y = 0; y < t.shape.length; y++) {
      for (int x = 0; x < t.shape[y].length; x++) {
        if (t.shape[y][x] == 1) {
          grid[t.y + y][t.x + x] = 1;
        }
      }
    }
    clearFullLines();
  }

  private void render() {
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

    // 🔳 LEFT BORDER
    gc.setFill(Color.LIGHTGRAY);
    gc.fillRect(-TILE_SIZE, 0, TILE_SIZE, ROWS * TILE_SIZE);

    // 🔳 RIGHT BORDER
    gc.setFill(Color.LIGHTGRAY);
    gc.fillRect(COLS * TILE_SIZE, 0, TILE_SIZE, ROWS * TILE_SIZE);

    // Draw fixed grid
    for (int y = 0; y < ROWS; y++) {
      for (int x = 0; x < COLS; x++) {
        if (grid[y][x] == 1) {
          drawBlock(x, y, Color.DARKGRAY);
        }
      }
    }

    // Draw current tetromino
    for (int y = 0; y < currentPiece.shape.length; y++) {
      for (int x = 0; x < currentPiece.shape[0].length; x++) {
        if (currentPiece.shape[y][x] == 1) {
          drawBlock(currentPiece.x + x, currentPiece.y + y, Color.BLACK);
        }
      }
    }

    gc.setFill(Color.BLACK);
    gc.fillText("Score:" + score, (COLS + 1) * TILE_SIZE, 10);

    if (nextPiece != null) {
      int previewBoxX = (COLS + 2) * TILE_SIZE; // shifted more right
      int previewBoxY = 15;
      int boxSize = 3 * TILE_SIZE;

      // 🟦 Draw preview frame
      gc.setStroke(Color.BLACK);
      gc.setLineWidth(1);
      gc.strokeRect(previewBoxX - 2, previewBoxY - 2, boxSize + 4, boxSize + 4);

      // 🏷️ Label
      gc.setFill(Color.BLACK);

      // 🧱 Center nextPiece inside the box
      int shapeHeight = nextPiece.shape.length;
      int shapeWidth = nextPiece.shape[0].length;

      int pieceOffsetX = (boxSize - shapeWidth * TILE_SIZE) / 2;
      int pieceOffsetY = (boxSize - shapeHeight * TILE_SIZE) / 2;

      gc.setFill(Color.BLACK);
      for (int y = 0; y < shapeHeight; y++) {
        for (int x = 0; x < shapeWidth; x++) {
          if (nextPiece.shape[y][x] == 1) {
            gc.fillRect(
                    previewBoxX + pieceOffsetX + x * TILE_SIZE,
                    previewBoxY + pieceOffsetY + y * TILE_SIZE,
                    TILE_SIZE - 1,
                    TILE_SIZE - 1
            );
          }
        }
      }
    }

    if (gameOver) {
      gc.setFill(Color.BLACK);

      Font gameOverFont = Font.loadFont(
              getClass().getResource("/fonts/PressStart2P-Regular.ttf").toExternalForm(),
              15 // or try 14–16 if you want it even bigger
      );
      gc.setFont(gameOverFont);

      gc.fillText("GAME OVER", (COLS - 9) * TILE_SIZE, 55);

      // Reset font back to original
      Font smallFont = Font.loadFont(
              getClass().getResource("/fonts/PressStart2P-Regular.ttf").toExternalForm(),
              8
      );
      gc.setFont(smallFont);
      // 👇 Hide Tamagotchi
      tamagotchiView.setVisible(false);
    } else {
      // 👇 Show Tamagotchi during play
      tamagotchiView.setVisible(true);
    }
  }

  private void clearFullLines() {
    int linesCleared = 0;

    for (int y = ROWS - 1; y >= 0; y--) {
      boolean isFull = true;
      for (int x = 0; x < COLS; x++) {
        if (grid[y][x] == 0) {
          isFull = false;
          break;
        }
      }

      if (isFull) {
        linesCleared++;
        removeLine(y);
        y++; // Recheck same row after shifting down
      }
    }

    if (linesCleared > 0) {
      playOneShot("/music/LineClear.mp3");
    }

    score += linesCleared * 100;
  }

  private void removeLine(int lineY) {
    for (int y = lineY; y > 0; y--) {
      for (int x = 0; x < COLS; x++) {
        grid[y][x] = grid[y - 1][x];
      }
    }

    // Clear top row
    for (int x = 0; x < COLS; x++) {
      grid[0][x] = 0;
    }
  }

  private void drawBlock(int x, int y, Color color) {
    gc.setFill(color);
    gc.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE - 1, TILE_SIZE - 1);
  }

  public void moveLeft() {
    if (canMove(currentPiece, currentPiece.x - 1, currentPiece.y)) {
      currentPiece.x--;
    }
  }

  public void moveRight() {
    if (canMove(currentPiece, currentPiece.x + 1, currentPiece.y)) {
      currentPiece.x++;
    }
  }

  public void rotateCurrentPiece() {
    int[][] rotated = rotateMatrix(currentPiece.shape);

    if (canMove(new Tetromino(rotated), currentPiece.x, currentPiece.y)) {
      currentPiece.shape = rotated;
    }
  }

  private int[][] rotateMatrix(int[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    int[][] rotated = new int[cols][rows];

    for (int y = 0; y < rows; y++) {
      for (int x = 0; x < cols; x++) {
        rotated[x][rows - 1 - y] = matrix[y][x];
      }
    }
    return rotated;
  }

  public int getScore() {
    return score;
  }
}
