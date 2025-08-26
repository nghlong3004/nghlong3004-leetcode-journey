package org.nghlong3004.prefixsum2D;

public class Q1277 {

  class Solution {

    private int[][] prefixSum2D;

    public int countSquares(int[][] matrix) {
      int n = matrix.length;
      int m = matrix[0].length;

      calcPrefixSum2D(n, m, matrix);

      return calcTotal(n, m, matrix);
    }

    private int calcTotal(int n, int m, int[][] matrix) {
      int max = m * n;
      int count = 0;
      for (int length = 1; length <= max; ++length) {
        boolean flag = true;
        for (int i = 0; i < n; ++i) {
          for (int j = 0; j < m; ++j) {
            int l = i + length - 1;
            int r = j + length - 1;

            int total = prefixSum2D[l][r];
            int top = (i - 1 < 0) ? 0 : prefixSum2D[i - 1][r];
            int left = (j - 1 < 0) ? 0 : prefixSum2D[l][j - 1];
            int overlap = ((i - 1 >= 0) && (j - 1 >= 0)) ? prefixSum2D[i - 1][j - 1] : 0;

            int area = total - top - left + overlap;
            if (area == length * length) {
              flag = false;
              ++count;
            }

          }
        }
        if (flag) {
          return count;
        }
      }

      return count;
    }

    private void calcPrefixSum2D(int n, int m, int[][] matrix) {
      prefixSum2D = new int[n + 1][m + 1];
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
          prefixSum2D[i][j] = matrix[i][j];
          if (i > 0) {
            prefixSum2D[i][j] += prefixSum2D[i - 1][j];
          }
          if (j > 0) {
            prefixSum2D[i][j] += prefixSum2D[i][j - 1];
          }
          if (i > 0 && j > 0) {
            prefixSum2D[i][j] -= prefixSum2D[i - 1][j - 1];
          }
        }
      }

    }
  }

}
