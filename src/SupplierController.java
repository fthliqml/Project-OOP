import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class SupplierController implements Initializable {
    @FXML
    private TableColumn<Supplier, String> idCol;
    @FXML
    private TableColumn<Supplier, String> namaCol;
    @FXML
    private TableColumn<Supplier, String> teleponCol;
    @FXML
    private TableColumn<Supplier, String> alamatCol;
    @FXML
    private TableColumn<Supplier, String> editCol;
    @FXML
    private TableView<Supplier> tableSupplier;

    @FXML
    private AnchorPane menambahkanPane;
    @FXML
    private TextField tfAlamat;
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfNama;
    @FXML
    private TextField tfTelepon;

    @FXML
    private Button resetID;
    @FXML
    private Button editID;
    @FXML
    private Button tambahID;

    ResultSet resultSet = null ;
    Supplier supplier = null ;
    ObservableList<Supplier> SupplierList = FXCollections.observableArrayList();

    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private double xoffset = 0;
    private double yoffset = 0;
    private boolean update;
    int i = 2;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        menambahkanPane.setVisible(false);
        editCol.setVisible(false);
        refreshTable();
    }

    void refreshTable(){
        SupplierList.clear();

        try {
        Connection connection;
        PreparedStatement preparedStatement;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/pbo project", "root", "");
        preparedStatement = connection.prepareStatement("SELECT * FROM `supplier`");
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            SupplierList.add(new Supplier(
                resultSet.getString("ID"), 
                resultSet.getString("Nama"), 
                resultSet.getString("Telepon"), 
                resultSet.getString("Alamat")));
                tableSupplier.setItems(SupplierList);
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        namaCol.setCellValueFactory(new PropertyValueFactory<>("Nama"));
        teleponCol.setCellValueFactory(new PropertyValueFactory<>("Telepon"));
        alamatCol.setCellValueFactory(new PropertyValueFactory<>("Alamat"));

        } catch (Exception e) {
            // TODO: handle exception
        }
        
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

 
    @FXML
    void btnReset(ActionEvent event) {
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
                supplier = tableSupplier.getSelectionModel().getSelectedItem();
                preparedStatement = connection.prepareStatement("DELETE FROM `supplier`");
                preparedStatement.execute();
                refreshTable();
                
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
        
                }
        }
        
    }
    
    @FXML
    void btnEdit(ActionEvent event) throws IOException {
        if (i%2==1) {
            editCol.setVisible(false);
        }
        else {
            editCol.setVisible(true);
            Callback<TableColumn<Supplier, String>, TableCell<Supplier, String>> cellFoctory = (TableColumn<Supplier, String> param) -> {
                final TableCell<Supplier, String> cell = new TableCell<Supplier, String>() {
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
                                HBox.setMargin(editView, new Insets(2, 3, 0, 2));
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
                                            supplier = tableSupplier.getSelectionModel().getSelectedItem();
                                            preparedStatement = connection.prepareStatement("DELETE FROM `supplier` WHERE ID = ?");
                                            preparedStatement.setString(1, supplier.getID());
                                            preparedStatement.execute();
                                            refreshTable();
                                            
                                        } catch (SQLException | ClassNotFoundException ex) {
                                            ex.printStackTrace();
                                    
                                            }
                                    }
                                
                                });

                                editView.setOnMouseClicked((MouseEvent event) -> {
                                        setUpdate(true);
                                        supplier = tableSupplier.getSelectionModel().getSelectedItem();
                                        menambahkanPane.setVisible(true);
                                        resetID.setVisible(false);
                                        editID.setVisible(false);
                                        tambahID.setVisible(false);
                                        setTextField(supplier.getID(), supplier.getNama(), supplier.getTelepon(), supplier.getAlamat());
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
    i++;
        
    }
    
    @FXML
    void btnTambah(ActionEvent event) {
        setUpdate(false);
        menambahkanPane.setVisible(true);
        resetID.setVisible(false);
        editID.setVisible(false);
        tambahID.setVisible(false);
    }

    @FXML
    void btnSimpan(ActionEvent event) {
        if (tfID.getText().isEmpty()||tfNama.getText().isEmpty()||tfTelepon.getText().isEmpty()||tfAlamat.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Isi Semua Data");
            alert.showAndWait();
        } 
        else if(update==true){
            try {
                Connection connection;
                PreparedStatement preparedStatement;
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/pbo project", "root", "");
                preparedStatement = connection.prepareStatement("UPDATE `supplier` SET "
                + "`ID`=?,"
                + "`Nama`=?,"
                + "`Telepon`=?,"
                + "`Alamat`=? WHERE ID = ?");
                preparedStatement.setString(1, tfID.getText());
                preparedStatement.setString(2, tfNama.getText());
                preparedStatement.setString(3, tfTelepon.getText());
                preparedStatement.setString(4, tfAlamat.getText());
                supplier = tableSupplier.getSelectionModel().getSelectedItem();
                preparedStatement.setString(5, supplier.getID());
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
                insert = con1.prepareStatement("insert into supplier(ID,Nama,Telepon,Alamat)values(?,?,?,?)");
                //Save To Database
                insert.setString(1, tfID.getText());
                insert.setString(2, tfNama.getText());
                insert.setString(3, tfTelepon.getText());
                insert.setString(4, tfAlamat.getText());
                insert.executeUpdate();
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Berhasil Menyimpan Data");
                alert.showAndWait();

                refreshTable();
                tfID.setText("");
                tfNama.setText("");
                tfTelepon.setText("");
                tfAlamat.setText("");          
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    @FXML
    void btnBatal(ActionEvent event) {
        menambahkanPane.setVisible(false);
        resetID.setVisible(true);
        editID.setVisible(true);
        tambahID.setVisible(true);
    }

    void setTextField(String ID, String Nama, String Telepon, String Alamat){
        tfID.setText(ID);
        tfNama.setText(Nama);
        tfTelepon.setText(Telepon);
        tfAlamat.setText(Alamat);
    }

    void setUpdate(Boolean b){
        this.update=b;
    }

}