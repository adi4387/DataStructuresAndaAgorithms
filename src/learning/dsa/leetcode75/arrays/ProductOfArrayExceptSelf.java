package learning.dsa.leetcode75.arrays;

public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        if(null == nums || nums.length == 0) {
            return new int[0];
        }
        int[] result = new int[nums.length];
        int product = 1;
        for(int i = 0; i < result.length; i++) {
            product *= nums[i];
        }

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                result[i] = 1;
                for(int j = 0; j < result.length; j++) {
                    if(i != j) {
                        result[i] *= nums[j];
                    }
                }
            } else {
                result[i] = product/nums[i];
            }
        }
        return result;
    }
}
