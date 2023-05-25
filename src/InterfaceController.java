import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InterfaceController {
    public static int Index;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private double xoffset = 0;
    private double yoffset = 0;

    @FXML
    void btnExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private TextField txtJabatan;

    @FXML
    private TextField txtNama;

    @FXML
    private TextField txtTelp;

    public void getText(String Jabatan, String Nama, String Telepon){
        txtJabatan.setText(Jabatan);
        txtNama.setText(Nama);
        txtTelp.setText(Telepon);
    }

    public void getIndex(int Index){
        InterfaceController.Index = Index;
    }

    @FXML
    void btnPenjualan(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Penjualan.fxml"));
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

    @FXML
    void btnSupplier(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Supplier.fxml"));
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
