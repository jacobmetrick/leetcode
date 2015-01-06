// Reverse digits of an integer
//
public class Solution {
    public static int reverse(int x) {
        if (x == -2147483648) { // two's complement baloney
            return 0;
        }
        final boolean isNegative = x < 0;
        if (isNegative) {
            x *= -1;
        }

        int digits = 0;
        for (int i = 1; digits < 10 && (i - 1) < x; i *= 10, digits++) { }

        final int iterations = digits / 2;
        for (int i = 1; iterations >= i; i++) {
            final int fromLeft = digits - i + 1;
            final int leftDigit = getDigit(x, fromLeft);
            final int rightDigit = getDigit(x, i);
            x = setDigit(x, fromLeft, rightDigit);
            if (x < 0 || (rightDigit > 2 && fromLeft == 10)) { //overflow
                return 0;
            }
            x = setDigit(x, i, leftDigit);
        }

        if (isNegative) {
            x *= -1;
        }
        return x;
    }

    private static int getDigit(final int x, final int digit) {
        final int multiplier = (int)Math.pow(10, digit - 1);
        final int shiftOver = x / multiplier;

        return shiftOver % 10;
    }

    private static int setDigit(int x, final int digit, final int newVal) {
        final int digitToRemove = getDigit(x, digit);
        final int multiplier = (int)Math.pow(10, digit - 1);

        x -= multiplier * digitToRemove;
        x += multiplier * newVal;
        return x;
    }

    public static void main(String[] args) {
        final int test = -2147483648;
        System.out.println("Value is " + test + " and reversed is " + reverse(test));
    }
}
