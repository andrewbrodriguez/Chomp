import java.awt.*;
//I haven't touched this

public class Player {
    public Chip[][] gameBoard;

    public Player() {

    }

    //add your code to return the row and the column of the chip you want to take.
    //you'll be returning a data type called Point which consists of two integers.
    public Point move(Chip[][] pBoard) {

        gameBoard = pBoard;
        Point myMove = new Point(0, 0);

        int column = 0;
        int row = 0;


        myMove = new Point(row, column);
        return myMove;
    }
}
