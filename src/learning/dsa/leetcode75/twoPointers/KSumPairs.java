package learning.dsa.leetcode75.twoPointers;

import java.util.HashMap;
import java.util.Map;

public class KSumPairs {

    public static void main(String[] args) {
        System.out.println(maxOperations(new int[]{3,1,3,4,3}, 6));
    }

    public static int maxOperations(int[] nums, int k) {
        int result = 0;
        if(null == nums || nums.length < 2) {
            return result;
        }
        Map<Integer, Integer> sum = new HashMap<>();
//        while(p1 < p2) {
//            if(nums[p1] >= k || nums[p1] == Integer.MIN_VALUE) {
//                p1++;
//                continue;
//            }
//
//            if(nums[p2] >= k || nums[p2] == Integer.MIN_VALUE) {
//                p2--;
//                if(p1 == p2) {
//                    p1++;
//                    p2 = length;
//                }
//                continue;
//            }
//
//            if(nums[p1] + nums[p2] == k) {
//                nums[p1] = Integer.MIN_VALUE;
//                nums[p2] = Integer.MIN_VALUE;
//                p1++;
//                p2 = length;
//                result++;
//            } else {
//                p2--;
//                if(p1 == p2) {
//                    p1++;
//                    p2 = length;
//                }
//            }
//        }
        int count;
        Integer value1;
        Integer value2;
        for(int num : nums) {
            if(num < k) {
                sum.putIfAbsent(num, 0);
                count = sum.get(num);
                sum.put(num, ++count);
            }
        }

        for(int num : nums) {
            int key1 = k - num;
            value1 = sum.get(key1);
            if(value1 != null && value1 > 0) {
                value1--;
                if(value1 == 0) {
                    sum.remove(key1);
                } else {
                    sum.put(key1, value1);
                }
                value2 = sum.get(num);
                if(value2 != null && value2 > 0) {
                    value2--;
                    if(value2 == 0) {
                        sum.remove(num);
                    } else {
                        sum.put(num, value2);
                    }
                    result++;
                }
            }
        }
        return result;
    }
}
