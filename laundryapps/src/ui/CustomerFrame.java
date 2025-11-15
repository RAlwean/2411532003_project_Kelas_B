package ui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import DAO.CustomerRepo;
import model.Customer;
import model.CustomerBuilder;
import model.User;
import table.TableCustomer;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.Color;
import javax.swing.JTextField;

public class CustomerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField txtNama;
	private JTextField txtAlamat;
	private JTextField txtNomorHp;
	private JTable tableCustomer;
	
	CustomerRepo customerRepo = new CustomerRepo();
	List<Customer> ls;
	public String id;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerFrame frame = new CustomerFrame();
					frame.setVisible(true);
					frame.loadTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(231, 231, 231));
		panel.setBounds(10, 11, 508, 210);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Nama");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(50, 44, 49, 14);
		panel.add(lblName);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAlamat.setBounds(50, 81, 71, 14);
		panel.add(lblAlamat);
		
		JLabel lblNomorHp = new JLabel("NomorHP");
		lblNomorHp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNomorHp.setBounds(50, 115, 71, 14);
		panel.add(lblNomorHp);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer customer = new CustomerBuilder()
			    .setNama(txtNama.getText())
			    .setAlamat(txtAlamat.getText())
			    .setnomorHp(txtNomorHp.getText())
			    .setEmail(txtEmail.getText())
			    .build();
			    
			    customerRepo.save(customer);
			    reset();
			    loadTable();
			}
		});
		btnSave.setBackground(new Color(0, 128, 0));
		btnSave.setBounds(116, 176, 71, 23);
		panel.add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if (id != null) {
				        customerRepo.delete(id);
				        reset();
				        loadTable();
				    } else {
				        JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus");
				    }
			}
		});
		btnDelete.setBackground(new Color(255, 0, 0));
		btnDelete.setBounds(197, 176, 83, 23);
		panel.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBackground(new Color(255, 255, 128));
		btnCancel.setBounds(375, 176, 83, 23);
		panel.add(btnCancel);
		
		txtNama = new JTextField();
		txtNama.setBounds(116, 42, 330, 20);
		panel.add(txtNama);
		txtNama.setColumns(10);
		
		txtAlamat = new JTextField();
		txtAlamat.setColumns(10);
		txtAlamat.setBounds(115, 79, 330, 20);
		panel.add(txtAlamat);
		
		txtNomorHp = new JTextField();
		txtNomorHp.setColumns(10);
		txtNomorHp.setBounds(116, 113, 330, 20);
		panel.add(txtNomorHp);
		
		JLabel lblNomorHp_1 = new JLabel("Email");
		lblNomorHp_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNomorHp_1.setBounds(50, 144, 71, 14);
		panel.add(lblNomorHp_1);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(115, 140, 330, 20);
		panel.add(txtEmail);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Customer customer = new CustomerBuilder()
				    .setNama(txtNama.getText())
				    .setAlamat(txtAlamat.getText())
				    .setnomorHp(txtNomorHp.getText())
				    .setEmail(txtEmail.getText())
				    .setId(id)
					.build();
					
				    customerRepo.update(customer);
				    reset();
				    loadTable();
			}
		});
		btnUpdate.setBackground(new Color(0, 128, 255));
		btnUpdate.setBounds(282, 176, 83, 23);
		panel.add(btnUpdate);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(231, 231, 231));
		panel_1.setBounds(10, 249, 508, 328);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		tableCustomer = new JTable();
		tableCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = tableCustomer.getValueAt(tableCustomer.getSelectedRow(),0).toString();
				txtNama.setText(tableCustomer.getValueAt(tableCustomer.getSelectedRow(),1).toString());
				txtAlamat.setText(tableCustomer.getValueAt(tableCustomer.getSelectedRow(),2).toString());
				txtNomorHp.setText(tableCustomer.getValueAt(tableCustomer.getSelectedRow(),3).toString());
				txtEmail.setText(tableCustomer.getValueAt(tableCustomer.getSelectedRow(),4).toString());
			}
		});
		tableCustomer.setBounds(10, 11, 488, 306);
		panel_1.add(tableCustomer);
		
	}
	public void reset() {
	    txtNama.setText("");
	    txtAlamat.setText("");
	    txtNomorHp.setText("");
	    txtEmail.setText("");
	}
	public void loadTable() {
	    ls = customerRepo.show();
	    TableCustomer tc = new TableCustomer(ls);
	    tableCustomer.setModel(tc);
	}
}