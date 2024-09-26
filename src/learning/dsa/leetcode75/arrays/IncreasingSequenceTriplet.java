package learning.dsa.leetcode75.arrays;

public class IncreasingSequenceTriplet {

    public static void main(String[] args) {
        System.out.println(increasingTriplet(new int[]{1,5,0,4,1,3}));
    }

    public static boolean increasingTriplet(int[] nums) {

        if(null == nums || nums.length < 3) {
            return false;
        }
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;

        for(int num : nums) {
            if(num < a) {
                a = num;
            } else if (num < b) {
                b = num;
            } else {
                return true;
            }
        }
        return false;
    }
}
