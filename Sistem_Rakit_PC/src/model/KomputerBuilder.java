package model;

public class KomputerBuilder {
    private String namaRakit;
    private Komponen processor;
    private Komponen vga;

    public KomputerBuilder setNamaRakit(String namaRakit) {
        this.namaRakit = namaRakit;
        return this; 
    }

    public KomputerBuilder setProcessor(Komponen processor) {
        this.processor = processor;
        return this;
    }

    public KomputerBuilder setVga(Komponen vga) {
        this.vga = vga;
        return this;
    }

    public Komputer build() {
        return new Komputer(namaRakit, processor, vga);
    }
}