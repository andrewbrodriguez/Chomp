import java.awt.*;


//Note any code in here is not used at all in the final analysis this is just the very start of my work
//So this is just a hard coded 3x3 bot





public class Computer {

    public Chip[][] gameBoard;
    public int outCome;
    public int losingBoard;





    public Point move(Chip[][] pBoard){

        gameBoard = pBoard;
        Point myMove = new Point(0,0);



        //STARTING OFF NICE AND EASY, LET'S HARD CODE THE BEST 3X3 STRATEGY



        //The row and collum where the Compute will make its move
        int myCol=0;
        int myRow=0;

        //A boolean to check if the full perimeter of the 3x3 is intact
        boolean fullPerm;

        //the total num of chips left, im not sure if i need this but it could be helpful
        int totalChips = 0;

        //A bool to simply state when the computer has made it move
        boolean moveMade = false;


        //An int to control which outcome we have created in our first sequence of moves



        //Tally up the total number of chips and save it
        for(int i=0;i<10;i++){
            for(int c=0;c<10;c++) {
                if (gameBoard[i][c].isAlive){
                    totalChips++;
                }
            }
        }

        //Check for the perimeter
        if(gameBoard[7][0].isAlive&&gameBoard[8][0].isAlive&&gameBoard[9][1].isAlive&&gameBoard[9][2].isAlive){
            fullPerm=true;
        }
        else{
            fullPerm=false;
        }

        //We're done analyzing the board to start now lets make some choices for moves

        //If we can, take 8,1 and leave the full perm this will insure our victory
        if(gameBoard[8][1].isAlive&&fullPerm){
            //Save the move
            myCol=8;
            myRow=1;
            moveMade=true;

            //we have to make a sequence of moves to win from here on out so we begin that here
            outCome=1;
        }

        if(outCome==1){
            //So now we have a board that looks like this
            //XOO
            //XOO
            //0XX
            //We want to match the players move from here on out

            if(!gameBoard[9][2].isAlive&&gameBoard[9][1].isAlive&&!moveMade&&totalChips==4){
                myCol=7;
                myRow=0;
                moveMade=true;
            }

            if(!gameBoard[7][0].isAlive&&gameBoard[8][0].isAlive&&!moveMade&&totalChips==4){
                myCol=9;
                myRow=2;
                moveMade=true;

            }

        }

        //So ^^^ that is a full win sequence as in if we get outcome 1, the game is over we simply have to walk the player down



        //Now we need to consider the other options.
        //So far that first sequence accounts for, computer having the first move, and 3 possible boards handed to us
        //That means that we now have to account for the remaining 5 boards
        //2 of those are easy to handle like so

        if(!gameBoard[8][0].isAlive&&!moveMade){
            myCol=9;
            myRow=1;
            moveMade=true;
        }

        if(!gameBoard[9][1].isAlive&&!moveMade){
            myCol=8;
            myRow=0;
            moveMade=true;
        }

        //So a bit of an apifany here, I needed this code in my outCome1 part and here, but if I just have it here then it does the same
        //The realization from that can be that this may be how we actually make the code, becuause we are covering multiple moves with one if
        //So my theory is that maybe we can get this simplfied even more so a lot of moves get covered at once

        //Now that's five possible starting boards covered and obv the computer starts
        //So we have to account for the remaining 3 starting boards

        if(totalChips==6){
            if(gameBoard[8][2].isAlive&&!moveMade){
                myCol=8;
                myRow=2;
                moveMade=true;


            }
            if(gameBoard[7][1].isAlive&&!moveMade){
                System.out.println("we should be taking 7,1");
                myCol=7;
                myRow=1;
                moveMade=true;

            }
        }

        //Now we need to handle the final possible board we get handed
        //That board is the scenario when the player takes 8, 1
        //This is a losing board so i have added the int losing board which i will use to set when we have a losing board


        if(!moveMade&&!gameBoard[8][1].isAlive){
            losingBoard=1;
        }

        //So we have on possible losing board set, im making this an int so we can use it past 3x3

        if(losingBoard==1){

            if(!moveMade&&gameBoard[9][2].isAlive){
                myCol=9;
                myRow=2;
                moveMade=true;
            }
            if(!moveMade&&gameBoard[7][0].isAlive){
                myCol=7;
                myRow=0;
                moveMade=true;
            }
        }

        //I think this should be the final piece in hard coding a full 3x3 perfect AI
        //This is just ensuring that even if we are about to lose we still make a move
        if(totalChips==3&&gameBoard[9][1].isAlive&&gameBoard[8][0].isAlive){
            myCol=9;
            myRow=0;

        }





        myMove = new Point(myCol,myRow);


        return myMove;
    }

    public void Reset(){
        losingBoard=0;
        outCome=0;
    }



}
