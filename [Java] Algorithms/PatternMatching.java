import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Your implementations of various string searching algorithms.
 *
 * @author Rayan Dabbagh
 * @version 2.0
 * @userid rdabbagh3
 * @GTID 903591640
 * <p>
 * Collaborators: NA
 * <p>
 * Resources: NA
 */
public class PatternMatching {

    /**
     * Knuth-Morris-Pratt (KMP) algorithm relies on the failure table (also
     * called failure function). Works better with small alphabets.
     * <p>
     * Make sure to implement the buildFailureTable() method before implementing
     * this method.
     *
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> kmp(CharSequence pattern, CharSequence text,
                                    CharacterComparator comparator) {
        if (pattern == null) {
            throw new IllegalArgumentException("pattern is null!!");
        }
        if (pattern.length() == 0) {
            throw new IllegalArgumentException("pattern length is 0!");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator is null!");
        }
        if (text == null) {
            throw new IllegalArgumentException("text is null!");
        }
        ArrayList<Integer> newList = new ArrayList<>();
        int patternLength = pattern.length();
        int textLength = text.length();
        if (patternLength > textLength) {
            return newList;
        }
        int[] table = buildFailureTable(pattern, comparator);
        int a = 0;
        int b = 0;
        while (a <= textLength - patternLength) {
            while (b < patternLength && comparator.compare(text.charAt(a + b), pattern.charAt(b)) == 0) {
                b++;
            }
            if (b == 0) {
                a++;
            } else {
                if (b == patternLength) {
                    newList.add(a);
                }
                int nextAlignment = table[b - 1];
                a = a + b - nextAlignment;
                b = nextAlignment;
            }
        }
        return newList;
    }


    /**
     * Builds failure table that will be used to run the Knuth-Morris-Pratt
     * (KMP) algorithm.
     * <p>
     * The table built should be the length of the input pattern.
     * <p>
     * Note that a given index i will contain the length of the largest prefix
     * of the pattern indices [0..i] that is also a suffix of the pattern
     * indices [1..i]. This means that index 0 of the returned table will always
     * be equal to 0
     * <p>
     * Ex. pattern = ababac
     * <p>
     * table[0] = 0
     * table[1] = 0
     * table[2] = 1
     * table[3] = 2
     * table[4] = 3
     * table[5] = 0
     * <p>
     * If the pattern is empty, return an empty array.
     *
     * @param pattern    a pattern you're building a failure table for
     * @param comparator you MUST use this to check if characters are equal
     * @return integer array holding your failure table
     * @throws java.lang.IllegalArgumentException if the pattern or comparator
     *                                            is null
     */
    public static int[] buildFailureTable(CharSequence pattern,
                                          CharacterComparator comparator) {
        if (pattern == null) {
            throw new IllegalArgumentException("the pattern is indeed null!");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("the comparator is null");
        }
        int patternLength = pattern.length();
        int[] table = new int[patternLength];
        if (patternLength != 0) {
            table[0] = 0;
        }
        int a = 0;
        int b = 1;
        while (b < patternLength) {
            if (comparator.compare(pattern.charAt(a), pattern.charAt(b)) == 0) {
                table[b] = a + 1;
                a++;
                b++;
            } else if (a == 0) {
                table[b] = 0;
                b++;
            } else {
                a = table[a - 1];
            }
        }
        return table;
    }

    /**
     * Boyer Moore algorithm that relies on last occurrence table. Works better
     * with large alphabets.
     * <p>
     * Make sure to implement the buildLastTable() method before implementing
     * this method.
     * <p>
     * Note: You may find the getOrDefault() method from Java's Map class
     * useful.
     *
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for the pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> boyerMoore(CharSequence pattern,
                                           CharSequence text,
                                           CharacterComparator comparator) {
        if (pattern == null) {
            throw new IllegalArgumentException("pattern null!");
        }
        if (pattern.length() == 0) {
            throw new IllegalArgumentException("pattern length is 0!");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator is null!");
        }
        if (text == null) {
            throw new IllegalArgumentException("text is null!");
        }
        ArrayList<Integer> newList = new ArrayList<>();
        Map<Character, Integer> newMap = buildLastTable(pattern);
        int a = 0;
        int patternLength = pattern.length();
        int textLength = text.length();
        if (patternLength > textLength) {
            return newList;
        }
        while (a <= textLength - patternLength) {
            int b = patternLength - 1;
            while (b >= 0 && comparator.compare(text.charAt(a + b), pattern.charAt(b)) == 0) {
                b--;
            }
            if (b == -1) {
                newList.add(a);
                a++;
            } else {
                int shift = newMap.getOrDefault(text.charAt(a + b), -1);
                if (shift < b) {
                    a = a + b - shift;
                } else {
                    a++;
                }
            }
        }
        return newList;
    }


    /**
     * Builds last occurrence table that will be used to run the Boyer Moore
     * algorithm.
     * <p>
     * Note that each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x,
     * and you will have to check for that in your Boyer Moore implementation.
     * <p>
     * Ex. pattern = octocat
     * <p>
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     * <p>
     * If the pattern is empty, return an empty map.
     *
     * @param pattern a pattern you are building last table for
     * @return a Map with keys of all of the characters in the pattern mapping
     * to their last occurrence in the pattern
     * @throws java.lang.IllegalArgumentException if the pattern is null
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("null pattern!");
        }
        HashMap<Character, Integer> newHashmap = new HashMap<Character, Integer>();
        int patternLength = pattern.length();
        for (int z = 0; z < patternLength; z++) {
            newHashmap.put(pattern.charAt(z), z);
        }
        return newHashmap;
    }

    /**
     * Prime base used for Rabin-Karp hashing.
     * DO NOT EDIT!
     */
    private static final int BASE = 113;

