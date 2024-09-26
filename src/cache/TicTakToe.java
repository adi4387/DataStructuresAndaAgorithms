package cache;

public class TicTakToe {

    private int[][] board = new int[3][3];
    private final int players = 2;

    public boolean place(int x, int y, Player player) {
        int value = player.getValue();
        board[x][y] = value;
        return isWinner(value);
    }

    private boolean isWinner(int value) {
        return true;
    }
}

class Player {
    int value;

    public Player(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}