package model;

public class Costumer {
    private int id;
    private String nama;
    private String alamat;
    private String noHp;
    public int getId() { return id; }
    
    public void setId(int id) { this.id = id; }
    public String getNama() { return nama; }
    
    public void setNama(String nama) { this.nama = nama; }

    public String getAlamat() { return alamat; }
    
    public void setAlamat(String alamat) { this.alamat = alamat; }

    public String getNoHp() { return noHp; }
    
    public void setNoHp(String noHp) { this.noHp = noHp; }

 
    public static Costumer createSampleCustomer() {
        Costumer c = new Costumer();
        c.setId(1);
        c.setNama("Andi");
        c.setAlamat("Jl. Melati No. 5");
        c.setNoHp("08123456789");
        return c;
    }
}
