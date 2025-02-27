package learning.dsa.leetcode75.twoPointers;

public class IsSubsequence {

    public static void main(String[] args) {
        System.out.println(isSubsequence("axc", "ahbgdc"));
    }

    public static boolean isSubsequence(String s, String t) {
        int p1 = 0;
        int p2 = 0;

        while(p1 < s.length() && p2 < t.length()) {
            if(s.charAt(p1) == t.charAt(p2)) {
                p1++;
            }
            p2++;
        }
        return p1 == s.length();
    }
}
