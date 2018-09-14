/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avengers;

import java.util.ArrayList;
import java.util.Random;

/**
 * Overall the board holds each individual tile so that they can be moved easily. 
 * @author Kyle
 */
public class Board {
    private final ArrayList<ArrayList<Tile>> board = new ArrayList<>();
    private final Random rand = new Random();
    private int score, tempMax;
    
    /**
     Initializes board with 2 random tiles set to either 2 or 4
     */
    public Board() {
        for(int i = 0 ; i < 4 ; i++)
        {
            ArrayList<Tile> temp = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                Tile tempo = new Tile(i, j, 0);
                temp.add(tempo);
            }
            board.add(temp);
        }
        
        for (int i = 0 ; i < 2 ; i++)
        {
            int x = rand.nextInt(4);
            int y = rand.nextInt(4);
            int temp = rand.nextInt(100)+1;
            temp = (temp > 90)? 4 : 2; //91% chance of it being a 2
        
            board.get(x).get(y).setValue(temp);
        }
    }
    
    //ij
    //00 01 02 03
    //10 11 12 13
    //20 21 22 23
    //30 31 32 33
    public void slideRight(){
        if(canMove("Right")){
            cleanBoard();
        for (int i = 3 ; i >= 0 ; i--){
            for (int j = 3 ; j >=0 ; j--){
                if (j != 3 && board.get(i).get(j).getValue() != 0){
                    int dex = j+1;
                    while (dex < 4 && board.get(i).get(dex).getValue() == 0){
                        mergeTile(board.get(i).get(dex-1), board.get(i).get(dex));
                        dex++;
                    }
                    board.get(i).get(dex-1).setFromX(j);
                    if (dex < 4 && !board.get(i).get(dex).isHasMerged()){
                        mergeHorizontal(board.get(i).get(dex-1), board.get(i).get(dex), j);
                    }
                }
            }
        }
        addTile();
        }       
    }
    
    public void slideLeft(){
        if(canMove("Left")){
            cleanBoard();
        for (int i = 0 ; i < 4 ; i++){
            for (int j = 0 ; j < 4 ; j++){
                if (j != 0 && board.get(i).get(j).getValue() != 0){
                    int dex = j-1;
                    while (dex >= 0 && board.get(i).get(dex).getValue() == 0){
                        mergeTile(board.get(i).get(dex+1), board.get(i).get(dex));
                        dex--;
                    }
                    board.get(i).get(dex+1).setFromX(j);
                    if (dex >= 0 && !board.get(i).get(dex).isHasMerged()){
                        mergeHorizontal(board.get(i).get(dex+1), board.get(i).get(dex), j);
                    }
                }
            }
        }
        addTile();
        }
    }
    
    //ij
    //00 01 02 03
    //10 11 12 13
    //20 21 22 23
    //30 31 32 33
    public void slideUp(){
        if(canMove("Up")){
            cleanBoard();
        for (int i = 0 ; i < 4 ; i++){
            for (int j = 0 ; j < 4 ; j++){
                if (i != 0 && board.get(i).get(j).getValue() != 0){
                    int dex = i-1;
                    while (dex >=0 && board.get(dex).get(j).getValue() == 0){
                        mergeTile(board.get(dex+1).get(j), board.get(dex).get(j));
                        dex--;
                    }
                    board.get(dex+1).get(j).setFromY(i);
                    if (dex >= 0 && !board.get(dex).get(j).isHasMerged()){
                        mergeVertical(board.get(dex+1).get(j), board.get(dex).get(j), i);
                    }
                }
            }
        }
        addTile();
        }
    }
    
    public void slideDown(){
        if(canMove("Down")){
            cleanBoard();
        for (int i = 3 ; i >= 0 ; i--){
            for (int j = 3 ; j >=0 ; j--){
                if (i != 3 && board.get(i).get(j).getValue() != 0){
                    int dex = i+1;
                    while (dex < 4 && board.get(dex).get(j).getValue() == 0){
                        mergeTile(board.get(dex-1).get(j), board.get(dex).get(j));
                        dex++;
                    }
                    board.get(dex-1).get(j).setFromY(i);
                    if (dex < 4 && !board.get(dex).get(j).isHasMerged()){
                        mergeVertical(board.get(dex-1).get(j), board.get(dex).get(j), i);
                    }
                }
            }
        }
        addTile();
        }
    }
    
    //If the next tile in the direction the current tile is trying to move is 0, set the next tile to the current tiles value and sets the current tile to 0
    //If the tiles are equal, combine them into the next tile and sets the current tile to 0
    private void mergeTile(Tile deleted, Tile bigger){
        if (bigger.equals(deleted) || bigger.getValue() == 0){
            bigger.merge(deleted);
            deleted.clear();
            bigger.setHasMerged(false);
            deleted.setHasMerged(false);
        }
    }
    //To do transitions
    private void mergeVertical(Tile deleted, Tile bigger, int i){
        if (bigger.equals(deleted) || bigger.getValue() == 0){
            bigger.merge(deleted);
            deleted.clear();
            bigger.setFromY(i);
            bigger.setHasMerged(true);
            score+=bigger.getValue();
            tempMax = (bigger.getValue() > tempMax)? bigger.getValue() : tempMax;
        }
    }
    private void mergeHorizontal(Tile deleted, Tile bigger, int i){
        if (bigger.equals(deleted) || bigger.getValue() == 0){
            bigger.merge(deleted);
            deleted.clear();
            bigger.setFromX(i);
            bigger.setHasMerged(true);
            score+=bigger.getValue();
            tempMax = (bigger.getValue() > tempMax)? bigger.getValue() : tempMax;
        }
    }

    public void cleanBoard(){
        score = tempMax = 0;
        for (int i = 0 ; i < 4 ; i++){
            for (int j = 0 ; j < 4 ; j++){
                board.get(i).get(j).setHasMerged(false);
                board.get(i).get(j).setFromX(j);
                board.get(i).get(j).setFromY(i);
            }
        }
    }
    //Checks, based on direction, whether at least one of the tiles on the board (not equal to 0) can move in that direction pressed
    public boolean canMove(String direction){
        switch(direction){
            case "Up":
                for (int i = 0 ; i < 4 ; i++){
                    for (int j = 0 ; j < 4 ; j++){
                        if (i != 0 && board.get(i).get(j).getValue() != 0 && 
                           (board.get(i-1).get(j).getValue() == 0 || board.get(i-1).get(j).getValue() == board.get(i).get(j).getValue())){
                            return true;
                        }
                    }}
                return false;
            case "Down":
                for (int i = 0 ; i < 4 ; i++){
                    for (int j = 0 ; j < 4 ; j++){
                        if (i != 3 && board.get(i).get(j).getValue() != 0 && 
                           (board.get(i+1).get(j).getValue() == 0 || board.get(i+1).get(j).getValue() == board.get(i).get(j).getValue())){
                            return true;
                        }
                    }}
                return false;
            case "Left":
                for (int i = 0 ; i < 4 ; i++){
                    for (int j = 0 ; j < 4 ; j++){
                        if (j != 0 && board.get(i).get(j).getValue() != 0 && 
                           (board.get(i).get(j-1).getValue() == 0 || board.get(i).get(j-1).getValue() == board.get(i).get(j).getValue())){
                            return true;
                        }
                    }}
                return false;
            case "Right":
                for (int i = 0 ; i < 4 ; i++){
                    for (int j = 0 ; j < 4 ; j++){
                        if (j != 3 && board.get(i).get(j).getValue() != 0 && 
                           (board.get(i).get(j+1).getValue() == 0 || board.get(i).get(j+1).getValue() == board.get(i).get(j).getValue())){
                            return true;
                        }
                    }}
                return false;
        }
        return false;
    }
     
    //Pick a random tile and if it's not 0 it picks another till it finds a 0 to change to a 2 or 4 
    public void addTile(){
        int x, y, temp;
        do {
            x = rand.nextInt(4);
            y = rand.nextInt(4);
            temp = rand.nextInt(100)+1;
            temp = (temp > 90)? 4 : 2; //91% chance of it being a 2
        
        } while (board.get(x).get(y).getValue() != 0);
        
        board.get(x).get(y).setValue(temp);
    }

    public ArrayList<ArrayList<Tile>> getBoard() {
        return board;
    }

    public int getScore() {
        return score;
    }

    public int getTempMax() {
        return tempMax;
    }
    
    
    @Override
    public String toString(){
        return ("It's a board. What else did you expect? Scwharma?");
    }
    
    public void printBoard(){
        System.out.println("----------------");
        for (int i = 0 ; i < 4 ; i++){
            for (int j = 0 ; j <4 ; j++)
                System.out.printf("%d ", board.get(i).get(j).getValue());
            System.out.printf("\n");
        }
        System.out.println("----------------");
        
//        for (int i = 0 ; i < 4 ; i++){
//            for (int j = 0 ; j <4 ; j++)
//                System.out.printf("%d%d ", i, j);
//            System.out.printf("\n");
//        }
//        System.out.println("----------------");
    }
}
