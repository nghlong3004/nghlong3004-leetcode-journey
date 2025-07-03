package org.nghlong3004.adhoc;

public class Q3304 {
	class Solution {

		private StringBuilder extra(String word) {
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < word.length(); ++i) {
				char c = word.charAt(i);
				int number = c - 'a';
				number = (number + 1) % 26;
				c = (char) (number + 'a');
				result.append(c);
			}
			return result;
		}

		public char kthCharacter(int k) {
			StringBuilder word = new StringBuilder("a");
			while (word.length() <= k) {
				word.append(extra(new String(word)));
			}
			return word.charAt(k - 1);
		}
	}
}
