package ui;

import DAO.PelangganRepo;
import model.Pelanggan;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class PelangganFrame extends JFrame {

    private JTextField txtNama, txtAlamat, txtNoTelp;
    private JTable tablePelanggan;
    private PelangganRepo repo = new PelangganRepo();
    private String id;

    public PelangganFrame() {
        setTitle("CRUD Pelanggan");
        setSize(650, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblNama = new JLabel("Nama");
        lblNama.setBounds(30, 20, 120, 25);
        add(lblNama);

        txtNama = new JTextField();
        txtNama.setBounds(150, 20, 200, 25);
        add(txtNama);

        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setBounds(30, 60, 120, 25);
        add(lblAlamat);

        txtAlamat = new JTextField();
        txtAlamat.setBounds(150, 60, 200, 25);
        add(txtAlamat);

        JLabel lblNoTelp = new JLabel("No Telp");
        lblNoTelp.setBounds(30, 100, 120, 25);
        add(lblNoTelp);

        txtNoTelp = new JTextField();
        txtNoTelp.setBounds(150, 100, 200, 25);
        add(txtNoTelp);

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(30, 140, 80, 25);
        add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(120, 140, 80, 25);
        add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(210, 140, 80, 25);
        add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(300, 140, 80, 25);
        add(btnCancel);

        tablePelanggan = new JTable(new DefaultTableModel(
                new Object[][]{}, new String[]{"ID", "Nama", "Alamat", "No Telp"}));
        JScrollPane sp = new JScrollPane(tablePelanggan);
        sp.setBounds(30, 190, 550, 180);
        add(sp);

        loadTable();

        // Event
        btnSave.addActionListener(e -> {
            Pelanggan p = new Pelanggan();
            p.setNama(txtNama.getText());
            p.setAlamat(txtAlamat.getText());
            p.setNoTelp(txtNoTelp.getText());
            repo.save(p);
            reset();
            loadTable();
        });

        btnUpdate.addActionListener(e -> {
            if (id != null) {
                Pelanggan p = new Pelanggan();
                p.setId(Integer.parseInt(id));
                p.setNama(txtNama.getText());
                p.setAlamat(txtAlamat.getText());
                p.setNoTelp(txtNoTelp.getText());
                repo.update(p);
                reset();
                loadTable();
            }
        });

        btnDelete.addActionListener(e -> {
            if (id != null) {
                repo.delete(Integer.parseInt(id));
                reset();
                loadTable();
            }
        });

        btnCancel.addActionListener(e -> reset());

        tablePelanggan.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tablePelanggan.getSelectedRow();
                id = tablePelanggan.getValueAt(row, 0).toString();
                txtNama.setText(tablePelanggan.getValueAt(row, 1).toString());
                txtAlamat.setText(tablePelanggan.getValueAt(row, 2).toString());
                txtNoTelp.setText(tablePelanggan.getValueAt(row, 3).toString());
            }
        });
    }

    private void loadTable() {
        List<Pelanggan> list = repo.show();
        DefaultTableModel model = (DefaultTableModel) tablePelanggan.getModel();
        model.setRowCount(0);
        for (Pelanggan p : list) {
            model.addRow(new Object[]{p.getId(), p.getNama(), p.getAlamat(), p.getNoTelp()});
        }
    }

    private void reset() {
        txtNama.setText("");
        txtAlamat.setText("");
        txtNoTelp.setText("");
        id = null;
    }

    public static void main(String[] args) {
        new PelangganFrame().setVisible(true);
    }
}
