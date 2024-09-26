package learning.dsa.leetcode75.arrays;

public class FlowerPot {

    public static void main(String[] args) {
        System.out.println(canPlaceFlowers(new int[]{1,0,0,0,0,0,1}, 2));
    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(n == 0) {
            return true;
        }
        if(null == flowerbed) {
            return false;
        }

        int i = 0;
        int len = flowerbed.length;

        while (i < len) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == len - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                n--;
                if (n == 0) {
                    return true;
                }
                i += 2; // Skip the next spot
            } else {
                i++;
            }
        }
        return n <= 0;
    }
}
