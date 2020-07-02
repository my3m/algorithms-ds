package com.interviewcake.hashmap;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class WordCloud {
	
	Map<String, Integer> wordsToCounts = new HashMap<>();
	
	String capitalizeWord(String word) {
		if(word.length() == 1)
			return word;
		return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
	}
	
	void addToHashMap(Map<String, Integer> wordFrq, String word) {
		//Mmm
		//capital Capital capital		
		//Capital capital
		if(wordFrq.containsKey(word)) {
			wordFrq.put(word, wordFrq.get(word) + 1);
		}
		else if(wordFrq.containsKey(capitalizeWord(word))) {
			String capital = capitalizeWord(word);
			wordFrq.put(capital, wordFrq.get(capital) + 1);
		}
		else if(wordFrq.containsKey(word.toLowerCase())) {
			int count = wordFrq.get(word.toLowerCase());
			//capital
			wordFrq.put(capitalizeWord(word), count + 1);
			wordFrq.remove(word.toLowerCase());
		}
		else {
			wordFrq.put(word, 1);
		}		
	}
	
	Map<String, Integer> populateToWordCloud(String text) {
		Map<String, Integer> wordFrq = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		int currentWordLength = 0;
		for(int i = 0; i < text.length(); i++) {
			char chr = text.charAt(i);
			if(Character.isLetter(chr) || chr == '\'' || chr == '-') {
				if(Character.isLetter(chr))
					currentWordLength++;
				sb.append(chr);
			}
			else {
				//assume punctuation
				if(sb.length() > 0) {
					String word = sb.toString();
					if(currentWordLength > 0) {
						addToHashMap(wordFrq, word);
						currentWordLength = 0;
					}
					sb.setLength(0);
				}
			}
			if(i == text.length() - 1) {
				if(sb.length() > 0) {
					String word = sb.toString();
					if(currentWordLength > 0) {
						addToHashMap(wordFrq, word);
						currentWordLength = 0;
					}
					sb.setLength(0);
				}				
			}
		}
		return wordFrq;
	}
	
	@Test
	public void CapitalTestCase() {
		final String text = "capital Capital capital";
		final Map<String, Integer> exp = new HashMap<String, Integer>() {
			{
				put("Capital", 3);
			}
		};
		assertEquals(exp, populateToWordCloud(text));
	}
	
	public void CapitalTestCase2() {
		final String text = "Capital capital capital";
		final Map<String, Integer> exp = new HashMap<String, Integer>() {
			{
				put("Capital", 3);
			}
		};
		assertEquals(exp, populateToWordCloud(text));
	}
	
	public void CapitalTestCase3() {
		final String text = "capital capital Capital";
		final Map<String, Integer> exp = new HashMap<String, Integer>() {
			{
				put("Capital", 3);
			}
		};
		assertEquals(exp, populateToWordCloud(text));
	}
	
	@Test
	public void SimpleTestCase() {
		final String text = "This is a sample";
		final Map<String, Integer> exp = new HashMap<String, Integer>() {
			{
				put("This", 1);
				put("is", 1);
				put("a", 1);
				put("sample", 1);
			}
		};
		assertEquals(exp, populateToWordCloud(text));
	}
	
	//{song=1, a=1, music=1, keys=2, Alicia's=1, is=1, rightong=1, good=1}>
	//{song=1, a=1, music=1, keys=2, Alicia's=1, is=1, right=1, good=1}>
	@Test
	public void PunctuationTestCase() {
		final String text = "  Alicia's keys is a good music!!! keys song!!! right??  ";
		final Map<String, Integer> exp = new HashMap<String, Integer>() {
			{
				put("Alicia's", 1);
				put("keys", 2);
				put("is", 1);
				put("a", 1);
				put("good", 1);
				put("music", 1);
				put("song", 1);
				put("right", 1);
			}
		};
		assertEquals(exp, populateToWordCloud(text));
	}	
	

    @Test
    public void sampleTest() {
        final String text = " This  is  a test  sample string!!!! for test?";
        final Map<String, Integer> expected = new HashMap<String, Integer>() {
            {
                put("This", 1);
                put("is", 1);
                put("a", 1);
                put("sample", 1);
                put("string", 1);
                put("for", 1);
                put("test", 2);
            }
        };
        assertEquals(expected, populateToWordCloud(text));        
    }





    // tests

    // There are lots of valid solutions for this one. You
    // might have to edit some of these tests if you made
    // different design decisions in your solution.

    @Test
    public void simpleSentenceTest() {
        final String text = "I like cake";
        final Map<String, Integer> expected = new HashMap<String, Integer>() { {
            put("I", 1);
            put("like", 1);
            put("cake", 1);
        }};
        assertEquals(expected, populateToWordCloud(text));
    }

    @Test
    public void longerSentenceTest() {
        final String text = "Chocolate cake for dinner and pound cake for dessert";
        final Map<String, Integer> expected = new HashMap<String, Integer>() { {
            put("and", 1);
            put("pound", 1);
            put("for", 2);
            put("dessert", 1);
            put("Chocolate", 1);
            put("dinner", 1);
            put("cake", 2);
        }};
        assertEquals(expected, populateToWordCloud(text));
    }

    @Test
    public void punctuationTest() {
        final String text = "Strawberry short cake? Yum!";
        final Map<String, Integer> expected = new HashMap<String, Integer>() { {
            put("cake", 1);
            put("Strawberry", 1);
            put("short", 1);
            put("Yum", 1);
        }};
        assertEquals(expected, populateToWordCloud(text));
    }

    @Test
    public void hyphenatedWordsTest() {
        final String text = "Dessert - mille-feuille cake";
        final Map<String, Integer> expected = new HashMap<String, Integer>() { {
            put("cake", 1);
            put("Dessert", 1);
            put("mille-feuille", 1);
        }};
        assertEquals(expected, populateToWordCloud(text));
    }

    @Test
    public void ellipsesBetweenWordsTest() {
        final String text = "Mmm...mmm...decisions...decisions";
        final Map<String, Integer> expected = new HashMap<String, Integer>() { {
                put("Mmm", 2);
                put("decisions", 2);
        }};
        assertEquals(expected, populateToWordCloud(text));
    }

    @Test
    public void apostrophesTest() {
        final String text = "Allie's Bakery: Sasha's Cakes";
        final Map<String, Integer> expected = new HashMap<String, Integer>() { {
            put("Bakery", 1);
            put("Cakes", 1);
            put("Allie's", 1);
            put("Sasha's", 1);
        }};
        assertEquals(expected, populateToWordCloud(text));
    }		
}
