package learning.dsa.arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Paranthesis {


    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String name1 = br.readLine();            // Read input from STDIN
//        System.out.println("Hello " + name1);    // Write output to STDOUT
//        isBalanced(name1);
        applicationWithVirus();
    }

    public static void isBalanced(String input) {
        int result = 0;
        if(input == null || input.trim().length() == 0) {
            System.out.println(-1);
            return;
        }

        Stack<Character> stack = new Stack<>();
        char[] array = input.toCharArray();
        Map<Character, Character> parentheses = new HashMap<>();
        parentheses.put(')', '(');
        for(char c : array) {
            if(stack.contains(parentheses.get(c))) {
                stack.pop();
                result++;
            } else {
                stack.push(c);
            }
        }
        if(stack.isEmpty()) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }

    }

    public static void applicationWithVirus() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        String input = br.readLine();
        String[] array = input.split(" ");
        if(num != array.length) {
            return;
        }

        List<Integer> list = Arrays.stream(new String[]{"1","3","5","7"})
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));


        while(list.size() > 1) {
            int lower = 0;
            int sum = list.stream().reduce(0, Integer::sum)/2;
            for(int i = 0; i < list.size(); i++) {
                lower = list.get(i);
                if(lower < sum) {
                    continue;
                }
                if(lower == sum) {
                    list.remove(lower);
                } else {
                    list.remove(i - 1);
                }
            }
            if(lower < sum) {
                list.remove(list.size() - 1);
            }
        }
        System.out.println(list.get(0));

    }
}
