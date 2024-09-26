package learning.oops;

public class Polymorphism {

}

interface Shape {
    int area() throws Exception;
}

class Square implements Shape {

    @Override
    public int area() throws RuntimeException {
        return 0;
    }
}
