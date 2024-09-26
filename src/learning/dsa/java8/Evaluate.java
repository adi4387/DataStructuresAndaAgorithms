package learning.dsa.java8;

@FunctionalInterface
public interface Evaluate<I> {

    boolean evaluate(I input);
}
