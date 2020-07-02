package io.arrays;

public class m_977_SquaresOfSortedArray {
	//Why did we need the <= sign??
    public int[] sortedSquares(int[] A) {
        int i = 0, j = A.length - 1, k = A.length -1;
        int[] AA = new int[A.length];
        //100
        //16,100
        //9,16,100
        //0,1,9,16,100
        //[-7,-3,2,3,11]
        while(i <= j) {
            int a = A[i] * A[i];
            int b = A[j] * A[j];
            if(a >= b) {
                AA[k--] = a;
                i++;
            }
            else {
                AA[k--] = b;
                j--;
            }
        }
        return AA;
    }
}
