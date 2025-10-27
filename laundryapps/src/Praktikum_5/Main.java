package Praktikum_5;

public class Main {
    public static void main(String[] args) {
        Mobil mobil = new Mobil("Toyota", "Avanza", 2021, "Bensin");
        Bus bus = new Bus("Mercedes-Benz", "Bus Pariwisata", 2018, 45, "Solar");
        Pesawat pesawat = new Pesawat("Garuda", "Boeing 737", 100, "Avtur");
        


        mobil.tampilkanInfo();
        bus.tampilkanInfo();
        pesawat.tampilkanInfo();
    }
}
