package config;

public class Testkoneksi {
    public static void main(String[] args) {
        if (Database.koneksi() != null) {
            System.out.println("Koneksi berhasil!");
        } else {
            System.out.println("Koneksi gagal!");
        }
    }
}
