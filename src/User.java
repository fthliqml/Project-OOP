import java.util.ArrayList;

public class User {
    public String nama;
    public String telepon;
    public String jabatan;
    public String username;
    public String password;

    static ArrayList<String> namaList = new ArrayList<String>();
    static ArrayList<String> teleponList = new ArrayList<String>();
    static ArrayList<String> jabatanList = new ArrayList<String>();
    static ArrayList<String> usernameList = new ArrayList<String>();
    static ArrayList<String> passwordList = new ArrayList<String>();

    public User (String nama, String Telepon, String Jabatan, String Username, String Password){
        this.nama = nama;
        this.telepon = Telepon;
        this.jabatan = Jabatan;
        this.username = Username;
        this.password = Password;
    }

    public String getNama(){
        return this.nama;
    }

    public String getTelp(){
        return this.telepon;
    }

    public String getJabatan(){
        return this.jabatan;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }


}
