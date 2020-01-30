package io.strings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/* Reverse string
 * Reverse using two pointers
 */
public class m_917_ReverseOnlyLetters {
    public String reverseOnlyLetters(String S) {
        if(S.length() == 0)
            return "";
        int left = 0;
        int right = S.length() - 1;
        char[] arr = S.toCharArray();
        while(left < right) {
            if(!isLetter(arr[left])) {
                left++;
            }
            if(!isLetter(arr[right])) {
                right--;
            }           
            if(isLetter(arr[left]) && isLetter(arr[right])) {
                swap(arr, left++, right--);
            }
        }
        return new String(arr);
    }
    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;        
    }
    public static boolean isLetter(char chr) {
        if(chr >= 'a' && chr <= 'z')
            return true;
        else if(chr >= 'A' && chr <= 'Z')
            return true;
        else
            return false;
    }
    
    @Test
    public void Test1() {
    	assertEquals("Qedo1ct-eeLg=ntse-T!", reverseOnlyLetters("Test1ng-Leet=code-Q!"));
    }
    @Test
    public void Test2() {
    	assertEquals("j-Ih-gfE-dCba", reverseOnlyLetters("a-bC-dEf-ghIj"));
    }
    @Test
    public void Test3() {
    	assertEquals("ab-cd", reverseOnlyLetters("dc-ba"));
    }
}
