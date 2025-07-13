package org.nghlong3004.adhoc;

import java.util.Arrays;

public class Q2410 {
	class Solution {
		public int matchPlayersAndTrainers(int[] players, int[] trainers) {
			Arrays.sort(players);
			Arrays.sort(trainers);

			int i = 0, j = 0, matches = 0;
			while (i < players.length && j < trainers.length) {
				if (players[i] <= trainers[j]) {
					matches++;
					++i;
				}
				++j;
			}
			return matches;
		}
	}

}
