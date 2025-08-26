package org.nghlong3004.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q679 {
  class Solution {
    private final double esp = 1e-6;
    private final int POINT = 24;

    public boolean judgePoint24(int[] cards) {
      List<Double> nums = new ArrayList<Double>();
      for (int card : cards) {
        nums.add((double) card);
      }

      return backtracking(nums);
    }

    private boolean backtracking(List<Double> nums) {
      int n = nums.size();
      if (n == 1) {
        return Math.abs(nums.get(0) - POINT) < esp;
      }
      for (int i = 0; i < n; ++i) {
        for (int j = i + 1; j < n; ++j) {
          double a = nums.get(i);
          double b = nums.get(j);

          List<Double> arr = new ArrayList<Double>();
          for (int k = 0; k < n; ++k) {
            if (k != i && k != j) {
              arr.add(nums.get(k));
            }
          }

          List<Double> calc = new ArrayList<Double>();
          calc.add(a - b);
          calc.add(b - a);
          calc.add(a + b);
          calc.add(a * b);
          if (Math.abs(a) > esp) {
            calc.add(b / a);
          }
          if (Math.abs(b) > esp) {
            calc.add(a / b);
          }

          for (Double number : calc) {
            arr.add(number);
            if (backtracking(arr)) {
              return true;
            }
            arr.remove(arr.size() - 1);
          }

        }
      }

      return false;
    }
  }
}
