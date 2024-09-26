package learning.dsa.arrays;

import java.util.Scanner;

public class Arrays {

    public static void main(String[] args) {
        AverageTemperature averageTemperature = null;
        System.out.println("How many days temperature?");
        Scanner scanner = new Scanner(System.in);
        try {
            int index = scanner.nextInt();
            averageTemperature = new AverageTemperature(index);
            for(int i = 0; i < index; i++) {
                System.out.println("Day " + i + 1 + "'s high temperature");
                averageTemperature.addTemperature(i, scanner.nextFloat());
            }
            System.out.println("Average: " + averageTemperature.getAverageTemperature());
        } catch (Exception e) {
            System.out.println("Error while reading input.");
        }
    }
}
