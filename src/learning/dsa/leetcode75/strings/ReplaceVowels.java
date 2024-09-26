package learning.dsa.leetcode75.strings;

public class ReplaceVowels {

    public static void main(String[] args) {
        System.out.println(reverseVowels("aA"));
    }

    public static String reverseVowels(String s) {

        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        if(null == s || s.length() == 0) {
            return "";
        }
        int len = s.length();
        if(len == 1) {
            return s;
        }
        int i = 0;
        int j = len - 1;

        while(i < j) {
            char leftC = s.charAt(i);
            char rightC = s.charAt(j);
            if(isNotVowel(leftC)) {
                i++;
                left.append(leftC);
            }

            if(isNotVowel(rightC)) {
                j--;
                right.append(rightC);
            }

            if(!isNotVowel(leftC) && !isNotVowel(rightC)) {
                left.append(rightC);
                right.append(leftC);
                i++;
                j--;
            }
        }

        String s1 = left + right.reverse().toString();
        if(s1.length() < len) {
            return left.append(s.charAt(i)).append(right).toString();
        }
        return s1;

    }

    public static boolean isNotVowel(char c) {
        return c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u' && c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U';
    }
}
