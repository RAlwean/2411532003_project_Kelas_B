package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DAO.ServiceRepo;
import model.Customer;
import model.Service;
import table.TableService;

public class ServiceFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtJenis;
	private JTextField txtHarga;
	private JTextField txtStatus;
	private JTable tableService;
	
	ServiceRepo serviceRepo = new ServiceRepo();
	List<Service> listService;
	public String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiceFrame frame = new ServiceFrame();
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
	public ServiceFrame() {
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
		
		JLabel lblJenis = new JLabel("Jenis");
		lblJenis.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblJenis.setBounds(50, 44, 49, 14);
		panel.add(lblJenis);
		
		JLabel lblHarga = new JLabel("Harga");
		lblHarga.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHarga.setBounds(50, 81, 71, 14);
		panel.add(lblHarga);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStatus.setBounds(50, 115, 71, 14);
		panel.add(lblStatus);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Service service = new Service();
				    service.setJenis(txtJenis.getText());
				    service.setHarga(txtHarga.getText());
				    service.setStatus(txtStatus.getText());
				    serviceRepo.save(service);
				    reset();
				    loadTable();
			}
		});
		btnSave.setBackground(new Color(0, 128, 0));
		btnSave.setBounds(115, 153, 71, 23);
		panel.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Service service = new Service();
			    service.setJenis(txtJenis.getText());
			    service.setHarga(txtHarga.getText());
			    service.setStatus(txtStatus.getText());
			    service.setId(id);
			    serviceRepo.update(service);
			    reset();
			    loadTable();
			}
		});
		btnUpdate.setBackground(new Color(0, 0, 160));
		btnUpdate.setBounds(189, 153, 83, 23);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id != null) {
					serviceRepo.delete(id);
					reset();
					loadTable();
				} else {
					JOptionPane.showMessageDialog(null, "Silahkan Pilih data yang akan di hapus");
				}
			}
		});
		btnDelete.setBackground(new Color(255, 0, 0));
		btnDelete.setBounds(276, 153, 83, 23);
		panel.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(255, 255, 128));
		btnCancel.setBounds(363, 153, 83, 23);
		panel.add(btnCancel);
		
		txtJenis = new JTextField();
		txtJenis.setBounds(109, 42, 337, 20);
		panel.add(txtJenis);
		txtJenis.setColumns(10);
		
		txtHarga = new JTextField();
		txtHarga.setColumns(10);
		txtHarga.setBounds(109, 79, 337, 20);
		panel.add(txtHarga);
		
		txtStatus = new JTextField();
		txtStatus.setColumns(10);
		txtStatus.setBounds(109, 113, 337, 20);
		panel.add(txtStatus);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(231, 231, 231));
		panel_1.setBounds(10, 249, 508, 328);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		tableService = new JTable();
		tableService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = tableService.getValueAt(tableService.getSelectedRow(),0).toString();
				txtJenis.setText(tableService.getValueAt(tableService.getSelectedRow(),1).toString());
				txtHarga.setText(tableService.getValueAt(tableService.getSelectedRow(),2).toString());
				txtStatus.setText(tableService.getValueAt(tableService.getSelectedRow(),3).toString());
			}
		});
		tableService.setBounds(10, 11, 488, 306);
		panel_1.add(tableService);
		
		
	}
	public void reset() {
	    txtJenis.setText("");
	    txtHarga.setText("");
	    txtStatus.setText(""); 
	}
	public void loadTable() {
	    listService = serviceRepo.show();
	    TableService ts = new TableService(listService);
	    tableService.setModel(ts); 
	}


}