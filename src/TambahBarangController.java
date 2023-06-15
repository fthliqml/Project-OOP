import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TambahBarangController implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;
    private double xoffset = 0;
    private double yoffset = 0;

    @FXML private DatePicker tfDate;

    @FXML private TextField tfHarga;

    @FXML private TextField tfID;

    @FXML private TextField tfStok;

    @FXML private TextField tfMerk;

    @FXML private TextField tfNamaBarang;

    @FXML private ChoiceBox <String> boxJenis;
    private String [] jenis = {"Makanan", "Minuman", "Bahan Baku", "Bahan Penyedap"};

    @FXML private ChoiceBox <String> boxSupplier;

    ResultSet resultSet = null ;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        DaftarBarangController.i++;
        try {
            Connection connection;
            PreparedStatement preparedStatement;
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/pbo project", "root", "");
            preparedStatement = connection.prepareStatement("SELECT * FROM `supplier`");
            resultSet = preparedStatement.executeQuery();
        if (DaftarBarangController.i == 1){
            while (resultSet.next()){
                Supplier.namaList.add(resultSet.getString("Nama"));
            }
        }

        } catch (Exception e) {
            // TODO: handle exception
        }

        boxJenis.getItems().addAll(jenis);
        boxSupplier.getItems().addAll(Supplier.namaList);
    }
    
    int stokInt(){
        String s = tfStok.getText();
        int i = Integer.valueOf(s);
        return i;
    }

    @FXML
    void btnKembali(ActionEvent event) throws IOException {
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
    void btnSimpan(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (tfID.getText().isEmpty()||tfNamaBarang.getText().isEmpty()||tfMerk.getText().isEmpty()||boxJenis.getValue().isEmpty()||tfStok.getText().isEmpty()||tfHarga.getText().isEmpty()||tfDate.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Isi Semua Data");
            alert.showAndWait();
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

            tfID.setText("");
            tfNamaBarang.setText("");
            tfMerk.setText("");
            boxJenis.setValue(null);
            tfStok.setText("");
            tfHarga.setText("");
            tfDate.setValue(null);
            boxSupplier.setValue(null);

                
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Isi Data Dengan Format Yang Benar");
                alert.showAndWait();
            }
            
    
        }
       
    }

    @FXML
    void btnPengguna(ActionEvent event) {

    }

    @FXML
    void btnSupplier(ActionEvent event) {

    }

    @FXML
    void btnTransaksi(ActionEvent event) {

    }

    @FXML
    void btnLogout(ActionEvent event) {

    }


    @FXML
    void btnExit(ActionEvent event) {
        System.exit(0);
    }


}
