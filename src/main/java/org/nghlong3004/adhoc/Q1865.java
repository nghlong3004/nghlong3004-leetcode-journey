package org.nghlong3004.adhoc;

import java.util.HashMap;
import java.util.Map;

public class Q1865 {
	class FindSumPairs {
		private int[] nums1;
		private int[] nums2;

		private Map<Integer, Integer> map;

		public FindSumPairs(int[] nums1, int[] nums2) {
			this.nums1 = nums1;
			this.nums2 = nums2;

			map = new HashMap<Integer, Integer>();
			initalizeMap();
		}

		public void add(int index, int val) {
			if (isValidIndex(index, nums2.length)) {
				return;
			}
			map.merge(nums2[index], 1, (a, b) -> (a - b));
			nums2[index] += val;
			map.merge(nums2[index], 1, (a, b) -> (a + b));
		}

		public int count(int tot) {
			int total = 0;
			for (int i = 0; i < nums1.length; ++i) {
				Integer count = map.get(tot - nums1[i]);
				if (count == null) {
					continue;
				}
				total += count;
			}
			return total;
		}

		private boolean isValidIndex(int index, int n) {
			return (index < 0 || index >= n);
		}

		private void initalizeMap() {
			for (int num : nums2) {
				map.merge(num, 1, (a, b) -> (a + b));
			}
		}
	}
}
