package org.nghlong3004.adhoc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Q1353 {
	class Solution {
		public int maxEvents(int[][] events) {
			Arrays.sort(events, Comparator.comparingInt(a -> a[0]));
			PriorityQueue<Integer> minHeap = new PriorityQueue<>();
			int day = 1;
			int total = 0;
			int i = 0;
			int n = events.length;
			while (i < n || !minHeap.isEmpty()) {
				while (i < n && events[i][0] == day) {
					minHeap.offer(events[i][1]);
					++i;
				}
				while (!minHeap.isEmpty() && minHeap.peek() < day) {
					minHeap.poll();
				}
				if (!minHeap.isEmpty()) {
					++total;
					minHeap.poll();
				}
				++day;
			}

			return total;
		}
	}
}