    /**
     * Runs the Rabin-Karp algorithm. This algorithms generates hashes for the
     * pattern and compares this hash to substrings of the text before doing
     * character by character comparisons.
     * <p>
     * When the hashes are equal and you do character comparisons, compare
     * starting from the beginning of the pattern to the end, not from the end
     * to the beginning.
     * <p>
     * You must use the Rabin-Karp Rolling Hash for this implementation. The
     * formula for it is:
     * <p>
     * sum of: c * BASE ^ (pattern.length - 1 - i)
     * c is the integer value of the current character, and
     * i is the index of the character
     * <p>
     * We recommend building the hash for the pattern and the first m characters
     * of the text by starting at index (m - 1) to efficiently exponentiate the
     * BASE. This allows you to avoid using Math.pow().
     * <p>
     * Note that if you were dealing with very large numbers here, your hash
     * will likely overflow; you will not need to handle this case.
     * You may assume that all powers and calculations CAN be done without
     * overflow. However, be careful with how you carry out your calculations.
     * For example, if BASE^(m - 1) is a number that fits into an int, it's
     * possible for BASE^m will overflow. So, you would not want to do
     * BASE^m / BASE to calculate BASE^(m - 1).
     * <p>
     * Ex. Hashing "bunn" as a substring of "bunny" with base 113
     * = (b * 113 ^ 3) + (u * 113 ^ 2) + (n * 113 ^ 1) + (n * 113 ^ 0)
     * = (98 * 113 ^ 3) + (117 * 113 ^ 2) + (110 * 113 ^ 1) + (110 * 113 ^ 0)
     * = 142910419
     * <p>
     * Another key point of this algorithm is that updating the hash from
     * one substring to the next substring must be O(1). To update the hash,
     * subtract the oldChar times BASE raised to the length - 1, multiply by
     * BASE, and add the newChar as shown by this formula:
     * (oldHash - oldChar * BASE ^ (pattern.length - 1)) * BASE + newChar
     * <p>
     * Ex. Shifting from "bunn" to "unny" in "bunny" with base 113
     * hash("unny") = (hash("bunn") - b * 113 ^ 3) * 113 + y
     * = (142910419 - 98 * 113 ^ 3) * 113 + 121
     * = 170236090
     * <p>
     * Keep in mind that calculating exponents is not O(1) in general, so you'll
     * need to keep track of what BASE^(m - 1) is for updating the hash.
     * <p>
     * Do NOT use Math.pow() in this method.
     *
     * @param pattern    a string you're searching for in a body of text
     * @param text       the body of text where you search for pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> rabinKarp(CharSequence pattern,
                                          CharSequence text,
                                          CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("pattern null or has length 0!");
        }
        if (comparator == null || text == null) {
            throw new IllegalArgumentException("text or comparator null!");
        }
        ArrayList<Integer> newList = new ArrayList<Integer>();
        int patternLength = pattern.length();
        int textLength = text.length();
        if (patternLength > textLength) {
            return newList;
        }
        int textHashValue = 0;
        int patternHashValue = 0;
        int base = 1;
        int a = patternLength - 1;
        while (a >= 0) {
            patternHashValue = patternHashValue + pattern.charAt(a) * base;
            textHashValue = textHashValue + text.charAt(a) * base;
            base = base * BASE;
            a--;
        }
        a = 0;
        base = base / BASE;
        int z;
        while (a <= textLength - patternLength) {
            if (patternHashValue == textHashValue) {
                z = 0;
                while (z < patternLength && comparator.compare(text.charAt(a + z), pattern.charAt(z)) == 0) {
                    z++;
                }
                if (z == patternLength) {
                    newList.add(a);
                }
            }
            a++;
            if (a <= textLength - patternLength) {
                textHashValue = (textHashValue - text.charAt(a - 1) * base) * BASE
                        + text.charAt(a + patternLength - 1);
            }

        }
        return newList;
    }
}

