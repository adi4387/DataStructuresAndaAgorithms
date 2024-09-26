package learning.dsa.java8;

import java.io.Serial;
import java.io.Serializable;

public class SerializedSingleton implements Serializable, Cloneable {

    private static final long serialVersionUID = -7604766932017737115L;
    private String name;

    private SerializedSingleton(){}

    @Override
    public SerializedSingleton clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    private static class SingletonHelper {
        private static final SerializedSingleton instance = new SerializedSingleton();
    }

    public static SerializedSingleton getInstance() {
        return SingletonHelper.instance;
    }

    @Serial
    protected Object readResolve() {
        return getInstance();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
