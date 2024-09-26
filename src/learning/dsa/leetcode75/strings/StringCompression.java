package learning.dsa.leetcode75.strings;

public class StringCompression {

    public static void main(String[] args) {
        System.out.println(compress(new char[]{'a'}));
    }

    public static int compress(char[] chars) {
        if(null == chars || chars.length == 0) {
            return 0;
        }

        StringBuilder result = new StringBuilder();
        int count = 1;
        boolean same;
        for(int i = 0; i < chars.length - 1; i++) {
            if(chars[i] == chars[i+1]) {
                count++;
                same = true;
            } else {
                same = false;
            }
            if(!same) {
                result.append(chars[i]);
                if(count > 1) {
                    result.append(count);
                }
                count = 1;
            }
        }
        result.append(chars[chars.length - 1]);
        if(count > 1) {
            result.append(count);
        }
        for(int i=0; i<result.length(); i++){
            chars[i] = result.charAt(i);
        }
        return result.length();
    }
}
