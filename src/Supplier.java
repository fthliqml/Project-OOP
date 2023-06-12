import java.util.ArrayList;

public class Supplier {
    private String ID;
    private String Nama;
    private String Telepon;
    private String Alamat;

    static ArrayList<String> namaList = new ArrayList<String>();

    public Supplier(String ID, String Nama, String Telepon, String Alamat){
        this.ID=ID;
        this.Nama=Nama;
        this.Telepon=Telepon;
        this.Alamat=Alamat;
    }
    public String getID(){
        return this.ID;
    }
    public String getNama(){
        return this.Nama;
    }
    public String getTelepon(){
        return this.Telepon;
    }
    public String getAlamat(){
        return this.Alamat;
    }


}
