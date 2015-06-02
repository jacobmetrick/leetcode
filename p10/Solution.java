/*
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
*/

public class Solution {
    public static boolean isMatch(String s, String p) {
        int stringLength = s.length();
        int patternLength = p.length();
        int stringIndex = 0;
        int patternIndex = 0;

        if (stringLength == 0) {
            return true;
        }

        for (;stringIndex < stringLength && patternIndex < patternLength; patternIndex++) {
            boolean matchMany = false;
            // check for *s
            if (patternIndex + 1 < patternLength && p.charAt(patternIndex + 1) == '*') {
                matchMany = true;
            }

            if (matchMany) {
                while(stringIndex < stringLength && letterMatches(s.charAt(stringIndex), p.charAt(patternIndex))) {
                    stringIndex++;
                }
                patternIndex++; // skip past the *.
            } else if (!letterMatches(s.charAt(stringIndex), p.charAt(patternIndex))) {
                break;
            } else {
                stringIndex++;
            }
        }

        return stringIndex >= stringLength && patternIndex >= patternLength;
    }

    private static boolean letterMatches(final char s, final char p) {
        return (p == '.' || p == s);
    }

    public static void main(String[] args) {
        System.out.println(isMatch("a", ""));
    }
}
