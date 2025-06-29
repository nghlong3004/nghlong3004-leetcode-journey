package org.nghlong3004.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {

	private boolean[] isMainDiagonal;
	private boolean[] isAntiDiagonal;
	private boolean[] isColumn;
	private List<List<String>> result;

	public Solution() {
		result = new ArrayList<List<String>>();
		isMainDiagonal = new boolean[24];
		isAntiDiagonal = new boolean[24];
		isColumn = new boolean[12];
	}

	private boolean isDiagonal(int i, int j) {
		return isMainDiagonal[i - j + 12] || isAntiDiagonal[i + j];
	}

	private boolean isChoose(int i, int j, int n) {
		if (isColumn[j] || isDiagonal(i, j)) {
			return false;
		}
		return true;
	}

	private List<String> convertDataToInformation(List<Integer> data) {

		List<String> infomation = new ArrayList<String>();
		for (int i = 0; i < data.size(); ++i) {
			StringBuilder builder = new StringBuilder();
			for (int j = 0; j < data.size(); ++j) {
				if (j == data.get(i)) {
					builder.append('Q');
				} else {
					builder.append('.');
				}
			}
			infomation.add(builder.toString());
		}
		return infomation;
	}

	private void backtracking(int row, int n, List<Integer> queens) {
		if (row == n) {
			result.add(convertDataToInformation(queens));
		}
		for (int column = 0; column < n; ++column) {
			if (isChoose(row, column, n)) {
				queens.add(column);
				isMainDiagonal[row - column + 12] = true;
				isAntiDiagonal[row + column] = true;
				isColumn[column] = true;
				backtracking(row + 1, n, queens);
				queens.remove(queens.size() - 1);
				isMainDiagonal[row - column + 12] = false;
				isAntiDiagonal[row + column] = false;
				isColumn[column] = false;
			}
		}
	}

	public List<List<String>> solveNQueens(int n) {
		backtracking(0, n, new ArrayList<Integer>());
		return result;
	}
}

public class NQueens {
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			Solution solution = new Solution();
			System.out.println(solution.solveNQueens(scanner.nextInt()));
		}
	}
}
