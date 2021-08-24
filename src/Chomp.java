//Chomp GAME AI Made by Andrew Rodriguez Summer 2021


import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;

//Keyboard and Mouse
import java.awt.event.*;
import java.util.ArrayList;

//*******************************************************************************
// Class Definition Section

public class Chomp implements Runnable, MouseListener {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //FINALS
    final int WIDTH = 1000;
    final int HEIGHT = 800;
    final int xOffset = 200;
    final int yOffset = 100;
    final int chipWidth = 50;
    final int chipBorder = 4;
    public final int BOARD_SIZE=10;

    //Graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public JPanel buttons;
    public BufferStrategy bufferStrategy;
    //Graphics for board
    public Chip[][] board;
    public boolean gameOver = false;



    //Array lists for possible boards
    public ArrayList<Board> boards;
    public ArrayList<Board> winners;
    public ArrayList<Board> losers;


    //sounds
    public SoundFile youLose;

    //buttons
    public JButton newGame, threeBoard, randomBoard, computerPlayer, myChomp;

    //players
    public Computer computer;
    public Player player;


    //Counter to tell how far into loading we are
    public int boardsChecked;


    //MAIN
    public static void main(String[] args) {
        Chomp myApp = new Chomp();
        new Thread(myApp).start();
    }

    //Chomp Constructor
    public Chomp() {


        //Set up Graphics
        setUpGraphics();
        //Make the new board-- visual
        board = new Chip[10][10];



        //Create the Array Lists
        boards = new ArrayList<Board>();
        winners= new ArrayList<Board>();
        losers = new ArrayList<Board>();
        //We haven't loaded any boards set this to 0
        boardsChecked=0;


        //10 nested for loops for a 10x10 board
        //NOTE: the for loops have two interesting properties
        //They range from 0-11 for the fact that a 10x10 board has 11 possibilities for each row
        //This is because 0 is a possibility
        //They are also designed with the outside loop's variable to make them only the legal boards
        for (int a = 0; a < 11; a++) {
            for (int b = 0; b < (a+1); b++) {
                for (int c = 0; c < (b+1); c++) {
                    for (int d = 0; d < (c+1); d++) {
                        for (int e = 0; e < (d+1); e++) {
                            for (int f = 0; f < (e+1); f++) {
                                for (int g = 0; g < (f+1); g++) {
                                    for (int h = 0; h < (g+1); h++) {
                                        for (int i = 0; i < (h+1); i++) {
                                            for (int j = 0; j < (i + 1); j++) {


                                                int [] B = new int[10];

                                                //A temp array
                                                B[0]=j;
                                                B[1]=i;
                                                B[2]=h;
                                                B[3]=g;
                                                B[4]=f;
                                                B[5]=e;
                                                B[6]=d;
                                                B[7]=c;
                                                B[8]=b;
                                                B[9]=a;

                                                //temp board
                                                Board temp = new Board(B);

                                                //Append temp to Array list
                                                boards.add(temp);


                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        //This is in case you want to play 3x3


//        for(int a=0; a<4; a++){
//            for(int b=0; b<(a+1); b++){
//                for(int c=0; c<(b+1); c++) {
//
//                    //validate
//                    int [] B = new int[3];
//                    B[0]=c;
//                    B[1]=b;
//                    B[2]=a;
//
//                    Board temp = new Board(B);
//
//                    boards.add(temp);
//                    boardCount++;
//
//
//                }
//            }
//        }





        //Hard Code our First winner in
        //This is the metaphorical root to our tree
        //We will define all other winning and losing boards based off of this board
        Board firstWinnerBoard = boards.get(1);
        firstWinnerBoard.setWinner();
        firstWinnerBoard.setMove(3,0);
        winners.add(firstWinnerBoard);

        //Remove the blank board
        //We remove this one because if you ever see it the game is already over
        boards.remove(0);

        //Just print out our total number of boards
        System.out.println("There are " + boards.size() + " possible boards");


        //The main analysis loop
        //We want to do this loop for every board so we go from 0 to the array list size
        for(int i=0; i<boards.size(); i++){

            //we make a temporary board to make adjustments to and to use in this loop
            Board thisBoard = boards.get(i);

            //We use checkBoard here and I'll explain it more later but basically -->>
            //it returns a bool, true if the board is not one move off a winner (NOTE: One move off of a winner makes it a loser)
            //and false if all the moves don't result in a winner (NOTE: All possible boards reached by one singular move, NOT one singular chip)

            //We then also make sure that we haven't already marked this board as a winner
            if( checkBoard(thisBoard)  &&  !winners.contains(thisBoard) )  {

                //We set that board to a winner
                thisBoard.setWinner();

                //and append it to the array
                winners.add(thisBoard);

            }



            //This is just a counter since it takes so damn long to load


            boardsChecked++;

            if(boardsChecked%10000==0){

                System.out.println("We are " + boardsChecked+ " boards of 184755 through loading...");
            }
        }

        //Print out total winners and total losers
        System.out.println("There are " + losers.size() + " losing boards");
        System.out.println("There are " + winners.size() + " winning boards");





        /* Debugs not really needed
        for(int i = 0; i<losers.size(); i++){
            losers.get(i).debug();

        }
        for(int i=0; i<winners.size(); i++){
        winners.get(i).debug();

        }
         */




        //This Calculates the moves to make for each winning board

        //For loop to go through each winner
        for(int i =0; i < winners.size(); i++){

            //Temp array for the boards layout
            int [] standIn = winners.get(i).board;

            //Check each possible move
            for (int row=0; row<BOARD_SIZE; row++){

                for(int col=0; col<standIn[row]; col++){

                    //if we this possible move is NOT going to lead to another winner we can make it
                    if(!winners.contains(takeChip(row, col, standIn.clone()))){
                        winners.get(i).moveC=col;
                        winners.get(i).moveR=row;
                    }


                }
            }


        }







        //Just creates the board
        for (int r = 0; r < board[0].length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = new Chip(r, c, xOffset, yOffset, chipWidth);
            }
        }


        //sounds
        // chipTaken = new SoundFile("takeChip.wav");
        youLose = new SoundFile("sound1.wav");

        //players
        computer = new Computer();
        player = new Player();


    }//


    //one of two analysis methods


    //For checkBoard we need a board as a parameter
    public boolean checkBoard( Board theBoard ){

        //Make an array as stand in for the layout
        int [] board = theBoard.board;

        //These two are going to check each possible chip
        for(int row =0; row < BOARD_SIZE; row++){
            for (int col =0; col < board[row]; col ++){

                //Make a new board as the take chip for that chip
                Board new_board = new Board ( takeChip( row , col , board.clone()) );

                //if that new board is a winner --->>
                if(winners.contains(new_board)) {

                    //Another temp
                    Board tempLoser = theBoard;

                    //---->> then it means the original board is one move off a winner
                    //This means we must mark the original as a loser
                    tempLoser.setMove(row , col);
                    if (tempLoser.isBlank()) {
                        
                    }
                    losers.add(tempLoser);


                    return false;
                }

            }


        }

        return true;
        //If we dont' find that this board leads to a loser that means it must be a winner, return true


    }

    //Second method for taking chips and giving a board back

    //we have three parameters, the coordinates of the 'move', and the board layout itself
    public int[] takeChip(int row, int col, int [] board){

        //stand in
        int [] new_b = board;

        //for every row above the move
        for (int i=0; i<(row+1); i++){
            //make it a valid board
            new_b[i] = Math.min(board[i], col);
        }

        //Send back the board created by this move
        return new_b;



    }


    //Find the move for the current board
    public Point findMove(){
        //this will be what we return, coordinates for a move
        int Row, Col;
        Row=0;
        Col=0;

        //A stand in for the current board
        int[] currentBoard = new int[10];

        //translate the current board into the 10 int style of storing a board
        for(int row=0; row<10; row++){
            for(int col=0; col<10; col++){

                if(board[row][col].isAlive){
                    currentBoard[row]++;

                }

            }

        }


        //itterate through all boards
        for(int i=0; i<boards.size(); i++){
            //if the current board matches that boards layout EXACTLY
            if(boards.get(i).board[0]==currentBoard[0]&&boards.get(i).board[1]==currentBoard[1]&&boards.get(i).board[2]==currentBoard[2]&&boards.get(i).board[3]==currentBoard[3]&&boards.get(i).board[4]==currentBoard[4]&&boards.get(i).board[5]==currentBoard[5]&&boards.get(i).board[6]==currentBoard[6]&&boards.get(i).board[7]==currentBoard[7]&&boards.get(i).board[8]==currentBoard[8]&&boards.get(i).board[9]==currentBoard[9]){
                //Store the moves associated with that board
                Row=boards.get(i).moveR;
                Col=boards.get(i).moveC;

            }
        }

        /* Code for a 3x3
        Board threeXthree = new Board();


        if(currentBoard[0]==0&&currentBoard[1]==0&&currentBoard[2]==0&&currentBoard[3]==0&&currentBoard[4]==0&&currentBoard[5]==0&&currentBoard[6]==0&&currentBoard[7]<4&&currentBoard[8]<4&&currentBoard[9]<4){
            System.out.println("3x3!");


            threeXthree.board[0]=currentBoard[7];
            threeXthree.board[1]=currentBoard[8];
            threeXthree.board[2]=currentBoard[9];

        }
        System.out.println("This board is: " + threeXthree.board[0]+" "+threeXthree.board[1]+" "+threeXthree.board[2]);

        for(int i=0; i<boards.size();i++){

            if(boards.get(i).board[0]==threeXthree.board[0]&&boards.get(i).board[1]==threeXthree.board[1]&&boards.get(i).board[2]==threeXthree.board[2]){

                Row=boards.get(i).moveR;
                Col=boards.get(i).moveC;

            }
        }

         */





        //return the coordinates as a point
        Point myMove = new Point(Row,Col);
        return myMove;

    }








//*******************************************************************************
    //BASE CODE FOR CHOMP


    public void updateBoard(int rowParameter, int columnParameter) {
        int row = rowParameter;
        int col = columnParameter;

        if (row >= 0 && col >= 0 && board[row][col].isAlive) {
            //chip is alive
            //fix board
            for (int r = 0; r < board[0].length; r++) {
                for (int c = 0; c < board[0].length; c++) {
                    if (r <= row && c >= col) {
                        if (row == 9 && col == 0 && !gameOver) {
                            gameOver = true;
                            youLose.play();
                        }
                        board[r][c].isAlive = false;
                    }
                }
            }

        }



    }

    public void run() {

        //for the moment we will loop things forever.
        while (true) {
            render();  // paint the graphics
            pause(50); // sleep for 10 ms
        }
    }

    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //Put all your code for drawing things on the screen here
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 70));
        g.drawString("CHOMP 2.0 BETA", 160, 75);
        g.setColor(Color.lightGray);
        g.fillRect(xOffset, yOffset, 500, 500);
        g.setColor(Color.BLACK);
        g.drawRect(xOffset, yOffset, 500, 500);

        //draw border
        g.setStroke(new BasicStroke(5));
        g.drawRect(xOffset - 10, yOffset - 10, 500 + 20, 500 + 20);

        if(!gameOver) {
            g.setStroke(new BasicStroke(2));
            //draw Grid
            for (int r = 0; r < board[0].length; r++) {
                for (int c = 0; c < board[0].length; c++) {
                    g.drawRect(board[r][c].xpos, board[r][c].ypos, chipWidth, chipWidth);
                }
            }

            //draw Chips
            for (int r = 0; r < board[0].length; r++) {
                for (int c = 0; c < board[0].length; c++) {
                    if (board[r][c].isAlive) {
                        g.setColor(Color.RED);
                        g.fillOval(board[r][c].xpos + chipBorder, board[r][c].ypos + chipBorder, chipWidth - 2 * chipBorder, chipWidth - 2 * chipBorder);
                        g.setColor(Color.BLACK);
                        g.drawOval(board[r][c].xpos + chipBorder, board[r][c].ypos + chipBorder, chipWidth - 2 * chipBorder, chipWidth - 2 * chipBorder);
                        //g.drawRect(board[r][c].xpos,board[r][c].ypos,chipWidth,chipWidth);
                    }
                }
            }

            //draw poison chip
            if (board[9][0].isAlive) {
                g.setColor(Color.BLUE);
                g.fillOval(board[9][0].xpos + chipBorder, board[9][0].ypos + chipBorder, chipWidth - 2 * chipBorder, chipWidth - 2 * chipBorder);
                g.setColor(Color.BLACK);
                g.drawOval(board[9][0].xpos + chipBorder, board[9][0].ypos + chipBorder, chipWidth - 2 * chipBorder, chipWidth - 2 * chipBorder);
                //g.drawRect(board[r][c].xpos,board[r][c].ypos,chipWidth,chipWidth);
            }
        }
        
        if (gameOver) {
            g.setColor(Color.blue);
            g.setFont(new Font("TimesRoman", Font.BOLD, 70));
            g.drawString("GAME OVER ", 235, 300);

        }

        //leave these two lines of code as the last lines of the render( ) method
        g.dispose();
        bufferStrategy.show();
    }

    private void setUpGraphics() {
        frame = new JFrame("Chomp 2.0  2021");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH - 100, HEIGHT - 100));  //sizes the JPanel
        panel.setLayout(new BorderLayout());   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);
        canvas.addMouseListener(this);
        panel.add(canvas, BorderLayout.CENTER);  // adds the canvas to the panel.

        setupButtons();

        buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        buttons.add(newGame);
        buttons.add(randomBoard);
        buttons.add(threeBoard);
        buttons.add(computerPlayer);
        buttons.add(myChomp);
        panel.add(buttons, BorderLayout.SOUTH);


        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
        frame.setLocationRelativeTo(null);

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }

    public void setupButtons(){
        //create buttons
        newGame = new JButton("New Game");
        newGame.setFont(new Font("Arial", Font.BOLD, 25));
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int r = 0; r < board[0].length; r++) {
                    for (int c = 0; c < board[0].length; c++) {
                        board[r][c].isAlive = true;
                    }
                }
                gameOver = false;
            }
        });


