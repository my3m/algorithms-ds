package io.arrays;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Amazon_PrisonCells {
	public int[] prisonAfterNDays(int[] cells, int N) {
				
		int[] previousDay = copy(cells);
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < cells.length; i++) {
				if (i == 0 || i == cells.length - 1)
					cells[i] = 0;
				else if (previousDay[i - 1] == 1 && previousDay[i + 1] == 1)
					cells[i] = 1;
				else if (previousDay[i - 1] == 0 && previousDay[i + 1] == 0)
					cells[i] = 1;
				else
					cells[i] = 0;
			}
			previousDay = copy(cells);
		}
		return cells;
	}
	
	int[] copy(int[] arr) {
		int[] result = new int[arr.length];
		for(int i = 0; i < arr.length; i++) {
			result[i] = arr[i];
		}
		return result;
	}

	@Test
	public void Test1() {
		int[] cells = new int[] { 0, 1, 0, 1, 1, 0, 0, 1 };
		assertArrayEquals(new int[] { 0, 1, 1, 0, 0, 0, 0, 0 }, prisonAfterNDays(cells, 1));
	}
	
	@Test
	public void Test2() {
		int[] cells = new int[] { 0, 1, 0, 1, 1, 0, 0, 1 };
		assertArrayEquals(new int[] { 0, 0, 0, 0, 1, 1, 1, 0 }, prisonAfterNDays(cells, 2));
	}
	
	@Test
	public void Test3() {
		int[] cells = new int[] { 0, 1, 0, 1, 1, 0, 0, 1 };
		assertArrayEquals(new int[] { 0, 1, 1, 0, 0, 1, 0, 0 }, prisonAfterNDays(cells, 3));
	}
	
	@Test
	public void Test4() {
		int[] cells = new int[] { 0, 1, 0, 1, 1, 0, 0, 1 };
		assertArrayEquals(new int[] { 0, 0, 0, 0, 0, 1, 0, 0 }, prisonAfterNDays(cells, 4));
	}
	
	@Test
	public void Test5() {
		int[] cells = new int[] { 0, 1, 0, 1, 1, 0, 0, 1 };
		assertArrayEquals(new int[] { 0, 1, 1, 1, 0, 1, 0, 0 }, prisonAfterNDays(cells, 5));
	}
	
	@Test
	public void Test6() {
		int[] cells = new int[] { 0, 1, 0, 1, 1, 0, 0, 1 };
		assertArrayEquals(new int[] { 0, 0, 1, 0, 1, 1, 0, 0 }, prisonAfterNDays(cells, 6));
	}
	
	@Test
	public void Test7() {
		int[] cells = new int[] { 0, 1, 0, 1, 1, 0, 0, 1 };
		assertArrayEquals(new int[] { 0, 0, 1, 1, 0, 0, 0, 0 }, prisonAfterNDays(cells, 7));
	}
}
