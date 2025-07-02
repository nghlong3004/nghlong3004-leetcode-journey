package org.nghlong3004.contest456;

public class Q2 {
	class Solution {
		public int[] longestCommonPrefix(String[] words) {
			final int n = words.length;
			int[] ans = new int[n];

			if (n <= 1)
				return ans;

			int[] lcpAdj = new int[n - 1];
			for (int j = 0; j < n - 1; ++j) {
				lcpAdj[j] = lcp(words[j], words[j + 1]);
			}

			int[] pre = new int[n - 1];
			int[] suf = new int[n - 1];
			pre[0] = lcpAdj[0];
			for (int j = 1; j < n - 1; ++j)
				pre[j] = Math.max(pre[j - 1], lcpAdj[j]);

			suf[n - 2] = lcpAdj[n - 2];
			for (int j = n - 3; j >= 0; --j)
				suf[j] = Math.max(suf[j + 1], lcpAdj[j]);

			for (int i = 0; i < n; ++i) {
				int maxLeft = (i >= 2) ? pre[i - 2] : 0;
				int maxRight = (i + 1 <= n - 2) ? suf[i + 1] : 0;
				int newL = (i > 0 && i < n - 1) ? lcp(words[i - 1], words[i + 1]) : 0;
				ans[i] = Math.max(Math.max(maxLeft, maxRight), newL);
			}
			return ans;
		}

		private int lcp(String a, String b) {
			int len = Math.min(a.length(), b.length());
			int k = 0;
			while (k < len && a.charAt(k) == b.charAt(k))
				++k;
			return k;
		}
	}

}
