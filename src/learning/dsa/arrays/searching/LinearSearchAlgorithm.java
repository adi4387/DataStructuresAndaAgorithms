package learning.dsa.arrays.searching;

import java.util.List;

public class LinearSearchAlgorithm<T> {

    public int search(List<T> data, T candidate) {
        if(data == null && candidate == null) {
            return -1;
        }
        for(int i = 0; i < data.size(); i++) {
            if (candidate.equals(data.get(i))) {
                return i;
            }
        }
        return -1;
    }
}
