package dao;

import config.Database;
import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KomponenRepo implements KomponenDao {
    private Connection connection;
    private final String update = "UPDATE komponen SET nama=?, tipe=?, harga=? WHERE id=?";
    private final String insert = "INSERT INTO komponen (nama, tipe, harga) VALUES (?, ?, ?)";
    private final String select = "SELECT * FROM komponen";
    private final String delete = "DELETE FROM komponen WHERE id=?";

    public KomponenRepo() {
        connection = Database.koneksi();
    }

    public void save(String nama, String tipe, double harga) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert);
            st.setString(1, nama);
            st.setString(2, tipe);
            st.setDouble(3, harga);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(int id, String nama, String tipe, double harga) {
    	 try {
    	     PreparedStatement st = connection.prepareStatement(update);
    	     st.setString(1, nama);
    	     st.setString(2, tipe);
    	     st.setDouble(3, harga);
    	     st.setInt(4, id);
    	     st.executeUpdate();
    	 } catch (SQLException e) {
    	     e.printStackTrace();
    	 }
    	}

    public List<Komponen> show() {
        List<Komponen> ls = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                String tipe = rs.getString("tipe");
                if(tipe.equalsIgnoreCase("Processor")) {
                    ls.add(new Processor(rs.getInt("id"), rs.getString("nama"), rs.getDouble("harga")));
                } else {
                    ls.add(new Vga(rs.getInt("id"), rs.getString("nama"), rs.getDouble("harga")));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(KomponenRepo.class.getName()).log(Level.SEVERE, null, e);
        }
        return ls;
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement st = connection.prepareStatement(delete);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}