package Sudoku_Solver;

import java.util.Scanner;

class Sudoku 
{
	public static boolean isSafe(int[][] board, int row, int col, int num)
	{
		for (int d = 0; d < board.length; d++) 
			if (board[row][d] == num) return false;

		
		for (int r = 0; r < board.length; r++) 
			if (board[r][col] == num) return false;
			
		int sqrt = (int)Math.sqrt(board.length);
		int boxRowStart = row - row % sqrt;
		int boxColStart = col - col % sqrt;

		for (int r = boxRowStart; r < boxRowStart + sqrt; r++) 
		{
			for (int d = boxColStart; d < boxColStart + sqrt; d++) 
				if (board[r][d] == num) return false;
		}
		return true;
	}

	public static boolean solveSudoku(
		int[][] board, int n)
	{
		int row = -1;
		int col = -1;
		boolean isEmpty = true;
		for (int i = 0; i < n; i++) 
		{
			for (int j = 0; j < n; j++) 
			{
				if (board[i][j] == 0) 
				{
					row = i;
					col = j;
					isEmpty = false;
					break;
				}
			}
			if (!isEmpty) break;
		}

		if (isEmpty) return true;
		
		for (int num = 1; num <= n; num++) 
		{
			if (isSafe(board, row, col, num)) 
			{
				board[row][col] = num;

				if (solveSudoku(board, n)) return true;
				else board[row][col] = 0;
			}
		}
		return false;
	}

	public static void print(int[][] board, int N)
	{
		int sqrt = (int)Math.sqrt(N);
		for (int r = 0; r < N; r++) 
		{
			for (int d = 0; d < N; d++)
			{
				if(d%sqrt == 0 && d != 0) System.out.print("| ");
				System.out.print(board[r][d]);
				System.out.print(" ");
			}
			if((r+1)%sqrt == 0 && r != N-1)
			{
				System.out.println();
				System.out.print("- - - - - - - - - - -");
			}
			System.out.println();
			
		}
	}

	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the size of Sudoku : ");
		int N = sc.nextInt();

		int[][] board = new int[N][N];

		System.out.println("Enter the numbers : ");

		for (int i = 0; i < N; i++) 
			for (int j = 0; j < N; j++) 
				board[i][j] = sc.nextInt();
			
		if (solveSudoku(board, N)) print(board, N);

		else System.out.println("No solution or Invalid Input given");
		sc.close();
	}
}