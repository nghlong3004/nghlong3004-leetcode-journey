package org.nghlong3004.adhoc;

import java.util.ArrayList;
import java.util.List;

public class Q3446 {
  class Solution {
    public int[][] sortMatrix(int[][] grid) {

      bottomLeftTriangle(grid);
      topRightTriangle(grid);

      return grid;
    }

    private void bottomLeftTriangle(int[][] grid) {
      int n = grid.length;
      for (int i = 1; i < n; ++i) {
        List<Integer> nums = new ArrayList<Integer>();
        for (int j = i; j < n; ++j) {
          nums.add(grid[j - i][j]);
        }
        sort(nums, true);
        for (int j = i, k = 0; j < n; ++j, ++k) {
          grid[j - i][j] = nums.get(k);
        }
      }
    }

    private void topRightTriangle(int[][] grid) {
      int n = grid.length;
      for (int i = 0; i < n; ++i) {
        List<Integer> nums = new ArrayList<Integer>();
        for (int j = i; j < n; ++j) {
          nums.add(grid[j][j - i]);
        }
        sort(nums, false);
        for (int j = i, k = 0; j < n; ++j, ++k) {
          grid[j][j - i] = nums.get(k);
        }
      }
    }

    private void sort(List<Integer> nums, boolean flag) {
      int n = nums.size();
      for (int i = 0; i < n; ++i) {
        for (int j = i + 1; j < n; ++j) {
          int a = nums.get(i), b = nums.get(j);
          if ((a > b) == flag) {
            swap(a, b, i, j, nums);
          }
        }
      }
    }

    private void swap(int a, int b, int i, int j, List<Integer> nums) {
      Integer temp = a;
      a = b;
      b = temp;
      nums.set(i, a);
      nums.set(j, b);
    }

  }
}
