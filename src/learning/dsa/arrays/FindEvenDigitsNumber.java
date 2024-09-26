package learning.dsa.arrays;

import java.util.List;

public class FindEvenDigitsNumber {

    public static void main(String[] args) {
        System.out.println(evenDigits(List.of(1, 10, 345, 1234)));
    }

    static int evenDigits(List<Integer> nums) {
        int result = 0;

        for(int num : nums) {
            if(even(num)) {
                result++;
            }
        }
        return result;
    }

    private static boolean even(int num) {
        return digits(num) % 2 == 0;
    }

    private static int digits(int num) {
        if(num < 0) {
            num = num * -1;
        }
        return (int) (Math.log10(num) + 1);
    }

}
