package org.nghlong3004.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenationOfAllWords {

	static class RollingHash {
		private final long BASE = 31;
		private final long MOD = 1_000_000_007;
		private long[] pow;
		private long[] hash;

		public RollingHash(String s) {
			int n = s.length();
			pow = new long[n + 1];
			hash = new long[n + 1];
			pow[0] = 1;
			for (int i = 1; i <= n; ++i) {
				pow[i] = (pow[i - 1] * BASE) % MOD;
				hash[i] = (hash[i - 1] * BASE + s.charAt(i - 1) - 'a' + 1) % MOD;
			}
		}

		public long getHash(int l, int r) {
			return (hash[r] - hash[l] * pow[r - l] % MOD + MOD) % MOD;
		}

		public long computeHash(String s) {
			long h = 0;
			for (int i = 0; i < s.length(); ++i) {
				h = (h * BASE + s.charAt(i) - 'a' + 1) % MOD;
			}
			return h;
		}
	}

	static class WordFrequency {
		private final Map<Long, Integer> freqMap = new HashMap<>();

		public void addWord(long hash) {
			freqMap.merge(hash, 1, Integer::sum);
		}

		public boolean consumeWord(long hash) {
			Integer count = freqMap.get(hash);
			if (count == null || count == 0)
				return false;
			freqMap.put(hash, count - 1);
			return true;
		}

		public WordFrequency copy() {
			WordFrequency copy = new WordFrequency();
			copy.freqMap.putAll(this.freqMap);
			return copy;
		}
	}

	static class SubstringFinder {
		private final RollingHash rollingHash;
		private final int wordSize;
		private final int numWords;
		private final List<Long> wordHashes;

		public SubstringFinder(String s, String[] words) {
			this.rollingHash = new RollingHash(s);
			this.wordSize = words[0].length();
			this.numWords = words.length;
			this.wordHashes = new ArrayList<>();
			RollingHash hashUtil = new RollingHash("");
			for (String word : words) {
				wordHashes.add(hashUtil.computeHash(word));
			}
		}

		public List<Integer> findSubString(String s, String[] words) {
			List<Integer> result = new ArrayList<>();
			WordFrequency freq = new WordFrequency();
			for (Long h : wordHashes)
				freq.addWord(h);

			int totalLen = wordSize * numWords;

			for (int i = 0; i + totalLen <= s.length(); ++i) {
				WordFrequency windowFreq = freq.copy();
				boolean match = true;
				for (int j = 0; j < numWords; ++j) {
					int start = i + j * wordSize;
					int end = start + wordSize;
					long h = rollingHash.getHash(start, end);
					if (!windowFreq.consumeWord(h)) {
						match = false;
						break;
					}
				}
				if (match)
					result.add(i);
			}
			return result;
		}
	}

	public static void main(String[] args) {
		String s = "barfoothefoobarman";
		String[] words = { "foo", "bar" };
		SubstringFinder finder = new SubstringFinder(s, words);
		List<Integer> indices = finder.findSubString(s, words);
		System.out.println(indices);
	}
}
