import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DaftarBarangController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private double xoffset = 0;
    private double yoffset = 0;

    
    @FXML private TableColumn<DaftarBarang, String> hargaCol;
    @FXML private TableColumn<DaftarBarang, String> idCol;
    @FXML private TableColumn<DaftarBarang, String> jenisCol;
    @FXML private TableColumn<DaftarBarang, Date> kadaluarsaCol;
    @FXML private TableColumn<DaftarBarang, String> merkCol;
    @FXML private TableColumn<DaftarBarang, String> namabarangCol;
    @FXML private TableColumn<DaftarBarang, Integer> stokCol;
    @FXML private TableView<DaftarBarang> tableBarang;

    String query = null;
    ResultSet resultSet = null ;
    DaftarBarang barang = null ;
    
    ObservableList<DaftarBarang> BarangList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            refreshTable();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void refreshTable() throws SQLException, ClassNotFoundException{
        BarangList.clear();

        Connection connection;
        PreparedStatement preparedStatement;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/pbo project", "root", "");
        preparedStatement = connection.prepareStatement("SELECT * FROM `barang`");
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            BarangList.add(new DaftarBarang(
                resultSet.getString("ID"), 
                resultSet.getString("Barang"), 
                resultSet.getString("Merk"), 
                resultSet.getString("Jenis"), 
                resultSet.getInt("Stok"), 
                resultSet.getString("Harga"), 
                resultSet.getDate("Tanggal")));
                tableBarang.setItems(BarangList);
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        namabarangCol.setCellValueFactory(new PropertyValueFactory<>("Barang"));
        merkCol.setCellValueFactory(new PropertyValueFactory<>("Merk"));
        jenisCol.setCellValueFactory(new PropertyValueFactory<>("Jenis"));
        stokCol.setCellValueFactory(new PropertyValueFactory<>("Stok"));
        hargaCol.setCellValueFactory(new PropertyValueFactory<>("Harga"));
        kadaluarsaCol.setCellValueFactory(new PropertyValueFactory<>("Tanggal"));

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
    void btnTambah(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Tambahkan Barang.fxml"));
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
    void btnExit(ActionEvent event) {
        System.exit(0);
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