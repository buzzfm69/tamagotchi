package com.example.demo.Menu.Game;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.Random;

public class TetrisPane extends Pane {

  private static final int TILE_SIZE = 7;
  private static final int ROWS = 11;
  private static final int COLS = 10;

  private final Canvas canvas;
  private final GraphicsContext gc;
  private final int[][] grid = new int[ROWS][COLS];

  private Tetromino currentPiece;
  private final Random random = new Random();
  private int tick = 0;
  private int score = 0;

  public TetrisPane() {
    canvas = new Canvas((COLS + 2) * TILE_SIZE, ROWS * TILE_SIZE);
    gc = canvas.getGraphicsContext2D();
    this.getChildren().add(canvas);

    spawnNewPiece();
    startGameLoop();
  }

  private void startGameLoop() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        tick++;
        if (tick % 30 == 0) { // Adjust speed here
          moveDown();
        }
        render();
      }
    };
    timer.start();
  }

  private void moveDown() {
    if (canMove(currentPiece, currentPiece.x, currentPiece.y + 1)) {
      currentPiece.y++;
    } else {
      placePiece(currentPiece);
      spawnNewPiece();
    }
  }

  private void spawnNewPiece() {
    currentPiece = new Tetromino(Tetromino.SHAPES[random.nextInt(Tetromino.SHAPES.length)]);
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

    gc.setFill(Color.RED);
    gc.fillText("Score: " + score, 2, 10);
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
