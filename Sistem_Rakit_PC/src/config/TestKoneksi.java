package config;

import java.sql.Connection;

public class TestKoneksi {
    public static void main(String[] args) {
        Connection conn = Database.koneksi();

        if (conn != null) {
            System.out.println("=== STATUS KONEKSI ===");
            System.out.println("Koneksi Berhasil!");
            System.out.println("Siap digunakan untuk CRUD.");
        } else {
            System.out.println("=== STATUS KONEKSI ===");
            System.out.println("Koneksi Gagal.");
            System.out.println("Cek kembali library JDBC atau XAMPP Anda.");
        }
    }
}