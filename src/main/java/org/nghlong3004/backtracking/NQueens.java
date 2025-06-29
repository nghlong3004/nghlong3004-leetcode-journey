package org.nghlong3004.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Solution {

	private boolean[][] matrix = new boolean[16][16];

	private boolean isDiagonal(int i, int j, int n) {
		for (int a = i + 1, b = j + 1; a < n && b < n; ++a, ++b) {
			if (matrix[a][b]) {
				return true;
			}
		}
		for (int a = i - 1, b = j - 1; a >= 0 && b >= 0; --a, --b) {
			if (matrix[a][b]) {
				return true;
			}
		}
		for (int a = i + 1, b = j - 1; a < n && b >= 0; ++a, --b) {
			if (matrix[a][b]) {
				return true;
			}
		}
		for (int a = i - 1, b = j + 1; a >= 0 && b < n; --a, ++b) {
			if (matrix[a][b]) {
				return true;
			}
		}
		return false;
	}

	private boolean isColumn(int j, int n) {
		for (int i = 0; i < n; ++i) {
			if (matrix[i][j]) {
				return true;
			}
		}
		return false;
	}

	private boolean isChoose(int i, int j, int n) {
		if (isColumn(j, n) || isDiagonal(i, j, n)) {
			return false;
		}
		return true;
	}

	private List<Character> initQueen(int n) {
		List<Character> queen = new ArrayList<Character>(Collections.nCopies(n, '.'));
		return queen;
	}

	private List<List<String>> backtracking(int i, int n, List<Character> queen, List<String> queens,
			List<List<String>> nQueens) {
		if (i == n) {
			nQueens.add(new ArrayList<String>(queens));
			return nQueens;
		}
		for (int j = 0; j < n; ++j) {
			if (isChoose(i, j, n)) {
				queen.set(j, 'Q');
				queens.add(queen.stream().map(String::valueOf).collect(Collectors.joining()));
				matrix[i][j] = true;
				backtracking(i + 1, n, initQueen(n), queens, nQueens);
				queen.set(j, '.');
				queens.remove(queens.size() - 1);
				matrix[i][j] = false;
			}
		}
		return nQueens;
	}

	public List<List<String>> solveNQueens(int n) {
		return backtracking(0, n, initQueen(n), new ArrayList<String>(), new ArrayList<List<String>>());
	}
}

public class NQueens {
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.solveNQueens(4));
	}
}
