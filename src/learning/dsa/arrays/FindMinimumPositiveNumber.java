package learning.dsa.arrays;

import java.util.TreeSet;

public class FindMinimumPositiveNumber {

    public static void main(String... args) {
        var FindMinimumPositiveNumber = new FindMinimumPositiveNumber();
        int[] input = {1, 2, 4, 3, 7, 5, 6};
        System.out.println(FindMinimumPositiveNumber.solution(input));
    }
    public int solution(int[] A) {
        TreeSet<Integer> objects = new TreeSet<>();

        // Populate the TreeSet with elements from array A
        for (int i : A) {
            objects.add(i);
        }

        // Initialize result to the smallest positive integer in the TreeSet
        int result = objects.isEmpty() || objects.first() <= 0 ? 1 : objects.first();

        // Check if result is present in the TreeSet
        while (objects.contains(result)) {
            result++;
        }

        return result;
    }


}
