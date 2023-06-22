import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class UserLoginController implements Initializable {
    private Stage stage;
    private Scene scene;
    private double xoffset = 0;
    private double yoffset = 0;

    @FXML
    private FontAwesomeIcon IconKey;

    @FXML
    private Button RegisterID;

    @FXML
    private Button btnLoginID;

    @FXML
    private ImageView imgGuest;

    @FXML
    private FontAwesomeIcon iconUser;

    @FXML
    private FontAwesomeIcon exitBlue;

    @FXML
    private FontAwesomeIcon exitWhite;

    @FXML
    private Text labelAgentLogin;

    @FXML
    private Text logintxt1;

    @FXML
    private Text logintxt2;

    @FXML
    private Label labelNote;

    @FXML
    private AnchorPane slider;

    @FXML
    private Pane registerPane;

    @FXML
    private AnchorPane rightAnchor;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private TextField tfUsername;

    
    @FXML
    private TextField Ralamat;

    @FXML
    private TextField Rjabatan;

    @FXML
    private TextField Rnama;

    @FXML
    private TextField Rpassword;

    @FXML
    private TextField Rtelepon;

    @FXML
    private TextField Rusername;

    ResultSet resultSet = null ;
    int index = 0;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        refreshDatabase();
        verifyLogin();

        registerPane.setVisible(false);
        exitWhite.setVisible(false);
        RegisterID.setOnMouseClicked(event -> {
                    exitWhite.setVisible(true);
                    IconKey.setVisible(false);
                    iconUser.setVisible(false);
                    imgGuest.setVisible(false);
                    btnLoginID.setVisible(false);
                    labelAgentLogin.setVisible(false);
                    labelNote.setVisible(false);
                    tfUsername.setVisible(false);
                    tfPassword.setVisible(false);
                    RegisterID.setVisible(false);
                    exitBlue.setVisible(false);
                    
        
                    TranslateTransition slide = new TranslateTransition();
                    slide.setDuration(Duration.seconds(1.2));
                    slide.setNode(slider);
                    slide.setToX(347);
                    slide.play();
                    slider.setTranslateX(50);
                    slide.setOnFinished((ActionEvent e)->{
                        registerPane.setVisible(true);
                    });
                });
    }


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
    void btnLogin(ActionEvent event) throws IOException {

        if (getUsername().isEmpty()||getPassword().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Isi Semua Data");
            alert.showAndWait();
        }
        
        else if (verifyLogin()==true){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setHeaderText(null);
            alert.setContentText("Login Berhasil");
            alert.showAndWait();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Pengguna.fxml"));
            Parent root = (Parent)loader.load();
            PenggunaController penggunaController = loader.getController();

            penggunaController.getText(
            Pengguna.jabatanlist.get(index), 
            Pengguna.namalist.get(index), 
            Pengguna.teleponlist.get(index), 
            Pengguna.alamatlist.get(index));

            penggunaController.getIndex(index);
        
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
                
        else if(verifyLogin()==false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Username/Password Tidak Ditemukan");
            alert.showAndWait();
        }
    }



    void refreshDatabase(){
        try {
            Connection connection;
            PreparedStatement preparedStatement;
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/pbo project", "root", "");
            preparedStatement = connection.prepareStatement("SELECT * FROM `user`");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                
                Pengguna.jabatanlist.add(resultSet.getString("Jabatan"));
                Pengguna.namalist.add(resultSet.getString("Nama"));
                Pengguna.teleponlist.add(resultSet.getString("Telepon"));
                Pengguna.alamatlist.add(resultSet.getString("Alamat"));
                Pengguna.usernameList.add(resultSet.getString("Username"));
                Pengguna.passwordList.add(resultSet.getString("Password"));
            }
        }
        catch (Exception e) {
        }
    } 

        boolean verifyLogin(){
            for ( int i = 0;i < Pengguna.usernameList.size();i++){
                if (getUsername().equals(Pengguna.usernameList.get(i))&&getPassword().equals(Pengguna.passwordList.get(i))){
                    index=i;
                    return true;
                }
            }
            return false;
        }


    @FXML
    void btnBuatAkun(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(
            Rjabatan.getText().isEmpty()||
            Rnama.getText().isEmpty()||
            Rtelepon.getText().isEmpty()||
            Rusername.getText().isEmpty()||
            Rpassword.getText().isEmpty()||
            Ralamat.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Isi Semua Data");
                alert.showAndWait();
            }
        else {
            Connection con1;
            PreparedStatement insert;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost/pbo project", "root", "");
            insert = con1.prepareStatement("insert into user(Jabatan,Nama,Telepon,Alamat,Username,Password)values(?,?,?,?,?,?)");
            //Save To Database
            insert.setString(1, Rjabatan.getText());
            insert.setString(2, Rnama.getText());
            insert.setString(3, Rtelepon.getText());
            insert.setString(4, Ralamat.getText());
            insert.setString(5, Rusername.getText());
            insert.setString(6, Rpassword.getText());
            insert.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Berhasil Menyimpan Data");
            alert.showAndWait();

            Rjabatan.setText("");
            Rnama.setText("");
            Rtelepon.setText("");
            Ralamat.setText("");
            Rusername.setText("");
            Rpassword.setText("");

            refreshDatabase();
            btnBackToLogin(event);
        }

            
    }

    @FXML
    void btnBackToLogin(ActionEvent event) {
        Rjabatan.setText("");
        Rnama.setText("");
        Rtelepon.setText("");
        Ralamat.setText("");
        Rusername.setText("");
        Rpassword.setText("");

        registerPane.setVisible(false);
        exitWhite.setVisible(false);
        exitBlue.setVisible(true);

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(1.2));
        slide.setNode(slider);
        slide.setToX(6);
        slide.play();
        slider.setTranslateX(50);
        slide.setOnFinished((ActionEvent e)->{
            IconKey.setVisible(true);
            iconUser.setVisible(true);
            imgGuest.setVisible(true);
            btnLoginID.setVisible(true);
            labelAgentLogin.setVisible(true);
            labelNote.setVisible(true);
            tfUsername.setVisible(true);
            tfPassword.setVisible(true);
            RegisterID.setVisible(true);
        });
    }

}
