package learning.dsa.arrays.searching;

import java.util.List;

public class BinarySearchAlgorithm {

    public int search(List<Integer> data, Integer candidate, int low, int high) {
        if(low > high) {
            return -1;
        }
        if((data == null || data.isEmpty()) && candidate == null) {
            return -1;
        }
        int mid = (low + high)/2;
        var t = data.get(mid);
        if(candidate.equals(t)) {
            return mid;
        } else if(candidate > t){
            return search(data, candidate, mid + 1, high);
        } else {
            return search(data, candidate, low, mid - 1);
        }
    }
}
