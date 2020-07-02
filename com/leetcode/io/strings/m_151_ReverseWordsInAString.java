package io.strings;

import org.junit.Test;

public class m_151_ReverseWordsInAString {
    public String reverseWords(String s) {
    	
        /*
            Reverse string, & reverse each word
            Trim left + right

            eulb si yks eht
            reverse("eulb") => "blue" -> append()
                
            "eulb     yks"
            while(arr[i] == " " && arr[i] == arr[i+1]) i++;
                
            for example     space
            
            
        */
        
        char[] chars = s.toCharArray();
        //O(n)
        reverse(chars, 0, chars.length - 1);
        //System.out.println(new String(chars));
        //cases, reversing a word b/w spaces
        //reversing a word reaching end of string
        //reversing a word beginning of string
        //asjfias  asopd a        
        
        //failure in reverse/
        //failure in sb append start, i loic
        
        int i = 0;
        int j = chars.length - 1;
        while(i < chars.length && chars[i] == ' ')
            i++;
        while(j > i && chars[j] == ' ')
            j--;          
        StringBuilder sb = new StringBuilder();
        int start = i;
        //O(n)
        for(; i <= j; i++) {   
            //if we reach a space or end of string, trigger, reverse
            if((i < j && chars[i+1] == ' ') || i == j) {
                //reverse(start, i);
                for(int k = i; k >= start; k--)
                    sb.append(chars[k]);
                //System.out.println(sb.toString());
                //i<j, we are not at the end of the string
                if(i < j) {
                    sb.append(" ");
                    i++;
                    while(i < j && chars[i] == ' ')
                        i++;
                    start = i;
                    i--;
                    // sb.append(" ");
                    // while(i < j && chars[i + 1] == ' ')
                    //     i++;
                    // start = i-1;
                    // i--;
                }                
            }
        }
        return sb.toString();
    }
    
    /**
     * IDEA!!!!!!!!!!!!!!!!!!!
	    Instead of reversing string, & then reversing each word,
	    we can scan backwards starting from end of the string,
	    once, we encounter a " ", we can add it to our sb
	    then continue looking for next " "
	    
	    the sky   is    blue
	       ^     ^     ^                <--------- get last index of ' ' iteratively
    */    
    public String reverseWords2(String s) {
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        int prevIndex = s.length(), curIndex = prevIndex;
        while ((curIndex = s.lastIndexOf(' ', curIndex - 1)) > 0) {
            if (curIndex < prevIndex - 1) {
                sb.append(s.substring(curIndex + 1, prevIndex)).append(" ");
            }
            prevIndex = curIndex;
        }
        sb.append(s.substring(curIndex + 1, prevIndex));
        return sb.toString();
    }
    
    //blues
    //slueb
    //seylb
    //
    void reverse (char[] arr, int left, int right) {
        if(left >= right)
            return;
        if(left >= arr.length)
            return;
        while(left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }
    
    void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
		arr[j] = temp;
	}
    
    @Test
    public void Test1() {
    	reverseWords2("                                               the sky    istr     blue                    ");
    }
}
