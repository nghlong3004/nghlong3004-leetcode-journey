package org.nghlong3004.adhoc;

public class Q3307 {
	class Solution {
		private int log2(long k) {
			int n = 64 - Long.numberOfLeadingZeros(k - 1);
			return n;
		}

		private int calc(int n, long k, int[] operations) {
			int extra = 0;
			for (int i = n - 1; i >= 0; --i) {
				long power = 1L << i;
				if (k > power) {
					k -= power;
					if (i < operations.length) {
						extra = (extra + operations[i]) % 26;
					}
				}
			}
			return extra;
		}

		public char kthCharacter(long k, int[] operations) {
			int n = log2(k);
			return (char) ('a' + calc(n, k, operations));
		}
	}

	public static void main(String[] args) {
		Solution solution = (new Q3307()).new Solution();
		solution.kthCharacter(3, null);
	}
}
