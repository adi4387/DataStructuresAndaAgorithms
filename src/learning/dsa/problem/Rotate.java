package learning.dsa.problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem:- Rotate an array to the left or right direction by count k.
 *
 * Examples:
 *
 * Input :- Array = [1,2,3,4,5,6,7], direction = left, k = 1 Output :- Array = [2,3,4,5,6,7,1]
 * Input :- Array = [1,2,3,4,5,6,7], direction = left, k = 2 Output :- Array = [3,4,5,6,7,1,2]
 * Input :- Array = [1,2,3,4,5,6,7], direction = left, k = 8 Output :- Array = [2,3,4,5,6,7,1]
 * Input :- Array = [1,2,3,4,5,6,7], direction = right, k = 3 Output :- Array = [5,6,7,1,2,3,4]
 * Input :- Array = [1,2,3,4,5,6,7], direction = right, k = 1 Output :- Array = [7,1,2,3,4,5,6]
 * Note: Treat array as a circular array where it can be rotated even if the number of rotations is more than the length of the array.
 */
public class Rotate {

    public static void main(String[] args) {
        var input = new ArrayList<Integer>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        input.add(5);
        input.add(6);
        input.add(7);
        System.out.println(rotate(input, 8, false));
    }

    public static List<Integer> rotate(List<Integer> array, int k, boolean direction) {
        while(k > 0) {
            if(direction) {
                array.add(array.size()-1, array.remove(0));
            } else {
                int temp = array.get(array.size()-1);
                for(int i = array.size() - 1; i > 0; i--) {
                    array.set(i, array.get(i - 1));
                }
                array.set(0, temp);
            }
            k--;
        }
        return array;
    }
}
