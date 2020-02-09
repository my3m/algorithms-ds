/*
use trie to implement here.
trie + dfs
why tire ?

if there is only one word we have to search - by using dfs3, the time complexity is 3^n
if there are multiple words in the list, the time complexity could be m * 3 ^n, if some words share common prefixes, there is repretitive works


if we use trie, repetitive works can be prevented if words share common prefixes.


time complexity for building a trie - if average length of a word is n, there are m words, tc : m * n, space: max len of a word.

time to walk through the whole tree, O(m * n)


*/


class Solution {
    class TrieNode {
        String word;
        TrieNode[] children;
        boolean isWord;
        public TrieNode() {
            //this.word = word;
            this.children = new TrieNode[26];
            //this.isWord = false;
        }
    }
    
    TrieNode root = new TrieNode();
    final int[][] dir = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }
        
        buildTrie(words);
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i][j];
                if (root.children[c - 'a'] != null) {
                    boolean[][] visited = new boolean[m][n];
                    // implement 细节,i, j 对应 root 的下一层！！！！
                    dfs(i, j, board, visited, res, root);
                }
            }
        }
        return res;
    }
    // where I got stuck, do I have to mark visited here? why?
    // like word search I, you still have to mark visted as you go,  [["a","a"]], ["aaa"]
    
    
    // 你需要搞清楚　i, j 对应当是当前node 还是下一个node.
    private void dfs(int i, int j, char[][] board, boolean[][] visited, List<String> res, TrieNode cur) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) {
            return;
        }
        TrieNode temp = cur.children[board[i][j] - 'a'];
        
        if (temp == null) {
            return;
        }
        
       
        if(temp.isWord) {
            res.add(temp.word);
            temp.isWord = false; //optimize
        }
        
        visited[i][j] = true;
        
        for (int[] d : dir) {
           dfs(i + d[0], j + d[1], board, visited, res, temp);
        }
        
        visited[i][j] = false;
    }
    
    
    private void buildTrie(String[] words) {
        
        for (String word : words) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                int position = c - 'a';
                if (cur.children[position] == null) {
                    cur.children[position] = new TrieNode();
                }
            
                cur = cur.children[position];
            }
            cur.isWord = true;
            cur.word = word;
        }
    }
}


/*
k : size of the words
if w/0 use trie
k * m * n * 3^l

if w/ trie

k * l + m * n * 3^l

*/