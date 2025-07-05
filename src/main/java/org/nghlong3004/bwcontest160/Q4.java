package org.nghlong3004.bwcontest160;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q4 {
	class Solution {

		private class GcdQueue {
			private final Deque<int[]> in = new ArrayDeque<>();
			private final Deque<int[]> out = new ArrayDeque<>();
			private int size = 0;

			public void push(int x) {
				int g = in.isEmpty() ? x : gcd(x, in.peek()[1]);
				in.push(new int[] { x, g });
				++size;
			}

			public void pop() {
				if (size == 0)
					return;
				if (out.isEmpty()) {
					while (!in.isEmpty()) {
						int[] cur = in.pop();
						int g = out.isEmpty() ? cur[0] : gcd(cur[0], out.peek()[1]);
						out.push(new int[] { cur[0], g });
					}
				}
				out.pop();
				--size;
			}

			public int gcd() {
				if (size == 0)
					return 0;
				int g1 = in.isEmpty() ? 0 : in.peek()[1];
				int g2 = out.isEmpty() ? 0 : out.peek()[1];
				return g1 == 0 ? g2 : g2 == 0 ? g1 : gcd(Math.max(g1, g2), Math.min(g1, g2));
			}

			public int size() {
				return size;
			}

			public void clear() {
				in.clear();
				out.clear();
				size = 0;
			}

			private int gcd(int a, int b) {
				if (b == 0) {
					return a;
				}
				return gcd(b, a % b);
			}
		}

		private boolean ok(int[] a, int maxC, int L) {
			if (L < 0)
				return false;
			if (L >= a.length)
				return true;
			int cuts = 0;
			GcdQueue q = new GcdQueue();
			for (int x : a) {
				q.push(x);
				if (q.size() > L + 1)
					q.pop();
				if (q.size() == L + 1 && q.gcd() > 1) {
					++cuts;
					if (cuts > maxC)
						return false;
					q.clear();
				}
			}
			return true;
		}

		public int minStable(int[] nums, int maxC) {
			int n = nums.length;
			int l = 0, r = n;
			while (l < r) {
				int m = (l + r) >>> 1;
				if (ok(nums, maxC, m))
					r = m;
				else
					l = m + 1;
			}
			return l;
		}
	}
}
