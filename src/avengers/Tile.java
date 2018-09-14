/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avengers;


import java.net.URL;

/**
 *
 * @author Kyle
 */
public class Tile {
    private double fromX, fromY;
    private int value;
    URL imageFile;
    private boolean hasMerged;
    
    
    public Tile() {
        this.value = 0;
        hasMerged = false;
    }

    public Tile(int fromX,  int fromY, int value) {
        this.fromX = 50 + fromX * 100;
        this.fromY = 250+ fromY * 100;
        this.value = value;
        hasMerged = false;
    }

    
    //Helper function to save lines in switch statement
//    public Image fileToImage(URL str){
//        Image temp = null;
//        try{
//            imagepre = ImageIO.read(str);
//            temp = SwingFXUtils.toFXImage(imagepre, null);
//        }catch(IOException e){
//            System.out.println("Could not find file");
//        }
//        return temp;
//    }
    
    //Use the value of the Tile to access the corresponding picture
    public URL getImage(){
        switch (value){
            case 2:
                imageFile = this.getClass().getClassLoader().getResource("black_widow.png");
//                fileToImage(imageFile);
                break;
            case 4:
                imageFile = this.getClass().getClassLoader().getResource("valkyvalk.png");
//                fileToImage(imageFile);
                break;
            case 8:
                imageFile = this.getClass().getClassLoader().getResource("spider.png");
//                fileToImage(imageFile);
                break;
            case 16:
                imageFile = this.getClass().getClassLoader().getResource("rocket.png");
//                fileToImage(imageFile);
                break;
            case 32:
                imageFile = this.getClass().getClassLoader().getResource("panther.png");
//                fileToImage(imageFile);
                break;
            case 64:
                imageFile = this.getClass().getClassLoader().getResource("witchy.png");
//                fileToImage(imageFile);
                break;
            case 128:
                imageFile = this.getClass().getClassLoader().getResource("captain-america-figure_0.jpg");
//                fileToImage(imageFile);
                break;
            case 256:
                imageFile = this.getClass().getClassLoader().getResource("Doctor_strange.jpg");
//                fileToImage(imageFile);
                break;
            case 512:
                imageFile = this.getClass().getClassLoader().getResource("iron.png");
//                fileToImage(imageFile);
                break;
            case 1024:
                imageFile = this.getClass().getClassLoader().getResource("hulk.png");
//                fileToImage(imageFile);
                break;
            case 2048:
                imageFile = this.getClass().getClassLoader().getResource("thanos.jpg");
//                fileToImage(imageFile);
                break;
        }
        return imageFile;
    }
    
    public boolean equals(Tile tile){
        return (tile.getValue() == this.getValue());  
    }
    
    public void merge(Tile tile){
        this.value += tile.getValue();
    }
    
    public void clear(){
        this.setValue(0);
    }
    
    public double getFromX() {
        return fromX;
    }

    public double getFromY() {
        return fromY;
    }


    public int getValue() {
        return value;
    }

    public void setFromX(double fromX) {
        this.fromX = 50 + fromX*100;
    }

    public void setFromY(double fromY) {
        this.fromY = 250 + fromY*100;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setHasMerged(boolean hasMerged) {
        this.hasMerged = hasMerged;
    }

    public boolean isHasMerged() {
        return hasMerged;
    }
   
    
    @Override
    public String toString(){
        return (Integer.toString(this.getValue()) + this.getImage().toString());
    }
}
