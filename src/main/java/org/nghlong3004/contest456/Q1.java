package org.nghlong3004.contest456;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q1 {
	class Solution {
		public List<String> partitionString(String s) {
			Map<String, Boolean> map = new HashMap<String, Boolean>();
			List<String> result = new ArrayList<String>();
			int i = 0;
			int n = s.length();
			StringBuilder builder = new StringBuilder();
			while (i < n) {
				builder.append(s.charAt(i));
				if (!map.containsKey(new String(builder))) {
					map.put(new String(builder), true);
					result.add(new String(builder));
					builder = new StringBuilder();
				}
				++i;
			}
			return result;
		}
	}
}
