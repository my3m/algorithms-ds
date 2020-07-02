package com.interviewcake.sort;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class MergingMeetingTimes {
	public class Meeting {
		public int startTime;
		public int endTime;
		
		public Meeting(int startTime, int endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj == this)
				return true;
			if(!(obj instanceof Meeting))
				return false;
			Meeting m2 = (Meeting)obj;
			return this.startTime == m2.startTime && this.endTime == m2.endTime;
		}
	}
	
	public List<Meeting> mergeRanges(List<Meeting> meetings) {
        Collections.sort(meetings, (a,b)-> a.startTime == b.startTime ?
                a.endTime - b.endTime : a.startTime - b.startTime);
        	if(meetings.size() == 1)
        		return meetings;
            //[1,3],[2,10]
            //[1,3],[2,4]
            List<Meeting> merged = new ArrayList<>();
            merged.add(meetings.get(0));
            for(int i = 1; i < meetings.size(); i++) {
            	Meeting last = merged.get(merged.size() - 1);
            	if(last.endTime > meetings.get(i).startTime) {
            		last.endTime = Math.max(last.endTime, meetings.get(i).endTime);
            	}
            	else {
            		merged.add(meetings.get(i));
            	}
            }
            return merged;
	}
	
	@Test
	public void TestHappyCase() {
		List<Meeting> meet = Arrays.asList(new Meeting(1,3), new Meeting(2,10), new Meeting(12,15));
		List<Meeting> expected = Arrays.asList(new Meeting(1,10), new Meeting(12,15));
		assertEquals(expected, mergeRanges(meet));		
	}
	
	@Test
	public void TestHappyCase2() {
		List<Meeting> meet = Arrays.asList(new Meeting(1,12), new Meeting(12,15));
		List<Meeting> expected = Arrays.asList(new Meeting(1,12), new Meeting(12,15));
		assertEquals(expected, mergeRanges(meet));		
	}	
	
	@Test
	public void TestHappyCase3() {
		List<Meeting> meet = Arrays.asList(new Meeting(1,12));
		List<Meeting> expected = Arrays.asList(new Meeting(1,12));
		assertEquals(expected, mergeRanges(meet));		
	}		
	
	@Test
	public void TestHappyCase4() {
		List<Meeting> meet = Arrays.asList(new Meeting(1,10), new Meeting(2,6), new Meeting(3,5), new Meeting(7, 9));
		List<Meeting> expected = Arrays.asList(new Meeting(1,10));
		assertEquals(expected, mergeRanges(meet));		
	}
	
	@Test
	public void TestUnsortedMeetings() {
		List<Meeting> meet = Arrays.asList(new Meeting(3,7), new Meeting(1,4), new Meeting(11, 14), new Meeting(10,12));
		List<Meeting> expected = Arrays.asList(new Meeting(1,7), new Meeting(10,14));
		assertEquals(expected, mergeRanges(meet));		
	}		
	
	@Test
	public void TestMultipleMerge() {
		List<Meeting> meet = Arrays.asList(new Meeting(3,7), new Meeting(1,4), new Meeting(6, 14), new Meeting(10,12));
		List<Meeting> expected = Arrays.asList(new Meeting(1,14));
		assertEquals(expected, mergeRanges(meet));		
	}		
}
