package DAO;

import config.Database;
import model.Pelanggan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PelangganRepo implements PelangganDAO {
    private Connection conn;

    public PelangganRepo() {
        conn = Database.getConnection();
    }

    @Override
    public void save(Pelanggan pelanggan) {
        try {
            String sql = "INSERT INTO pelanggan (nama, alamat, telepon) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pelanggan.getNama());
            ps.setString(2, pelanggan.getAlamat());
            ps.setString(3, pelanggan.getTelepon());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pelanggan> show() {
        List<Pelanggan> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM pelanggan";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Pelanggan p = new Pelanggan();
                p.setId(rs.getInt("id"));
                p.setNama(rs.getString("nama"));
                p.setAlamat(rs.getString("alamat"));
                p.setTelepon(rs.getString("telepon"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(Pelanggan pelanggan) {
        try {
            String sql = "UPDATE pelanggan SET nama=?, alamat=?, telepon=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pelanggan.getNama());
            ps.setString(2, pelanggan.getAlamat());
            ps.setString(3, pelanggan.getTelepon());
            ps.setInt(4, pelanggan.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM pelanggan WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
