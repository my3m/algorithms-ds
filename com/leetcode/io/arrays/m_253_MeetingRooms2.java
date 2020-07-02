package io.arrays;

import java.util.Arrays;
import java.util.PriorityQueue;

import org.junit.Test;

/**
	Idea is to sort by start time, end time
		increase by 1 for each start, subtract 1 for each end
			find the max simulatenous numbe
 */
public class m_253_MeetingRooms2 {
	
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b)->(a[0]-b[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->(a[1]-b[1]));
        for (int[] interval : intervals) {
            if (!pq.isEmpty() && pq.peek()[1] <= interval[0]) {
                pq.poll();
            }
            pq.add(interval);
        }
        return pq.size();
    }
    
    @Test
    public void Test1() {
    	minMeetingRooms(new int[][] {
    			{0, 30},
    			{5,10},
    			{15, 20}
    	});
    }
	
    public int minMeetingRooms2(int[][] intervals) {
        if(intervals.length == 0)
            return 0;
    
        int[] startTimes = new int[intervals.length];
        int[] endTimes = new int[intervals.length];
        for(int i = 0; i < intervals.length; i++) {
            startTimes[i] = intervals[i][0];
            endTimes[i] = intervals[i][1];
        }
        
        Arrays.sort(startTimes);
        Arrays.sort(endTimes);
              
        int i = 0, j = 0, currentSimultaneous = 0, maxSimultaneous = 0;
        while(i < startTimes.length) {
            if(startTimes[i] < endTimes[j]) {
                currentSimultaneous++;
                maxSimultaneous = Math.max(currentSimultaneous, maxSimultaneous);
                i++;
            }
            else {
                currentSimultaneous--;
                j++;
            }
        }
        return maxSimultaneous;
    }
}
