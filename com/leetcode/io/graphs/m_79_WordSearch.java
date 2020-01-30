package io.graphs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * Application: dfs/Back-tracking
 */
public class m_79_WordSearch {
	public boolean exist(char[][] board, String word) {
		if(board.length == 0 || board[0].length == 0)
			return false;
		int rows = board.length;
		int columns = board[0].length;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				if(board[i][j] == word.charAt(0)) {
					boolean[][] visited = new boolean[rows][columns];
					if(dfs(board, visited, word, i, j, 0)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean dfs(char[][] board, boolean[][] visited, String word, int i, int j, int k) {
		if(i < 0 || i >= board.length)
			return false;
		else if(j < 0 || j >= board[0].length)
			return false;
		else if(word.charAt(k) != board[i][j])
			return false;
		else if(visited[i][j])
			return false;
		else if(k == word.length() - 1)
			return true;
		visited[i][j] = true;
		//Should you mark a cell visited if it does not match char??
		int[][] directions = new int[][]
				{
					new int[] {-1,0},
					new int[] {1, 0},
					new int[] {0,-1},
					new int[] {0, 1}
				};
		
		for(int[] dir : directions) {
			int ni = i + dir[0];
			int nj = j + dir[1];
			
			//SEE
			/*
			 * We can get away with these checks, since they are performed in base cases
			 */
			if((ni >= 0 && ni < board.length) && (nj >= 0 && nj < board[0].length)) {
				if(k + 1 < word.length() && word.charAt(k + 1) == board[ni][nj]) {
					if(dfs(board, visited, word, ni, nj, k + 1)) {
						return true;
					}
					else {
//						if(visited[ni][nj])
//							visited[ni][nj] = false;
					}
				}
			}
		}
		visited[i][j] = false;
		return false;
	}
	
	@Test
	public void Test1() {
		System.out.println(false | true);
		char[][] board = new char[][] 
			{
				{'A','B','C','E'},
				{'S','F','C','S'},
				{'A','D','E','E'}
			};
				
		String word = "ABCCED";
		assertEquals(true, exist(board, word));
	}
	
	/*
	 * Each path should have its own 'visited' array
	 */
	@Test
	public void Test2() {
		char[][] board = new char[][] 
			{
				{'C','A','A'},
				{'A','A','A'},
				{'B','C','D'}
			};
				
		String word = "AAB";
		assertEquals(true, exist(board, word));
	}
	
	@Test
	public void Test3() {
		char[][] board = new char[][] 
			{
				{'A','B','C','E'},
				{'S','F','E','S'},
				{'A','D','E','E'}
			};
				
		String word = "ABCESEEEFS";
		assertEquals(true, exist(board, word));
	}
	
	@Test
	public void Test4() {
		char[][] board = new char[][] 
			{
				{'A','A','A','A'},
				{'A','A','A','A'},
				{'A','A','A','A'}
			};
				
		String word = "AAAAAAAAAAAAA";
		assertEquals(false, exist(board, word));
	}
}
