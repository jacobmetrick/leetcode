// Given a string s, partition s such that every substring of the partition is a palindrome.

// Return all possible palindrome partitioning of s.

// For example, given s = "aab",
// Return

//  [
//    ["aa","b"],
//    ["a","a","b"]
//  ]
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;

public class Palindrome {
    /*
    private List<List<String>> mPartitionList = new ArrayList<List<String>>();
    public List<List<String>> partition(String s) {
        partitionInternal(s, 0);
        return mPartitionList;
    }

    private void partitionInternal(final String s, final int index) {
        System.out.println("String is " + s + " index is " + index);
        if (isPalindrome(s)) {
            System.out.println("adding...");
            if (mPartitionList.size() >= index) {
                mPartitionList.add(new ArrayList<String>());
            }
            mPartitionList.get(index).add(s);
        }

        final int slen = s.length();
        for (int i = 1; i < slen; i++) {
            final int partitions = mPartitionList.size();
            partitionInternal(s.substring(0, i), partitions);
            partitionInternal(s.substring(i), partitions);
        }
    }
    */
    public static void main(String[] args) {
        for (final String s : args) {
            System.out.println(new Palindrome().partition(s));
        }
    }

    private boolean isPalindrome(final String s) {
        return (new StringBuilder(s)).reverse().toString().equals(s);
    }

    private boolean listIsPalindromePartition(final List<String> partition) {
        for (final String s : partition) {
            if (!isPalindrome(s)) {
                return false;
            }
        }
        return true;
    }

    public List<List<String>> partition(String s) {
        final List<List<String>> partitions = new ArrayList<List<String>>();
        final int slen = s.length();
        final long possiblePartitions = 1 << (slen - 1);

        for (int i = 0; i < possiblePartitions; i++) {
            final List<String> partition = new ArrayList<String>();
            int previousDivision = 0;
            for (int j = 0; j < slen; j++) {
                // This cryptic looking line means to check if there is a division to be made between index j and
                // and index j + 1
                if ((i & (1 << j)) != 0) {
                    partition.add(s.substring(previousDivision, j + 1));
                    previousDivision = j + 1;
                }
            }
            partition.add(s.substring(previousDivision));
            if (listIsPalindromePartition(partition)) {
                partitions.add(partition);
            }
        }
        return partitions;
    }
}
