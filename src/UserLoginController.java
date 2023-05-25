import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UserLoginController {
    private Stage stage;
    private Scene scene;
    private double xoffset = 0;
    private double yoffset = 0;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private TextField tfUsername;

    @FXML
    void checkSimpanUsername(ActionEvent event) {
    }

    @FXML
    void btnExit(ActionEvent event) {
        System.exit(0);
    }

    public String getUsername(){
        return tfUsername.getText();
    }

    public String getPassword(){
        return tfPassword.getText();
    }

    @FXML
    void btnLogIn(ActionEvent event) throws IOException {
        for (int i = 0 ; i < User.usernameList.size() ; i++){
            if (getUsername().equals(User.usernameList.get(i))&&getPassword().equals(User.passwordList.get(i))){

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Interface.fxml"));
                Parent root = (Parent)loader.load();

                InterfaceController interfaceController = loader.getController();
                interfaceController.getText(User.jabatanList.get(i), User.namaList.get(i), User.teleponList.get(i));
                interfaceController.getIndex(i);
    
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
    
                     root.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event){
                        xoffset = event.getSceneX();
                        yoffset = event.getSceneY();
                    }
                } );
    
                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event){
                        stage.setX(event.getScreenX() - xoffset);
                        stage.setY(event.getScreenY() - yoffset);
                    }
                } );
    
                stage.setScene(scene);
                stage.show();
            }           
        }
        
    }

}
