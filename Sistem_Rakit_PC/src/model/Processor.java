package model;

public class Processor extends Komponen {
    public Processor(int id, String nama, double harga) {
        super(id, nama, harga);
    }

    public String getTipe() {
        return "Processor";
    }
}