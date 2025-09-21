package DAO;

import config.Database;
import model.Layanan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LayananRepo implements LayananDAO {
    private Connection conn;

    public LayananRepo() {
        conn = Database.getConnection();
    }

    @Override
    public void save(Layanan layanan) {
        try {
            String sql = "INSERT INTO layanan (nama_layanan, harga) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, layanan.getNamaLayanan());
            ps.setDouble(2, layanan.getHarga());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Layanan> show() {
        List<Layanan> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM layanan";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Layanan l = new Layanan();
                l.setId(rs.getInt("id"));
                l.setNamaLayanan(rs.getString("nama_layanan"));
                l.setHarga(rs.getDouble("harga"));
                list.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(Layanan layanan) {
        try {
            String sql = "UPDATE layanan SET nama_layanan=?, harga=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, layanan.getNamaLayanan());
            ps.setDouble(2, layanan.getHarga());
            ps.setInt(3, layanan.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM layanan WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
