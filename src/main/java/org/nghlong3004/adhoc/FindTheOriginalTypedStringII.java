package org.nghlong3004.adhoc;

import java.util.ArrayList;
import java.util.List;

public class FindTheOriginalTypedStringII {
	class Solution {

		private final int N = 2001;
		private final int MOD = 1000000007;

		private List<Integer> caculateWords(String word) {
			List<Integer> words = new ArrayList<Integer>();
			int count = 1;
			char currentValue = word.charAt(0);
			for (int i = 1; i < word.length(); ++i) {
				if (currentValue == word.charAt(i)) {
					++count;
				} else {
					words.add(count);
					count = 1;
					currentValue = word.charAt(i);
				}
			}
			words.add(count);
			return words;
		}

		private int getLoss(List<Integer> words, int k) {
			int[] dp = new int[N];
			dp[0] = 1;
			for (int word : words) {
				int[] next = new int[N];
				int totalForDp = 0;
				for (int i = 0; i < k; ++i) {
					next[i] = ((next[i]) % MOD + (totalForDp) % MOD) % MOD;
					totalForDp = ((dp[i]) % MOD + (totalForDp) % MOD) % MOD;
					if (i - word >= 0) {
						totalForDp = (totalForDp - dp[i - word] + MOD) % MOD;
					}
				}
				dp = next;
			}
			int loss = 0;
			for (int i = 0; i < dp.length; ++i) {
				loss = (loss + dp[i]) % MOD;
			}
			return loss;
		}

		private int getTotal(List<Integer> words) {
			long total = 1;
			for (int word : words) {
				total = ((total % MOD) * ((long) word % MOD)) % MOD;
			}
			return (int) total;
		}

		public int possibleStringCount(String word, int k) {
			List<Integer> words = caculateWords(word);
			if (k <= words.size()) {
				return getTotal(words);
			}
			int total = getTotal(words);
			int loss = getLoss(words, k);

			return (total - loss + MOD) % MOD;
		}
	}

	public static void main(String[] args) {
		FindTheOriginalTypedStringII obj = new FindTheOriginalTypedStringII();
		Solution solution = obj.new Solution();
		System.out.println(solution.possibleStringCount(
				"ggggggggaaaaallsssssaaaaaaaaaiiqqqqqqqqqqbbbbbbbvvfffffjjjjeeeeeefffmmiiiix", 34));
	}
}
