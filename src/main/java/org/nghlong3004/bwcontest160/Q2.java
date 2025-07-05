package org.nghlong3004.bwcontest160;

public class Q2 {
	class Solution {
		private long INF = 1000000000000L;

		private long entry(int i, int j) {
			long a = i + 1;
			long b = j + 1;
			return a * b;
		}

		public long minCost(int m, int n, int[][] waitCost) {
			long[][] dp = new long[m + 1][n + 1];
			dp[0][0] = 0;
			for (int i = 0; i < m; ++i) {
				for (int j = 0; j < n; ++j) {
					if (i == 0 && j == 0) {
						continue;
					}
					long top = i > 0 ? dp[i - 1][j] : INF;
					long left = j > 0 ? dp[i][j - 1] : INF;
					long wCost = (i == m - 1 && j == n - 1 ? 0 : waitCost[i][j]);
					dp[i][j] = Math.min(top, left) + entry(i, j) + wCost;
				}
			}
			return dp[m - 1][n - 1] + 1;
		}
	}

}
