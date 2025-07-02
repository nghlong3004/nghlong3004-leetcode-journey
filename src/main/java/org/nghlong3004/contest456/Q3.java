package org.nghlong3004.contest456;

public class Q3 {
	public class Solution {
		private int[] nums;
		private int n;
		private int[] px;
		private int[][] memo;

		public int minXor(int[] nums, int k) {
			this.nums = nums;
			this.n = nums.length;
			px = new int[n + 1];

			for (int i = 0; i < n; ++i)
				px[i + 1] = px[i] ^ nums[i];
			memo = new int[n][k + 1];

			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < k + 1; ++j) {
					memo[i][j] = -1;
				}
			}

			return dp(0, k);
		}

		private int xor(int l, int r) {
			return px[r] ^ px[l];
		}

		private int maxTail(int i) {
			int mx = 0;
			for (int j = i; j < n; ++j)
				mx = Math.max(mx, nums[j]);
			return mx;
		}

		private int dp(int i, int c) {
			if (memo[i][c] != -1)
				return memo[i][c];

			if (c == 1)
				return memo[i][c] = xor(i, n);
			if (n - i == c)
				return memo[i][c] = maxTail(i);

			int best = Integer.MAX_VALUE;
			int currXor = 0;

			for (int j = i; j <= n - c; ++j) {
				currXor ^= nums[j];
				int worst = Math.max(currXor, dp(j + 1, c - 1));
				best = Math.min(best, worst);
				if (best == 0)
					break;
			}
			return memo[i][c] = best;
		}
	}
}
