package io.graphs;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/* 
 
 	!!IDEA~~ 
 	for each 'O' cell, recursively perform dfs if it does meet
 	conditions for captured cell (region surround by 'X')
 	
 	Wrong assumptions
 	1. Thinking we can skip the borders (because cell cannot be captured)
 		(However, a border cell 'O' neighbouring an inner cell cannot be captured)
 	2. Naively performing dfs on each 'O' cell. We can just perform DFS on
 		border cells on 'O' since those cells cannot be captured ('E')
 		Any remaining 'O' cell in matrix can be captured (inversed problem)
 	3. Backtracking if not returning true can be tricky.
 	4. Sharing global visited list.

*/
public class m_130_SurroundedRegions {
	
	   public void solve(char[][] board) {
	        if(board.length == 0 || board[0].length == 0)
	            return;
	        int rows = board.length;
	        int columns = board[0].length;
	        
	        for(int i = 0; i < rows; i++) {
	            dfs(board, i, 0);
	            dfs(board, i, columns - 1);
	        }
	        
	        for(int j = 0; j < columns; j++) {
	            dfs(board, 0, j);
	            dfs(board, rows - 1, j);
	        }
	        
	        //E, O, X
	        for(int i = 0; i < rows; i++) {
	            for(int j = 0; j < columns; j++) {
	                if(board[i][j] == 'O')
	                    board[i][j] = 'X';
	                if(board[i][j] == 'E')
	                    board[i][j] = 'O';
	            }
	        }
	    }
	    
	    //dfs:recurse if cannot be captured
	    void dfs(char[][] board, int i, int j) {
	        if(i < 0 || i >= board.length)
	            return;
	        if(j < 0 || j >= board[i].length)
	            return;
	        if(board[i][j] == 'X' || board[i][j] == 'E')
	            return;
	        board[i][j] = 'E';
	        dfs(board, i - 1, j);
	        dfs(board, i + 1, j);
	        dfs(board, i, j + 1);
	        dfs(board, i, j - 1);
	    }
	
