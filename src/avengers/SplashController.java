package avengers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SplashController {
    
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane splash;

    @FXML
    private Button player;

    @FXML
    void playgame(ActionEvent event) {
        Parent root = null;
        FXMLLoader root1 = new FXMLLoader(getClass().getResource("FXMLAvenger.fxml"));
        try { 
            root = (Parent)root1.load();
        } catch (IOException ex) {
        }
        FXMLAvengerController controller = root1.<FXMLAvengerController>getController();
        Scene sc = new Scene(root);
        Stage st = new Stage();
        st.setScene(sc);
        st.show();
        root.requestFocus();
        root.setFocusTraversable(true);
        
        splash.getScene().getWindow().hide();
    }
    

    @FXML
    void initialize() {
        assert splash != null : "fx:id=\"splash\" was not injected: check your FXML file 'Splash.fxml'.";
        assert player != null : "fx:id=\"player\" was not injected: check your FXML file 'Splash.fxml'.";
    }
}
