package ui;

import DAO.LayananRepo;
import model.Layanan;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class LayananFrame extends JFrame {

    private JTextField txtNama, txtHarga;
    private JTable tableLayanan;
    private LayananRepo repo = new LayananRepo();
    private String id;

    public LayananFrame() {
        setTitle("CRUD Layanan");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblNama = new JLabel("Nama Layanan");
        lblNama.setBounds(30, 20, 120, 25);
        add(lblNama);

        txtNama = new JTextField();
        txtNama.setBounds(150, 20, 200, 25);
        add(txtNama);

        JLabel lblHarga = new JLabel("Harga");
        lblHarga.setBounds(30, 60, 120, 25);
        add(lblHarga);

        txtHarga = new JTextField();
        txtHarga.setBounds(150, 60, 200, 25);
        add(txtHarga);

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(30, 100, 80, 25);
        add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(120, 100, 80, 25);
        add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(210, 100, 80, 25);
        add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(300, 100, 80, 25);
        add(btnCancel);

        tableLayanan = new JTable(new DefaultTableModel(
                new Object[][]{}, new String[]{"ID", "Nama", "Harga"}));
        JScrollPane sp = new JScrollPane(tableLayanan);
        sp.setBounds(30, 150, 500, 180);
        add(sp);

        loadTable();

        // Event
        btnSave.addActionListener(e -> {
            Layanan l = new Layanan();
            l.setNama(txtNama.getText());
            l.setHarga(Double.parseDouble(txtHarga.getText()));
            repo.save(l);
            reset();
            loadTable();
        });

        btnUpdate.addActionListener(e -> {
            if (id != null) {
                Layanan l = new Layanan();
                l.setId(Integer.parseInt(id));
                l.setNama(txtNama.getText());
                l.setHarga(Double.parseDouble(txtHarga.getText()));
                repo.update(l);
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

        tableLayanan.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tableLayanan.getSelectedRow();
                id = tableLayanan.getValueAt(row, 0).toString();
                txtNama.setText(tableLayanan.getValueAt(row, 1).toString());
                txtHarga.setText(tableLayanan.getValueAt(row, 2).toString());
            }
        });
    }

    private void loadTable() {
        List<Layanan> list = repo.show();
        DefaultTableModel model = (DefaultTableModel) tableLayanan.getModel();
        model.setRowCount(0);
        for (Layanan l : list) {
            model.addRow(new Object[]{l.getId(), l.getNama(), l.getHarga()});
        }
    }

    private void reset() {
        txtNama.setText("");
        txtHarga.setText("");
        id = null;
    }

    public static void main(String[] args) {
        new LayananFrame().setVisible(true);
    }
}
