import java.awt.*;
//I haven't touched this

public class Chip {
    //variables
    public int xpos;
    public int ypos;
    public boolean isAlive;
    public Rectangle rec;

    public Chip(int row, int col, int xOff, int yOff, int size){

        isAlive = true;
        xpos = col*size+xOff;
        ypos = row*size+yOff;
        rec = new Rectangle(xpos,ypos, size, size);
    }

}
