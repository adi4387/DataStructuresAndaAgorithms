package learning.dsa.leetcode75.strings;

public class StringDivision {

    public static void main(String[] args) {
        System.out.println(gcdOfStrings("YOUTUBE", "YOU"));
    }

    public static String gcdOfStrings(String str1, String str2) {

        if(!(str1 + str2).equals(str2 + str1)) {
            return "";
        } else {
            return str1.substring(0, gcd(str1.length(), str2.length()));
        }
    }

    public static int gcd(int a, int b) {
        if(a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
}
