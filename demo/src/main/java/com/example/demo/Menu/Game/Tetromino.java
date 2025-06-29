package com.example.demo.Menu.Game;

public class Tetromino {

  public static final int[][][] SHAPES = {
          // I
          {
                  {1, 1, 1, 1}
          },
          // O
          {
                  {1, 1},
                  {1, 1}
          },
          // T
          {
                  {0, 1, 0},
                  {1, 1, 1}
          },
          // S
          {
                  {0, 1, 1},
                  {1, 1, 0}
          },
          // Z
          {
                  {1, 1, 0},
                  {0, 1, 1}
          },
          // J
          {
                  {1, 0, 0},
                  {1, 1, 1}
          },
          // L
          {
                  {0, 0, 1},
                  {1, 1, 1}
          }
  };

  public int[][] shape;
  public int x = 3;
  public int y = 0;

  public Tetromino(int[][] shape) {
    this.shape = shape;
  }
}