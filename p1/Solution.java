/* Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
//import java.util.NoSuchElementException;

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        final DuplicatedIntMap integers = new DuplicatedIntMap();
        final DuplicatedIntMap mutableIntegers = new DuplicatedIntMap();

        // Populate sets
        for (int i = 0; i < numbers.length; i++) {
            integers.add(numbers[i], i);
            mutableIntegers.add(numbers[i], i);
        }

        for (final Map.Entry<Integer, Integer> entry : integers) {
            int mutableTarget = target;
            final int number1 = entry.getKey();
            final int index1 = entry.getValue();
            mutableIntegers.remove(number1, index1);
            mutableTarget -= number1;
            if (mutableIntegers.contains(mutableTarget)) {
                final int index2 = mutableIntegers.get(mutableTarget);
                if (index1 < index2) {
                    return new int[] {index1 + 1, index2 + 1};
                } else {
                    return new int[] {index2 + 1, index1 + 1};
                }
            }
            mutableIntegers.add(number1, index1);
        }
        return new int[] {0, 0};
    }

    private class DuplicatedIntMap implements Iterable<Map.Entry<Integer, Integer>> {
        private final Map<Integer, Integer> mIntegers = new HashMap<Integer, Integer>();
        private final Map<Integer, Integer> mDuplicateIntegers = new HashMap<Integer, Integer>();

        public boolean contains(final int number) {
            return mIntegers.containsKey(number);
        }

        public int get(final int number) {
            if (mIntegers.containsKey(number)) {
                return mIntegers.get(number);
            } else {
                return mDuplicateIntegers.get(number);
            }
        }

        public void add(final int number, final int index) {
            if (mIntegers.containsKey(number)) {
                mDuplicateIntegers.put(number, index);
            } else {
                mIntegers.put(number, index);
            }
        }

        public void remove(final int number, final int index) {
            checkAndRemove(mIntegers, number, index);
            checkAndRemove(mDuplicateIntegers, number, index);
        }

        private void checkAndRemove(final Map<Integer, Integer> map, final int number, final int index) {
            if (map.containsKey(number)) {
                if (map.get(number) == index) {
                    map.remove(number);
                }
            }
        }

        @Override
        public Iterator<Map.Entry<Integer, Integer>> iterator() {
            return new DuplicatedIntIterator();
        }

        private class DuplicatedIntIterator implements Iterator<Map.Entry<Integer, Integer>> {
            private final Iterator<Map.Entry<Integer, Integer>> mIntegersIterator =
                DuplicatedIntMap.this.mIntegers.entrySet().iterator();
            private final Iterator<Map.Entry<Integer, Integer>> mDuplicateIntegersIterator =
                DuplicatedIntMap.this.mDuplicateIntegers.entrySet().iterator();
            private boolean hasIntegers = true;

            @Override
            public boolean hasNext() {
                return mIntegersIterator.hasNext() || mDuplicateIntegersIterator.hasNext();
            }

            @Override
            public Map.Entry<Integer, Integer> next() /* throws NoSuchElementException */ {
                if (mIntegersIterator.hasNext()) {
                    return mIntegersIterator.next();
                } else if (mDuplicateIntegersIterator.hasNext()) {
                    hasIntegers = false;
                    return mDuplicateIntegersIterator.next();
                } else {
                    //throw new NoSuchElementException();
                    return mDuplicateIntegersIterator.next();
                }
            }

            @Override
            public void remove() throws UnsupportedOperationException {
                throw new UnsupportedOperationException();
            }
        }
    }

    public static void main(String[] args) {
        final int[] answer = new Solution().twoSum(new int[] {0, 4, 3, 0}, 0);
        System.out.println("index1=" + answer[0] + ", index2=" + answer[1]);
    }
}
