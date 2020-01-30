package io.strings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Assert;

public class RemoveTrailingSpaces {

	public String run(String s) {
        //"aaaaaaaa" -> "a"
        if(s.length() == 0)
        	return s;
        int j = -1;
        int i = 0;
        int k = 0;
        char[] arr = s.toCharArray();
    
        //
        for(;i<arr.length;i++) {
        	if(arr[i] != ' ') {
        		++j;
        		swap(arr, i, j);
        		k++;
        		if(i + 1 < arr.length && arr[i + 1] == ' ') {
        			j += 1;       			
        		}
        	}
        }
        if(j == -1) 
        	return "";
        
        while(j < arr.length && arr[j] == ' ')
        	j--;
        
        char[] normalizedArr = new char[j + 1];
        i = 0;
        for(;i < normalizedArr.length; i++)
        	normalizedArr[i] = arr[i];
        
        System.out.printf("%d\r\n", arr.length);
        System.out.printf("%d\r\n", j + 1);
        System.out.printf("%d\r\n", j);
        String before = new String(arr);
        String after = new String(normalizedArr);
        System.out.printf("\"%s\"\r\n", before);
        System.out.printf("\"%s\"\r\n\r\n", after);
        return after;		
	}
	
	public char[] toCharArray(ArrayList<Character> chars) {
		char[] temp = new char[chars.size()];
		for(int z = 0; z < chars.size(); z++)
			temp[z] = chars.get(z);
		return temp;
	}
	
    public void swap(char[] arr, int i, int j)
    {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }	
    
    @Test
    public void Test1() {
    	String res = new RemoveTrailingSpaces().run(" test   a very       fine absolute   crazy");
    	Assert.assertEquals("test a very fine absolute crazy", res);
    }
    
    @Test
    public void Test2() {
    	String res = new RemoveTrailingSpaces().run("         ");
    	Assert.assertEquals("", res);
    }
    
    @Test
    public void Test3() {
    	String res = new RemoveTrailingSpaces().run("    s     ");
    	Assert.assertEquals("s", res);
    }    
    
    @Test
    public void Test4() {
    	String res = new RemoveTrailingSpaces().run("    s   y  ");
    	Assert.assertEquals("s y", res);
    }  
    
    @Test
    public void Test5() {
    	String res = new RemoveTrailingSpaces().run("         hello");
    	Assert.assertEquals("hello", res);
    }
    
    @Test
    public void Test6() {
    	String res = new RemoveTrailingSpaces().run("         hello          ");
    	Assert.assertEquals("hello", res);
    }
    
    @Test
    public void Test7() {
    	String res = new RemoveTrailingSpaces().run("hello world");
    	Assert.assertEquals("hello world", res);
    }         
    
    @Test
    public void Test8() {
    	assertEquals("hello, this is world", new RemoveTrailingSpaces().run("hello, this is world "));
    }
    

}
