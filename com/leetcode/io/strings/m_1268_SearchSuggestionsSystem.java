package io.strings;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Test;

/*
 
 idea; starting with first char (0 index), pre-populate an "availableSuggestions" list
 		from the products array with products[0] == searchWord[0]
 			Sort the list to make lexicographically minimum appear first
 			This is answer for first typed char
 			
 		Starting off with 2nd char (1st index), we use the list from the (0th index),
 		List<String> currentSuggestions = new ArrayList<String>(availableSuggestions.size());
 		Filter those that meet till the 2nd charm & add to currentSuggestions
 		Make availableSuggestions=currentSuggestions
 		
 		Using same list & filtering it as you type more chars.
 		This wont work if m is changed to some other char in the beginning
 		
 		EXAMINE this solution 6ms
 		
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> res = new ArrayList();
        int l = 0, r = products.length - 1;
        
        // match each character in searchWord
        for (int i = 0; i < searchWord.length(); i++) {
            // find range          
            
            while (l <= r && (products[l].length() <= i || products[l].charAt(i) != searchWord.charAt(i))) {
                l++;
            } 
            while (l <= r && (products[r].length() == i || products[r].charAt(i) != searchWord.charAt(i))) {
                r--;
            }
            
            List<String> prefixMatched = new ArrayList();
            for (int j = l; j < Math.min(l + 3, r + 1); j++) {
                prefixMatched.add(products[j]);
            }
            res.add(prefixMatched);
        } 
        
        return res;
    }
} 		
 		
 */
public class m_1268_SearchSuggestionsSystem {
	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		List<List<String>> allSuggestions = new ArrayList<>();
		List<String> availableSuggestions = new ArrayList<>();
		for(String product : products) {
			if(searchWord.charAt(0) == product.charAt(0)) {
				availableSuggestions.add(product);
			}
		}
		Collections.sort(availableSuggestions);
		allSuggestions.add(getRange(availableSuggestions, 0, 2));
		for(int i = 1; i < searchWord.length(); i++) {
			List<String> currentSuggestions = new ArrayList<String>(availableSuggestions.size());
			for(String suggestion : availableSuggestions) {
				/*
				 * We know all 1st chars match, so just 2nd chars and so on
				 */
				if(i < suggestion.length() && suggestion.charAt(i) == searchWord.charAt(i)) {
					currentSuggestions.add(suggestion);
//					if(currentSuggestions.size() == 3)
//						break;
				}
			}
			allSuggestions.add(getRange(currentSuggestions, 0, 2));
			availableSuggestions = currentSuggestions;
		}
		return allSuggestions;
	}

	public List<String> getRange(List<String> source, int start, int end) {
		List<String> result = new ArrayList<>();
		int exit = Math.min(end, source.size() - 1);
		for(int i = start; i <= exit; i++) {
			result.add(source.get(i));
		}
		return result;
	}
	
	@Test
	public void Test1() {
		String[] products = new String[] { "mobile", "mouse", "moneypot", "monitor", "mousepad" };
		String searchWord = "mouse";
		List<List<String>> expected = new ArrayList<>();
		expected.add(Arrays.asList("mobile", "moneypot", "monitor"));
		expected.add(Arrays.asList("mobile", "moneypot", "monitor"));
		expected.add(Arrays.asList("mouse", "mousepad"));
		expected.add(Arrays.asList("mouse", "mousepad"));
		expected.add(Arrays.asList("mouse", "mousepad"));

		List<List<String>> result = suggestedProducts(products, searchWord);
		assertTrue(result.size() == expected.size());
		for (int i = 0; i < result.size(); i++) {
			assertTrue(expected.get(i).equals(result.get(i)));
		}
	}
}
