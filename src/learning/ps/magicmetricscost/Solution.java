package learning.ps.magicmetricscost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'formingMagicSquare' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY s as parameter.
     */
    private static int[][][] magicArrays = new int[][][]{
            {
                    {4,9,2},
                    {3,5,7},
                    {8,1,6}
            },
            {
                    {8,3,4},
                    {1,5,9},
                    {6,7,2}
            },
            {
                    {6,1,8},
                    {7,5,3},
                    {2,9,4}
            },
            {
                    {2,7,6},
                    {9,5,1},
                    {4,3,8}
            },
            {
                    {8,1,6},
                    {3,5,7},
                    {4,9,2}
            },
            {
                    {4,3,8},
                    {9,5,1},
                    {2,7,6}
            },
            {
                    {2,9,4},
                    {7,5,3},
                    {6,1,8}
            },
            {
                    {6,7,2},
                    {1,5,9},
                    {8,3,4}
            }
    };

    public static int formingMagicSquare(List<List<Integer>> s) {
        int max = Integer.MAX_VALUE;
        for (int[][] magicArray : magicArrays) {
            int sum = 0;
            for (int i = 0; i < s.size(); i++) {
                for (int j = 0; j < s.size(); j++) {
                    sum += Math.abs(magicArray[i][j] - s.get(i).get(j));
                }
            }
            if (sum < max) {
                max = sum;
            }
        }
        return max;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<List<Integer>> s = new ArrayList<>();

        IntStream.range(0, 3).forEach(i -> {
            try {
                s.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.formingMagicSquare(s);
        System.out.println(result);
        bufferedReader.close();
    }
}
