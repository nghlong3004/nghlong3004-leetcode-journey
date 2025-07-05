package org.nghlong3004.bwcontest160;

public class Q1 {
	class Solution {
		private String reverse(String s) {
			String result = "";
			for (int i = s.length() - 1; i >= 0; --i) {
				result += s.charAt(i);
			}
			return result;
		}

		private String convertBaseK(long n, int[] chars, long k) {
			String s = "";
			while (n > 0) {
				s += (char) (chars[(int) (n % k)]);
				n /= k;
			}
			return reverse(s);
		}

		public String concatHex36(int n) {
			int[] chars = new int[37];
			for (int i = 0; i < 10; ++i) {
				chars[i] = (i + '0');
			}
			for (int i = 10; i < 36; ++i) {
				chars[i] = ('A' + i - 10);
			}
			long power2 = n;
			power2 *= power2;
			long power3 = n;
			power3 = power3 * power3 * power3;
			return convertBaseK(power2, chars, 16L) + convertBaseK(power3, chars, 36L);
		}
	}
}
