package model;

public class Komputer {
    private String namaRakit;
    private Komponen processor;
    private Komponen vga;
    private double totalHarga;

    public Komputer(String namaRakit, Komponen processor, Komponen vga) {
        this.namaRakit = namaRakit;
        this.processor = processor;
        this.vga = vga;
        this.totalHarga = processor.getHarga() + (vga != null ? vga.getHarga() : 0);
    }

    public String getDetail() {
        return "PC: " + namaRakit + " | CPU: " + processor.getNama() + 
               " | VGA: " + (vga != null ? vga.getNama() : "Onboard") + 
               " | Total: Rp" + (int)totalHarga;
    }
}