	public void solve1(char[][] board) {
		if (board.length == 0 || board[0].length == 0)
			return;
		int rows = board.length;
		int columns = board[0].length;
		boolean[][] gvisited = new boolean[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (board[i][j] == 'O') {
					List<int[]> region = new ArrayList<int[]>();
					/*
					 * Cannot share visited, as each 'O' will do its own dfs
					 */
					//boolean[][] visited = new boolean[rows][columns];
					if (!gvisited[i][j]) {
						if (dfs1(board, i, j, gvisited, region)) {
							for(int[] point : region) {
								board[point[0]][point[1]] = 'X';
							}
						}
					}
				}
			}
		}
	}

	boolean dfs1(char[][] board, int i, int j, boolean[][] visited, List<int[]> region) {
		if (i < 0 || i >= board.length)
			return false;
		if (j < 0 || j >= board[i].length)
			return false;
		if (board[i][j] == 'X')
			return true;
		if (board[i][j] == 'O') {
			if (i == 0 || i == board.length - 1 || j == 0 || j == board[i].length - 1)
				return false;
		}
		if (visited[i][j])
			return true;
		visited[i][j] = true;

		if (dfs1(board, i - 1, j, visited, region) &
				dfs1(board, i + 1, j, visited, region) &
					dfs1(board, i, j + 1, visited, region) &
						dfs1(board, i, j - 1, visited, region)) {
			region.add(new int[] { i, j });
			//Back-tracking won't work, because we could return false & 
			//still backtrack
			return true;
		}

		return false;
	}

	@Test
	public void Test1() {
		char[][] board = new char[][]
		{ 
			new char[] { 'O', 'O', 'O', 'O', 'X', 'X' },
			new char[] { 'O', 'O', 'O', 'O', 'O', 'O' },
			new char[] { 'O', 'X', 'O', 'X', 'O', 'O' },
			new char[] { 'O', 'X', 'O', 'O', 'X', 'O' },
			new char[] { 'O', 'X', 'O', 'X', 'O', 'O' },
			new char[] { 'O', 'X', 'O', 'O', 'O', 'O' }
		};

		char[][] expected = new char[][] 
		{
			new char[] { 'O', 'O', 'O', 'O', 'X', 'X' },
			new char[] { 'O', 'O', 'O', 'O', 'O', 'O' },
			new char[] { 'O', 'X', 'O', 'X', 'O', 'O' },
			new char[] { 'O', 'X', 'O', 'O', 'X', 'O' },
			new char[] { 'O', 'X', 'O', 'X', 'O', 'O' },
			new char[] { 'O', 'X', 'O', 'O', 'O', 'O' }
		};

		solve(board);
		for(int i = 0; i < board.length; i++) {
			System.out.println(new String(board[i]));
		}
		for (int i = 0; i < expected.length; i++) {
			assertArrayEquals(expected[i], board[i]);
		}
	}

	@Test
	public void Test2() {
		//XXXX
		//XOOX
		//XXOX
		//XOXX
		char[][] expected = parseCharConsoleInput(
				"[[\"X\",\"X\",\"X\",\"X\"],[\"X\",\"X\",\"X\",\"X\"],[\"X\",\"X\",\"X\",\"X\"],[\"X\",\"O\",\"X\",\"X\"]]");
		char[][] input = parseCharConsoleInput(
				"[[\"X\",\"X\",\"X\",\"X\"],[\"X\",\"O\",\"O\",\"X\"],[\"X\",\"X\",\"O\",\"X\"],[\"X\",\"O\",\"X\",\"X\"]]");
		solve(input);

		for (int i = 0; i < expected.length; i++) {
			assertArrayEquals(expected[i], input[i]);
		}
	}

	@Test
	public void Test3() {
		char[][] input = parseCharConsoleInput(
				"[[\"O\",\"X\",\"X\",\"O\",\"X\"],[\"X\",\"O\",\"O\",\"X\",\"O\"],[\"X\",\"O\",\"X\",\"O\",\"X\"],[\"O\",\"X\",\"O\",\"O\",\"O\"],[\"X\",\"X\",\"O\",\"X\",\"O\"]]");
		char[][] expected = parseCharConsoleInput(
				"[[\"O\",\"X\",\"X\",\"O\",\"X\"],[\"X\",\"X\",\"X\",\"X\",\"O\"],[\"X\",\"X\",\"X\",\"O\",\"X\"],[\"O\",\"X\",\"O\",\"O\",\"O\"],[\"X\",\"X\",\"O\",\"X\",\"O\"]]");
		solve(input);
		for (int i = 0; i < input.length; i++) {
			System.out.println(new String(input[i]));
		}
		for (int i = 0; i < expected.length; i++) {
			assertArrayEquals(expected[i], input[i]);
		}
	}

	@Test
	public void Test4() {
		char[][] expected = parseCharConsoleInput(
				"[[\"X\",\"X\",\"X\",\"X\"],[\"X\",\"X\",\"X\",\"X\"],[\"X\",\"X\",\"X\",\"X\"],[\"X\",\"O\",\"X\",\"X\"]]");
		char[][] input = parseCharConsoleInput(
				"[[\"X\",\"X\",\"X\",\"X\"],[\"X\",\"O\",\"O\",\"X\"],[\"X\",\"X\",\"O\",\"X\"],[\"X\",\"O\",\"X\",\"X\"]]");
		solve(input);
		for (int i = 0; i < expected.length; i++) {
			assertArrayEquals(expected[i], input[i]);
		}
	}

	@Test
	public void Test5() {
		char[][] expected = parseCharConsoleInput(
				"[[\"X\",\"X\",\"X\",\"X\"],[\"X\",\"X\",\"X\",\"X\"],[\"X\",\"X\",\"X\",\"X\"],[\"X\",\"O\",\"X\",\"X\"]]");
		char[][] input = parseCharConsoleInput(
				"[[\"X\",\"X\",\"X\",\"X\"],[\"X\",\"O\",\"O\",\"X\"],[\"X\",\"X\",\"O\",\"X\"],[\"X\",\"O\",\"X\",\"X\"]]");
		solve(input);
		for (int i = 0; i < expected.length; i++) {
			assertArrayEquals(expected[i], input[i]);
		}
	}

	@Test
	public void TestParsingConsoleInput() {
		char[][] expected = new char[][] { { 'O', 'O', 'O', 'O', 'X', 'X' }, { 'O', 'O', 'O', 'O', 'O', 'O' },
				{ 'O', 'X', 'O', 'X', 'O', 'O' }, { 'O', 'X', 'O', 'O', 'X', 'O' }, { 'O', 'X', 'O', 'X', 'O', 'O' },
				{ 'O', 'X', 'O', 'O', 'O', 'O' } };

		char[][] parsed = parseCharConsoleInput(
				"[[\"O\",\"O\",\"O\",\"O\",\"X\",\"X\"],[\"O\",\"O\",\"O\",\"O\",\"O\",\"O\"],[\"O\",\"X\",\"O\",\"X\",\"O\",\"O\"],[\"O\",\"X\",\"O\",\"O\",\"X\",\"O\"],[\"O\",\"X\",\"O\",\"X\",\"O\",\"O\"],[\"O\",\"X\",\"O\",\"O\",\"O\",\"O\"]]");
		for (int i = 0; i < expected.length; i++) {
			assertArrayEquals(expected[i], parsed[i]);
		}
	}

	// TestUtils.parseCharArray
	char[][] parseCharConsoleInput(String input) {
		List<String> lines = new ArrayList<String>();
		for (int i = 0; i < input.length(); i++) {
			char chr = input.charAt(i);
			if (chr == '"') {
				if (i - 1 >= 0 && input.charAt(i - 1) == '[') {
					StringBuilder sb = new StringBuilder();
					int j = 0;
					while (i < input.length() && input.charAt(i) != ']') {
						if (input.charAt(i - 1) == '"' && input.charAt(i + 1) == '"') {
							if (j % 2 == 0)
								sb.append(input.charAt(i));
							j++;
						}
						i++;
					}
					lines.add(sb.toString());
				}
			}
		}

		char[][] arr = new char[lines.size()][lines.get(0).length()];
		for (int i = 0; i < arr.length; i++) {
			System.out.println(lines.get(i));
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = lines.get(i).charAt(j);
			}
		}
		System.out.println("<!-------------------------------------!>");
		return arr;
	}
}