        //newGame.setPreferredSize(new Dimension(150,50));
        randomBoard = new JButton("Random Board");
        randomBoard.setFont(new Font("Arial", Font.BOLD, 25));
        randomBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //reset game
                for (int r = 0; r < board[0].length; r++) {
                    for (int c = 0; c < board[0].length; c++) {
                        board[r][c].isAlive = true;
                    }
                }
                gameOver = false;

                //generate random numbers and create new board
                int randomCol;
                int randomRow = (int) (Math.random() * 9 + 2);

                do {
                    randomCol = (int) (Math.random() * 9 + 2);
                } while (randomRow == randomCol);

                for (int r = 0; r < 10 - randomRow; r++) {
                    for (int c = 0; c < board[0].length; c++) {
                        board[r][c].isAlive = false;
                    }
                }
                for (int c = randomCol; c < board[0].length; c++) {
                    for (int r = 0; r < board[0].length; r++) {
                        board[r][c].isAlive = false;
                    }
                }


            }
        });

        threeBoard = new JButton("3x3 Board");
        threeBoard.setFont(new Font("Arial", Font.BOLD, 25));
        threeBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //make 3x3
                //reset game
                for (int r = 0; r < board[0].length; r++) {
                    for (int c = 0; c < board[0].length; c++) {
                        board[r][c].isAlive = true;
                    }
                }
                gameOver = false;


                //Reset the computer
                computer.Reset();


                //make 3x3
                for (int r = 0; r < 7; r++) {
                    for (int c = 0; c < board[0].length; c++) {
                        board[r][c].isAlive = false;
                    }
                }
                for (int c = 3; c < board[0].length; c++) {
                    for (int r = 7; r < 10; r++) {
                        board[r][c].isAlive = false;
                    }
                }
            }
        });

        computerPlayer = new JButton("Computer");
        computerPlayer.setFont(new Font("Arial", Font.BOLD, 25));
        computerPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {

                    //***************************


                    //Time to actually include our code
                    Point theMove = findMove();

                    int row = (int) theMove.getX();
                    int col = (int) theMove.getY();

                    //Translation for 3x3
//                    if(row==0)
//                        row=7;
//                    if(row==1)
//                        row=8;
//                    if(row==2)
//                        row=9;

                    //Make our move
                    if (row >= 0 && col >= 0 && board[row][col].isAlive) {
                        updateBoard(row, col);

                    }

                }
            }
        });


        // randomBoard.setPreferredSize(new Dimension(200,50));
        myChomp = new JButton("My Player");
        myChomp.setFont(new Font("Arial", Font.BOLD, 25));
        myChomp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    Point theMove = player.move(board);
                    int row = (int) theMove.getX();
                    int col = (int) theMove.getY();

                    if (row >= 0 && col >= 0 && board[row][col].isAlive) {
                        //chip is alive
                        //fix board
                        updateBoard(row, col);
                    }


                }
            }
        });
        //myChomp.setPreferredSize(new Dimension(150,50));


    }

    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int row = -1;
        int col = -1;

        for (int r = 0; r < board[0].length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c].rec.contains(e.getX(), e.getY())) {
                    System.out.println("Row: " + r + "   Column:  " + c);
                    row = r;
                    col = c;
                }
            }
        }


        if (row >= 0 && col >= 0 && board[row][col].isAlive) {
            //chip is alive
            //fix board
            updateBoard(row, col);

        }


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}