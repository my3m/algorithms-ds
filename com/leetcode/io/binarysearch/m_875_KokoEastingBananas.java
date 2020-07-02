package io.binarysearch;

public class m_875_KokoEastingBananas {
    public int minEatingSpeed(int[] piles, int H) {
        return lower_bound(piles, 1, getMaximumElement(piles), H);
    }
    
    int lower_bound(int[] nums, int l, int r, int H) {
        while(l < r) {
            int mid = l + (r - l)/2;
            if (!canEatAllBananas(nums, mid, H)) {
            	l = mid + 1;
            }
            else {
            	r = mid;
            }
        }
        return l;
    }
    
    boolean canEatAllBananas(int[] piles, int K, int H) {
        int time = 0;
        for(int num : piles) {
            time += num / K;
            if(num % K != 0)
                time++;
        }
        //System.out.printf("time=%d, speed=%d\r\n", time, K);
        return time <= H;
    }
    
    int getMaximumElement(int[] piles) { 
        int max = piles[0];
        for(int i = 1; i < piles.length; i++)
            max = Math.max(piles[i], max);
        //System.out.println(max);
        return max;
    }
}
