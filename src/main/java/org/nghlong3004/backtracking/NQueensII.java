package org.nghlong3004.backtracking;

import java.util.Scanner;

public class NQueensII extends Solution {
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			NQueensII nQueensII = new NQueensII();
			System.out.println(nQueensII.solveNQueens(scanner.nextInt()).size());
		}
	}
}
