package org.nghlong3004.dp;

public class Q3459 {
  class Solution {
    public int lenOfVDiagonal(int[][] grid) {
      int m = grid.length, n = grid[0].length;
      Integer[][][][][] mem = new Integer[m][n][2][2][4];
      int ans = 0;

      final int[][] DIRS = {{-1, 1}, {1, 1}, {1, -1}, {-1, -1}};

      for (int i = 0; i < m; ++i)
        for (int j = 0; j < n; ++j)
          if (grid[i][j] == 1) {
            for (int d = 0; d < 4; ++d) {
              int ni = i + DIRS[d][0], nj = j + DIRS[d][1];
              ans = Math.max(ans, 1 + dfs(grid, ni, nj, 0, 2, d, mem, DIRS));
            }
          }

      return ans;
    }

    private int dfs(int[][] g, int i, int j, int turned, int need, int dir,
        Integer[][][][][] mem, int[][] DIRS) {
      if (i < 0 || i >= g.length || j < 0 || j >= g[0].length)
        return 0;
      if (g[i][j] != need)
        return 0;

      int needHash = (need == 2 ? 1 : 0);
      if (mem[i][j][turned][needHash][dir] != null)
        return mem[i][j][turned][needHash][dir];

      int nextNeed = (need == 2 ? 0 : 2);

      int ni = i + DIRS[dir][0], nj = j + DIRS[dir][1];
      int res = 1 + dfs(g, ni, nj, turned, nextNeed, dir, mem, DIRS);

      if (turned == 0) {
        int ndir = (dir + 1) % 4;
        int qi = i + DIRS[ndir][0], qj = j + DIRS[ndir][1];
        res = Math.max(res, 1 + dfs(g, qi, qj, 1, nextNeed, ndir, mem, DIRS));
      }

      return mem[i][j][turned][needHash][dir] = res;
    }
  }

}
