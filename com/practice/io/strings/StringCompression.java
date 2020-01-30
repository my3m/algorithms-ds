package io.strings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//aaab
//a3b
public class StringCompression {
	
	/*
	 * aaab
	 * i start ptr, i = 0
	 * j initial start ptr, increments until uniquechar, j = 3
	 * diff = j - i, => 3
	 * k ptr is writing ptr
	 * chars[k++] = chars[i];
	 * if(diff > 1) write diff chars
	 * update i ptr to j ('b')
	 * Sliding window, fast,slow ptr
	 */
    public String compress2(char[] chars) {
        int k = 0;
        int i = 0;
        int j = 0;
        while(i < chars.length) {
            while(i < chars.length && chars[i] == chars[j]) {
                i++;
            }
            chars[k++] = chars[j];
            int frq = i - j;
            if(frq > 1) {
                for(char chr : Integer.toString(frq).toCharArray()) {
                    chars[k++] = chr;
                }
            }
            j = i;
        }
        return generateSubstring(chars, 0, k - 1);
    }
    
    public String compress3(char[] chars) {
    	int i = 0;
    	int j = 0;
    	int k = 0;
    	//aaabbbbb
    	//aaabbbbc, j=0, i=3,
    	//aaabbbbc, j=3, i=7
    	//aaabbbbb, j=3, i=7
    	//abcd
    	//aaaa
    	//'a','3','b', '5', 
    	for(;i<chars.length;i++) {
    		if(i + 1 == chars.length || chars[j] != chars[i + 1]) {
    			chars[k++] = chars[j];
    			int frq = i - j + 1;
    			if(frq > 1) {
    				for(char chr : Integer.toString(frq).toCharArray()) {
    					chars[k++] = chr;
    				}
    			}
    			j = i + 1;
    		}
    	}
    	return generateSubstring(chars, 0, k - 1);
    }
	
	public String compress(char[] chars) {
		int j = 0;
		int n = 1;
		//a3bc
		for(int i = 1; i < chars.length; i++) {
			if(chars[j] != chars[i]) {
				if(n > 1) {
					char[] chrfrq = new Integer(n).toString().toCharArray();
					//'3'
					for(char cfq : chrfrq) {
						chars[++j] = cfq;
					}
					chars[++j] = chars[i];
					n = 1;
				}
				else {
					//a3bc
					chars[++j] = chars[i];
				}
			} else {
				n++;
			}
		}
		if(n > 1) {
			char[] chrfrq = new Integer(n).toString().toCharArray();
			//'3'
			//'a',3,'a'
			for(char cfq : chrfrq) {
				chars[++j] = cfq;
			}
		}
		return generateSubstring(chars, 0, j);
	}
	
	public String generateSubstring(char[] arr, int start, int end) {
		char[] substr = new char[end - start + 1];
		for(int i = 0; i < substr.length; i++) {
			substr[i] = arr[start + i]; 
		}
		return new String(substr);
	}
	
	@Test
	public void Test1() {
		assertEquals("a4", compress2(new char[] { 'a', 'a', 'a', 'a' }));
	}
	
	@Test
	public void Test5() {
		assertEquals("abcd", compress2(new char[] { 'a', 'b', 'c', 'd' }));
	}
	
	
	@Test
	public void Test2() {
		assertEquals("a3", compress2(new char[] { 'a', 'a', 'a' }));
	}
	@Test
	public void Test3() {
		assertEquals("a3bc", compress2(new char[] { 'a', 'a', 'a', 'b', 'c' }));
	}
	@Test
	public void Test4() {
		assertEquals("axd2c", compress2(new char[] { 'a', 'x', 'd', 'd', 'c' }));
	}
	
}
