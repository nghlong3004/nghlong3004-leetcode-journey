package org.nghlong3004.dp;

public class Q837 {
  class Solution {
    private final int N = 20000 + 10;
    private double[] p;

    public double new21Game(int n, int k, int maxPts) {
      if (n >= k + maxPts - 1 || k == 0) {
        return 1;
      }
      p = new double[N];
      double total = 0;
      for (int s = k; s <= n; ++s) {
        p[s] = 1;
      }
      for (int d = 1; d <= maxPts; ++d) {
        total += p[k + d - 1];
      }
      for (int i = k - 1; i >= 0; --i) {
        p[i] = total / maxPts;
        total = total + p[i] - p[i + maxPts];
      }
      return p[0];
    }
  }
}
