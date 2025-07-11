package org.nghlong3004.adhoc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Q2402 {
	class Solution {
		public int mostBooked(int n, int[][] meetings) {
			Arrays.sort(meetings, Comparator.comparingInt(arr -> arr[0]));
			PriorityQueue<Integer> available = new PriorityQueue<>();
			for (int i = 0; i < n; ++i) {
				available.offer(i);
			}
			PriorityQueue<long[]> busy = new PriorityQueue<>(
					(a, b) -> a[0] == b[0] ? Long.compare(a[1], b[1]) : Long.compare(a[0], b[0]));
			long[] used = new long[n];
			for (int[] m : meetings) {
				long start = m[0], end = m[1], dur = end - start;
				while (!busy.isEmpty() && busy.peek()[0] <= start) {
					available.offer((int) busy.poll()[1]);
				}
				if (!available.isEmpty()) {
					int room = available.poll();
					busy.offer(new long[] { end, room });
					used[room]++;
				} else {
					long[] earliest = busy.poll();
					long newEnd = earliest[0] + dur;
					busy.offer(new long[] { newEnd, earliest[1] });
					used[(int) earliest[1]]++;
				}
			}

			int ans = 0;
			for (int i = 1; i < n; ++i) {
				if (used[i] > used[ans])
					ans = i;
			}
			return ans;
		}
	}

}
