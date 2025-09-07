package ui;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsername;
    private JTextField txtPassword;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginFrame frame = new LoginFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 540, 503);

        // Panel biasa tanpa background image
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY); // bisa diganti warna lain
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Laundry Apps");
        lblNewLabel.setFont(new Font("STZhongsong", Font.BOLD, 30));
        lblNewLabel.setBounds(136, 27, 299, 103);
        lblNewLabel.setForeground(Color.YELLOW);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Males nyucii? , Sini kami cuciin");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1.setBounds(126, 94, 300, 25);
        lblNewLabel_1.setForeground(Color.YELLOW);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Username :");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_2.setBounds(80, 189, 134, 36);
        lblNewLabel_2.setForeground(Color.YELLOW);
        contentPane.add(lblNewLabel_2);

        txtUsername = new JTextField();
        txtUsername.setBounds(90, 235, 317, 36);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);

        JLabel lblNewLabel_2_1 = new JLabel("Password :");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_2_1.setBounds(80, 281, 134, 36);
        lblNewLabel_2_1.setForeground(Color.YELLOW);
        contentPane.add(lblNewLabel_2_1);

        txtPassword = new JTextField();
        txtPassword.setColumns(10);
        txtPassword.setBounds(90, 315, 317, 36);
        contentPane.add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(0, 64, 128));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (model.User.login(txtUsername.getText(), txtPassword.getText())) {
                    new MainFrame().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Login Gagal!");
                }
            }
        });
        btnLogin.setFont(new Font("STXinwei", Font.BOLD, 15));
        btnLogin.setBounds(166, 395, 161, 25);
        contentPane.add(btnLogin);
    }
}
