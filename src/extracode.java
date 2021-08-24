public class extracode {


//Nothing at all important in here just a bunch of extra code i was trying to use but never did



    //    public int[] takeChip(int row, int col) {
//        int[] temp = new int[10];
//        temp = layout;
//
//        for (int i = 0; i < row + 1; i++) {
//            if (layout[i] < col) {
//                temp[i] = layout[i];
//            } else {
//                temp[i] = col;
//            }
//        }
//
//        return temp;
//
//    }


//    public int[] checkBoard(int bSize) {
////        int[] temp = new int[10];
////        for (int r = 0; r < bSize; r++) {
////
////            for (int c = 0; c < layout[r]; c++) {
////
////                temp = takeChip(r, c);
////
////                return temp;
////            }
//
//
//
//
//
//
//
//    }


    // if(winners.contains(threeXthree)){
//            for(int i=0; i<winners.size(); i++){
//                if(threeXthree==winners.get(i)){
//                    point[0]=winners.get(i).moveR;
//                    point[1]=winners.get(i).moveC;
//                    System.out.println("sending the move " + point[0] + " " +point[1]);
//                }
//            }
//        }
//
//        if(losers.contains(threeXthree)){
//            for(int i=0; i < losers.size(); i++){
//                if(threeXthree==losers.get(i)){
//                    point[0]=losers.get(i).moveR;
//                    point[1]=losers.get(i).moveC;
//                    System.out.println("sending the move " + point[0] + " " +point[1]);
//                }
//            }
//        }
//
//  for (int i = 0; i < boardCount; i++) {
//        for (int r = 0; r < boardSize; r++) {
//            for (int c = 0; c < r; c++) {
//
//
//                tempBoard.layout = boards[i];
//
//
//
//                for (int x = 0; x < winnerCount; x++) {
//                    if (tempBoard.takeChip(r, c) == winners.get(x).layout) {
//                        boards[i].loser = true;
//
//
//                        //losers = Arrays.copyOf(losers, loserCount + 1);
//                        //losers[losers.length - 1] = boards[i];
//
//                        losers.add(boards[i]);
//                        loserCount++;
//                    }
//
//                }
//            }
//        }








//
//        for(int a=0; a<4; a++){
//            for(int b=0; b<4; b++){
//                for(int c=0; c<4; c++){
//
//                    //validate
//                    if(c<=b && b<=a){
//
//                        boards[boardCount] = new Board(a,b,c);
//                        boardCount++;
//                    }
//                }
//            }
//        }



//        for(int i=0; i<boardCount; i++){
//            System.out.println("Board: " + i + " looks like this " + boards[i].LayoutString);
//        }


/*
        //Set the zeroith board to the base winner
        boards[1].winner=true;

        for(int z=0; z<boardCount; z++) {
            for (int r = 0; r < 3; r++) {
                for (int i = 1; i < boards[z].layout[r]; i++) {
                    for (int x = 0; x < 3; x++) {
                        tempBoard.layout[x] = boards[z].layout[x];
                    }
                    tempBoard.layout[r] -= i;
                    tempBoard.validate();

                    for (int c = 0; c < boardCount; c++) {
                        if (tempBoard.layout[2] == boards[c].layout[2] && tempBoard.layout[1] == boards[c].layout[1] && tempBoard.layout[0] == boards[c].layout[0]) {
                            if (boards[c].winner) {
                                boards[z].loser = true;
                            }
                            if (boards[c].loser) {
                                boards[z].winner = true;
                            }
                        }
                    }
                }
            }
        }















        //Right so now here's our main for loop
//        for(int i=1; i<boardCount; i++){
//
//            for (int x=0; x<3; x++){
//                tempBoard[x]=boards[i].layout[x];
//
//            }
//
//            for(int z=0; z<3; z++){
//                tempBoard[z]-=1;
//                for(int y=0; y<boardCount; y++){
//                    if(tempBoard[0]==boards[y].layout[0]&&tempBoard[1]==boards[y].layout[1]&&tempBoard[2]==boards[y].layout[2]&&boards[y].winner){
//                        boards[i].loser=true;
//
//                    }
//
//                    if(tempBoard[0]==boards[y].layout[0]&&tempBoard[1]==boards[y].layout[1]&&tempBoard[2]==boards[y].layout[2]&&boards[y].loser){
//                        boards[i].winner=true;
//
//                    }
//                }
//                tempBoard[z]+=1;
//            }
//
//
//        }

        for(int i=0; i<boardCount; i++){
            if(boards[i].loser){
                loserCount++;
                System.out.println(boards[i].LayoutString + " LOSER");
            }
            else if(boards[i].winner){
                winnerCount++;
                System.out.println(boards[i].LayoutString + " WINNER");

            }
        }




*/





            /*
//        //so given board N lets find all boards that are one move away from it
//        N=230;
//        chipsInBoard=0;
//
//        System.out.println("Board " + N + " looks like this: " + allBoards[N][0] + " " + allBoards[N][1]+ " " + allBoards[N][2]+ " " + allBoards[N][3]+ " " + allBoards[N][4]+ " " + allBoards[N][5]+ " " + allBoards[N][6]+ " " + allBoards[N][7]+ " " + allBoards[N][8]+ " " + allBoards[N][9]);
//
//
//        for(int i=0; i<10; i++){
//            System.out.println("i = "+ i + " " + allBoards[N][i]);
//
//            for(int t=0; t<allBoards[N][i]; t++){
//                System.out.print(t);
//
//
//
//                //Lets copy over board N to our temp board
//                for (int r = 0; r < 10; r++) {
//                    //we save the allBoards[N] as the tempBoard for now
//                    tempBoard[r] = allBoards[N][r];
//                    chipsInBoard+=allBoards[N][r];
//                }
//
//
//                tempBoard[i]-=t;
//
//                //must validate move
//
//                for(int x=0; x<9; x++) {
//                    if (tempBoard[x+1] > tempBoard[x]) {
//                        tempBoard[x+1] = tempBoard[x];
//                    }
//                }
//
//                //System.out.println("Temp Board" + ": " + tempBoard[0] + " " + tempBoard[1] + " " + tempBoard[2] + " " + tempBoard[3] + " " + tempBoard[4] + " " + tempBoard[5] + " " + tempBoard[6] + " " + tempBoard[7] + " " + tempBoard[8] + " " + tempBoard[9]);
//
//                for(int d=0; d<allBoards.length; d++){
//
//                }
//            }
//
//
//        }














        //okay im going to write this out because im at a block rn
        //what i need to do is to check to see if any given board can reach a winning board in one move
        //if it can then we need to mark that board as a loser

        //first for loop is iterating through all the boards
        //rn ill use a set amount so i don't put undue stress on my laptop

        for (int i = 1; i < totalBCount; i++) {

            //we will use a temp board to copy back and forth
            for (int r = 0; r < 10; r++) {
                //we save the allBoards[i] as the tempBoard for now
                //we have to copy over all 10 ints
                tempBoard[r] = allBoards[i][r];
            }

            //okay now we need to check and see if this temp board can be made into a winning board
            //we take one chip, rn im a little confused as to which chip i should take
            //but first we're gonna need a for loop that checks for all of our winning boards


            //now we need an if statement to see if our temp board is one off the winning board
            for (int b = 0; b < 10; b++) {
                //subtract one from any one of the rows
                tempBoard[b] -= 1;


                //This code is gonna see if the board we have just made is a winner which means the original board is a loser
                //we gotta check against all our winning boards
                for (int r = 0; r < totalWBCount; r++) {
                    //if this new board is an exact match
                    if (tempBoard[0] == winningBoards[r][0] && tempBoard[1] == winningBoards[r][1] && tempBoard[2] == winningBoards[r][2] && tempBoard[3] == winningBoards[r][3] && tempBoard[4] == winningBoards[r][4] && tempBoard[5] == winningBoards[r][5] && tempBoard[6] == winningBoards[r][6] && tempBoard[7] == winningBoards[r][7] && tempBoard[8] == winningBoards[r][8] && tempBoard[9] == winningBoards[r][9]) {
                        //revert to the original board
                        tempBoard[b] += 1;

                        //now we need to make sure this additional loser does not exist

                        for (int x = 0; x < totalLBCount; x++) {

                            if (tempBoard[0] != losingBoards[x][0] || tempBoard[1] != losingBoards[x][1] || tempBoard[2] != losingBoards[x][2] || tempBoard[3] != losingBoards[x][3] || tempBoard[4] != losingBoards[x][4] || tempBoard[5] != losingBoards[x][5] || tempBoard[6] != losingBoards[x][6] || tempBoard[7] != losingBoards[x][7] || tempBoard[8] != losingBoards[x][8] || tempBoard[9] != losingBoards[x][9]) {


                                //We now have a new distinct losing board
                                //We should copy it over to our losing board array

                                //for bug testing
                                //System.out.println("Temp Board" + ": " + tempBoard[0] + " " + tempBoard[1] + " " + tempBoard[2] + " " + tempBoard[3] + " " + tempBoard[4] + " " + tempBoard[5] + " " + tempBoard[6] + " " + tempBoard[7] + " " + tempBoard[8] + " " + tempBoard[9] + "    This is a stand in for board " + i + ": " + allBoards[i][0] + " " + allBoards[i][1] + " " + allBoards[i][2] + " " + allBoards[i][3] + " " + allBoards[i][4] + " " + allBoards[i][5] + " " + allBoards[i][6] + " " + allBoards[i][7] + " " + allBoards[i][8] + " " + allBoards[i][9]);


                                //so we need another 10 for loop to save our losing board
                                for (int c = 0; c < 10; c++) {
                                    losingBoards[totalLBCount][c] = tempBoard[c];
                                }
                                //increase the number of losing boards
                                totalLBCount++;
                                break;
                            }
                        }
                    }
                }











//                if(tempBoard[0]==0&&tempBoard[1]==0&&tempBoard[2]==0&&tempBoard[3]==0&&tempBoard[4]==0&&tempBoard[5]==0&&tempBoard[6]==0&&tempBoard[7]==0&&tempBoard[8]==0&&tempBoard[9]==0){
//                    //Set the first board to the base winner
//                    for (int x = 0; x < 10; x++) {
//                        winningBoards[totalWBCount][x] = tempBoard[x];
//                    }
//                    //increase the number of winning boards
//                    totalWBCount++;
//                }
//                else{
//                    System.out.println("so we gotta keep taking chips cause this aint either");
//                }
//


                tempBoard[b] += 1;


            }
        }


*/







}
