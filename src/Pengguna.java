import java.util.ArrayList;

public class Pengguna {
    public String jabatan;
    public String nama;
    public String telepon;
    public String Alamat;
    public String username;
    public String password;

    static ArrayList<String> jabatanlist = new ArrayList<String>();
    static ArrayList<String> namalist = new ArrayList<String>();
    static ArrayList<String> teleponlist = new ArrayList<String>();
    static ArrayList<String> alamatlist = new ArrayList<String>();
    static ArrayList<String> usernameList = new ArrayList<String>();
    static ArrayList<String> passwordList = new ArrayList<String>();

    public Pengguna (String Jabatan, String nama, String Telepon, String Alamat, String Username, String Password){
        this.jabatan = Jabatan;
        this.nama = nama;
        this.telepon = Telepon;
        this.Alamat = Alamat;
        this.username = Username;
        this.password = Password;
    }
    
    public String getJabatan(){
        return this.jabatan;
    }

    public String getNama(){
        return this.nama;
    }

    public String getTelp(){
        return this.telepon;
    }

    
    public String getAlamat(){
        return this.Alamat;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }


}
