package org.nghlong3004.adhoc;

public class LongestStrictlyIncreasingOrStrictlyDecreasingSubarray {
	class Solution {
		private int sizeMax(int[] nums) {
			int countIncreasing = 1;
			int countDecreasing = 1;
			int result = 1;
			for (int i = 1; i < nums.length; ++i) {
				if (nums[i] > nums[i - 1]) {
					++countIncreasing;
				} else {
					result = Math.max(result, countIncreasing);
					countIncreasing = 1;
				}
				if (nums[i] < nums[i - 1]) {
					++countDecreasing;
				} else {
					result = Math.max(result, countDecreasing);
					countDecreasing = 1;
				}
			}
			return Math.max(Math.max(countDecreasing, countIncreasing), result);
		}

		public int longestMonotonicSubarray(int[] nums) {
			return sizeMax(nums);
		}
	}

	public static void main(String[] args) {

	}
}
