package org.nghlong3004.adhoc;

public class Q3021 {
  class Solution {
    public long flowerGame(int n, int y) {
      // end turn if turn for Alice -> Alice win
      // -> (x + y) % 2 == 1
      // if x % 2 == 1 -> y % 2 == 0
      // if x % 2 == 0 -> y % 2 == 1
      long total = 0;
      for (int x = 1; x <= n; ++x) {
        long k = 0;
        if (x % 2 == 0) {
          if (y % 2 == 0) {
            k = y >> 1;
          } else {
            k = y + 1 >> 1;
          }
        } else {
          if (y % 2 == 0) {
            k = y >> 1;
          } else {
            k = y - 1 >> 1;
          }
        }
        total += k;
      }
      return total;
    }
  }

}
