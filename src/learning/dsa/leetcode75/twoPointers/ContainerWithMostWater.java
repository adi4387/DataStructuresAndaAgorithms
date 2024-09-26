package learning.dsa.leetcode75.twoPointers;

public class ContainerWithMostWater {

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }

    public static int maxArea(int[] height) {
        int maxArea = Integer.MIN_VALUE;
        int p1 = 0;
        int p2 = height.length - 1;
        int area;
        while(p1 < p2) {
            if(height[p1] < height[p2]) {
                area = height[p1++] * (p2 - p1);
                p1++;
            } else {
                area = height[p2--] * (p2 - p1);
                p2--;
            }
            if(area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }
}
