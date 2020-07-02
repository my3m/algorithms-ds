package io.sort;

public class Comparison {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
		returns the smallest lexicographical order, from String.compareTo
	 */
    public int compareTo(String value1, String value2) {
        int len1 = value1.length();
        int len2 = value2.length();
        int lim = Math.min(len1, len2);
        char v1[] = value1.toCharArray();
        char v2[] = value2.toCharArray();

        int k = 0;
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
    }
}
