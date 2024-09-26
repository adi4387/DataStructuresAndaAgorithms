package learning.dsa.leetcode75.arrays;

import java.util.ArrayList;
import java.util.List;

public class KidsWithGreatestCandies {

    public static void main(String[] args) {
        System.out.println(kidsWithCandies(new int[]{2,3,5,1,3}, 3));
    }

    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> result = new ArrayList<>();
        int max = candies[0];
        for(int i : candies) {
            if(i > max) {
                max = i;
            }
        }

        for(int i : candies) {
            if(i + extraCandies >= max) {
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;
    }
}
