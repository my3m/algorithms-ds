package io.graphs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class m_1239_MaximumLengthOfConcatenatedStringWithUniqueChars {
    //1. Reached a dead-end not knowing how to DFS properly due to "", "un", "iq", "ue" concats
    public int maxLength(List<String> arr) {
        /**
            IDEA!!!!!
                Well, we can create all possible concatentations (WHICH ARE SUBSEQUENCES) that have unique chars, & 
                find out the one with the maximum lenth. dfs
                
                To know if an index arr[i] has a duplicate, create a hashmap, which contains the chars
                arr = ["un", "iq", "ue"]
                map[0] = 'u,n', map[1] = 'i,q', map[2] = 'u,e'
                
                case where an index contains duplicate chars. arr[1] = 'aaa'
                
                
            IDEA!!!
                Perhaps, we can use an int[] array length 26, since we know chars only consist of 26letters
                Remove the String path, add a int array
        */
        if(arr.size() == 0)
            return 0;
        Map<Integer, Set<Character>> map = new HashMap<Integer, Set<Character>>();
        List<Integer> current = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++) {
            map.putIfAbsent(i, new HashSet<Character>());
            for(int j = 0; j < arr.get(i).length(); j++) {
                /**
                    Handle the case where an idx contains non-unique chars 'aaa'
                */
                if(!map.get(i).add(arr.get(i).charAt(j))) {
                    map.remove(i);
                    break;
                }
            }
            current.add(i);
        }
        return dfs(arr, map, "", 0).length();
        //What ds should I use to hold my current concatenation? List, Set : Use Set<Integer>
    }
    
    /**
        i = 0, path='un'
            i=1, path = 'uniq'
                i=2, path='uniq' (return 'uniq')
        i = 0, compare path='un' with temp 'uniq', path='uniq'
        
        #2
        i = 1, path='unique', 
    */
    String dfs(List<String> arr, Map<Integer, Set<Character>> map, String path, int startIdx) {
        if(startIdx >= arr.size())
            return path;
        String temp = "";
        for(int i = startIdx; i < arr.size(); i++) {
            //can we use backtrack here???
            //can we add index i here, using our map
            boolean exploreFurther = true;
            if(map.containsKey(i)) {
                for(char c : path.toCharArray()) {
                    if(map.get(i).contains(c)) {
                        /** We don't want to further explore this path as it there are duplicate chars **/
                        //BUG!!!!continue;
                        exploreFurther = false;
                        break;
                    }
                }
                if(!exploreFurther)
                    continue;
                //path = path + arr.get(i); ###BUGGG!!!!
                String temp2 = dfs(arr, map, path + arr.get(i), i + 1);
                if(temp2.length() > temp.length())
                    temp = temp2;
            }
        }
        if(temp.length() > path.length())
            path = temp;
        return path;
    }
    
    public int maxLength2(List<String> arr) {
        /**               
            IDEA!!!
                Perhaps, we can use an int[] array length 26, since we know chars only consist of 26letters
                Remove the String path, add a int array
                
                How do we know if a recursive call has a greater length? running sum?
                How do we check for uniqueness? arr[i - 'a'] == 0?
                how do I pass new ref of int[26] array into the new call?
                
                Do I check for duplicate inside the for loop or in the base case??
        */
        if(arr.size() == 0)
            return 0;
        boolean[] visited = new boolean[arr.size()];
        return dfs2(arr, 0, new int[26], 0, visited);
    }
    
    int[] makeCopy(int[] arr) {
        int[] copy = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }
    
    /**
        Optimization: if we explored, 'un' -> 'iq'
            then we don't need to traverse  'iq' ->
    */
    int dfs2(List<String> arr, int currentLength, int[] unique, int startIndex, boolean[] visited) {
        int temp = Integer.MIN_VALUE;
        for(int i = startIndex; i < arr.size(); i++) {
            if(visited[i])
                continue;
            String next = arr.get(i);
            boolean explore = true;
            int[] inst = makeCopy(unique);
            for(int j = 0; j < next.length(); j++) {
                //How do I check for duplicate chars here? If duplicate, how to backtrack??
                int offset = next.charAt(j) - 'a';
                if(inst[offset] == 1) {
                    explore = false;
                    break;
                }
                inst[offset] = 1;
            }
            if(explore) {
                //visited[i] = true;
                //Why???????
                temp = Math.max(temp, dfs2(arr, currentLength + next.length(), inst, i + 1, visited));
            }
        }
        return currentLength > temp ? currentLength : temp;
    }    

	@Test
	public void Test1() {
		List<String> arr = Arrays.asList("abcdefghijklm","bcdefghijklmn","cdefghijklmno","defghijklmnop","efghijklmnopq","fghijklmnopqr","ghijklmnopqrs","hijklmnopqrst","ijklmnopqrstu","jklmnopqrstuv","klmnopqrstuvw","lmnopqrstuvwx","mnopqrstuvwxy","nopqrstuvwxyz","opqrstuvwxyza","pqrstuvwxyzab");
		assertEquals(26, maxLength2(arr));
	}
	
	@Test
	public void Test2() {
		List<String> arr = Arrays.asList("un", "iq", "ue");
		assertEquals(4, maxLength2(arr));
	}
	
	/** All duplicate chars each idx */
	@Test
	public void Test3() {
		List<String> arr = Arrays.asList("unn", "iqi", "queq");
		assertEquals(0, maxLength2(arr));
	}
	
	/** Duplicate chars idx 0 & 1 */
	@Test
	public void Test4() {
		List<String> arr = Arrays.asList("unn", "iqi", "que");
		"mississippi".charAt(2);//("issip");
		assertEquals(3, maxLength2(arr));
	}
}
