package learning.dsa.arrays;

import java.util.HashMap;

public class IntegerToRoman {

    public static void main(String[] args) {
        System.out.println(intToRoman(3749));
    }

    public static String intToRoman(int num) {
        var roman = "";
        var integerStringMap = new HashMap<Integer, String>();
        integerStringMap.put(1, "I");
        integerStringMap.put(2, "II");
        integerStringMap.put(3, "III");
        integerStringMap.put(4, "IV");
        integerStringMap.put(5, "V");
        integerStringMap.put(6, "VI");
        integerStringMap.put(7, "VII");
        integerStringMap.put(8, "VIII");
        integerStringMap.put(9, "IX");
        integerStringMap.put(10, "X");
        integerStringMap.put(20, "XX");
        integerStringMap.put(30, "XXX");
        integerStringMap.put(40, "XL");
        integerStringMap.put(50, "L");
        integerStringMap.put(60, "LX");
        integerStringMap.put(70, "LXX");
        integerStringMap.put(80, "LXXX");
        integerStringMap.put(90, "XC");
        integerStringMap.put(100, "C");
        integerStringMap.put(200, "CC");
        integerStringMap.put(300, "CCC");
        integerStringMap.put(400, "CD");
        integerStringMap.put(500, "D");
        integerStringMap.put(600, "DC");
        integerStringMap.put(700, "DCC");
        integerStringMap.put(800, "DCCC");
        integerStringMap.put(900, "CM");
        integerStringMap.put(1000, "M");
        integerStringMap.put(2000, "MM");
        integerStringMap.put(3000, "MMM");

        if (num <= 0) {
            return "";
        }
        int remainder;
        int decimalPosition = 1;
        int key;
        while(num > 0) {
            remainder = num % 10;
            num = num / 10;
            key = remainder * decimalPosition;
            roman = integerStringMap.get(key) == null ? "" + roman : integerStringMap.get(key) + roman;
            decimalPosition *= 10;
        }
        return roman;
    }
}
