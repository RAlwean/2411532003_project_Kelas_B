package DAO;

import config.Database;
import model.Customer;
import model.CustomerBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerRepo implements CustomerDao {
    private Connection connection;

    final String insert = "INSERT INTO customer (nama, email, alamat, nomorhp) VALUES (?,?,?,?);";
    final String select = "SELECT * FROM customer;";
    final String update = "UPDATE customer SET nama=?, email=?, alamat=?, nomorhp=? WHERE id=?;";
    final String delete = "DELETE FROM customer WHERE id=?;";

    public CustomerRepo() {
        connection = Database.koneksi();
    }

    @Override
    public void save(Customer customer) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert);
            st.setString(1, customer.getNama());
            st.setString(2, customer.getEmail());
            st.setString(3, customer.getAlamat());
            st.setString(4, customer.getNomorHp());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Customer> show() {
        List<Customer> ls = null;
        try {
        	ls = new ArrayList<Customer>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                Customer cs = new CustomerBuilder()
                .setId(rs.getString("id"))
                .setNama(rs.getString("nama"))
                .setEmail(rs.getString("email"))
                .setAlamat(rs.getString("alamat"))
                .setnomorHp(rs.getString("nomorhp"))
                .build();
                ls.add(cs);
            }
        } catch (SQLException e) {
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.SEVERE, null, e);
        }
        return ls;
    }

    @Override
    public void update(Customer customer) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(update);
            st.setString(1, customer.getNama());
            st.setString(2, customer.getEmail());
            st.setString(3, customer.getAlamat());
            st.setString(4, customer.getNomorHp());
            st.setString(5, customer.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String id) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(delete);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}