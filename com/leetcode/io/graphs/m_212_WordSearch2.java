package io.graphs;

import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Test;

public class m_212_WordSearch2 {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> foundWords = new ArrayList<>();
    	SearchTrie trie = new SearchTrie();
        /**
         * Time=O(w*s), 
         * 	where s is the maximum length of word in words.
         */
        trie.populateSuffixTrie(words);
        /**
         * I don't need to created a visited for each dfs-path,
         * I'm just using backtracking to unvisit the nodes I just
         * visited.
         * Space= O(n*m)
         */
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                dfs(board, visited, trie.root, i, j, foundWords);
            }
        }
        return foundWords;
    }
    
    /**
     * At most, for each node in the grid, we will visit 4 nodes.
     * We will do this for a maximum for s times, where s is the
     * largest string in our list of words. 
     * Thereforce, Time=O(4^s)
     */
    void dfs(char[][] board, boolean[][] visited, TrieNode node, int i, int j, List<String> found) {
    	/**
    	 * We need to check if the char at i, j is at the root of our search trie
    	 * Can be place here.
    	 */
        /**
         * Note=, We may have another string which is a prefix of the found string
         * 	race, racecar
         * 		We end searching, when there is no matching char next to ptr in our trie
         */
//        if(node.children.containsKey('*'))
//            return true;        
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j])
            return;
        char letter = board[i][j];
        if(!node.children.containsKey(letter))
            return;
        visited[i][j] = true;
        TrieNode next = node.children.get(letter);
        //if(letter.children.containsKey('*')) {
        if(next.isText) {
        	/**
        	 * This means that we have found a matching string from our trie
        	 * in the grid.
        	 * We cannot assume, this can be triggered next node, if we become out of bounds
        	 */
        	found.add(next.searchText);
            next.isText = false; //Think this means don't add the duplicate
        }
        dfs(board, visited, next, i - 1, j, found);
        dfs(board, visited, next, i, j + 1, found);
        dfs(board, visited, next, i + 1, j, found);
        dfs(board, visited, next, i, j - 1, found);
        /**
         * Note=, We may have another string which is a prefix of the found string
         * 	race, racecar
         * 		We end searching, when there is no matching char next to ptr in our trie
         */
//        if(result) {
//            return true;
//        }
        
        visited[i][j] = false;
    }  
    
    
    @Test
    public void Test1() {
    	char[][] board = new char[][]
		{
    		{'o','a','a','n'},
			{'e','t','a','e'},
			{'i','h','k','r'},
			{'i','f','l','v'}
		};
    	String[] words = new String[]
		{
				"oath","pea","eat","rain"    	
		};
    	List<String> expected = Arrays.asList("oath","eat");
    	List<String> actual = findWords(board, words);
    	assertTrue(expected.equals(actual));
    }    
    
    @Test
    public void Test2() {
    	char[][] board = new char[][]
		{
			{'a', 'b'},
			{'c', 'd'}
		};
    	String[] words = new String[]
		{
			"ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb"    	
		};
    	List<String> expected = Arrays.asList("ab","ac","bd","ca","db");
    	List<String> actual = findWords(board, words);
    	assertTrue(expected.equals(actual));
    }
    
    @Test
    public void Test3() {
    	char[][] board = new char[][]
		{
			{'a', 'a'}
		};
    	String[] words = new String[]
		{
			"a"    	
		};
    	List<String> expected = Arrays.asList("a");
    	List<String> actual = findWords(board, words);
    	assertTrue(expected.equals(actual));
    }
    
}

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    String searchText;
    public boolean isText;
}

class SearchTrie {
    TrieNode root = new TrieNode();
    //char endSymbol = '*';
    
    void populateSuffixTrie(String[] strings) {
        for(String str : strings) {
            TrieNode node = root;
            for(char letter : str.toCharArray()) {
                if(!node.children.containsKey(letter))
                    node.children.put(letter, new TrieNode());
                node = node.children.get(letter);
            }
            //at the very end of our suffix trie, add an '*' to mark the end
            //root->b,a,b,c
            node.isText = true;
            node.searchText = str;
            // TrieNode end = new TrieNode();
            // end.searchText = str;
            // node.children.put(endSymbol, end);
        }
    }   
    
    //b,a,b,c
    //root
    //b     a       c
    //| |   |       |
    //a c   b       *
    //| |   |
    //b *   c
    //|     |
    //c     *
    //|
    //*
    
    boolean contains(String str) {
        TrieNode node = root;
        for(int i = 0; i < str.length(); i++) {
            char letter = str.charAt(i);
            if(!node.children.containsKey(letter))
                return false;
            node = node.children.get(letter);
        }
        return node.isText;
    }
}