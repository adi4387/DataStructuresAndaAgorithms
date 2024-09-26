package learning.dsa.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Brainvita {

    ArrayList<ArrayList<Integer>> board;
    ArrayList<ArrayList<ArrayList<Integer>>> boards;
    ArrayList<Move> moves;

    public Brainvita(ArrayList<ArrayList<Integer>> board) {
        this.board = board;
        boards = new ArrayList<>();
        moves = new ArrayList<>();
    }

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> board = new ArrayList<>();

        Integer[] array1 = {-1,-1,1,1,1,-1,-1};
        Integer[] array2 = {-1,-1,1,1,1,-1,-1};
        Integer[] array3 = {1,1,1,1,1,1,1};
        Integer[] array4 = {1,1,1,0,1,1,1};
        Integer[] array5 = {1,1,1,1,1,1,1};
        Integer[] array6 = {-1,-1,1,1,1,-1,-1};
        Integer[] array7 = {-1,-1,1,1,1,-1,-1};

        board.add(new ArrayList<>(Arrays.asList(array1)));
        board.add(new ArrayList<>(Arrays.asList(array2)));
        board.add(new ArrayList<>(Arrays.asList(array3)));
        board.add(new ArrayList<>(Arrays.asList(array4)));
        board.add(new ArrayList<>(Arrays.asList(array5)));
        board.add(new ArrayList<>(Arrays.asList(array6)));
        board.add(new ArrayList<>(Arrays.asList(array7)));
        Brainvita brainvita = new Brainvita(board);
        brainvita.print();
        brainvita.solve();
    }

    private void print() {
        for(ArrayList<Integer> row : board) {
            for(int i : row) {
                if(i == -1) {
                    System.out.print("- ");
                } else {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        }
    }

    private int count() {
        int count = 0;
        for(int i = 0; i < board.size(); i++) {
            for(int j = 0; j < board.size(); j++) {
                if(board.get(i).get(j) == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean solve() {
        if(boards.contains(board)) {
            return false;
        }

        if(count() == 1 && board.get(3).get(3) == 1) {
            print();
            printMoves();
            return true;
        } else {
            ArrayList<Move> moveList = findOpenPosition();
            Collections.sort(moveList);

            for(Move move : moveList) {
                makeMove(move);
                if(solve()) {
                    return true;
                } else {
                    undoMove(move);
                }
            }
        }
        if(!boards.contains(board)) {
            boards.add(deepCopy(board));
        }
        return false;
    }

    private static ArrayList<ArrayList<Integer>> deepCopy(ArrayList<ArrayList<Integer>> board) {
        ArrayList<ArrayList<Integer>> newBoard = new ArrayList<>();
        for(ArrayList<Integer> line : board) {
            ArrayList<Integer> temp = new ArrayList<>();
            for(Integer i : line) {
                temp.add(i);
            }
            newBoard.add(temp);
        }
        return newBoard;
    }

    private void printMoves() {
        for(Move move : moves) {
            System.out.println(move);
        }
    }

    private ArrayList<Move> findOpenPosition() {
        ArrayList<Move> possibilities = new ArrayList<>();
        for(int i = 0; i < board.size(); i++) {
            for(int j = 0; j < board.get(i).size(); j++) {
                if(board.get(i).get(j) == 0) {
                    if(i - 2 >= 0) {
                        if((board.get(i-2).get(j) == 1) &&  (board.get(i-1).get(j) == 1)) {
                            possibilities.add(new Move((i-2) * 7 + j, (i-1) * 7 + j, i*7 + j));
                        }
                    }
                    if(j - 2 >= 0) {
                        if((board.get(i).get(j-2) == 1) &&  (board.get(i).get(j-1) == 1)) {
                            possibilities.add(new Move(i * 7 + j - 2, i * 7 + j - 1, i*7 + j));
                        }
                    }
                    if(i + 2 <= 6) {
                        if((board.get(i+2).get(j) == 1) &&  (board.get(i+1).get(j) == 1)) {
                            possibilities.add(new Move((i+2) * 7 + j, (i+1) * 7 + j, i*7 + j));
                        }
                    }
                    if(j + 2 <= 6) {
                        if((board.get(i).get(j+2) == 1) &&  (board.get(i).get(j+1) == 1)) {
                            possibilities.add(new Move(i * 7 + j + 2, i * 7 + j + 1, i*7 + j));
                        }
                    }
                }
            }
        }
        return possibilities;
    }

    private void makeMove(Move move) {
        board.get(move.from/7).set(move.from%7, 0);
        board.get(move.hole/7).set(move.hole%7, 0);
        board.get(move.to/7).set(move.to%7, 1);
        moves.add(move);
    }

    private void undoMove(Move move) {
        board.get(move.from/7).set(move.from%7, 1);
        board.get(move.hole/7).set(move.hole%7, 1);
        board.get(move.to/7).set(move.to%7, 0);
        moves.remove(moves.size() - 1);
    }
}

class Move implements Comparable<Move> {
    int from;
    int hole;
    int to;

    public Move(int from, int hole, int to) {
        this.from = from;
        this.hole = hole;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Move{" +
                "from=" + from +
                ", hole=" + hole +
                ", to=" + to +
                '}';
    }

    @Override
    public int compareTo(Move o) {
        return Integer.valueOf(this.from).compareTo(Integer.valueOf(o.from));
    }
}