package model;

public class Layanan {
    private int id;
    private String namaLayanan;
    private double harga;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNamaLayanan() {
        return namaLayanan;
    }
    public void setNamaLayanan(String namaLayanan) {
        this.namaLayanan = namaLayanan;
    }

    public double getHarga() {
        return harga;
    }
    public void setHarga(double harga) {
        this.harga = harga;
    }
}
