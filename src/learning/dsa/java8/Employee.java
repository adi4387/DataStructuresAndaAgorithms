package learning.dsa.java8;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Employee {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(5,1,2,3,4,6,8,9,10));

        list.sort((o1,o2) -> {
            if (o1%2 == o2%2) {
                // Both numbers are odd or both numbers are even
                return 0;
            }
            // One is odd, the other one is even
            if (o1%2 == 0) {
                return -1;
            }
            return 1;
        });
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println(next);
            list.remove(next);
        }
        list.stream().forEach(System.out::println);
    }

}
