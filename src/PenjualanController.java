import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class PenjualanController implements Initializable{

    @FXML
    private TableColumn<Penjualan, String> hargaCol;
    @FXML
    private TableColumn<Penjualan, String> idCol;
    @FXML
    private TableColumn<Penjualan, Integer> jumlahCol;
    @FXML
    private TableColumn<Penjualan, String> merkCol;
    @FXML
    private TableColumn<Penjualan, String> namaCol;
    @FXML
    private TableColumn<Penjualan, String> editCol;
    @FXML
    private TableView<Penjualan> tablePenjualan;
    
    @FXML
    private TableColumn<Transaksi, String> plusColTr;
    @FXML
    private TableColumn<Transaksi, String> idColTr;
    @FXML
    private TableColumn<Transaksi, String> namaColTr;
    @FXML
    private TableColumn<Transaksi, String> merkColTr;
    @FXML
    private TableColumn<Transaksi, String> hargaColTr;
    @FXML
    private TableColumn<Transaksi, Integer> jumlahColTr;
    @FXML
    private TableColumn<Transaksi, String> checkColTr;
    @FXML
    private TableView<Transaksi> tableTransaksi;

    
    @FXML
    private AnchorPane menambahkanPane;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private double xoffset = 0;
    private double yoffset = 0;
    private int index;
    int i = 2;

    ResultSet resultSet = null ;
    Penjualan penjualan = null ;
    Transaksi transaksi = null ;
    ObservableList<Penjualan> penjualanList = FXCollections.observableArrayList();
    ObservableList<Transaksi> transaksiList = FXCollections.observableArrayList();
    ArrayList<String> stokRS = new ArrayList<String>();
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            refreshtablePenjualan();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        Transaksi.BoxList.clear();
        Transaksi.jumlahTFields.clear();
        menambahkanPane.setVisible(false);
    }

    @FXML
    void btnReset(ActionEvent event) throws SQLException, ClassNotFoundException {
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
                preparedStatement = connection.prepareStatement("DELETE FROM `penjualan`");
                preparedStatement.execute();
                refreshtablePenjualan();
                
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
        
                }
        }
    }

    @FXML
    void btnEdit(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (i%2==1) {
            editCol.setVisible(false);
        }
        else {
            editCol.setVisible(true);
            Callback<TableColumn<Penjualan, String>, TableCell<Penjualan, String>> cellFoctory = (TableColumn<Penjualan, String> param) -> {
                final TableCell<Penjualan, String> cell = new TableCell<Penjualan, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
    
                        if (empty) {
                            setGraphic(null);
                            setText(null);
    
                        } else {
    
                            try {
                                Image deleteIcon = new Image(new FileInputStream("D:\\Programming\\Java\\Big Project\\src\\foto\\trash.png"));
                                ImageView deleteView = new ImageView(deleteIcon);
                                deleteView.setFitHeight(23); 
                                deleteView.setFitWidth(26);
    
                                HBox managebtn = new HBox(deleteView);
                                managebtn.setStyle("-fx-alignment:center;"+"-fx-cursor:hand;");
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
                                            penjualan = tablePenjualan.getSelectionModel().getSelectedItem();
                                            preparedStatement = connection.prepareStatement("DELETE FROM `penjualan` WHERE ID = ?");
                                            preparedStatement.setString(1, penjualan.getID());
                                            preparedStatement.execute();
                                            refreshtablePenjualan();
                                            
                                        } catch (SQLException | ClassNotFoundException ex) {
                                            ex.printStackTrace();
                                    
                                            }
                                    }
                                
                                });

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
    
                        }
                    }
    
                };
                return cell;
            };
             editCol.setCellFactory(cellFoctory);
             
        }
    i++;
    }

    @FXML
    void btnTambah(ActionEvent event) throws ClassNotFoundException, SQLException {
        refreshTableTransaksi();
        menambahkanPane.setVisible(true);
        idCol.setVisible(false);
    }

    @FXML
    void btnSimpan(ActionEvent event) throws ClassNotFoundException, SQLException {
        checkTransaksi();
        int selected = 0;
        while (selected < Transaksi.BoxList.size()){
            if (Transaksi.BoxList.get(selected).isSelected()&&!Transaksi.jumlahTFields.get(selected).getText().isEmpty()){

                try {
                    Connection con1;
                    PreparedStatement preparedStatement;
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con1 = DriverManager.getConnection("jdbc:mysql://localhost/pbo project", "root", "");
                    preparedStatement = con1.prepareStatement("insert into penjualan(ID,Barang,Merk,Jumlah,Harga)values(?,?,?,?,?)");
                    //Save To Database
                    preparedStatement.setString(1, idColTr.getCellData(selected));
                    preparedStatement.setString(2, namaColTr.getCellData(selected));
                    preparedStatement.setString(3, merkColTr.getCellData(selected));
                    preparedStatement.setInt(4, Integer.valueOf(Transaksi.jumlahTFields.get(selected).getText()));
                    preparedStatement.setString(5, hargaColTr.getCellData(selected));
                    preparedStatement.executeUpdate();

                    //Update Stok Barang
                    int sisa = Integer.valueOf(stokRS.get(selected)) - Integer.valueOf(Transaksi.jumlahTFields.get(selected).getText());
                    preparedStatement = con1.prepareStatement("UPDATE `barang` SET `Stok`=? WHERE ID = ?");
                    preparedStatement.setInt(1, sisa);
                    preparedStatement.setString(2, idColTr.getCellData(selected));
                    preparedStatement.execute();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            selected++;
        }

        if (checkTransaksi()==true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Berhasil Menyimpan Data");
            alert.showAndWait();
            refreshtablePenjualan();
            refreshTableTransaksi();
            btnBatal(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Mohon Click Tambah dan Isi Jumlahnya");
            alert.showAndWait();
        }

    }

    boolean checkTransaksi(){
        int i = 0;
        while (i < Transaksi.BoxList.size()){
            if (Transaksi.BoxList.get(i).isSelected()&&!Transaksi.jumlahTFields.get(i).getText().isEmpty()){
                return true;
            }
        i++;
        }
        return false;
    }

    @FXML
    void btnBatal(ActionEvent event) {
        menambahkanPane.setVisible(false);
        idCol.setVisible(true);
    }

    private void refreshtablePenjualan() throws ClassNotFoundException, SQLException {
        penjualanList.clear();

        Connection connection;
        PreparedStatement preparedStatement;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/pbo project", "root", "");
        preparedStatement = connection.prepareStatement("SELECT * FROM `penjualan`");
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            penjualanList.add(new Penjualan(
                resultSet.getString("ID"), 
                resultSet.getString("Barang"), 
                resultSet.getString("Merk"), 
                null, 
                resultSet.getInt("Jumlah"), 
                resultSet.getString("Harga"), 
                null, 
                null));
                tablePenjualan.setItems(penjualanList);
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        namaCol.setCellValueFactory(new PropertyValueFactory<>("Barang"));
        merkCol.setCellValueFactory(new PropertyValueFactory<>("Merk"));
        jumlahCol.setCellValueFactory(new PropertyValueFactory<>("Stok"));
        hargaCol.setCellValueFactory(new PropertyValueFactory<>("Harga"));
    }


    private void refreshTableTransaksi() throws SQLException, ClassNotFoundException{
        Transaksi.BoxList.clear();
        Transaksi.jumlahTFields.clear();
        tambahBarangIcon();
        transaksiList.clear();

        Connection connection;
        PreparedStatement preparedStatement;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/pbo project", "root", "");
        preparedStatement = connection.prepareStatement("SELECT * FROM `barang`");
        resultSet = preparedStatement.executeQuery();
        int dex = 0;

        while (resultSet.next()){
            
            transaksiList.add(new Transaksi(
                resultSet.getString("ID"), 
                resultSet.getString("Barang"), 
                resultSet.getString("Merk"), 
                null, 
                0, 
                resultSet.getString("Harga"), 
                null, 
                null));
                
                tableTransaksi.setItems(transaksiList);
                stokRS.add(resultSet.getString("Stok"));
        }

        while (dex < Transaksi.BoxList.size()){
        
            Transaksi.BoxList.get(dex).setDisable(true);
            Transaksi.jumlahTFields.get(dex).setStyle("-fx-alignment:center;"+"-fx-cursor:hand;");
            Transaksi.jumlahTFields.get(dex).setPromptText("Max. ("+stokRS.get(dex)+")");
            dex++;
        }

        idColTr.setCellValueFactory(new PropertyValueFactory<>("ID"));
        namaColTr.setCellValueFactory(new PropertyValueFactory<>("Barang"));
        merkColTr.setCellValueFactory(new PropertyValueFactory<>("Merk"));
        hargaColTr.setCellValueFactory(new PropertyValueFactory<>("Harga"));
        jumlahColTr.setCellValueFactory(new PropertyValueFactory<>("Jumlah"));
        checkColTr.setCellValueFactory(new PropertyValueFactory<>("Box"));
    }
    
    private void tambahBarangIcon() {
            Callback<TableColumn<Transaksi, String>, TableCell<Transaksi, String>> cellFoctory = (TableColumn<Transaksi, String> param) -> {
            final TableCell<Transaksi, String> cell = new TableCell<Transaksi, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } 
                    else {
                        try {
                            Image plusImg = new Image(new FileInputStream("D:\\Programming\\Java\\Big Project\\src\\foto\\plus.png"));
                            ImageView plusView = new ImageView(plusImg);
                            plusView.setFitHeight(26); 
                            plusView.setFitWidth(24);
                            plusView.setStyle("-fx-cursor:hand");
    
                            HBox managebtn = new HBox(plusView);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(plusView, new Insets(2, 3, 0, 2));
                            setGraphic(managebtn);
                            setText(null);
                            

                            plusView.setOnMouseClicked((MouseEvent event) -> {
                                index = tableTransaksi.getSelectionModel().getSelectedIndex();
                                if (Transaksi.BoxList.get(index).isSelected()) {
                                    Transaksi.BoxList.get(index).setSelected(false);
                                }
                                else {
                                    Transaksi.BoxList.get(index).setSelected(true);
                                }
                                });

                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                    }

                }

            };
            return cell;
        };
        plusColTr.setCellFactory(cellFoctory);
    }

    @FXML
    void btnExit(ActionEvent event) {
        System.exit(0);
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

}