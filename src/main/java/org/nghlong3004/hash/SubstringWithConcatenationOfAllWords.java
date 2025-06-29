package org.nghlong3004.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenationOfAllWords {

	class Hash {

		private final long BASE;
		private final long MOD;
		private final int N;

		private long[] pow;
		private long[] hashTable;

		public Hash(String s, int n) {
			BASE = (long) 31;
			MOD = (long) 1000000007;
			N = 10009;

			pow = new long[N];
			hashTable = new long[N];
			precalculateBasePower(s, n);
		}

		protected Long getHashValue(int l, int r) {
			return (hashTable[r] - hashTable[l - 1] * pow[r - l + 1] + MOD * MOD) % MOD;
		}

		private void precalculateBasePower(String s, int n) {
			pow[0] = 1;
			for (int i = 1; i <= n; ++i) {
				pow[i] = (pow[i - 1] * BASE) % MOD;
			}
			hashTable[0] = 0;
			for (int i = 1; i <= n; ++i) {
				hashTable[i] = (hashTable[i - 1] * BASE + s.charAt(i) - 'a' + 1) % MOD;
			}
		}

		protected long calculateHashValue(String s, int n) {
			long hashValue = 0;
			for (int i = 1; i <= n; ++i) {
				hashValue = (hashValue * BASE + s.charAt(i) - 'a' + 1) % MOD;
			}

			return hashValue;
		}

	}

	class Solution {
		private Map<Long, Integer> map;

		public Solution() {
			map = new HashMap<Long, Integer>();
		}

		private void initMap(String[] words, Hash hash) {
			for (int i = 0; i < words.length; ++i) {
				words[i] = '$' + words[i];
			}
			for (String word : words) {
				long hashValue = hash.calculateHashValue(word, word.length() - 1);
				map.merge(hashValue, 1, Integer::sum);
			}
		}

		public List<Integer> findSubstring(String s, String[] words) {
			List<Integer> result = new ArrayList<Integer>();
			int n = s.length();
			s = " " + s;
			int size = words[0].length();
			Hash hash = new Hash(s, n);
			initMap(words, hash);
			for (int i = 1; i <= n + 1; ++i) {
				if (i + words.length * size > n + 1) {
					break;
				}
				Map<Long, Integer> newMap = new HashMap<Long, Integer>(map);
				boolean flag = true;
				for (int j = 1; j <= words.length; ++j) {
					Long hashValue = hash.getHashValue(i + (j - 1) * size, i + j * size - 1);
					int quantity = newMap.getOrDefault(hashValue, -1);
					if (quantity <= 0) {
						flag = false;
						break;
					}
					newMap.put(hashValue, quantity - 1);
				}
				if (flag) {
					result.add(i - 1);
				}
			}
			return result;
		}
	}

	public static void main(String[] args) {

	}

}
