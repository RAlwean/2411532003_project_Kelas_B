package ui;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 647, 536);

        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Laundry Apps");
        lblNewLabel.setFont(new Font("STZhongsong", Font.BOLD, 30));
        lblNewLabel.setBounds(41, 30, 300, 50);
        contentPane.add(lblNewLabel);

        // Membuat tombol biasa tanpa logo
        JButton btnPesanan   = createTextButton("Pesanan", 51, 100, 152, 108);
        JButton btnLayanan   = createTextButton("Layanan", 253, 100, 152, 108);
        JButton btnPelanggan = createTextButton("Pelanggan", 446, 100, 152, 108);
        JButton btnPengguna  = createTextButton("Pengguna", 51, 240, 152, 108);
        JButton btnLaporan   = createTextButton("Laporan", 253, 240, 152, 108);
        JButton btnProfile   = createTextButton("Profile", 446, 240, 152, 108);
        JButton btnExit      = createTextButton("Keluar", 243, 400, 173, 80);

        contentPane.add(btnPesanan);
        contentPane.add(btnLayanan);
        contentPane.add(btnPelanggan);
        contentPane.add(btnPengguna);
        contentPane.add(btnLaporan);
        contentPane.add(btnProfile);
        contentPane.add(btnExit);

        // ActionListener tombol
        btnPesanan.addActionListener(e -> {
            model.Costumer c = model.Costumer.createSampleCustomer();
            model.Service s = model.Service.createSampleService();
            model.Order o = model.Order.createSampleOrder(c, s, 1);

            JOptionPane.showMessageDialog(this,
                "Pesanan Baru\n" +
                "=================\n" +
                "Customer : " + c.getNama() + "\n" +
                "Alamat   : " + c.getAlamat() + "\n" +
                "HP       : " + c.getNoHp() + "\n\n" +
                "Layanan  : " + s.getJenis() + "\n" +
                "Harga    : Rp " + s.getHarga() + "\n\n" +
                "Total    : Rp " + o.getTotal() + "\n" +
                "Status   : " + o.getStatus() + "\n" +
                "Pembayaran: " + o.getStatusPembayaran()
            );
        });

        btnLayanan.addActionListener(e -> {
            model.Service s = model.Service.createSampleService();
            JOptionPane.showMessageDialog(this,
                "Layanan\n" +
                "=================\n" +
                "ID     : " + s.getId() + "\n" +
                "Jenis  : " + s.getJenis() + "\n" +
                "Harga  : Rp " + s.getHarga() + "\n" +
                "Status : " + s.getStatus()
            );
        });

        btnPelanggan.addActionListener(e -> {
            model.Costumer c = model.Costumer.createSampleCustomer();
            JOptionPane.showMessageDialog(this,
                "Pelanggan\n" +
                "=================\n" +
                "ID     : " + c.getId() + "\n" +
                "Nama   : " + c.getNama() + "\n" +
                "Alamat : " + c.getAlamat() + "\n" +
                "No HP  : " + c.getNoHp()
            );
        });

        btnPengguna.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                "Pengguna\n" +
                "=================\n" +
                "Fitur ini masih dalam pengembangan."
            );
        });

        btnLaporan.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                "Laporan\n" +
                "=================\n" +
                "Fitur laporan belum tersedia."
            );
        });

        btnProfile.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                "Profile\n" +
                "=================\n" +
                "Fitur profile belum tersedia."
            );
        });

        btnExit.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Keluar aplikasi...");
            System.exit(0);
        });
    }

    private JButton createTextButton(String text, int x, int y, int w, int h) {
        JButton button = new JButton(text);
        button.setBounds(x, y, w, h);
        button.setFont(new Font("Tahoma", Font.BOLD, 14));
        return button;
    }
}
