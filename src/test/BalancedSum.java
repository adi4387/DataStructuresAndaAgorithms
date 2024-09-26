package test;

import java.util.List;

public class BalancedSum {

    public static void main(String[] args) {
        System.out.println(balancedSum(List.of(1,2,3,4,1,2,3)));
    }

    public static int balancedSum(List<Integer> arr) {
        // Write your code here
        int size = arr.size();
        int leftPtr = 0;
        int rightPtr = size - 1;
        int sumLeft = arr.get(leftPtr++);
        int sumRight = arr.get(rightPtr--);
        while(leftPtr < rightPtr) {
            if(sumLeft < sumRight) {
                sumLeft += arr.get(leftPtr);
                leftPtr++;
            } else if(sumLeft > sumRight) {
                sumRight += arr.get(rightPtr);
                rightPtr--;
            } else {
                sumLeft += arr.get(leftPtr);
                leftPtr++;
                sumRight += arr.get(rightPtr);
                rightPtr--;
            }
        }
        return leftPtr;
    }
}
