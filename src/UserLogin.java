import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
 
public class UserLogin extends Application {
    private double xoffset = 0;
    private double yoffset = 0;
    
    // Tampilan 
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
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

 public static void main(String[] args) {
    User user1 = new User("Muhammad Fatihul Iqmal", "087840458946", "Manager", "fthliqml", "iqmal");
    User user2 = new User("Muhammad Aditama Rachman", "0852747236386", "Staff", "aditama", "rahman");

    User.namaList.add(user1.getNama());
    User.teleponList.add(user1.getTelp());
    User.jabatanList.add(user1.getJabatan());
    User.usernameList.add(user1.getUsername());
    User.passwordList.add(user1.getPassword());

    User.namaList.add(user2.getNama());
    User.teleponList.add(user2.getTelp());
    User.jabatanList.add(user2.getJabatan());
    User.usernameList.add(user2.getUsername());
    User.passwordList.add(user2.getPassword());

    launch(args);
    
    }
}
