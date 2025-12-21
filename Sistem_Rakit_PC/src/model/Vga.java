package model;

public class Vga extends Komponen {
    public Vga(int id, String nama, double harga) {
        super(id, nama, harga);
    }

    public String getTipe() {
        return "VGA Card";
    }
}