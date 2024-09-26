package learning.dsa.leetcode75.strings;

public class ReverseWords {

    public static void main(String[] args) {
        System.out.println("[" + reverseWords("a good   example") + "]");
    }

    public static String reverseWords(String s) {
        String delimiter = " ";
        StringBuilder result = new StringBuilder();
        if(null == s || s.length() == 0) {
            return "";
        }
        var words = s.split(delimiter);
        for(int i = words.length - 1; i >= 0; i--) {
            String str = words[i].trim();
            if(i != words.length - 1 && str.length() > 0) {
                result.append(delimiter);
            }
            result.append(str);
        }
        return result.toString();
    }
}
