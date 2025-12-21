package model;

public abstract class Komponen {
    private int id;
    private String nama;
    private double harga;

    public Komponen(int id, String nama, double harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }

    public abstract String getTipe();

    public int getId() { return id; }
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public String toString() {
        return nama + " (Rp" + (int)harga + ")";
    }
}