import java.util.Date;

public class DaftarBarang extends AbstractBarang {
    private String ID_Barang;
    private String Barang;
    private String Merk;
    private String Jenis;
    private String Harga;
    private int Stok;
    private Date Tanggal_Kadaluarsa;

    public DaftarBarang(String Id_Barang, String Barang, String Merk, String Jenis, int Stok, String Harga,  Date Tanggal_Kadaluarsa){
        this.ID_Barang=Id_Barang;
        this.Barang=Barang;
        this.Merk=Merk;
        this.Jenis=Jenis;
        this.Stok=Stok;
        this.Harga=Harga;
        this.Tanggal_Kadaluarsa=Tanggal_Kadaluarsa;
    }

    @Override
    public String getID(){
        return ID_Barang;
    }
    @Override
    public String getBarang(){
        return Barang;
    }
    @Override
    public String getMerk(){
        return Merk;
    }
    @Override
    public String getJenis(){
        return Jenis;
    }
    @Override
    public int getStok(){
        return Stok;
    }
    @Override
    public String getHarga(){
        return Harga;
    }
    @Override
    public Date getTanggal(){
        return Tanggal_Kadaluarsa;
    }
    @Override
    public void macamBarang() {
    }
}
