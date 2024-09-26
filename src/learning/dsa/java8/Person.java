package learning.dsa.java8;

import java.util.List;

public record Person(String name, int age, double height, List<String> skills) {
}
