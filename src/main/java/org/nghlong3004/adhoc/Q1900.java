package org.nghlong3004.adhoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q1900 {
	class Solution {
		class Pair {
			int early, late;

			Pair(int e, int l) {
				early = e;
				late = l;
			}
		}

		private final Map<String, Pair> memo = new HashMap<>();

		public int[] earliestAndLatest(int n, int a, int b) {
			if (a > b) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			Pair ans = dfs(n, a, b);
			return new int[] { ans.early, ans.late };
		}

		private Pair dfs(int n, int a, int b) {
			String key = n + "," + a + "," + b;
			if (memo.containsKey(key)) {
				return memo.get(key);
			}

			if (a + b == n + 1) {
				return memo.computeIfAbsent(key, k -> new Pair(1, 1));
			}

			int m = n >> 1;
			int earliest = Integer.MAX_VALUE;
			int latest = Integer.MIN_VALUE;

			int bit = 0;
			int[] pairBit = new int[m + 1];
			for (int i = 1; i <= m; ++i) {
				int p = i;
				int q = n - i + 1;
				if (p == a || q == a || p == b || q == b) {
					pairBit[i] = -1;
				} else {
					pairBit[i] = bit++;
				}
			}

			int totalFree = 1 << bit;

			for (int mask = 0; mask < totalFree; ++mask) {
				List<Integer> nxt = new ArrayList<>();
				int na = 0, nb = 0;

				for (int i = 1; i <= m; ++i) {
					int p = i;
					int q = n - i + 1;
					int winner;
					if (p == a || q == a) {
						winner = a;
					} else if (p == b || q == b) {
						winner = b;
					} else {
						boolean chooseLeft = ((mask >> pairBit[i]) & 1) == 0;
						winner = chooseLeft ? p : q;
					}
					nxt.add(winner);
				}
				if (n % 2 == 1) {
					nxt.add((n + 1) >> 1);
				}
				nxt.sort(Integer::compare);
				for (int i = 0; i < nxt.size(); ++i) {
					if (nxt.get(i) == a) {
						na = i + 1;
					}
					if (nxt.get(i) == b) {
						nb = i + 1;
					}
				}

				Pair child = dfs(nxt.size(), Math.min(na, nb), Math.max(na, nb));
				earliest = Math.min(earliest, child.early + 1);
				latest = Math.max(latest, child.late + 1);
			}

			Pair res = new Pair(earliest, latest);
			memo.put(key, res);
			return res;
		}
	}

}
