import java.util.ArrayList;
import java.util.Date;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class Transaksi extends DaftarBarang {
    private CheckBox Box;
    private TextField Jumlah;
    static ArrayList<CheckBox> BoxList = new ArrayList<CheckBox>();
    static ArrayList<TextField> jumlahTFields = new ArrayList<TextField>();

    public Transaksi(String Id_Barang, String Barang, String Merk, String Jenis, int Stok, String Harga, Date Tanggal_Kadaluarsa, String Supplier) {
        super(Id_Barang, Barang, Merk, Jenis, Stok, Harga, Tanggal_Kadaluarsa, Supplier);
        this.Box = new CheckBox();
        this.Jumlah = new TextField();
        BoxList.add(Box);
        jumlahTFields.add(Jumlah);
    }
    
    public CheckBox getBox(){
        return Box;
    }

    public TextField getJumlah(){
        return Jumlah;
    }

    public void setBox(CheckBox checkBox){
        this.Box = checkBox;
    }

    public void setJumlah(TextField Jumlah){
        this.Jumlah = Jumlah;
    }
}
