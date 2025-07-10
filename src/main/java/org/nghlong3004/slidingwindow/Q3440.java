package org.nghlong3004.slidingwindow;

public class Q3440 {
	class Solution {
		public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
			int n = startTime.length;
			int[] gap = new int[n + 1];
			gap[0] = startTime[0];
			for (int i = 1; i < n; ++i) {
				gap[i] = startTime[i] - endTime[i - 1];
			}
			gap[n] = eventTime - endTime[n - 1];
			int[][] rangeMax = new int[n + 1][2];
			int ans = 0;
			rangeMax[0][0] = gap[0];
			for (int i = 1; i <= n; ++i) {
				rangeMax[i][0] = Math.max(rangeMax[i - 1][0], gap[i]);
			}
			rangeMax[n][1] = gap[n];
			for (int i = n - 1; i >= 0; --i) {
				rangeMax[i][1] = Math.max(rangeMax[i + 1][1], gap[i]);
			}
			for (int i = 0; i < n; ++i) {
				int merged = gap[i] + gap[i + 1];
				int leng = endTime[i] - startTime[i];
				int maxLeft = (i > 0) ? rangeMax[i - 1][0] : 0;
				int maxRight = (i + 2 <= n) ? rangeMax[i + 2][1] : 0;
				boolean canMove = (leng <= Math.max(maxLeft, maxRight));
				int extra = canMove ? leng : 0;
				ans = Math.max(ans, merged + extra);
			}
			return ans;
		}
	}

}
