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

import javafx.animation.TranslateTransition;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class DaftarBarangController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private double xoffset = 0;
    private double yoffset = 0;

    @FXML
    private Pane sliderNav;

    @FXML
    private ImageView closeNav1;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    @FXML
    private ImageView img5;

    @FXML
    private Pane openNav;
    @FXML
    private ImageView openNavimg;
    @FXML
    private ImageView imgPengguna;
    @FXML
    private ImageView imgBarang;
    @FXML
    private ImageView imgSupplier;
    @FXML
    private ImageView imgPenjualan;
    @FXML
    private ImageView imgLogout;
    
    @FXML
    private Button closeNav;
    @FXML
    private Button penggunaID;
    @FXML
    private Button barangID;
    @FXML
    private Button supplierID;
    @FXML
    private Button penjualanID;
    @FXML
    private Button logoutID;
    
    @FXML private TableColumn<DaftarBarang, String> hargaCol;
    @FXML private TableColumn<DaftarBarang, String> idCol;
    @FXML private TableColumn<DaftarBarang, String> jenisCol;
    @FXML private TableColumn<DaftarBarang, Date> kadaluarsaCol;
    @FXML private TableColumn<DaftarBarang, String> merkCol;
    @FXML private TableColumn<DaftarBarang, String> namabarangCol;
    @FXML private TableColumn<DaftarBarang, Integer> stokCol;
    @FXML private TableColumn<DaftarBarang, String> supplierCol;
    @FXML private TableView<DaftarBarang> tableBarang;

    ResultSet resultSet = null ;
    DaftarBarang barang = null ;
    
    ObservableList<DaftarBarang> BarangList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            refreshTable();
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR");
        } catch (SQLException e) {
            System.out.println("ERROR");
        }

        
        openNavimg.setVisible(false);
        imgPengguna.setVisible(false);
        imgBarang.setVisible(false);
        imgSupplier.setVisible(false);
        imgPenjualan.setVisible(false);
        imgLogout.setVisible(false);

        closeNav.setOnMouseClicked(event -> {
            closeNav.setVisible(false);
            closeNav1.setVisible(false);
            img1.setVisible(false);
            img2.setVisible(false);
            img3.setVisible(false);
            img4.setVisible(false);
            img5.setVisible(false);
            penggunaID.setVisible(false);
            barangID.setVisible(false);
            supplierID.setVisible(false);
            penjualanID.setVisible(false);
            logoutID.setVisible(false);

            TranslateTransition slide = new TranslateTransition();
                    slide.setDuration(Duration.seconds(0.5));
                    slide.setNode(sliderNav);
                    slide.setToX(-125);
                    slide.play();
                    sliderNav.setTranslateX(50);
                    slide.setOnFinished((ActionEvent e)->{
                        openNavimg.setVisible(true);
                        imgPengguna.setVisible(true);
                        imgBarang.setVisible(true);
                        imgSupplier.setVisible(true);
                        imgPenjualan.setVisible(true);
                        imgLogout.setVisible(true);
                    });
        });

        closeNav1.setOnMouseClicked(event -> {
            closeNav.setVisible(false);
            closeNav1.setVisible(false);
            img1.setVisible(false);
            img2.setVisible(false);
            img3.setVisible(false);
            img4.setVisible(false);
            img5.setVisible(false);
            penggunaID.setVisible(false);
            barangID.setVisible(false);
            supplierID.setVisible(false);
            penjualanID.setVisible(false);
            logoutID.setVisible(false);

            TranslateTransition slide = new TranslateTransition();
                    slide.setDuration(Duration.seconds(0.5));
                    slide.setNode(sliderNav);
                    slide.setToX(-125);
                    slide.play();
                    sliderNav.setTranslateX(50);
                    slide.setOnFinished((ActionEvent e)->{
                        openNavimg.setVisible(true);
                        imgPengguna.setVisible(true);
                        imgBarang.setVisible(true);
                        imgSupplier.setVisible(true);
                        imgPenjualan.setVisible(true);
                        imgLogout.setVisible(true);
                    });
        });

        openNav.setOnMouseClicked(event -> {
            openNavimg.setVisible(false);
            imgPengguna.setVisible(false);
            imgBarang.setVisible(false);
            imgSupplier.setVisible(false);
            imgPenjualan.setVisible(false);
            imgLogout.setVisible(false);
            closeNav.setVisible(true);
            closeNav1.setVisible(true);
            img1.setVisible(true);
            img2.setVisible(true);
            img3.setVisible(true);
            img4.setVisible(true);
            img5.setVisible(true);
            penggunaID.setVisible(true);
            barangID.setVisible(true);
            supplierID.setVisible(true);
            penjualanID.setVisible(true);
            logoutID.setVisible(true);

            TranslateTransition slide = new TranslateTransition();
                    slide.setDuration(Duration.seconds(0.5));
                    slide.setNode(sliderNav);
                    slide.setToX(0);
                    slide.play();
                    sliderNav.setTranslateX(50);
                    slide.setOnFinished((ActionEvent e)->{
                    });
        });

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
                resultSet.getDate("Tanggal"),
                resultSet.getString("Supplier")));
                tableBarang.setItems(BarangList);
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        namabarangCol.setCellValueFactory(new PropertyValueFactory<>("Barang"));
        merkCol.setCellValueFactory(new PropertyValueFactory<>("Merk"));
        jenisCol.setCellValueFactory(new PropertyValueFactory<>("Jenis"));
        stokCol.setCellValueFactory(new PropertyValueFactory<>("Stok"));
        hargaCol.setCellValueFactory(new PropertyValueFactory<>("Harga"));
        kadaluarsaCol.setCellValueFactory(new PropertyValueFactory<>("Tanggal"));
        supplierCol.setCellValueFactory(new PropertyValueFactory<>("Supplier"));

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