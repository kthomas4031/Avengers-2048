/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avengers;


/**
 *
 * @author Kyle
 */
public class Game {
    private final Board board;
    private int score;
    
    public Game() {
        board = new Board();
        score = 0;
    }
    
    public String getMax(){
        int max = board.getTempMax();
        switch (max){
            case 0:
                return ("Widow.wav");
            case 2:
                return ("Widow.wav");
            case 4:
                return ("Valk.wav");
            case 8:
                return ("Spidey.wav");
            case 16:
                return ("Rocket.wav");
            case 32:
                return ("Panther.wav");
            case 64:
                return ("Witch.wav");
            case 128:
                return ("Cap.wav");
            case 256:
                return ("Strange.wav");
            case 512:
                return ("Iron.wav");
            case 1024:
                return ("Hulk.wav");
            case 2048:
                return ("End.wav");
            default:
                return null;
        }
    }
        
    public boolean checkWin(){
        int val;
        for(int i = 0 ; i < 4 ; i++){
            for (int j = 0 ; j < 4 ; j++){
                val = board.getBoard().get(i).get(j).getValue();
                if (val == 2048) return true;
            }
        }
        return false;
    }
    
    
    //Checking the four tiles in the middle is simple. You just have to check the 4 surrounding tiles to see if they can merge
    //For edges and corners, you can't check all 4 sides because you can't access those sides without throwing errors. 
    //So instead, it checks the sides that are there.
//    public boolean checkLose(){
//        int val;
//        for(int i = 0 ; i < 3 ; i++){
//            for (int j = 0 ; j < 3 ; j++){
//                val = board.getBoard().get(i).get(j).getValue();
//                //First checks to see if there's any 0s left and ends it there if there are.
//                if (val == 0)
//                    return false;
//                //Checks middle tiles for movement potential
//                if ( i != 0 && i != 3 && j != 0 && j != 3){
//                    if (val == board.getBoard().get(i+1).get(j).getValue()
//                            || val == board.getBoard().get(i-1).get(j).getValue()
//                            || val == board.getBoard().get(i).get(j-1).getValue() 
//                            || val == board.getBoard().get(i).get(j+1).getValue())
//                        return false;
//                }else if(i == 0){
//                    //checks top
//                    switch(j){
//                        case 0:
//                            if(val == board.getBoard().get(i+1).get(j).getValue()
//                            || val == board.getBoard().get(i).get(j+1).getValue())
//                                return false;
//                            break;
//                        case 3:
//                            if(val == board.getBoard().get(i+1).get(j).getValue()
//                            || val == board.getBoard().get(i).get(j-1).getValue())
//                                return false;
//                            break;
//                        default:
//                            if(val == board.getBoard().get(i+1).get(j).getValue()
//                            || val == board.getBoard().get(i).get(j-1).getValue() 
//                            || val == board.getBoard().get(i).get(j+1).getValue())
//                                return false;
//                            break;
//                    }
//                }else if(i == 3){
//                    //checks bottom
//                    switch(j){
//                        case 0:
//                            if(val == board.getBoard().get(i+1).get(j).getValue()
//                            || val == board.getBoard().get(i).get(j+1).getValue())
//                                return false;
//                            break;
//                        case 3:
//                            if(val == board.getBoard().get(i+1).get(j).getValue()
//                            || val == board.getBoard().get(i).get(j-1).getValue())
//                                return false;
//                            break;
//                        default:
//                            if(val == board.getBoard().get(i+1).get(j).getValue()
//                            || val == board.getBoard().get(i).get(j-1).getValue() 
//                            || val == board.getBoard().get(i).get(j+1).getValue())
//                                return false;
//                            break;
//                    }
//                }else if(j == 0){
//                    //checks left-side
//                    switch(i){
//                        case 0:
//                            if(val == board.getBoard().get(i+1).get(j).getValue()
//                            || val == board.getBoard().get(i).get(j+1).getValue())
//                                return false;
//                            break;
//                        case 3:
//                            if(val == board.getBoard().get(i-1).get(j).getValue()
//                            || val == board.getBoard().get(i).get(j+1).getValue())
//                                return false;
//                            break;
//                        default:
//                            if(val == board.getBoard().get(i+1).get(j).getValue()
//                            || val == board.getBoard().get(i-1).get(j).getValue() 
//                            || val == board.getBoard().get(i).get(j+1).getValue())
//                                return false;
//                            break;
//                    }
//                }else if(j == 3){
//                    //checks right-side
//                    switch(i){
//                        case 0:
//                            if(val == board.getBoard().get(i+1).get(j).getValue()
//                            || val == board.getBoard().get(i).get(j-1).getValue())
//                                return false;
//                            break;
//                        case 3:
//                            if(val == board.getBoard().get(i-1).get(j).getValue()
//                            || val == board.getBoard().get(i).get(j-1).getValue())
//                                return false;
//                            break;
//                        default:
//                            if(val == board.getBoard().get(i+1).get(j).getValue()
//                            || val == board.getBoard().get(i-1).get(j).getValue() 
//                            || val == board.getBoard().get(i).get(j-1).getValue())
//                                return false;
//                            break;
//                    }
//                }
//            }
//        }
//        return true;
//    }
    
    //Haha just kidding I hate how much time I spent typing that big ass function cause now I have easy, breezy, and beautiful down here
    public boolean checkLose(){
        return !(board.canMove("Up") || board.canMove("Down") || board.canMove("Left") || board.canMove("Right"));
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public Board getBoard() {
        return board;
    }
    
    
}
