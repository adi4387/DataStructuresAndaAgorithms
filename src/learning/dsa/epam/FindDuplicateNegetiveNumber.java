package learning.dsa.epam;

import java.util.*;

public class FindDuplicateNegetiveNumber {

    public static void main(String... args) {
        var FindMinimumPositiveNumber = new FindDuplicateNegetiveNumber();
        int[] input = {-1, 2, 1};
        System.out.println(FindMinimumPositiveNumber.solution(input));
    }
    public int solution(int[] A) {
        Set<Integer> objects = new LinkedHashSet<>();

        for(int i : objects) {
            if(objects.contains(i * -1)) {
                return i < 0 ? i * -1 : i;
            }
        }
        return 0;
    }


}
