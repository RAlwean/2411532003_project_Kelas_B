package config;

import java.sql.*;
import javax.swing.JOptionPane;

public class Database {
    public static Connection koneksi() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/laundry_apps?useSSL=false&serverTimezone=UTC",
                "root",
                ""
            );
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal: " + e.getMessage());
            return null;
        }
    }

	public static Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}
}
