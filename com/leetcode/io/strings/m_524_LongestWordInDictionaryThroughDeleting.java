package io.strings;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
/*
 * Improvement
 * For example, comparing S="azzzzzzzzzzzzzb" and word="ab",
 *  we only need to know the index of the next 'b' for current 'a',
 *  which will avoid interating each 'z' of S in the middle.
	O(26* len(s) + n* len(word)), and O(26* len(s)) space
 */
public class m_524_LongestWordInDictionaryThroughDeleting {
	public String findLongestWord(String s, List<String> dictionary) {
		HashMap<Character, List<Integer>> charmap = new HashMap<Character, List<Integer>>();
		for(int i=0; i<s.length();i++) {
			if(!charmap.containsKey(s.charAt(i)))
				charmap.put(s.charAt(i), new ArrayList<Integer>());
			charmap.get(s.charAt(i)).add(i);
		}
		String result = "";
		for(String word : dictionary) {
			//if(isSubsequence(word, s)) {
			if(isSubsequencePreProcessed(word, s, charmap)) {
				if(word.length() > result.length()) {
					result = word;
				}
				else if(word.length() == result.length()) {
					result = findSmallestLexicograpicalOrder(word, result);
				}
			}
		}
		return result;
	}

	private String findSmallestLexicograpicalOrder(String s1, String s2) {
		if(s1.length() == 0)
			return s1;
		else if(s2.length() == 0)
			return s2;
		int i = 0;
		int j = 0;
		//abch
		//abcw
		//10, 2
		while(i < s1.length() && j < s2.length() && s1.charAt(i) == s2.charAt(j)) {
			i++;
			j++;
		}
		//#Bug;If strings are equals, charAt will produce index out of bounds
		if(i < s1.length() && j < s2.length()) {
			if(s1.charAt(i) < s2.charAt(j))
				return s1;
			else
				return s2;
		}
		else {
			return s1;
		}
	}


	private boolean isSubsequencePreProcessed(String w, String s, HashMap<Character, List<Integer>> charmap) {
        if(w.length() > s.length())
            return false;
	/*  apple
	 * 
	 *  abpcplea	
		p=[2, 4]
		a=[0, 7]
		b=[1]
		c=[3]
		e=[6]
		l=[5]
        testest
	 */
	int i = -1;
    int j = 0;

    //e, i=1, j=1
    //w, i=2, j=2
    //a, i=4, j=3
    //f, i=5, j=4
    //"aazzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzbsf"
    //"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    //W
    int place = -1; 
    while(j < w.length() && charmap.containsKey(w.charAt(j))) {
        List<Integer> idxs = charmap.get(w.charAt(j));
        int idx = binarySearch(idxs, 0, idxs.size() - 1, place);
        if(idx != -1) {
            place = idx;
            j++;
        }
        else
            return false;
    }
    return j == w.length();
}	

//32
//0,4,10,12,22,27,30,32,33,40,42,44
//find smallest integer such that X > target
public int binarySearch(List<Integer> idxs, int left, int right, int target) {
	if(idxs.size() ==0)
		return -1;
    if(left > right)
        return -1;
    if(idxs.get(0) > target)
        return idxs.get(0);
    //[1,1,1,1,1,1,1,3]
    int mid = left + ((right-left)/2);
    int min = idxs.get(idxs.size() - 1) > target ? idxs.get(idxs.size() - 1) : -1;
    while(left <= right) {
        mid = left + ((right-left)/2);
        if(idxs.get(mid) < target) {
            left = mid + 1;
        }
        else if(idxs.get(mid) > target) {
        	if((idxs.get(mid) - target) < (min - target)) {
        		min = idxs.get(mid);
        	}
            right = mid - 1;
        }
        else {
        	left = mid + 1;
        }
    }
    return min;
}
	
