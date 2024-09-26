package learning.dsa.leetcode75.twoPointers;

import java.util.Arrays;

public class MoveZeros {

    public static void main(String[] args) {
        var nums = new int[]{0};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void moveZeroes(int[] nums) {
        if(null == nums || nums.length == 0) {
            return;
        }
        int p1 = 0;
        int p2 = 0;
        int temp;
        while(p1 < nums.length) {
            if (nums[p1] != 0) {
                temp = nums[p2];
                nums[p2] = nums[p1];
                nums[p1] = temp;
                p2++;
            }
            p1++;
        }
    }
}
