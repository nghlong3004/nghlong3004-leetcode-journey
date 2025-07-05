package org.nghlong3004.bwcontest160;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Q3 {
	class Solution {

		class Edge {
			int to, start, end;

			public Edge(int to, int start, int end) {
				this.to = to;
				this.start = start;
				this.end = end;
			}
		}

		private ArrayList<ArrayList<Edge>> initGraphics(int n, int[][] edges) {
			ArrayList<ArrayList<Edge>> g = new ArrayList<ArrayList<Edge>>(n);
			for (int i = 0; i < n; ++i) {
				g.add(new ArrayList<Edge>());
			}
			for (int[] e : edges) {
				g.get(e[0]).add(new Edge(e[1], e[2], e[3]));
			}
			return g;
		}

		private int calc(int n, int[][] edges, ArrayList<ArrayList<Edge>> g) {
			long INF = Long.MAX_VALUE / 4;
			long[] dist = new long[n];
			Arrays.fill(dist, INF);
			dist[0] = 0;
			PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
			pq.add(new long[] { 0, 0 });
			while (!pq.isEmpty()) {
				long[] cur = pq.poll();
				long t = cur[0];
				int u = (int) cur[1];
				if (t > dist[u])
					continue;
				if (u == n - 1)
					return (int) t;
				for (Edge ed : g.get(u)) {
					if (t > ed.end)
						continue;
					long depart = Math.max(t, ed.start);
					long arrive = depart + 1;
					if (arrive < dist[ed.to]) {
						dist[ed.to] = arrive;
						pq.add(new long[] { arrive, ed.to });
					}
				}
			}
			return -1;
		}

		public int minTime(int n, int[][] edges) {
			ArrayList<ArrayList<Edge>> g = initGraphics(n, edges);
			return calc(n, edges, g);

		}
	}
}
