package org.nghlong3004.contest456;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q4 {
	class Solution {
		class DSU {
			int[] p, sz;

			DSU(int n) {
				p = new int[n];
				sz = new int[n];
				Arrays.setAll(p, i -> i);
				Arrays.fill(sz, 1);
			}

			int find(int x) {
				return p[x] == x ? x : (p[x] = find(p[x]));
			}

			boolean union(int a, int b) {
				int ra = find(a), rb = find(b);
				if (ra == rb)
					return false;
				if (sz[ra] < sz[rb]) {
					int t = ra;
					ra = rb;
					rb = t;
				}
				p[rb] = ra;
				sz[ra] += sz[rb];
				return true;
			}
		}

		class Edge {
			int u, v;
			int s;
			boolean must;

			Edge(int u, int v, int s, int must) {
				this.u = u;
				this.v = v;
				this.s = s;
				this.must = (must == 1);
			}
		}

		private int n, k;
		private List<Edge> edges;
		private long minMust = Long.MAX_VALUE;
		private long maxS = 0;

		public int maxStability(int n, int[][] raw, int k) {
			this.n = n;
			this.k = k;
			edges = new ArrayList<>(raw.length);
			for (int[] e : raw) {
				Edge ed = new Edge(e[0], e[1], e[2], e[3]);
				edges.add(ed);
				maxS = Math.max(maxS, ed.s);
				if (ed.must)
					minMust = Math.min(minMust, ed.s);
			}

			if (hasMustCycle())
				return -1;

			long hi = edges.stream().anyMatch(e -> e.must) ? minMust : (maxS << 1);
			long lo = 0;

			while (lo < hi) {
				long mid = (lo + hi + 1) >>> 1;
				if (feasible(mid))
					lo = mid;
				else
					hi = mid - 1;
			}
			return (int) lo;
		}

		private boolean hasMustCycle() {
			DSU dsu = new DSU(n);
			int cnt = 0;
			for (Edge e : edges)
				if (e.must) {
					if (!dsu.union(e.u, e.v))
						return true;
					cnt++;
				}
			return cnt > n - 1;
		}

		private boolean feasible(long T) {
			for (Edge e : edges)
				if (e.must && e.s < T)
					return false;

			DSU dsu = new DSU(n);
			int comps = n;

			for (Edge e : edges)
				if (e.must && dsu.union(e.u, e.v))
					comps--;

			for (Edge e : edges)
				if (!e.must && e.s >= T && dsu.union(e.u, e.v))
					comps--;

			if (comps == 1)
				return true;

			int usedUp = 0;
			for (Edge e : edges) {
				if (e.must)
					continue;
				if (e.s < T && ((long) e.s << 1) >= T) {
					if (dsu.union(e.u, e.v)) {
						usedUp++;
						comps--;
						if (usedUp > k)
							return false;
						if (comps == 1)
							return true;
					}
				}
			}
			return false;
		}
	}
}
