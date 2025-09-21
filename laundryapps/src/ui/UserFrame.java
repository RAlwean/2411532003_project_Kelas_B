package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;
import javax.swing.JOptionPane;

import dao.UserRepo;
import model.User;

public class UserFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTable tableUsers;

	UserRepo usr = new UserRepo();
	List<User> ls;
	public String id;
	// ===========================

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame frame = new UserFrame();
					frame.setVisible(true);
					frame.loadTable(); // panggil loadTable() saat frame muncul
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(68, 74, 80, 19);
		contentPane.add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setBounds(138, 76, 295, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(48, 122, 80, 19);
		contentPane.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(138, 124, 295, 19);
		contentPane.add(txtUsername);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(138, 176, 295, 19);
		contentPane.add(txtPassword);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(48, 176, 80, 19);
		contentPane.add(lblPassword);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(91, 236, 85, 21);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(194, 236, 85, 21);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(298, 236, 85, 21);
		contentPane.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(401, 236, 85, 21);
		contentPane.add(btnCancel);
		
		tableUsers = new JTable();
		tableUsers.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] { "ID", "Name", "Username", "Password" }
		));
		tableUsers.setBounds(74, 289, 412, 190);
		contentPane.add(tableUsers);

		// ===== Event Handler sesuai modul =====
		
		// CREATE USER
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.setNama(txtName.getText());
				user.setUsername(txtUsername.getText());
				user.setPassword(txtPassword.getText());
				usr.save(user);
				reset();
				loadTable();
			}
		});
		
		// UPDATE USER
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.setName(txtName.getText());
				user.setUsername(txtUsername.getText());
				user.setPassword(txtPassword.getText());
				user.setId(Integer.parseInt(id));
				usr.update(user);
				reset();
				loadTable();
			}
		});
		
		// DELETE USER
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id != null) {
					usr.delete(Integer.parseInt(id));
					reset();
					loadTable();
				} else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan di hapus");
				}
			}
		});
		
		// CANCEL
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		
		// Klik tabel untuk isi form
		tableUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = tableUsers.getValueAt(tableUsers.getSelectedRow(), 0).toString();
				txtName.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(), 1).toString());
				txtUsername.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(), 2).toString());
				txtPassword.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(), 3).toString());
			}
		});
		// ======================================
	}
	
	// ===== method dari modul =====
	public void reset() {
		txtName.setText("");
		txtUsername.setText("");
		txtPassword.setText("");
	}
	
	public void loadTable() {
		ls = usr.show();
		DefaultTableModel model = (DefaultTableModel) tableUsers.getModel();
		model.setRowCount(0);
		for (User u : ls) {
			model.addRow(new Object[] { u.getId(), u.getName(), u.getUsername(), u.getPassword() });
		}
	}
}
