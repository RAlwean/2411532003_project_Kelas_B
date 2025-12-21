package ui;

import dao.KomponenRepo;
import model.*;
import util.ValidasiException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainFrame extends JFrame {
    private JTextField txtNamaKomponen, txtHarga, txtNamaRakit;
    private JComboBox<String> cmbTipe;
    private JComboBox<Komponen> cmbPilihCpu, cmbPilihVga;
    private JTable tableKomponen;
    private JTextArea txtLog;
    private DefaultTableModel tableModel;
    private JProgressBar progressBar;
    private JButton btnSimpan, btnHapus, btnRakit, btnEdit; 
    
    // Data & Service
    private KomponenRepo repo;
    private List<Komponen> listKomponen;
    private ExecutorService threadPool;
    
    private int selectedId = 0; 

    public MainFrame() {
        repo = new KomponenRepo();
        threadPool = Executors.newFixedThreadPool(2); 

        setTitle("Aplikasi PC Builder - Praktikum PBO");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        initUI();
        loadTable();
        loadComboBox();
    }

    private void initUI() {
        JLabel lbl1 = new JLabel("Nama Komponen:");
        lbl1.setBounds(20, 20, 120, 25);
        add(lbl1);

        txtNamaKomponen = new JTextField();
        txtNamaKomponen.setBounds(140, 20, 150, 25);
        add(txtNamaKomponen);

        JLabel lbl2 = new JLabel("Tipe:");
        lbl2.setBounds(20, 50, 120, 25);
        add(lbl2);

        String[] tipes = {"Processor", "VGA"};
        cmbTipe = new JComboBox<>(tipes);
        cmbTipe.setBounds(140, 50, 150, 25);
        add(cmbTipe);

        JLabel lbl3 = new JLabel("Harga:");
        lbl3.setBounds(20, 80, 120, 25);
        add(lbl3);

        txtHarga = new JTextField();
        txtHarga.setBounds(140, 80, 150, 25);
        add(txtHarga);

        btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(20, 120, 80, 30);
        add(btnSimpan);

        btnEdit = new JButton("Update"); 
        btnEdit.setBounds(110, 120, 80, 30);
        btnEdit.setEnabled(false); 
        add(btnEdit);

        btnHapus = new JButton("Hapus");
        btnHapus.setBounds(200, 120, 80, 30);
        add(btnHapus);
        
        String[] columns = {"ID", "Nama", "Tipe", "Harga"};
        tableModel = new DefaultTableModel(columns, 0);
        tableKomponen = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(tableKomponen);
        sp.setBounds(20, 170, 350, 350);
        add(sp);

        JLabel lbl4 = new JLabel("=== RAKIT PC ===");
        lbl4.setBounds(400, 20, 200, 25);
        add(lbl4);

        txtNamaRakit = new JTextField("PC Gaming Ultimate");
        txtNamaRakit.setBounds(400, 50, 200, 25);
        add(txtNamaRakit);

        cmbPilihCpu = new JComboBox<>();
        cmbPilihCpu.setBounds(400, 80, 250, 25);
        add(cmbPilihCpu);

        cmbPilihVga = new JComboBox<>();
        cmbPilihVga.setBounds(400, 110, 250, 25);
        add(cmbPilihVga);

        btnRakit = new JButton("Rakit Sekarang!");
        btnRakit.setBounds(400, 150, 150, 30);
        add(btnRakit);

        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(400, 190, 350, 20);
        progressBar.setStringPainted(true);
        add(progressBar);

        txtLog = new JTextArea();
        txtLog.setEditable(false);
        JScrollPane spLog = new JScrollPane(txtLog);
        spLog.setBounds(400, 220, 350, 300);
        add(spLog);

        btnSimpan.addActionListener(e -> actionSimpan());
        btnEdit.addActionListener(e -> actionUpdate()); 
        btnHapus.addActionListener(e -> actionHapus());
        btnRakit.addActionListener(e -> actionRakit());

        tableKomponen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableKomponen.getSelectedRow();
                if (row != -1) {
                    selectedId = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
                    String nama = tableModel.getValueAt(row, 1).toString();
                    String tipe = tableModel.getValueAt(row, 2).toString();
                    String harga = tableModel.getValueAt(row, 3).toString();

                    txtNamaKomponen.setText(nama);
                    cmbTipe.setSelectedItem(tipe);
                    txtHarga.setText(harga.replace(".0", "")); 

                   
                    btnSimpan.setEnabled(false);
                    btnEdit.setEnabled(true);
                }
            }
        });
    }

    private void actionSimpan() {
        try {
            String nama = txtNamaKomponen.getText();
            String hargaStr = txtHarga.getText();
            String tipe = (String) cmbTipe.getSelectedItem();
            
            if (nama.isEmpty() || hargaStr.isEmpty()) {
                throw new ValidasiException("Input tidak boleh kosong!");
            }
            double harga = Double.parseDouble(hargaStr);
            if (harga < 0) {
                throw new ValidasiException("Harga tidak boleh negatif!");
            }

            repo.save(nama, tipe, harga);
            loadTable();
            loadComboBox();
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
            resetForm();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga harus angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ValidasiException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validasi Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actionUpdate() {
        try {
            String nama = txtNamaKomponen.getText();
            String hargaStr = txtHarga.getText();
            String tipe = (String) cmbTipe.getSelectedItem();

            if (selectedId == 0) {
                JOptionPane.showMessageDialog(this, "Pilih data dulu!");
                return;
            }

            if (nama.isEmpty() || hargaStr.isEmpty()) {
                throw new ValidasiException("Input tidak boleh kosong!");
            }

            double harga = Double.parseDouble(hargaStr);
            if (harga < 0) {
                throw new ValidasiException("Harga tidak boleh negatif!");
            }

            repo.update(selectedId, nama, tipe, harga);
            
            loadTable();
            loadComboBox();
            JOptionPane.showMessageDialog(this, "Data berhasil diupdate!");
            resetForm(); 
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga harus angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ValidasiException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validasi Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actionHapus() {
        int row = tableKomponen.getSelectedRow();
        if (row != -1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
                repo.delete(id);
                loadTable();
                loadComboBox();
                resetForm(); 
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris yang akan dihapus.");
        }
    }


    private void actionRakit() {
        String namaPC = txtNamaRakit.getText();
        Komponen cpu = (Komponen) cmbPilihCpu.getSelectedItem();
        Komponen vga = (Komponen) cmbPilihVga.getSelectedItem();

        if (cpu == null) {
            JOptionPane.showMessageDialog(this, "Pilih Processor dulu!");
            return;
        }

        btnRakit.setEnabled(false);
        txtLog.setText("");
        progressBar.setValue(0);
        
        threadPool.execute(() -> {
            try {
                updateLog("Memulai perakitan " + namaPC + "...");
                Thread.sleep(1000);
                
                updateLog("Memasang Processor: " + cpu.getNama());
                updateProgress(30);
                Thread.sleep(1000);

                if (vga != null) {
                    updateLog("Memasang VGA: " + vga.getNama());
                    updateProgress(60);
                    Thread.sleep(1000);
                } else {
                    updateLog("Menggunakan VGA Onboard...");
                    updateProgress(60);
                    Thread.sleep(500);
                }

                updateLog("Menghubungkan Kabel Power...");
                updateProgress(80);
                Thread.sleep(1000);
                
                Komputer pcBaru = new KomputerBuilder()
                        .setNamaRakit(namaPC)
                        .setProcessor(cpu)
                        .setVga(vga)
                        .build();

                updateLog("--------------------------------");
                updateLog("PERAKITAN SELESAI!");
                updateLog(pcBaru.getDetail());
                updateProgress(100);

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                SwingUtilities.invokeLater(() -> btnRakit.setEnabled(true));
            }
        });
    }

    private void updateLog(String message) {
        SwingUtilities.invokeLater(() -> txtLog.append(message + "\n"));
    }

    private void updateProgress(int value) {
        SwingUtilities.invokeLater(() -> progressBar.setValue(value));
    }

    private void loadTable() {
        tableModel.setRowCount(0);
        listKomponen = repo.show();
        for (Komponen k : listKomponen) {
            tableModel.addRow(new Object[]{k.getId(), k.getNama(), k.getTipe(), k.getHarga()});
        }
    }

    private void loadComboBox() {
        cmbPilihCpu.removeAllItems();
        cmbPilihVga.removeAllItems();
        
        for (Komponen k : listKomponen) {
            if (k instanceof Processor) {
                cmbPilihCpu.addItem(k);
            } else if (k instanceof Vga) {
                cmbPilihVga.addItem(k);
            }
        }
    }

    private void resetForm() {
        txtNamaKomponen.setText("");
        txtHarga.setText("");
        selectedId = 0;
        
        btnSimpan.setEnabled(true);
        btnEdit.setEnabled(false);
        tableKomponen.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}