package learning.dsa.java8;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FilterDuplicates<T> {

    public static void main(String[] args) {
        System.out.println(new FilterDuplicates<Integer>().filterDuplicates(List.of(1,2,3,4,5,5,3)));
    }

    private List<T> filterDuplicates(List<T> list) {

        return list
                .stream()
                .collect(Collectors.toMap(Function.identity(), word -> 1, Math::addExact))
                .entrySet()
                .stream()
                .filter(tIntegerEntry -> tIntegerEntry.getValue() == 1)
                .collect(Collectors.toSet())
                .stream().map(Map.Entry::getKey)
                .toList();

    }
}
