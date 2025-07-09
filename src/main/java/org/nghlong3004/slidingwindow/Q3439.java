package org.nghlong3004.slidingwindow;

public class Q3439 {
	class Solution {
		public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
			int n = startTime.length;
			int[] gaps = new int[n + 1];
			gaps[0] = startTime[0];
			for (int i = 1; i < n; ++i)
				gaps[i] = startTime[i] - endTime[i - 1];
			gaps[n] = eventTime - endTime[n - 1];
			int window = 0;
			for (int i = 0; i <= k; ++i)
				window += gaps[i];
			int ans = window;
			for (int r = k + 1; r < gaps.length; ++r) {
				window += gaps[r] - gaps[r - k - 1];
				ans = Math.max(ans, window);
			}
			return ans;
		}
	}
}
