package io.playground;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class ArraysDS {
	
	public static void rollingHash() {
		Set<Integer> visited  = new HashSet<>();

		String str = "fnjdkbgjdfbgfdkbnfkdnf";

		final int radix = 31;
		final int MOD = 31*31*31;

		int temp = (str.charAt(0)-'a')*radix + str.charAt(1)-'a';

		for(int i=2;i<str.length();i++){
		    temp = (temp*radix + str.charAt(i)-'a') % MOD;

		    if(visited.contains(temp)){
		        System.out.println(str.substring(i-2,i+1));
		        break;
		    } else {
		        visited.add(temp);
		    }
		}
	}
	
	public static void main(String[] args) {
		
		String path = "/a/b/c";
		String[] paths = path.split("\\/");
		
		int[] temp = new int[] { 1,5,5,5,11,16,20,16,11,15,20,22,17 };
		System.out.println(maxProfitStock(temp));
		System.out.println(maxProfitStockSimple(temp));
		System.out.println(maxProfitHelper(new int[] { 3,2,6,5,0,3 }, 0, 0, 2));
		int[] prices = new int[] {397,6621,4997,7506,8918,1662,9187,3278,3890,514,18,9305,93,5508,3031,2692,6019,1134,1691,4949,5071,799,8953,7882,4273,302,6753,4657,8368,3942,1982,5117,563,3332,2623,9482,4994,8163,9112,5236,5029,5483,4542,1474,991,3925,4166,3362,5059,5857,4663,6482,3008,3616,4365,3634,270,1118,8291,4990,1413,273,107,1976,9957,9083,7810,4952,7246,3275,6540,2275,8758,7434,3750,6101,1359,4268,5815,2771,126,478,9253,9486,446,3618,3120,7068,1089,1411,2058,2502,8037,2165,830,7994,1248,4993,9298,4846,8268,2191,3474,3378,9625,7224,9479,985,1492,1646,3756,7970,8476,3009,7457,8922,2980,577,2342,4069,8341,4400,2923,2730,2917,105,724,518,5098,6375,5364,3366,8566,8838,3096,8191,2414,2575,5528,259,573,5636,4581,9049,4998,2038,4323,7978,8968,6665,8399,7309,7417,1322,6391,335,1427,7115,853,2878,9842,2569,2596,4760,7760,5693,9304,6526,8268,4832,6785,5194,6821,1367,4243,1819,9757,4919,6149,8725,7936,4548,2386,5354,2222,8777,2041,1,2245,9246,2879,8439,1815,5476,3200,5927,7521,2504,2454,5789,3688,9239,7335,6861,6958,7931,8680,3068,2850,1181,1793,7138,2081,532,2492,4303,5661,885,657,4258,131,9888,9050,1947,1716,2250,4226,9237,1106,6680,1379,1146,2272,8714,8008,9230,6645,3040,2298,5847,4222,444,2986,2655,7328,1830,6959,9341,2716,3968,9952,2847,3856,9002,1146,5573,1252,5373,1162,8710,2053,2541,9856,677,1256,4216,9908,4253,3609,8558,6453,4183,5354,9439,6838,2682,7621,149,8376,337,4117,8328,9537,4326,7330,683,9899,4934,2408,7413,9996,814,9955,9852,1491,7563,421,7751,1816,4030,2662,8269,8213,8016,4060,5051,7051,1682,5201,5427,8371,5670,3755,7908,9996,7437,4944,9895,2371,7352,3661,2367,4518,3616,8571,6010,1179,5344,113,9347,9374,2775,3969,3939,792,4381,8991,7843,2415,544,3270,787,6214,3377,8695,6211,814,9991,2458,9537,7344,6119,1904,8214,6087,6827,4224,7266,2172,690,2966,7898,3465,3287,1838,609,7668,829,8452,84,7725,8074,871,3939,7803,5918,6502,4969,5910,5313,4506,9606,1432,2762,7820,3872,9590,8397,1138,8114,9087,456,6012,8904,3743,7850,9514,7764,5031,4318,7848,9108,8745,5071,9400,2900,7341,5902,7870,3251,7567,2376,9209,9000,1491,7030,2872,7433,1779,362,5547,7218,7171,7911,2474,914,2114,8340,8678,3497,2659,2878,2606,7756,7949,2006,656,5291,4260,8526,4894,1828,7255,456,7180,8746,3838,6404,6179,5617,3118,8078,9187,289,5989,1661,1204,8103,2,6234,7953,9013,5465,559,6769,9766,2565,7425,1409,3177,2304,6304,5005,9559,6760,2185,4657,598,8589,836,2567,1708,5266,1754,8349,1255,9767,5905,5711,9769,8492,3664,5134,3957,575,1903,3723,3140,5681,5133,6317,4337,7789,7675,3896,4549,6212,8553,1499,1154,5741,418,9214,1007,2172,7563,8614,8291,3469,677,4413,1961,4341,9547,5918,4916,7803,9641,4408,3484,1126,7078,7821,8915,1105,8069,9816,7317,2974,1315,8471,8715,1733,7685,6074,257,5249,4688,8549,5070,5366,2962,7031,6059,8861,9301,7328,6664,5294,8088,6500,6421,1518,4321,5336,2623,8742,1505,9941,1716,2820,4764,6783,906,2450,2857,7515,4051,7546,2416,9121,9264,1730,6152,1675,592,1805,9003,7256,7099,3444,3757,9872,4962,4430,1561,7586,3173,3066,3879,1241,2238,8643,8025,3144,7445,882,7012,1496,4780,9428,617,396,1159,3121,2072,1751,4926,7427,5359,8378,871,5468,8250,5834,9899,9811,9772,9424,2877,3651,7017,5116,8646,5042,4612,6092,2277,1624,7588,3409,1053,8206,3806,8564,7679,2230,6667,8958,6009,2026,7336,6881,3847,5586,9067,98,1750,8839,9522,4627,8842,2891,6095,7488,7934,708,3580,6563,8684,7521,9972,6089,2079,130,4653,9758,2360,1320,8716,8370,9699,6052,1603,3546,7991,670,3644,6093,9509,9518,7072,4703,2409,3168,2191,6695,228,2124,3258,5264,9645,9583,1354,1724,9713,2359,1482,8426,3680,6551,3148,9731,8955,4751,9629,6946,5421,9625,9391,1282,5495,6464,5985,4256,5984,4528,952,6212,6652,562,1476,6297,145,9182,8021,6211,1542,5856,4637,1574,2407,7785,1305,1362,2536,934,4661,4309,559,4052,1943,2406,516,4280,6662,2852,8808,7614,9064,1813,4529,6893,8110,4674,2427,2484,7237,3969,8340,1874,5543,7099,6011,3200,8461,8547,486,9474,9208,7397,9879,7503,9803,6747,1783,6466,9600,6944,432,8664,8757,4961,1909,6867,5988,4337,5703,3225,4658,4043,1452,6554,1142,7463,9754,5956,2363,241,1782,7923,7638,1661,5427,3794,8409,7210,260,8009,4154,692,3025,9263,2006,4935,2483,7994,5624,8186,7571,282,8582,9023,6836,6076,6487,6591,2032,8850,3184,3815,3125,7174,5476,8552,968,3885,2115,7580,8246,2621,4625,1272,1885,6631,6207,4368,4625,8183,2554,8548,8465,1136,7572,1654,7213,411,4597,5597,5613,7781,5764,8738,1307,7593,7291,8628,7830,9406,6208,6077,2027,833,7349,3912,7464,9908,4632,8441,8091,7187,6990,2908,4675,914,4562,8240,1325,9159,190,6938,3292,5954,2028,4600,9899,9319,3228,7730,5077,9436,159,7105,6622,7508,7369,4086,3768,2002,8880,8211,5541,2222,1119,216,3136,5682,4809,813,1193,4999,4103,4486,7305,6131,9086,7205,5451,2314,1287,528,8102,1446,3985,4724,5306,1355,5163,9074,9709,4043,7285,5250,2617,4756,1818,2105,6790,6627,2918,7984,7978,7021,2470,1636,3152,7908,8841,4955,222,6480,5484,4676,7926,5821,9401,3232,7176,916,8658,3237,1311,5943,8487,3928,7051,306,6033,3842,3285,8951,1826,7616,2324,648,9252,5476,8556,4445,6784};
		

		
		System.out.println(maxProfitHelper(prices ,0, 0, 2));
		java.util.Arrays.sort(temp);
		//System.out.println(java.util.Arrays.toString(temp));
		System.out.println(lowerBound(temp, 7));
		
		System.out.println(new StringBuilder("abab").toString().equals("abab"));
		
		int[] check = new int[] { 6,6,6,7,8,8,9,9,12 };
		System.out.println(java.util.Arrays.toString(check));
		System.out.println(countElementsDifferentByAtMostK2(check, 3));
		System.out.println(countElementsDifferentByAtMostK1(check, 3));
		System.out.println(countElementsDifferentByAtMostK(check, 3));		
//		System.out.println(countElementsDifferentByAtMostK(new int[] {
//				6,6,6,7,8,8,9,9,12
//		}, 3));
		//jumpGame2(new int[] {3,2,0,0,2,0,1});
		//System.out.println(count1s(11110011));
		//rotate(check, 2);
		//System.out.println(Arrays.toString(check));
		trap(new int[] { 3,2,1,0,3 });
		//10,8,3,2,4,12
		//10,8,4,12
		longestMountain(new int[] { 0,1,2,3,4,5,6,7,8,9 });
		
		Long.parseLong("3456237490");
	}	
	
	public int[] findAlmostSortedArray() {
		int[]arr = new int[] { 1,2,3,99,4,2,2,3,5 };
		
		//1,2,3,99,4,2,2,3,5
		int r1 = -1;
		for(int i = arr.length - 2; i > -1; i--) {
			if(arr[i] > arr[i+1]) {
				r1 = i;
			}
		}
		
		if(r1 != -1) {
			int r0 = r1;
			while(r0 > -1 && arr[r0] > arr[r1+1]) {
				r0--;
			}
		}
		
		
		int l0 = -1;
		for(int i = 0; i < arr.length - 1; i++) {
			if(arr[i] < arr[i-1]) {
				l0 = i;
				break;
			}
		}
		
		if(l0 != -1) {
			int l1 = l0;
			while(l1 < arr.length - 1) {
				
			}
		}
		return new int[2];
	}
	
    public static int trap(int[] height) {
        if (height == null || height.length < 2) return 0;
        
        Stack<Integer> stack = new Stack<>();
        int water = 0, i = 0;
        while (i < height.length) {
            if (stack.isEmpty() || height[i] <= height[stack.peek()]) {
                stack.push(i++);
            } else {
                int pre = stack.pop();
                if (!stack.isEmpty()) {
                    // find the smaller height between the two sides
                    int minHeight = Math.min(height[stack.peek()], height[i]);
                    // calculate the area
                    water += (minHeight - height[pre]) * (i - stack.peek() - 1);
                }
            }
        }
        return water;
    }	
	
	//[1,2,3,4,5,4,3,2,1]
	//[8,7,6,5,4,3,2,1]
	//[1,2,3,4,5]
	//[1,2,3,3,2,2,1,3,4,6,8,9,12,4,2]
	public static int longestMountain(int[] A) {
        //[1,2,3,4,5,3,2,1]
        //[6,5,4,3,1]
        //[1,2,3,4,5]
        //[1,1,1,2,2,2,1,4,5,7,9,5,4,1]
        //     ^       ^
        
        int left = 0, max = 0;
        while(left < A.length - 2) {
            while(left+1 < A.length && A[left+1] <= A[left]) {
                left++;
            }
            int right = left;
            //lets move up the mountain
            //but what if, the mountain breaks?
            //1,2,4,4           
            while(right+1 < A.length && A[right+1] > A[right]) {
                right++;
            }
            if(right+1 < A.length && A[right+1] < A[right]) {
                //1,2,3,4,3
                while(right+1 < A.length && A[right+1] < A[right]) {
                    right++;
                }
                int len = right - left + 1;
                max = Math.max(max, len);
                left = right;
                //1,2,1
            }
            else {
                left = right + 1;
            }
        }
        return max;
    }	
	
	static void rotate(int[] arr, int k) {

		/**
		 * O(n)
			int[] copy = Arrays.copyOf(arr, arr.length);
			for(int i = 0; i < arr.length; i++) {
				//what does 0 map to?
				arr[(i+k)%arr.length] = copy[i];
			}
		**/
		
		
		/** O(1) space
		
			1,2,3,4,5, k=8
		i  =0,1,2,3,4
			
			0->3->1->4->2->0	(how to prevent cycle??)
			
			int start = i; (0)
			int previous = nums[start];
			int next = (i + k) % arr.length;
			int prev = -1;
			
			while(prev != start) {
				int temp = nums[next];
				nums[next] = previous;
				previous = temp;
				next = (next + k) % arr.length;
				prev = next;
			}
			
		*/
		
		
		int count = 0;
		for (int i = 0; count < arr.length; i++) {
			int start = i;
			int previous = arr[start];
			int next = (i + k) % arr.length;
			int prev = -1;

			while (prev != start) {
				int temp = arr[next];
				arr[next] = previous;
				previous = temp;
				next = (next + k) % arr.length;
				prev = next;
				count++;
			}
		}
	}
	
	static int count1s(int num) {
		int count = 0;
		while(num != 0) {
		 count += num % 10 == 1 ? 1 : 0;
		 num /= 10;
		}
		return count;		
	}
	
	static int jumpGame2(int[] nums) {
		int n = nums.length;
		if (n < 2)
			return 0;

		// max position one could reach
		// starting from index <= i
		int maxPos = nums[0];
		// max number of steps one could do
		// inside this jump
		int maxSteps = nums[0];

		int jumps = 1;
		for (int i = 1; i < n; ++i) {
			// if to reach this point
			// one needs one more jump
			if (maxSteps < i) {
				++jumps;
				maxSteps = maxPos;
			}
			maxPos = Math.max(maxPos, nums[i] + i);
		}
		return jumps;	
	}
	
	
	/**
		[1, 5, 5, 5, 11, 11, 15, 16, 16, 17, 20, 20, 22]
		l = 0, r = 12, m=6, arr[m] = 15, x = 7
		l = 0, r = 6, m=3, arr[m] = 5, x = 7
		l=4, r = 6, m=5, arr[m] = 11, x = 7
		l=4, r=5, m=4, arr[m] = 11, x = 7
	*/
	static int lowerBound(int[] arr, int x) {
		if(x < arr[0])
			return 0;
		else if(x > arr[arr.length - 1])
			return arr.length;
		int l = 0, r = arr.length - 1;
		while(l < r) {
			int mid = l + (r-l)/2;	
			if(x <= arr[mid]) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}
		return l;
	}
	
	static int countElementsDifferentByAtMostK2(int[] arr, int k) {
		/**
			for i:
			   while j < N and arr[j] - arr[i] <= K: j++
			   answer += j- i - 1 
		 */
		//6,6,6,7,8,8,9,9,12
		//
		Arrays.sort(arr);
		int first = 0, cnt = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] - arr[first] > k) {
				while(first < arr.length && arr[i] - arr[first] > k) {
					first++;
				}
			}
			cnt += i - first;
		}
		return cnt;
	}
		
	static int countElementsDifferentByAtMostK1(int[] arr, int k) {
		java.util.Arrays.sort(arr);
		int count = 0;
		for(int i = 0; i < arr.length; i++) {
			for(int j = i + 1; j < arr.length; j++) {
				if(arr[j] > arr[i] + k)
					break;
				count++;
			}
		}
		return count;
	}
	
	static int countElementsDifferentByAtMostK(int[] arr, int k) {
		//k=3
		//6,6,6,7,8,8,9,9,12
		Arrays.sort(arr);
		LinkedList<Integer> s1 = new LinkedList<>();
		int count = 0;
		for(int i = 0; i < arr.length; i++) {
			while(s1.size() > 0 && arr[i] - s1.getFirst() > k)
				s1.pollFirst();
			count += s1.size();
			s1.add(arr[i]);
		}
		return count;
	}
    
	static Map<Integer, Integer> map = new HashMap<>();
    static int maxProfitHelper(int[] prices, int idx, int sum, int left) {
    	if(map.containsKey(idx))
    		return map.get(idx);
        if(idx == prices.length || left == 0)
            return 0;
        //Bug:resetting max to 0, it should be set to sum
        int max = 0;
        //Bug: asummed, oyu only had the option to buy the next day
        for(int i = idx; i < prices.length-1; i++) {
        	/***irrelevant as you are already checking for prices[j] - prices[i] > 0 ***/
        	//if((i == 0 || prices[i] < prices[i-1]) && prices[i+1] >= prices[i]) {
	        	for(int j = i + 1; j < prices.length; j++) {
		            if(prices[j] - prices[i] > 0) {
//		            max = Math.max(max,
//		               maxProfitHelper(prices, j + 1, sum + prices[j] - prices[i], left - 1));
		            	int profit = prices[j] - prices[i] +maxProfitHelper(prices, j + 1, 0, left-1);
		            	max = Math.max(profit, max);
		            }
		            //break;
	        	}
        	//}
        }
    	map.putIfAbsent(idx, max);
        return max;
    }	

	static int maxProfitStockSimple(int[] temp) {
		int profit = 0;
		for (int i = 0; i < temp.length -1; i++) {
			// [5,5,5,20,11,16,20]
			if (temp[i] < temp[i + 1])
				profit += temp[i + 1] - temp[i];
		}
		return profit;
	}

	static int maxProfitStock(int[] temp) {
		int profit = 0;
		for (int i = 0; i < temp.length - 1; i++) {
			/** Valley **/
			if ((i == 0 || temp[i - 1] > temp[i]) && temp[i + 1] >= temp[i]) {
				int j = i + 1;
				while (j < temp.length - 1 && temp[j + 1] >= temp[j]) {
					j++;
				}
				profit += temp[j] - temp[i];
				i = j;
			}
		}
		return profit;
	}
}
