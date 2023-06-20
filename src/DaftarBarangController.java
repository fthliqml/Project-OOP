import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
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

    @FXML private DatePicker tfDate;

    @FXML private TextField tfHarga;

    @FXML private TextField tfID;

    @FXML private TextField tfStok;

    @FXML private TextField tfMerk;

    @FXML private TextField tfNamaBarang;

    @FXML private ChoiceBox <String> boxJenis;
    private String [] jenis = {"Makanan", "Minuman", "Bahan Baku", "Bahan Penyedap"};

    @FXML private ChoiceBox <String> boxSupplier;
    
    @FXML
    private AnchorPane menambahkanPane;

    
    @FXML private TableColumn<DaftarBarang, String> hargaCol;
    @FXML private TableColumn<DaftarBarang, String> idCol;
    @FXML private TableColumn<DaftarBarang, String> jenisCol;
    @FXML private TableColumn<DaftarBarang, Date> kadaluarsaCol;
    @FXML private TableColumn<DaftarBarang, String> merkCol;
    @FXML private TableColumn<DaftarBarang, String> namabarangCol;
    @FXML private TableColumn<DaftarBarang, Integer> stokCol;
    @FXML private TableColumn<DaftarBarang, String> supplierCol;
    @FXML private TableColumn<DaftarBarang, String> editCol;
    @FXML private TableView<DaftarBarang> tableBarang;

    ResultSet resultSet = null ;
    DaftarBarang barang = null ;
    static int i;
    private Boolean update;
   
    
    ObservableList<DaftarBarang> BarangList = FXCollections.observableArrayList();
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Supplier.namaList.clear();
        try {
            Connection connection;
            PreparedStatement preparedStatement;
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/pbo project", "root", "");
            preparedStatement = connection.prepareStatement("SELECT * FROM `supplier`");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Supplier.namaList.add(resultSet.getString("Nama"));
                }
            refreshTable();
            editTable();
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR");
        } catch (SQLException e) {
            System.out.println("ERROR");
        }

        boxJenis.getItems().addAll(jenis);
        boxSupplier.getItems().addAll(Supplier.namaList);

        menambahkanPane.setVisible(false);
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

    int stokInt(){
        String s = tfStok.getText();
        int i = Integer.valueOf(s);
        return i;
    }

    void setTextField(String Id_Barang, String Barang, String Merk, String Jenis, int Stok, String Harga, LocalDate Tanggal_Kadaluarsa, String Supplier){
        String stok = String.valueOf(Stok);
        tfID.setText(Id_Barang);
        tfNamaBarang.setText(Barang);
        tfMerk.setText(Merk);
        boxJenis.setValue(Jenis);
        tfStok.setText(stok);
        tfHarga.setText(Harga);
        tfDate.setValue(Tanggal_Kadaluarsa);
        boxSupplier.setValue(Supplier);
    }

    void setUpdate(Boolean b){
        this.update=b;
    }

    @FXML
    void btnSimpan(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (tfID.getText().isEmpty()||tfNamaBarang.getText().isEmpty()||tfMerk.getText().isEmpty()||boxJenis.getValue().isEmpty()||tfStok.getText().isEmpty()||tfHarga.getText().isEmpty()||tfDate.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Isi Semua Data");
            alert.showAndWait();
        }
        else if (update==true){
            try {
                Connection connection;
                PreparedStatement preparedStatement;
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/pbo project", "root", "");
                preparedStatement = connection.prepareStatement("UPDATE `barang` SET "
                + "`ID`=?,"
                + "`Barang`=?,"
                + "`Merk`=?,"
                + "`Jenis`=?," 
                + "`Stok`=?,"
                + "`Harga`=?,"
                + "`Tanggal`=?,"
                + "`Supplier`=? WHERE ID = ?");
                java.sql.Date Date = java.sql.Date.valueOf(tfDate.getValue());

                preparedStatement.setString(1, tfID.getText());
                preparedStatement.setString(2, tfNamaBarang.getText());
                preparedStatement.setString(3, tfMerk.getText());
                preparedStatement.setString(4, boxJenis.getValue());
                preparedStatement.setInt(5, stokInt());
                preparedStatement.setString(6, tfHarga.getText());
                preparedStatement.setDate(7, Date);
                preparedStatement.setString(8, boxSupplier.getValue());
                barang = tableBarang.getSelectionModel().getSelectedItem();
                preparedStatement.setString(9, barang.getID());
                preparedStatement.execute();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Berhasil Mengedit Data");
                alert.showAndWait();
                refreshTable();
                btnBatal(event);

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        else { 
            try {
            Connection con1;
            PreparedStatement insert;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost/pbo project", "root", "");
            insert = con1.prepareStatement("insert into barang(ID,Barang,Merk,Jenis,Stok,Harga,Tanggal,Supplier)values(?,?,?,?,?,?,?,?)");
            java.sql.Date Date = java.sql.Date.valueOf(tfDate.getValue());
            
            //Save to Database
            insert.setString(1, tfID.getText());
            insert.setString(2, tfNamaBarang.getText());
            insert.setString(3, tfMerk.getText());
            insert.setString(4, boxJenis.getValue());
            insert.setInt(5, stokInt());
            insert.setString(6, tfHarga.getText());
            insert.setDate(7, Date);
            insert.setString(8, boxSupplier.getValue());
            insert.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Berhasil Menyimpan Data");
            alert.showAndWait();
            refreshTable();

            setTextField(null, null, null, null, 0, null, null, null);
            tfStok.setText(null);

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Isi Data Dengan Format Yang Benar");
                alert.showAndWait();
            }
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

    void editTable(){
        Callback<TableColumn<DaftarBarang, String>, TableCell<DaftarBarang, String>> cellFoctory = (TableColumn<DaftarBarang, String> param) -> {
            final TableCell<DaftarBarang, String> cell = new TableCell<DaftarBarang, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        try {
                            Image editIcon = new Image(new FileInputStream("D:\\Programming\\Java\\Big Project\\src\\foto\\editICON.png"));
                            ImageView editView = new ImageView(editIcon);
                            editView.setFitHeight(22); 
                            editView.setFitWidth(23);

                            Image deleteIcon = new Image(new FileInputStream("D:\\Programming\\Java\\Big Project\\src\\foto\\trash.png"));
                            ImageView deleteView = new ImageView(deleteIcon);
                            deleteView.setFitHeight(24); 
                            deleteView.setFitWidth(26);

                            HBox managebtn = new HBox(editView, deleteView);
                            managebtn.setStyle("-fx-alignment:center;"+"-fx-cursor:hand;");
                            HBox.setMargin(editView, new Insets(2, 2, 0, 3));
                            HBox.setMargin(deleteView, new Insets(2, 3, 0, 2));

                            setGraphic(managebtn);
                            setText(null);

                            deleteView.setOnMouseClicked((MouseEvent event) -> {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setX(650);
                                    alert.setY(270);
                                    alert.initStyle(StageStyle.UTILITY);
                                    alert.setHeaderText(null);
                                    alert.setContentText("Apakah Anda Yakin Ingin Menghapus?");
                                    
                                    Optional<ButtonType> result = alert.showAndWait();
                                    if (result.get() == ButtonType.OK){
                                        try {
                                            Connection connection;
                                            PreparedStatement preparedStatement;
                                            Class.forName("com.mysql.cj.jdbc.Driver");
                                            connection = DriverManager.getConnection("jdbc:mysql://localhost/pbo project", "root", "");
                                            barang = tableBarang.getSelectionModel().getSelectedItem();
                                            preparedStatement = connection.prepareStatement("DELETE FROM `barang` WHERE ID = ?");
                                            preparedStatement.setString(1, barang.getID());
                                            preparedStatement.execute();
                                            refreshTable();
                                            
                                        } catch (SQLException | ClassNotFoundException ex) {
                                            ex.printStackTrace();
                                    
                                            }
                                    }
                            
                            });

                            editView.setOnMouseClicked((MouseEvent event) -> {
                                setUpdate(true);
                                barang = tableBarang.getSelectionModel().getSelectedItem();
                                menambahkanPane.setVisible(true);
                                setTextField(
                                            barang.getID(), 
                                            barang.getBarang(), 
                                            barang.getMerk(), 
                                            barang.getJenis(), 
                                            barang.getStok(), 
                                            barang.getHarga(), 
                                            convertToLocalDate(barang.getTanggal()), 
                                            barang.getSupplier());
                        });

                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                }

            };

            return cell;
        };
         editCol.setCellFactory(cellFoctory);
         
    }

    public LocalDate convertToLocalDate(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    @FXML
    void btnReset(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setX(650);
        alert.setY(270);
        alert.initStyle(StageStyle.UTILITY);
        alert.setHeaderText(null);
        alert.setContentText("Apakah Anda Yakin Ingin Menghapus Semua Data?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                Connection connection;
                PreparedStatement preparedStatement;
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/pbo project", "root", "");
                preparedStatement = connection.prepareStatement("DELETE FROM `barang`");
                preparedStatement.execute();
                refreshTable();
                
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
        
                }
        }
    }

    @FXML
    void btnTambah(ActionEvent event) throws IOException {
        setUpdate(false);
        menambahkanPane.setVisible(true);
    }

    @FXML
    void btnBatal(ActionEvent event) throws IOException {
        menambahkanPane.setVisible(false);
    }

    @FXML
    void btnLogout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setX(650);
        alert.setY(270);
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
    void btnPenjualan(ActionEvent event) throws IOException{
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
}