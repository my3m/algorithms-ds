package io.strings;

/**
 
 Study clean colution
 -------------------------------------------------------------------
 public int compareVersion(String version1, String version2) {
        int len1 = version1.length();
        int len2 = version2.length();
        int i = 0, j = 0;
        while (i < len1 || j < len2) {
            int level1 = 0, level2 = 0;
            while (i < len1 && version1.charAt(i) != '.') {
                level1 = level1 * 10 + version1.charAt(i++) - '0';
            }
            while (j < len2 && version2.charAt(j) != '.') {
                level2 = level2 * 10 + version2.charAt(j++) - '0';
            }

            if (level1 < level2) {
                return -1;
            } else if (level1 > level2) {
                return 1;
            } else {
                j++;
                i++;
            }
        }
        return 0;    
    }
 
 */
public class m_165_CompareVersionNumbers {
	   public int compareVersion(String version1, String version2) {
	       /*
	            "1.17", "1.12" 1 > 2, ret 1     18, 13
	            "1.1",  "1.10" 1 == 2, return 0, 2, 11
	            "1.0.1" "1"    1 > 2, return 1, 2, 1
	            "1.01"  "1.001" 1==2, return 0  2, 2
	            
	            "1.2", "1.333" 1=1, 333 > 2
	        
	            "2"
	            "300"
	            
	            "1.2" "1.2.0"
	            
	            "1.0", "1.0.0"
	            
	             "1.0.1", "1"
	             
	             1,1
	             0,0
	             1,0
	       
	       */
	        
	        //O(n)
	        String[] v1s = version1.split("\\.");
	        String[] v2s = version2.split("\\.");
	        
	        int i = 0;
	        
	        int len = Math.max(v1s.length, v2s.length);
	        while(i < len) {
	            int v1 = i < v1s.length ? parseInteger(v1s[i]) : 0;
	            int v2 = i < v2s.length ? parseInteger(v2s[i]) : 0;
	            //System.out.printf("v1=%d v2=%d\r\n", v1, v2);
	            if(v1 > v2) {
	                return 1;
	            } else if(v2 > v1) {
	                return -1;
	            }
	            i++;
	        }
	        return 0;
	        
	    }
	    
	    int parseInteger(String s) {
	        int sum = 0; 
	        //721
	        //0*10 + 7
	        //7*10 + 2 => 70 + 2
	        //720 + 1
	        for(int i = 0; i < s.length(); i++) {
	            sum = sum* 10 + s.charAt(i) - '0';
	        }
	        return sum;
	    }
}
