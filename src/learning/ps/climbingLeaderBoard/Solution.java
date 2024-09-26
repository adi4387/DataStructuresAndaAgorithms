package learning.ps.climbingLeaderBoard;

import java.io.*;
import java.math.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'climbingLeaderboard' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ranked
     *  2. INTEGER_ARRAY player
     */

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        // Write your code here
        List<Integer> result = new ArrayList<Integer>();
        Map<Integer, Integer> uniqueRankMap = new HashMap<>();
        int rank = 1;
        for (Integer integer : ranked) {
            if (!uniqueRankMap.containsKey(integer)) {
                uniqueRankMap.put(integer, rank++);
            }
        }
        int initial = ranked.size() - 1;
        rank = ranked.size();
        for(int playerScore : player) {
            for(int i = initial; i >= 0; i--) {
                if(playerScore >= ranked.get(i)) {
                    rank = uniqueRankMap.get(ranked.get(i));
                    initial = i - 1;
                } else {
                    break;
                }
            }
            result.add(rank);
        }

        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {

        Path inputFilePath = Paths.get("input.txt");

        try(BufferedReader reader = Files.newBufferedReader(inputFilePath, UTF_8)) {
            writeToFile(reader.readLine().getBytes(UTF_8));
        }



        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.climbingLeaderboard(ranked, player);

        System.out.println(result);
        bufferedReader.close();
    }

    private static void writeToFile(byte[] output) throws IOException {
        Path outputFilePath = Paths.get("output.txt");
        try(var out = Files.newOutputStream(outputFilePath, StandardOpenOption.WRITE)) {
            out.write(output);
        }
    }
}

