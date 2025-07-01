package org.nghlong3004.adhoc;

public class FindTheOriginalTypedStringI {

	class Solution {
		public int possibleStringCount(String word) {
			int total = 1;
			int count = 0;
			char currentKey = word.charAt(0);
			for (int i = 1; i < word.length(); ++i) {
				if (currentKey == word.charAt(i)) {
					++count;
				} else {
					currentKey = word.charAt(i);
					total += count;
					count = 0;
				}
			}
			return total + count;
		}
	}

	public static void main(String[] args) {
		FindTheOriginalTypedStringI obj = new FindTheOriginalTypedStringI();
		Solution solution = obj.new Solution();
		System.out.println(solution.possibleStringCount("aaaa"));
	}

}
