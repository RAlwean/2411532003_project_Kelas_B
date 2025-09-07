package model;

public class Service {
    private int id;
    private String jenis;
    private double harga;
    private String status;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }


    public static Service createSampleService() {
        Service s = new Service();
        s.setId(101);
        s.setJenis("Cuci Kering");
        s.setHarga(25000);
        s.setStatus("Tersedia");
        return s;
    }
}
