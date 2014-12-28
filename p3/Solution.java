//Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int lengthOfLongestSubstring(String s) {
        final Map<Character, Integer> charsInString = new HashMap<Character, Integer>();

        final int slen = s.length();
        int bestLength = 0;
        int curLength = 0;
        int substringStart = 0;
        for (int i = 0; i < slen; i++) {
            final char c = s.charAt(i);
            if (charsInString.containsKey(c)) {
                final int previousIndex = charsInString.get(c);
                for (int j = substringStart; j <= previousIndex; j++) {
                    final char charToRemove = s.charAt(j);
                    charsInString.remove(charToRemove);
                }
                substringStart = previousIndex + 1;
                curLength = i - substringStart;
            }
            curLength++;
            charsInString.put(c, i);
            if (curLength > bestLength) {
                bestLength = curLength;
            }
        }
        return bestLength;
    }

    public static void main(String[] args) {
        for (final String s : args) {
            System.out.println(new Solution().lengthOfLongestSubstring(s));
        }
    }
}
