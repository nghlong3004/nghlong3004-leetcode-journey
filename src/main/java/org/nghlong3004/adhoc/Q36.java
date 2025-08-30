package org.nghlong3004.adhoc;

public class Q36 {
  class Solution {
    public boolean isValidSudoku(char[][] board) {
      int n = board.length, m = board.length;
      for (int i = 0; i < n; ++i) {
        if (!checkRow(i, board)) {
          return false;
        }
      }
      for (int j = 0; j < m; ++j) {
        if (!checkColumn(j, board)) {
          return false;
        }
      }

      for (int i = 0; i < n; i += 3) {
        for (int j = 0; j < m; j += 3) {
          if (!checkMatrix(i, j, i + 2, j + 2, board)) {
            return false;
          }
        }
      }

      return true;
    }

    private boolean checkMatrix(int startRow, int startColumn, int endRow, int endColumn,
        char[][] board) {
      char[] index = new char[10];
      for (int id = 0; id < 10; ++id) {
        index[id] = 1;
      }
      for (int i = startRow; i <= endRow; ++i) {
        for (int j = startColumn; j <= endColumn; ++j) {
          if (board[i][j] >= '0' && board[i][j] <= '9') {
            int id = (int) (board[i][j] - '0');
            if (index[id]-- == 0) {
              return false;
            }
          }
        }
      }

      return true;
    }

    private boolean checkColumn(int j, char[][] board) {
      int n = board.length;
      char[] index = new char[10];
      for (int id = 0; id < 10; ++id) {
        index[id] = 1;
      }
      for (int i = 0; i < n; ++i) {
        if (board[i][j] >= '0' && board[i][j] <= '9') {
          int id = (int) (board[i][j] - '0');
          if (index[id]-- == 0) {
            return false;
          }
        }
      }
      return true;
    }

    private boolean checkRow(int i, char[][] board) {
      int m = board[i].length;
      char[] index = new char[10];
      for (int id = 0; id < 10; ++id) {
        index[id] = 1;
      }
      for (int j = 0; j < m; ++j) {
        if (board[i][j] >= '0' && board[i][j] <= '9') {
          int id = (int) (board[i][j] - '0');
          if (index[id]-- == 0) {
            return false;
          }
        }
      }
      return true;
    }
  }
}
