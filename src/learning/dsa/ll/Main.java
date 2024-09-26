package learning.dsa.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        List a = new ArrayList();
        a.add(new Object());

        LinkedList myLinkedList = new LinkedList(1);
//        myLinkedList.append(2);

        // (2) Items - Returns 2 Node
        System.out.println(myLinkedList.removeLast().value);
        // (1) Item - Returns 1 Node
//        System.out.println(myLinkedList.removeLast().value);
        // (0) Items - Returns null
//        System.out.println(myLinkedList.removeLast());


    	/*
        	EXPECTED OUTPUT:
        	----------------
        	2
        	1
        	null

     	*/
//        Predicate predicate = x -> x < 10;
//        System.out.println(predicate.test(4));
     //   Function
    }

}

