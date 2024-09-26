package learning.dsa.java8;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.*;

public class TestLearnJavaEight {

    public static void main(String[] args) {
//        Runnable runnable = () -> System.out.println("Run");
//        new Thread(runnable).start();
//
//        Comparator<Integer> comparator = Integer::compare;
//        System.out.println(comparator.compare(10,11));
//
//        Consumer<String>  stringConsumer = (a) -> System.out.println(a.toUpperCase());
//        stringConsumer.accept("Hello");
//
//        BiConsumer<String, String> twoStringConsumer = (a, b) -> System.out.println(a.toUpperCase() + b);
//        twoStringConsumer.accept("Hello", "Java");
//
//        Predicate<Integer> predicate = (i) -> i/10 > 2;
//        Predicate<Integer> predicate1 = (i) -> i/10 > 3;
//        System.out.println(predicate.and(predicate1).test(30));
//
//        Function<String, String> function1 = (name) -> name.toUpperCase();
//        Function<String, String> function2 = (name) -> name.concat("default");
//        System.out.println(function1.compose(function2).apply("java8"));
//
//        UnaryOperator<String> identity = UnaryOperator.identity();
//        System.out.println(identity.apply("Hello"));
//        System.out.println(findUniqueCharacters("Hello World"));
//        List<Integer> integerList = Arrays.asList(1,2,3,4,5,6);
//        System.out.println(integerList.stream().reduce(0, (x,y) -> x > y ? x : y));
//        System.out.println(Runtime.getRuntime().availableProcessors());
//
//        Printable<String> print1 = System.out::println;
//        print1.print("Hello");
//
//        Retrievable<Integer> retrievable = () -> 77;
//        System.out.println(retrievable.retrieve());
//
//        Evaluate<Integer> evaluate = num -> num < 0;
//        System.out.println(evaluate.evaluate(-1));
//        System.out.println(evaluate.evaluate(1));
//
//        Evaluate<Integer> evenOrOdd = num -> num % 2 == 0;
//        System.out.println(evenOrOdd.evaluate(4));
//        System.out.println(evenOrOdd.evaluate(7));
//        Evaluate<String> male = name -> name.startsWith("Mr.");
//        System.out.println(male.evaluate("Mr. Joe Bloggs"));
//        System.out.println(male.evaluate("Ms. Ann Bloggs"));
//        Evaluate<Integer> eligible = num -> num >= 18;
//        System.out.println(eligible.evaluate(33));
//        System.out.println(eligible.evaluate(13));
//
//        IntFunction<String> intFunction = num -> "The number is: "+ num;
//        System.out.println(intFunction.apply(20));
//
//        System.out.println(sortAge(getPeople()));
//        System.out.println(average(List.of(1,2,3,4,5,6,7,8,9,10)));
//        System.out.println(longestName(getPeople()));
//        System.out.println(stringStartingWithSubstring("Mi", getPeople()));
//        System.out.println(productOfAllNumbers(List.of(1,2,3,4,5,6,7,8,9,10)));
//        System.out.println(secondLargestNumber(List.of(1,3,4,5,6,7,8,9,10,2)));
//        System.out.println(sortNameReversed(getPeople()));
        System.out.println(mostSkilled(getPeople()));

    }

    private static List<Person> getPeople() {

        List<Person> result = new ArrayList<>();
        result.add(new Person("Mike", 33, 1.8, List.of("MS", "Java", "SP")));
        result.add(new Person("Mary", 25, 1.4, List.of("Java", "MS")));
        result.add(new Person("Alanord", 34, 1.7, List.of("Java", "SP")));
        result.add(new Person("Zoe", 30, 1.5, List.of("JS")));
        return result;

    }

    private static Map<String, Integer> mostSkilled(List<Person> persons) {
        return persons.stream()
                .flatMap(person -> person.skills().stream())
                .collect(Collectors.toMap(Function.identity(), word -> 1, Math::addExact));
    }

    //Given a list of strings, write a program to sort the strings alphabetically using the Stream API.
    private static List<Person> sortNameReversed(List<Person> persons) {
        return persons.stream()
                .sorted(Comparator.comparing(Person::name).reversed())
                .toList();
    }


    //Write a program to find the second smallest element in a list of integers using the Stream API.
    private static Integer secondSmallestNumber(List<Integer> numbers) {
        return numbers.stream().mapToInt(value -> value)
                .sorted()
                .limit(2)
                .skip(1)
                .findFirst().getAsInt();
    }

    private static Integer secondLargestNumber(List<Integer> numbers) {
        return numbers.stream().mapToInt(value -> value)
                .map(num -> num * -1)
                .sorted()
                .limit(2)
                .skip(1)
                .map(Math::abs)
                .findFirst().getAsInt();
    }

    //Given a list of integers, write a program to find the product of all numbers using the Stream API.
    private static Integer productOfAllNumbers(List<Integer> numbers) {
        return numbers.stream().mapToInt(value -> value)
                .reduce(1, (a,b) -> a*b);
    }

    //Given a list of strings, write a program to count the number of strings containing a specific substring using the Stream API.
    private static List<String> stringStartingWithSubstring(String substring, List<Person> words) {
        return words.stream()
                .map(Person::name)
                .filter(word -> word.contains(substring))
                .toList();

    }
    private static String longestName(List<Person> persons) {
        return persons.stream()
                .map(Person::name)
                .max(comparingInt(String::length))
                .get();
    }

    public static Double average(List<Integer> numbers) {
        return numbers.stream().mapToInt(value -> value)
                .average().getAsDouble();
    }

    public static Integer sumAllEvenNumbers(List<Integer> numbers) {
        return numbers.stream().mapToInt(value -> value)
                .filter(number -> number % 2 == 0)
                .sum();
    }

    private static List<Person> sortAge(List<Person> persons) {
        return persons.stream()
                .sorted(comparing(Person::age))
                .toList();
    }

    private static List<Person> sortName(List<Person> persons) {
        return persons.stream()
                .sorted(comparing(Person::name))
                .toList();
    }

    private static List<Person> sortHeight(List<Person> persons) {
        return persons.stream()
                .sorted(comparing(Person::height))
                .toList();
    }

    public static char[] findUniqueCharacters(String pattern) {
        if(null == pattern) {
            return new char[0];
        }
        Set<Character> discardedCharacters = new HashSet<>();
        Set<Character> uniqueCharacters = new LinkedHashSet<>();
        for(char c : pattern.toCharArray()) {
            if(discardedCharacters.contains(c)) {
                continue;
            }
            if(!uniqueCharacters.add(c)) {
                uniqueCharacters.remove(c);
                discardedCharacters.add(c);
            }
        }
        char[] result = new char[uniqueCharacters.size()];
        int i = 0;
        for(char c : uniqueCharacters) {
            result[i++] = c;
        }
        return result;
    }
}
