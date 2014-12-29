/*The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/
public class Solution {
    public static String convert(String s, int nRows) {
        if (nRows == 1) {
            return s;
        }
        final StringBuilder answer = new StringBuilder();
        final int wavelength = 2 * (nRows - 1);
        final int slen = s.length();

        for (int i = 0; i < nRows; i++) {
            final int criticalNumber1 = wavelength - i * 2;
            final int criticalNumber2 = wavelength - criticalNumber1;
            int j = i;
            for (int iter = 0; j < slen; iter++) {
                answer.append(s.charAt(j));
                if (criticalNumber1 == 0) {
                    j += criticalNumber2;
                } else if (criticalNumber2 == 0) {
                    j += criticalNumber1;
                } else if (iter % 2 == 0) {
                    j += criticalNumber1;
                } else {
                    j += criticalNumber2;
                }
            }
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 100));
    }
}
