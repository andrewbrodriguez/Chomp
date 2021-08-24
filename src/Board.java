import java.util.Arrays;

public class Board {

    public final int[] board;
    public boolean isWinner;
    public int moveR, moveC;

    public Board() {
        board = new int [3];
    }
    public Board(int [] b) {

        isWinner=false;

        board=b;

        moveR = 9;
        moveC = 0;



    }
    public void setWinner() {
        isWinner = true;
    }

    public void setMove(int theMoveR, int theMoveC) {
        moveR = theMoveR;
        moveC = theMoveC;
    }

    public boolean isBlank() {
        if (board[0] + board[1] + board[2] == 0) {
            return true;
        } else {
            return false;
        }

    }
    public void debug(){
        System.out.print("Coordinates: " + board[0] + board[1] + board[2] +"        ");
        System.out.println("Move: " + moveR + " " + moveC);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return Arrays.equals(board, board1.board);
    }




}
