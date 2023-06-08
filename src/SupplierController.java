import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SupplierController {
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
    void btnLogout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setHeaderText(null);
        alert.setContentText("Anda Yakin Ingin Keluar?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            root = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
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

    @FXML
    void btnBarang(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Daftar Barang.fxml"));
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
    void btnPengguna(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Pengguna.fxml"));
        Parent root = (Parent)loader.load();
        PenggunaController penggunaController = loader.getController();
        
        penggunaController.getText(
            Pengguna.jabatanlist.get(PenggunaController.Index), 
            Pengguna.namalist.get(PenggunaController.Index), 
            Pengguna.teleponlist.get(PenggunaController.Index), 
            Pengguna.alamatlist.get(PenggunaController.Index));

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
    void btnTransaksi(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Transaksi.fxml"));
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