package config;

import java.sql.*;
import javax.swing.JOptionPane;

public class Database {
    private static Connection conn;
    
    public static Connection koneksi() {
        if (conn == null) {
    	try {	
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
            	    "jdbc:mysql://localhost:3306/laundry",
            	    "root",
            	    ""
            	);
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
        return conn;
}

	public static Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}
}