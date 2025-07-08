package org.nghlong3004.dp;

import java.util.Arrays;

public class Q1751 {
	public class Solution {
		public int maxValue(int[][] events, int k) {
			Arrays.sort(events, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
			int[] starts = initStarts(events);
			Integer[][] memory = new Integer[events.length][k + 1];
			return dfs(0, k, events, starts, memory);
		}

		private int[] initStarts(int[][] events) {
			int n = events.length;
			int[] starts = new int[n];
			for (int i = 0; i < n; ++i)
				starts[i] = events[i][0];
			return starts;
		}

		private int dfs(int idx, int remain, int[][] events, int[] starts, Integer[][] memory) {
			if (remain == 0 || idx == events.length)
				return 0;
			if (memory[idx][remain] != null)
				return memory[idx][remain];

			int best = dfs(idx + 1, remain, events, starts, memory);

			int next = upperBound(starts, events[idx][1]);
			best = Math.max(best, events[idx][2] + dfs(next, remain - 1, events, starts, memory));

			return memory[idx][remain] = best;
		}

		private int upperBound(int[] arr, int target) {
			int l = 0, r = arr.length;
			while (l < r) {
				int m = (l + r) >>> 1;
				if (arr[m] <= target)
					l = m + 1;
				else
					r = m;
			}
			return l;
		}
	}
}
