package learning.dsa.arrays.searching;

import java.util.List;

public class Solution {
    public static void main(String[] args) {
//        LinearSearchAlgorithm<Integer> linearSearchAlgorithmInteger = new LinearSearchAlgorithm<>();
//        int integerCandidate = 5;
//        System.out.println(integerCandidate + " is present at index: " + linearSearchAlgorithmInteger.search(List.of(1,5,4,2), integerCandidate));
//        LinearSearchAlgorithm<String> linearSearchAlgorithmString = new LinearSearchAlgorithm<>();
//        String stringCandidate = "D";
//        System.out.println(stringCandidate + " is present at index: " + linearSearchAlgorithmString.search(List.of("A","B","C","D"), stringCandidate));

//        BinarySearchAlgorithm binarySearchAlgorithm = new BinarySearchAlgorithm();
//        int candidate = 9;
//        System.out.println(candidate + " is present at index: " + binarySearchAlgorithm.search(List.of(1,2,3,4,5,6,7,8,9), candidate, 0, 8));
        System.out.println(findBoundary(List.of(true)));
    }

    public static int findBoundary(List<Boolean> arr) {
        // WRITE YOUR BRILLIANT CODE HERE
        if(null == arr || arr.isEmpty()) {
            return -1;
        }
        int left = 0;
        int right = arr.size();
        int index = -1;
        while(left < right) {
            int mid = left + ((right-left)/2);
            if(arr.get(mid)) {
                right = mid - 1;
                index = mid;
            } else {
                left = mid + 1;
            }
        }

        return index;
    }
}