	private boolean isSubsequence(String w, String s) {
		if(w.length() > s.length())
			return false;
		int i = 0;
		int j = 0;
		//abcm, laaffasfasabcmasfasf
		while(i < w.length() && j < s.length()) {
			if(w.charAt(i) == s.charAt(j))
				i++;
			j++;
		}
		return i == w.length();
	}
	
//	@Test
//	public void Test1() {
//		assertEquals("apple", findLongestWord("abpcplea", Arrays.asList("ale","apple","monkey","plea")));
//	}
//	@Test
//	public void Test2() {
//		assertEquals("kangaroo", findLongestWord("iwfajfkafafnafgaafafafrfafoffao", Arrays.asList("ale","apple","monkey","plea", "kangaroo")));
//	}
//	@Test
//	public void Test3() {
//		assertEquals("fiaoibnpihgzqwnbdlhyovhatvyqinhjukbzmxphgkivolzqrnfmiaymrkjwmmeunhlodflsoltkwrjhugeyqwknaguerhkonjnwcndmunhduxmxwlmbkxehiukhzcjlqhhlewqbnedonniuexxgnsyxuumgedlvfgaicahaulecuoxatlusmpnsbziwekhnhtdxdauwwrnijzswnabkkktwlsepbshvjysdliwujzvjqvmtpsjgwatvuhjvkzgerxaemvosdrssgandvejxlqgvamvrauknovpsfypgdqydkqacdjyhadcpnfjhzkrulahnhlwpxviodgftsjnnisvyxmkqwlabkgswgujlmmyrpxhqepyewtjidoprkgshdnebxchhfnsuuqtfgaylyfwblujffbnowsaulkagrjjkarsddrzqrebkggwxvucuiefsxvharplzgabglywnicojtgahfhqcpaacbogvxkuunhvrblfcfhgfqzlhxtzlrkbsgtkaoqwvsafeygsracpscvjfmbnlvhlzoxyyrtzuftecwibrphjvpdbylhueeohxmhkiohzcdlcxgbmliovabwduwbunfdwapwntecugkwgbdyomrhjfcilpbyykapposmpgmxflahwswtdohzsvvwrobuxsdrxwaxoyydbwanuzzfbbrqlaujpvhmzafkxtxnidlubomqlgqagjtvveynuorapklrcmbaoxtcoktsopsjvwxjmnncupcukzqqbvvrskegygpneevfuzxxsnracmvcmutefpeebjzbgvnlrmgwunmlpsvlfbvajcoukwgzfbapwchbxofxahcwvyjcnoutvscpyzatdedeiggogirrvoswhximanhepqpwtigrhxisrwmylqskhffeduhfyeyxpbgvbjfxblowozsbjmpxtejqrxmhttnioovpbifcemsfgcxpofbabjtqbmnpzkzsoeoigrxbin", findLongestWord("fiaoibnpihgzqwnbdlhyovhatvyqinhjukbzmxphgkivolzqrnfmiaymrkjwmmeunhlodflsoltkwrjhugeyqwknaguerhkonjnwcndmunhduxmxwlmbkxehiukhzcjlqhhlewqbnedonniuexxgnsyxuumgedlvfgaicahaulecuoxatlusmpnsbziwekhnhtdxdauwwrnijzswnabkkktwlsepbshvjysdliwujzvjqvmtpsjgwatvuhjvkzgerxaemvosdrssgandvejxlqgvamvrauknovpsfypgdqydkqacdjyhadcpnfjhzkrulahnhlwpxviodgftsjnnisvyxmkqwlabkgswgujlmmyrpxhqepyewtjidoprkgshdnebxchhfnsuuqtfgaylyfwblujffbnowsaulkagrjjkarsddrzqrebkggwxvucuiefsxvharplzgabglywnicojtgahfhqcpaacbogvxkuunhvrblfcfhgfqzlhxtzlrkbsgtkaoqwvsafeygsracpscvjfmbnlvhlzoxyyrtzuftecwibrphjvpdbylhueeohxmhkiohzcdlcxgbmliovabwduwbunfdwapwntecugkwgbdyomrhjfcilpbyykapposmpgmxflahwswtdohzsvvwrobuxsdrxwaxoyydbwanuzzfbbrqlaujpvhmzafkxtxnidlubomqlgqagjtvveynuorapklrcmbaoxtcoktsopsjvwxjmnncupcukzqqbvvrskegygpneevfuzxxsnracmvcmutefpeebjzbgvnlrmgwunmlpsvlfbvajcoukwgzfbapwchbxofxahcwvyjcnoutvscpyzatdedeiggogirrvoswhximanhepqpwtigrhxisrwmylqskhffeduhfyeyxpbgvbjfxblowozsbjmpxtejqrxmhttnioovpbifcemsfgcxpofbabjtqbmnpzkzsoeoigrxbin",
//				Arrays.asList("fiaoibnpihgzqwnbdlhyovhatvyqinhjukbzmxphgkivolzqrnfmiaymrkjwmmeunhlodflsoltkwrjhugeyqwknaguerhkonjnwcndmunhduxmxwlmbkxehiukhzcjlqhhlewqbnedonniuexxgnsyxuumgedlvfgaicahaulecuoxatlusmpnsbziwekhnhtdxdauwwrnijzswnabkkktwlsepbshvjysdliwujzvjqvmtpsjgwatvuhjvkzgerxaemvosdrssgandvejxlqgvamvrauknovpsfypgdqydkqacdjyhadcpnfjhzkrulahnhlwpxviodgftsjnnisvyxmkqwlabkgswgujlmmyrpxhqepyewtjidoprkgshdnebxchhfnsuuqtfgaylyfwblujffbnowsaulkagrjjkarsddrzqrebkggwxvucuiefsxvharplzgabglywnicojtgahfhqcpaacbogvxkuunhvrblfcfhgfqzlhxtzlrkbsgtkaoqwvsafeygsracpscvjfmbnlvhlzoxyyrtzuftecwibrphjvpdbylhueeohxmhkiohzcdlcxgbmliovabwduwbunfdwapwntecugkwgbdyomrhjfcilpbyykapposmpgmxflahwswtdohzsvvwrobuxsdrxwaxoyydbwanuzzfbbrqlaujpvhmzafkxtxnidlubomqlgqagjtvveynuorapklrcmbaoxtcoktsopsjvwxjmnncupcukzqqbvvrskegygpneevfuzxxsnracmvcmutefpeebjzbgvnlrmgwunmlpsvlfbvajcoukwgzfbapwchbxofxahcwvyjcnoutvscpyzatdedeiggogirrvoswhximanhepqpwtigrhxisrwmylqskhffeduhfyeyxpbgvbjfxblowozsbjmpxtejqrxmhttnioovpbifcemsfgcxpofbabjtqbmnpzkzsoeoigrxbin","fiaoibnpihgzqwnbdlhyovhatvyqinhjukbzmxphgkivolzqrnfmiaymrkjwmmeunhlodflsoltkwrjhugeyqwknaguerhkonjnwcndmunhduxmxwlmbkxehiukhzcjlqhhlewqbnedonniuexxgnsyxuumgedlvfgaicahaulecuoxatlusmpnsbziwekhnhtdxdauwwrnijzswnabkkktwlsepbshvjysdliwujzvjqvmtpsjgwatvuhjvkzgerxaemvosdrssgandvejxlqgvamvrauknovpsfypgdqydkqacdjyhadcpnfjhzkrulahnhlwpxviodgftsjnnisvyxmkqwlabkgswgujlmmyrpxhqepyewtjidoprkgshdnebxchhfnsuuqtfgaylyfwblujffbnowsaulkagrjjkarsddrzqrebkggwxvucuiefsxvharplzgabglywnicojtgahfhqcpaacbogvxkuunhvrblfcfhgfqzlhxtzlrkbsgtkaoqwvsafeygsracpscvjfmbnlvhlzoxyyrtzuftecwibrphjvpdbylhueeohxmhkiohzcdlcxgbmliovabwduwbunfdwapwntecugkwgbdyomrhjfcilpbyykapposmpgmxflahwswtdohzsvvwrobuxsdrxwaxoyydbwanuzzfbbrqlaujpvhmzafkxtxnidlubomqlgqagjtvveynuorapklrcmbaoxtcoktsopsjvwxjmnncupcukzqqbvvrskegygpneevfuzxxsnracmvcmutefpeebjzbgvnlrmgwunmlpsvlfbvajcoukwgzfbapwchbxofxahcwvyjcnoutvscpyzatdedeiggogirrvoswhximanhepqpwtigrhxisrwmylqskhffeduhfyeyxpbgvbjfxblowozsbjmpxtejqrxmhttnioovpbifcemsfgcxpofbabjtqbmnpzkzsoeoigrxbin","fiaoibnpihgzqwnbdlhyovhatvyqinhjukbzmxphgkivolzqrnfmiaymrkjwmmeunhlodflsoltkwrjhugeyqwknaguerhkonjnwcndmunhduxmxwlmbkxehiukhzcjlqhhlewqbnedonniuexxgnsyxuumgedlvfgaicahaulecuoxatlusmpnsbziwekhnhtdxdauwwrnijzswnabkkktwlsepbshvjysdliwujzvjqvmtpsjgwatvuhjvkzgerxaemvosdrssgandvejxlqgvamvrauknovpsfypgdqydkqacdjyhadcpnfjhzkrulahnhlwpxviodgftsjnnisvyxmkqwlabkgswgujlmmyrpxhqepyewtjidoprkgshdnebxchhfnsuuqtfgaylyfwblujffbnowsaulkagrjjkarsddrzqrebkggwxvucuiefsxvharplzgabglywnicojtgahfhqcpaacbogvxkuunhvrblfcfhgfqzlhxtzlrkbsgtkaoqwvsafeygsracpscvjfmbnlvhlzoxyyrtzuftecwibrphjvpdbylhueeohxmhkiohzcdlcxgbmliovabwduwbunfdwapwntecugkwgbdyomrhjfcilpbyykapposmpgmxflahwswtdohzsvvwrobuxsdrxwaxoyydbwanuzzfbbrqlaujpvhmzafkxtxnidlubomqlgqagjtvveynuorapklrcmbaoxtcoktsopsjvwxjmnncupcukzqqbvvrskegygpneevfuzxxsnracmvcmutefpeebjzbgvnlrmgwunmlpsvlfbvajcoukwgzfbapwchbxofxahcwvyjcnoutvscpyzatdedeiggogirrvoswhximanhepqpwtigrhxisrwmylqskhffeduhfyeyxpbgvbjfxblowozsbjmpxtejqrxmhttnioovpbifcemsfgcxpofbabjtqbmnpzkzsoeoigrxbin","fiaoibnpihgzqwnbdlhyovhatvyqinhjukbzmxphgkivolzqrnfmiaymrkjwmmeunhlodflsoltkwrjhugeyqwknaguerhkonjnwcndmunhduxmxwlmbkxehiukhzcjlqhhlewqbnedonniuexxgnsyxuumgedlvfgaicahaulecuoxatlusmpnsbziwekhnhtdxdauwwrnijzswnabkkktwlsepbshvjysdliwujzvjqvmtpsjgwatvuhjvkzgerxaemvosdrssgandvejxlqgvamvrauknovpsfypgdqydkqacdjyhadcpnfjhzkrulahnhlwpxviodgftsjnnisvyxmkqwlabkgswgujlmmyrpxhqepyewtjidoprkgshdnebxchhfnsuuqtfgaylyfwblujffbnowsaulkagrjjkarsddrzqrebkggwxvucuiefsxvharplzgabglywnicojtgahfhqcpaacbogvxkuunhvrblfcfhgfqzlhxtzlrkbsgtkaoqwvsafeygsracpscvjfmbnlvhlzoxyyrtzuftecwibrphjvpdbylhueeohxmhkiohzcdlcxgbmliovabwduwbunfdwapwntecugkwgbdyomrhjfcilpbyykapposmpgmxflahwswtdohzsvvwrobuxsdrxwaxoyydbwanuzzfbbrqlaujpvhmzafkxtxnidlubomqlgqagjtvveynuorapklrcmbaoxtcoktsopsjvwxjmnncupcukzqqbvvrskegygpneevfuzxxsnracmvcmutefpeebjzbgvnlrmgwunmlpsvlfbvajcoukwgzfbapwchbxofxahcwvyjcnoutvscpyzatdedeiggogirrvoswhximanhepqpwtigrhxisrwmylqskhffeduhfyeyxpbgvbjfxblowozsbjmpxtejqrxmhttnioovpbifcemsfgcxpofbabjtqbmnpzkzsoeoigrxbin","fiaoibnpihgzqwnbdlhyovhatvyqinhjukbzmxphgkivolzqrnfmiaymrkjwmmeunhlodflsoltkwrjhugeyqwknaguerhkonjnwcndmunhduxmxwlmbkxehiukhzcjlqhhlewqbnedonniuexxgnsyxuumgedlvfgaicahaulecuoxatlusmpnsbziwekhnhtdxdauwwrnijzswnabkkktwlsepbshvjysdliwujzvjqvmtpsjgwatvuhjvkzgerxaemvosdrssgandvejxlqgvamvrauknovpsfypgdqydkqacdjyhadcpnfjhzkrulahnhlwpxviodgftsjnnisvyxmkqwlabkgswgujlmmyrpxhqepyewtjidoprkgshdnebxchhfnsuuqtfgaylyfwblujffbnowsaulkagrjjkarsddrzqrebkggwxvucuiefsxvharplzgabglywnicojtgahfhqcpaacbogvxkuunhvrblfcfhgfqzlhxtzlrkbsgtkaoqwvsafeygsracpscvjfmbnlvhlzoxyyrtzuftecwibrphjvpdbylhueeohxmhkiohzcdlcxgbmliovabwduwbunfdwapwntecugkwgbdyomrhjfcilpbyykapposmpgmxflahwswtdohzsvvwrobuxsdrxwaxoyydbwanuzzfbbrqlaujpvhmzafkxtxnidlubomqlgqagjtvveynuorapklrcmbaoxtcoktsopsjvwxjmnncupcukzqqbvvrskegygpneevfuzxxsnracmvcmutefpeebjzbgvnlrmgwunmlpsvlfbvajcoukwgzfbapwchbxofxahcwvyjcnoutvscpyzatdedeiggogirrvoswhximanhepqpwtigrhxisrwmylqskhffeduhfyeyxpbgvbjfxblowozsbjmpxtejqrxmhttnioovpbifcemsfgcxpofbabjtqbmnpzkzsoeoigrxbin","fiaoibnpihgzqwnbdlhyovhatvyqinhjukbzmxphgkivolzqrnfmiaymrkjwmmeunhlodflsoltkwrjhugeyqwknaguerhkonjnwcndmunhduxmxwlmbkxehiukhzcjlqhhlewqbnedonniuexxgnsyxuumgedlvfgaicahaulecuoxatlusmpnsbziwekhnhtdxdauwwrnijzswnabkkktwlsepbshvjysdliwujzvjqvmtpsjgwatvuhjvkzgerxaemvosdrssgandvejxlqgvamvrauknovpsfypgdqydkqacdjyhadcpnfjhzkrulahnhlwpxviodgftsjnnisvyxmkqwlabkgswgujlmmyrpxhqepyewtjidoprkgshdnebxchhfnsuuqtfgaylyfwblujffbnowsaulkagrjjkarsddrzqrebkggwxvucuiefsxvharplzgabglywnicojtgahfhqcpaacbogvxkuunhvrblfcfhgfqzlhxtzlrkbsgtkaoqwvsafeygsracpscvjfmbnlvhlzoxyyrtzuftecwibrphjvpdbylhueeohxmhkiohzcdlcxgbmliovabwduwbunfdwapwntecugkwgbdyomrhjfcilpbyykapposmpgmxflahwswtdohzsvvwrobuxsdrxwaxoyydbwanuzzfbbrqlaujpvhmzafkxtxnidlubomqlgqagjtvveynuorapklrcmbaoxtcoktsopsjvwxjmnncupcukzqqbvvrskegygpneevfuzxxsnracmvcmutefpeebjzbgvnlrmgwunmlpsvlfbvajcoukwgzfbapwchbxofxahcwvyjcnoutvscpyzatdedeiggogirrvoswhximanhepqpwtigrhxisrwmylqskhffeduhfyeyxpbgvbjfxblowozsbjmpxtejqrxmhttnioovpbifcemsfgcxpofbabjtqbmnpzkzsoeoigrxbin","fiaoibnpihgzqwnbdlhyovhatvyqinhjukbzmxphgkivolzqrnfmiaymrkjwmmeunhlodflsoltkwrjhugeyqwknaguerhkonjnwcndmunhduxmxwlmbkxehiukhzcjlqhhlewqbnedonniuexxgnsyxuumgedlvfgaicahaulecuoxatlusmpnsbziwekhnhtdxdauwwrnijzswnabkkktwlsepbshvjysdliwujzvjqvmtpsjgwatvuhjvkzgerxaemvosdrssgandvejxlqgvamvrauknovpsfypgdqydkqacdjyhadcpnfjhzkrulahnhlwpxviodgftsjnnisvyxmkqwlabkgswgujlmmyrpxhqepyewtjidoprkgshdnebxchhfnsuuqtfgaylyfwblujffbnowsaulkagrjjkarsddrzqrebkggwxvucuiefsxvharplzgabglywnicojtgahfhqcpaacbogvxkuunhvrblfcfhgfqzlhxtzlrkbsgtkaoqwvsafeygsracpscvjfmbnlvhlzoxyyrtzuftecwibrphjvpdbylhueeohxmhkiohzcdlcxgbmliovabwduwbunfdwapwntecugkwgbdyomrhjfcilpbyykapposmpgmxflahwswtdohzsvvwrobuxsdrxwaxoyydbwanuzzfbbrqlaujpvhmzafkxtxnidlubomqlgqagjtvveynuorapklrcmbaoxtcoktsopsjvwxjmnncupcukzqqbvvrskegygpneevfuzxxsnracmvcmutefpeebjzbgvnlrmgwunmlpsvlfbvajcoukwgzfbapwchbxofxahcwvyjcnoutvscpyzatdedeiggogirrvoswhximanhepqpwtigrhxisrwmylqskhffeduhfyeyxpbgvbjfxblowozsbjmpxtejqrxmhttnioovpbifcemsfgcxpofbabjtqbmnpzkzsoeoigrxbin","fiaoibnpihgzqwnbdlhyovhatvyqinhjukbzmxphgkivolzqrnfmiaymrkjwmmeunhlodflsoltkwrjhugeyqwknaguerhkonjnwcndmunhduxmxwlmbkxehiukhzcjlqhhlewqbnedonniuexxgnsyxuumgedlvfgaicahaulecuoxatlusmpnsbziwekhnhtdxdauwwrnijzswnabkkktwlsepbshvjysdliwujzvjqvmtpsjgwatvuhjvkzgerxaemvosdrssgandvejxlqgvamvrauknovpsfypgdqydkqacdjyhadcpnfjhzkrulahnhlwpxviodgftsjnnisvyxmkqwlabkgswgujlmmyrpxhqepyewtjidoprkgshdnebxchhfnsuuqtfgaylyfwblujffbnowsaulkagrjjkarsddrzqrebkggwxvucuiefsxvharplzgabglywnicojtgahfhqcpaacbogvxkuunhvrblfcfhgfqzlhxtzlrkbsgtkaoqwvsafeygsracpscvjfmbnlvhlzoxyyrtzuftecwibrphjvpdbylhueeohxmhkiohzcdlcxgbmliovabwduwbunfdwapwntecugkwgbdyomrhjfcilpbyykapposmpgmxflahwswtdohzsvvwrobuxsdrxwaxoyydbwanuzzfbbrqlaujpvhmzafkxtxnidlubomqlgqagjtvveynuorapklrcmbaoxtcoktsopsjvwxjmnncupcukzqqbvvrskegygpneevfuzxxsnracmvcmutefpeebjzbgvnlrmgwunmlpsvlfbvajcoukwgzfbapwchbxofxahcwvyjcnoutvscpyzatdedeiggogirrvoswhximanhepqpwtigrhxisrwmylqskhffeduhfyeyxpbgvbjfxblowozsbjmpxtejqrxmhttnioovpbifcemsfgcxpofbabjtqbmnpzkzsoeoigrxbin","fiaoibnpihgzqwnbdlhyovhatvyqinhjukbzmxphgkivolzqrnfmiaymrkjwmmeunhlodflsoltkwrjhugeyqwknaguerhkonjnwcndmunhduxmxwlmbkxehiukhzcjlqhhlewqbnedonniuexxgnsyxuumgedlvfgaicahaulecuoxatlusmpnsbziwekhnhtdxdauwwrnijzswnabkkktwlsepbshvjysdliwujzvjqvmtpsjgwatvuhjvkzgerxaemvosdrssgandvejxlqgvamvrauknovpsfypgdqydkqacdjyhadcpnfjhzkrulahnhlwpxviodgftsjnnisvyxmkqwlabkgswgujlmmyrpxhqepyewtjidoprkgshdnebxchhfnsuuqtfgaylyfwblujffbnowsaulkagrjjkarsddrzqrebkggwxvucuiefsxvharplzgabglywnicojtgahfhqcpaacbogvxkuunhvrblfcfhgfqzlhxtzlrkbsgtkaoqwvsafeygsracpscvjfmbnlvhlzoxyyrtzuftecwibrphjvpdbylhueeohxmhkiohzcdlcxgbmliovabwduwbunfdwapwntecugkwgbdyomrhjfcilpbyykapposmpgmxflahwswtdohzsvvwrobuxsdrxwaxoyydbwanuzzfbbrqlaujpvhmzafkxtxnidlubomqlgqagjtvveynuorapklrcmbaoxtcoktsopsjvwxjmnncupcukzqqbvvrskegygpneevfuzxxsnracmvcmutefpeebjzbgvnlrmgwunmlpsvlfbvajcoukwgzfbapwchbxofxahcwvyjcnoutvscpyzatdedeiggogirrvoswhximanhepqpwtigrhxisrwmylqskhffeduhfyeyxpbgvbjfxblowozsbjmpxtejqrxmhttnioovpbifcemsfgcxpofbabjtqbmnpzkzsoeoigrxbin","fiaoibnpihgzqwnbdlhyovhatvyqinhjukbzmxphgkivolzqrnfmiaymrkjwmmeunhlodflsoltkwrjhugeyqwknaguerhkonjnwcndmunhduxmxwlmbkxehiukhzcjlqhhlewqbnedonniuexxgnsyxuumgedlvfgaicahaulecuoxatlusmpnsbziwekhnhtdxdauwwrnijzswnabkkktwlsepbshvjysdliwujzvjqvmtpsjgwatvuhjvkzgerxaemvosdrssgandvejxlqgvamvrauknovpsfypgdqydkqacdjyhadcpnfjhzkrulahnhlwpxviodgftsjnnisvyxmkqwlabkgswgujlmmyrpxhqepyewtjidoprkgshdnebxchhfnsuuqtfgaylyfwblujffbnowsaulkagrjjkarsddrzqrebkggwxvucuiefsxvharplzgabglywnicojtgahfhqcpaacbogvxkuunhvrblfcfhgfqzlhxtzlrkbsgtkaoqwvsafeygsracpscvjfmbnlvhlzoxyyrtzuftecwibrphjvpdbylhueeohxmhkiohzcdlcxgbmliovabwduwbunfdwapwntecugkwgbdyomrhjfcilpbyykapposmpgmxflahwswtdohzsvvwrobuxsdrxwaxoyydbwanuzzfbbrqlaujpvhmzafkxtxnidlubomqlgqagjtvveynuorapklrcmbaoxtcoktsopsjvwxjmnncupcukzqqbvvrskegygpneevfuzxxsnracmvcmutefpeebjzbgvnlrmgwunmlpsvlfbvajcoukwgzfbapwchbxofxahcwvyjcnoutvscpyzatdedeiggogirrvoswhximanhepqpwtigrhxisrwmylqskhffeduhfyeyxpbgvbjfxblowozsbjmpxtejqrxmhttnioovpbifcemsfgcxpofbabjtqbmnpzkzsoeoigrxbin")));
//	}		
//	
//	@Test
//	public void Test4() {
//		assertEquals("ewaf", 
//				findLongestWord("aewfafwafjlwajflwajflwafj", Arrays.asList("apple","ewaf","awefawfwaf","awef","awefe","ewafeffewafewf")));
//	}
	
	@Test
	public void BinarySearchTest() {
		List<Integer> list = Arrays.asList(2,2,2,2,2,2,2,2,2,2,2,3);
		List<Integer> list2 = Arrays.asList(2,2,2,2,2,2,2,2,2,2,2,2);
		List<Integer> list3 = Arrays.asList();
		List<Integer> list4 = Arrays.asList(1);
		
		assertEquals(3, binarySearch(list, 0, list.size() - 1, 2));
		assertEquals(-1, binarySearch(list2, 0, list.size() - 1, 2));
		assertEquals(-1, binarySearch(list3, 0, list.size() - 1, 0));
		assertEquals(1, binarySearch(list4, 0, list.size() - 1, 0));
	}
}
