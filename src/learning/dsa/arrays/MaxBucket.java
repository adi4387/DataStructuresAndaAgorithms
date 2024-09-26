package learning.dsa.arrays;

public class MaxBucket {
    public static void main(String[] args) {
        System.out.println(Solution.maxArea(new int[]{1,8,6,2,5,4,8,25,7}));
    }
}

class Solution {

    public static int maxArea(int[] height) {
        int max = 0;
        if(height == null || height.length == 0) {
            return max;
        }
        int i = 0;
        int j = height.length - 1;
        int length = height.length - 1;
        int area;
        while(i < j) {
            if(height[i] < height[j]) {
                while (height[i] < height[j]) {
                    area = height[i] * length;
                    max = Math.max(max,area);
                    i++;
                    length--;
                }
            } else if (height[i] > height[j]) {
                while (height[i] > height[j]) {
                    area = height[j] * length;
                    max = Math.max(max,area);
                    j--;
                    length--;
                }
            } else {
                area = height[j] * length;
                max = Math.max(max,area);
                i++; j--;
                length = length - 2;
            }
        }
        return max;
    }
}
