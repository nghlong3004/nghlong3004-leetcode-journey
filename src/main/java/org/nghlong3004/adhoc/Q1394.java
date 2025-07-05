package org.nghlong3004.adhoc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q1394 {
	class Solution {
		public int findLucky(int[] arr) {
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			Arrays.stream(arr).forEach(num -> map.put(num, map.getOrDefault(num, 0) + 1));
			int result = -1;
			for (Map.Entry<Integer, Integer> num : map.entrySet()) {
				if (num.getKey() == num.getValue()) {
					result = Math.max(result, num.getKey());
				}
			}
			return result;
		}
	}
}
