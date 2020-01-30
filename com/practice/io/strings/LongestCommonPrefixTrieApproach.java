package io.strings;

import java.util.*;

class TrieNode {
  int value;
  Map<Character, TrieNode> children;
    
  public TrieNode() {
    this.children = new HashMap<>();
  }
  
  public TrieNode(int value) {
    this.value = value;
    this.children = new HashMap<>();
  }
}

class Trie {
  TrieNode root;
  
  // Public Methods
  
  public Trie() {
    this.root = new TrieNode();
  }
  
  public void insert(String word) {
    TrieNode current = this.root;    
    for (int i = 0; i < word.length(); i++) {
      current = insertNode(current, word.charAt(i));
    }
  }  
  
  public String findLongestPrefix() {
    TrieNode current = this.root;
    String longestPrefix = "";
    int pValue = 0;
    int cValue = pValue;
    
    while (current != null) {                 
      if (current.children.keySet().size() == 1) {        
        Character c = current.children.keySet().iterator().next(); 
        cValue = current.children.get(c).value;
        if  (pValue <= cValue) {                        
          longestPrefix = longestPrefix + String.valueOf(c);
          current = current.children.get(c);
        } else {
          break;
        }
      } else {
        break;
      }
      pValue = cValue;
    }
    
    return longestPrefix;
  }
  
  // Helper Methods
  
  private TrieNode insertNode(TrieNode current, char letter) { 
    if (current == null)
      return current;
    
    // If there's no exising child, we create one
    if (!current.children.containsKey(letter)) { 
      current.children.put(letter, new TrieNode(1));  
    }
    current.children.get(letter).value++; // Else Increment value
    
    return current.children.get(letter);
  }
}

/*
	Input = ["cat", "cattle", "call"]
	Output = "ca"
	
	                 ( root )
	                /
	            ( c ):3
	             /
	          ( a ):3
	           /        \
	       ( t ):2    ( l ):1
	        /               \
	     ( t ):1         ( l ):1
	      /
	   ( l ):1
	    /
	( e ):1
 */
class LongestCommonPrefixTrieApproach {
    public String longestCommonPrefix(String[] strs) {
      Trie trie = new Trie();      
      for (String word : strs) {        
        if (word.equals(""))
          return "";
          
        trie.insert(word);
      }
                        
      return trie.findLongestPrefix();
    }
}