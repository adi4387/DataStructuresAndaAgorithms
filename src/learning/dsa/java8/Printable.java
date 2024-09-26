package learning.dsa.java8;

@FunctionalInterface
public interface Printable<T> {

    void print(T content);
}
