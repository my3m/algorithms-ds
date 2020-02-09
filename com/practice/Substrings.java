
	/**
	 
	 
 Suppose you have a string, haystack, and a set of characters which may or may not appear in that string, alphabet. How many non-empty sub-strings of haystack do not contain every character in alphabet? Write a function that accepts haystack and alphabet as input and returns an answer to this question as an integer.

	Example:
	
	Input: haystack = "cab", alphabet = ['a', 'c']
	Output: 4
	Explanation:
	We can enumerate all 6 non-empty substrings of `cab`:
	"c" - this does not have `a`.
	"a" - this does not have `c`.
	"ca" - this has every entry in `alphabet`.
	"b" - this does not have `a` or `c`.
	"ab" - this does not have `c`.
	"cab" - this has every entry in `alphabet`.
	4 of these substrings don't have every answer, so the function should return 4.
	My Solutions
	Solution O(n^3)
	The most brutal brute force solution is O(n^3): generate every possible substring of haystack, a set with O(n^2) members. For each substring - whose length is O(n) - iterate through it and see if it contains every character in alphabet.
	
	Solution O(n^2)
	A slightly less brutal brute force solution is O(n^2):
	For every index i in haystack, start at that index and iterate to the right until you've seen every character in the alphabet to find every substring with i as the leftmost character that does not contain the whole alphabet. Once you've found the entire alphabet, you do not need to keep searching to the right, as you only add characters. The alphabet stays found.
	There are O(n) indices in haystack, and from each index you could conceivably go all the way to the right, which would take another O(n) - multiplying, that gets us O(n^2).
	
	Solution O(n)
	The linear solution exploits our knowledge of how the number of subsets of a string changes as the length of a string changes.
	
	Consider three strings: 'a', 'ab', and 'abc'. 'ab' has two substrings that 'a' does not have - the two with b as the rightmost character. Likewise, 'abc' has three substrings that 'ab' does not have: the three with c as the rightmost character.
	Briefly: if we increase a string from length n to length n+1, it has n+1 more substrings than it did before.
	
	We are going to use a sliding window to iterate through every index in haystack, and at each index i, we will calculate the number of substrings with i as the rightmost index that do not contain all of alphabet.
	
	We'll have two indices, i and j, which will begin at 0. They will be a sliding window over the substring. We will also have a count of how many of each character in the alphabet appear in the sliding window.
	
	In short:
	
	while i < length(haystack):
	  i++
	  add haystack[i] to count
	  while every character in the alphabet appears in the count: // a miracle occurs
	    remove haystack[j] from the count
	    j++
	  returnValue += i-j+1 // length of sliding window
	For n = length of haystack and m = length of alphabet, this can be O(n×m) or O(n), depending on how you implement the miracle in line 4.
	
	Complete O(n) Golang Solution
	package main
	
	import (
		"fmt"
	)
	
	type Counter struct {
		bytecounts map[byte]int
		missing    int
	}
	
	func getCounter(alphabet []byte) *Counter {
		bytecounts := make(map[byte]int)
		missing := 0
		for _, a := range alphabet {
			_, ok := bytecounts[a]
			if ok {
				panic(fmt.Sprintf("Alphabet %v has a character twice+", string(alphabet)))
			}
			bytecounts[a] = 0
			missing += 1
		}
		return &Counter{
			bytecounts: bytecounts,
			missing:    missing,
		}
	}
	
	func (c *Counter) addByte(b byte) {
		_, ok := c.bytecounts[b]
		if !ok {
			return
		}
		c.bytecounts[b] += 1
		if c.bytecounts[b] == 1 {
			c.missing -= 1
		}
	}
	
	func (c *Counter) remByte(b byte) {
		_, ok := c.bytecounts[b]
		if !ok {
			return
		}
		if c.bytecounts[b] == 0 {
			panic(fmt.Sprintf("key[%c] cannot decrement from 0", b))
		}
		c.bytecounts[b] -= 1
		if c.bytecounts[b] == 0 {
			c.missing += 1
		}
	}
	
	func (c *Counter) hasAlphabet() bool {
		return c.missing == 0
	}
	
	func NumberOfSubstringsWithoutEntireAlphabet(alphabet, haystack []byte) int {
	
		// Edge cases:
		if len(alphabet) == 0 {
			return 0
		}
		if len(haystack) == 0 {
			return 0
		}
	
		// We're going to iterate pointer 'r' across haystack.
		// We'll leave a pointer behind us.
		// Whenever the substr between the character we've reached
		// and the pointer we're dragging behind us includes every
		// character in the alphabet, we pull the pointer behind us
		// closer, until it doesn't.
	
		// Counts represents the number of each byte in the byte array.
		counter := getCounter(alphabet)
		runningTotal := 0
	
		l := 0
		var span int
		for r, _ := range haystack {
			counter.addByte(haystack[r])
			for counter.hasAlphabet() {
				counter.remByte(haystack[l])
				l++
			}
			span = r - l + 1
			runningTotal += span
			fmt.Printf("%v len %v total %v\n", string(haystack[l:r+1]), span, runningTotal)
		}
	
		return runningTotal
	}
	
	type testCase struct {
		alphabet string
		haystack string
	}
	
	func main() {
		for _, t := range []testCase{
			{"abq", "albuquerque"},
			{"abc", "baculum"},
			{"abc", "abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabc"},
		} {
			total := NumberOfSubstringsWithoutEntireAlphabet(
				[]byte(t.alphabet),
				[]byte(t.haystack))
			fmt.Printf("Total for %v and %v: %v\n\n", t.alphabet, t.haystack, total)
		}
	}
	 
	 */
