package io.strings;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

public class m_482_LicenseKeyFormatting {
	
    public String licenseKeyFormatting(String S, int K) {
        /* find number of alphanumeric chars 
        */
        int x = 0;
        for(int i = 0; i < S.length(); i++) {
            if(S.charAt(i) != '-') {
                x++;
            }
        }
        
        //if there are no alphanumeric chars, then return ""
        if(x == 0)
            return "";
        
        /*
        "2-4A0r7-4k"
        4
        */
        //"24A0-R74K"
        int first = x % K;
        //System.out.println(first);
        StringBuilder sb = new StringBuilder();
        //if first is zero, it means we dont have to process first grp
        if(first != 0) {
            //print the first x % k alphanumerics
            int k = 0;
            for(int i = 0; i < S.length(); i++) {
                if(S.charAt(i) != '-') {
                    sb.append(ConvertToUpperCase(S.charAt(i)));
                    k++;
                    if(k == first) {
                        first = i + 1;
                        //only want to add a - if there are more chars to process
                        if(first < S.length()) {
                            sb.append('-');
                        }
                        break;
                    }                    
                } 
            }           
        }
        int c = 0;
        for(int j = first; j < S.length(); j++) {
            if(S.charAt(j) != '-') {
                if(c == K) {
                    sb.append('-');
                    c = 0;
                }                    
                sb.append(ConvertToUpperCase(S.charAt(j)));
                c++;
                //i could have added append '-' here, but again, we
                //want to only add a '-' when we know there is another char
                //pre-process concept
            }
        }
        return sb.toString();
    }
    
    char ConvertToUpperCase(char c) {
        return Character.isUpperCase(c) ? c : Character.toUpperCase(c);
        //return (char)(('a' - c) + (int)'A');
    }
	
    @Test
	public void Test1() {
		// TODO Auto-generated method stub
		assertEquals(licenseKeyFormatting("2-4A0r7-4k", 4), "24A0-R74K");
		assertEquals(licenseKeyFormatting("5F3Z-2e-9-w", 4), "5F3Z-2E9W");
		assertEquals(licenseKeyFormatting("2-5g-3-J", 2), "2-5G-3J");
	}

}
