package learning.dsa.problem;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Problem :- Check if the array is balanced array or not. Description:- A balanced array is defined to be an array where for every value n in the array, -n also is in the array.
 *
 *         Example 1:- {-2, 3, 2, -3} is a balanced array.
 *         Example 2:- {1,1,-1,-1} is a balanced array.
 *         Example 3:- {1,1,-1} is a NOT balanced array.
 *         Example 4:- {-2, 3, 2, -3, 0, 5,-5} is a balanced array.
 *         Example 5:- {1, 2, -3} is NOT a balanced array.
 *         Example 6:- {-3,-2, -3, -2, 4, 1, 4, 1 , 3, 2, -4, -1} is NOT a balanced array
 *
 *         Note:-
 *
 *         1. Zeroes can be ignored.
 *         2. There can be duplicates in the array. Every duplicate n needs to have -n
 */

public class BalancedArray {

    public static void main(String[] args) {
        var input = new ArrayList<Integer>();
        input.add(-2);
        input.add(3);
        input.add(2);
        input.add(-3);
        System.out.println(isBalanced(input));

    }

    public static boolean isBalanced(List<Integer> array) {
        int sum = 0;
        for (Integer integer : array) {
            sum += integer;
        }
        return sum == 0;
    }
}
