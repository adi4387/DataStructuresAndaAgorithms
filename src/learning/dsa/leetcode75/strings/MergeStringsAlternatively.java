package learning.dsa.leetcode75.strings;

public class MergeStringsAlternatively {

    public static void main(String[] args) {
        System.out.println(mergeAlternately("abc", "pqr"));
    }

    public static String mergeAlternately(String word1, String word2) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < Math.max(word1.length(),word2.length()); i++) {
            if(i < word1.length())
                result.append(word1.charAt(i));
            if(i < word2.length())
                result.append(word2.charAt(i));
        }
        return result.toString();
    }
}
