package learning.dsa.arrays;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        StringBuilder subStr = new StringBuilder();
        int length = 0;
        int maxLength = 0;
        int i = 0;
        if(s == null || s.length() == 0) {
            return maxLength;
        }
        while(s.length() > 0) {
            char c = s.charAt(i);
            for(int j = 0; j < subStr.length(); j++) {
                if(c == subStr.charAt(j)) {
                    subStr = new StringBuilder(subStr.substring(j + 1));
                    length = subStr.length();
                }
            }
            s = s.substring(i+1);
            subStr.append(c);
            length++;
            if(length > maxLength) {
                maxLength = length;
            }
        }
        return maxLength;
    }
}
