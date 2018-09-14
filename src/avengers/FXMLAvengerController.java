package avengers;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class FXMLAvengerController {

    URL url;
    AudioInputStream audioIn;
    Clip clip;
    
    private Game game;

    private HashMap<Tile, ImageView> exchange;

    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane panel;

    @FXML
    private ImageView tile1, tile5, tile9, tile13, tile14, tile10, tile6, tile2,
            tile3, tile7, tile11, tile15, tile4, tile8, tile12, tile16;

    @FXML
    private Button again;
    
    @FXML
    private Label score;
    
    @FXML
    private Text starter;
       
    @FXML
    void move(KeyEvent event) {
        KeyCode id = event.getCode();
        switch (id) {
            case UP:
                System.out.println("Up");
                game.getBoard().slideUp();
                break;
            case DOWN:
                System.out.println("Down");
                game.getBoard().slideDown();
                break;
            case LEFT:
                System.out.println("Left");
                game.getBoard().slideLeft();
                break;
            case RIGHT:
                System.out.println("Right");
                game.getBoard().slideRight();
                break;
            default:
                System.out.println("Yeah it doesn't work with that key...");
                break;
        }
        playMaxSound();
        game.setScore(game.getScore() + game.getBoard().getScore());
        displayBoard(game.getBoard());
        checkEndGame();
    }

    @FXML
    void startup(MouseEvent event) {
        game = new Game();
        exchange = new HashMap<>();
        starter.setText("");
        
        System.out.println("Start Game");
        
        exchange.put(game.getBoard().getBoard().get(0).get(0), tile13);
        exchange.put(game.getBoard().getBoard().get(0).get(1), tile14);
        exchange.put(game.getBoard().getBoard().get(0).get(2), tile15);
        exchange.put(game.getBoard().getBoard().get(0).get(3), tile16);
        exchange.put(game.getBoard().getBoard().get(1).get(0), tile9);
        exchange.put(game.getBoard().getBoard().get(1).get(1), tile10);
        exchange.put(game.getBoard().getBoard().get(1).get(2), tile11);
        exchange.put(game.getBoard().getBoard().get(1).get(3), tile12);
        exchange.put(game.getBoard().getBoard().get(2).get(0), tile5);
        exchange.put(game.getBoard().getBoard().get(2).get(1), tile6);
        exchange.put(game.getBoard().getBoard().get(2).get(2), tile7);
        exchange.put(game.getBoard().getBoard().get(2).get(3), tile8);
        exchange.put(game.getBoard().getBoard().get(3).get(0), tile1);
        exchange.put(game.getBoard().getBoard().get(3).get(1), tile2);
        exchange.put(game.getBoard().getBoard().get(3).get(2), tile3);
        exchange.put(game.getBoard().getBoard().get(3).get(3), tile4);
        displayBoard(game.getBoard());
        panel.onMouseClickedProperty().isNull();
    }
    
    private void displayBoard(Board board){
        exchange.entrySet().forEach((entry) -> {
            if (entry.getKey().getValue() == 0){
                entry.getValue().setImage(null);
            }
            else
                entry.getValue().setImage(new Image(entry.getKey().getImage().toString()));
            
//            if (entry.getKey().getFromX() != entry.getValue().getX()) {
//                transLocatorX(entry.getValue(), entry.getKey().getFromX(), entry.getValue().getX());
//            }
//            else if(entry.getKey().getFromY() != entry.getValue().getY()){
//                transLocatorY(entry.getValue(), entry.getKey().getFromY(), entry.getValue().getY());
//            }
        });
        score.setText("Score: " + game.getScore());
        game.getBoard().printBoard();
    }
    
//    void transLocatorX(ImageView visage, double from, double to){
//        TranslateTransition trans = new TranslateTransition();
//        trans.setNode(visage);
//        trans.setDuration(Duration.seconds(.15));
//        trans.setFromX(from);
//        trans.setToX(to);
//        trans.play();        
//    }
//    void transLocatorY(ImageView visage, double from, double to){
//        TranslateTransition trans = new TranslateTransition();
//        trans.setDuration(Duration.seconds(.15));
//        trans.setNode(visage);
//        trans.setFromY(from);
//        trans.setToY(to);
//        trans.play();        
//    }

    void checkEndGame() {
        if (game.checkWin()) {
            win();
        } else if (game.checkLose()) {
            lose();
        }
    }

    void win() {
        System.out.println("Winner Winner Chicken Dinner");
        starter.setText("You Win!");
        starter.setFill(Paint.valueOf("#00ff04"));
        
        again.setVisible(true);
        again.setDisable(false);
        panel.onKeyReleasedProperty().isNull();
    }

    void lose() {
        System.out.println("Boozer Snoozer Little Loozer");
        starter.setText("Game... Over");
        starter.setFill(Paint.valueOf("#ff0000"));
        
        again.setVisible(true);
        again.setDisable(false);
        panel.onKeyReleasedProperty().isNull();
    }

    @FXML
    void restart(ActionEvent event) {
        panel.onMouseClickedProperty().isNotNull();
        panel.onKeyReleasedProperty().isNotNull();
        
        again.setVisible(false);
        again.setDisable(true);
        starter.setText("Click Anywhere to Start the Game");
        starter.setFill(Paint.valueOf("#faff00"));
    }
    
    void playMaxSound(){
        
            String max = game.getMax();
            url = this.getClass().getClassLoader().getResource(max);
        try {
            audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Ooof. Right in the infinity stones");
        }
        clip.start();
    }
    
    @FXML
    void initialize() {
        assert panel != null : "fx:id=\"panel\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
        assert tile1 != null : "fx:id=\"tile1\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
        assert tile5 != null : "fx:id=\"tile5\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
        assert tile9 != null : "fx:id=\"tile9\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
        assert tile13 != null : "fx:id=\"tile13\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
        assert tile14 != null : "fx:id=\"tile14\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
        assert tile10 != null : "fx:id=\"tile10\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
        assert tile6 != null : "fx:id=\"tile6\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
        assert tile2 != null : "fx:id=\"tile2\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
        assert tile3 != null : "fx:id=\"tile3\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
        assert tile7 != null : "fx:id=\"tile7\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
        assert tile11 != null : "fx:id=\"tile11\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
        assert tile15 != null : "fx:id=\"tile15\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
        assert tile4 != null : "fx:id=\"tile4\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
        assert tile8 != null : "fx:id=\"tile8\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
        assert tile12 != null : "fx:id=\"tile12\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
        assert tile16 != null : "fx:id=\"tile16\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
        assert starter != null : "fx:id=\"starter\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
        assert again != null : "fx:id=\"again\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
        assert score != null : "fx:id=\"score\" was not injected: check your FXML file 'FXMLAvenger.fxml'.";
    }
}
