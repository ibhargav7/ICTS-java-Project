package Person;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connection.databaseConnect;
import HoD.HoD;
import ICTS_Staff.Staff;
import home.MainMenu;
import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Person extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	public static String user_id1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Person frame = new Person();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	databaseConnect dbc= new databaseConnect();
	
	
	public Person() {
		setTitle("Initiator's Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 966, 543);
		contentPane.add(tabbedPane);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Request Form", null , panel, null);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(407, 185, 327, 32);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(407, 261, 327, 32);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Make a Request");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
		lblNewLabel.setBounds(241, 51, 440, 70);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Request  :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1.setBounds(233, 181, 140, 32);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Deapetment  :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_2.setBounds(199, 260, 152, 26);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Place Request");
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection=dbc.getconnection();
				try {
					String query = "insert into request_details(request_name,department,person_id) values(?,?,?) ";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setString(1,textField.getText());
					pst.setString(2,textField_1.getText());
					pst.setString(3,user_id1);

					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Request Placed Successfully");
					}
					
			catch(Exception e2){
				JOptionPane.showMessageDialog(null,e2);
				}
			contentPane.revalidate();
			contentPane.repaint();
			
			connection=dbc.getconnection();
			try {
				String query = "select request_name,department,request_approval,request_status,hod_name,staff_name from request_details natural join hod where person_id=?";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, user_id1);
				ResultSet rs = pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				}					
			catch(Exception e2){
				JOptionPane.showMessageDialog(null,e2);
				}
			
			}
		});
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("All Requests", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(54, 116, 847, 248);
		panel_1.add(scrollPane_1);
		
		table = new JTable();
		
		scrollPane_1.setViewportView(table);
		
		connection=dbc.getconnection();
		try {
			String query = "select request_name,department,request_approval,request_status,hod_name,staff_name from request_details natural join hod where person_id=?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, user_id1);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			}
			
		catch(Exception e2){
			JOptionPane.showMessageDialog(null,e2);
			}
		
		
		JLabel lblNewLabel_3 = new JLabel("Request History");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_3.setBounds(70, 34, 303, 41);
		panel_1.add(lblNewLabel_3);
		
		JButton btnNewButton_2 = new JButton("Delete Completed & Disaaproved Requests");
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				connection=dbc.getconnection();
				try {
					String query = "delete from request_details where (person_id=? and request_status='completed') or (person_id=? and request_approval='disapproved')";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, user_id1);
					pst.setString(2, user_id1);
					pst.executeUpdate();
					}
					
				catch(Exception e2){
					JOptionPane.showMessageDialog(null,e2);
					}
				connection=dbc.getconnection();
				try {
					String query = "select request_name,department,request_approval,request_status,hod_name,staff_name from request_details natural join hod where person_id=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, user_id1);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					}					
				catch(Exception e2){
					JOptionPane.showMessageDialog(null,e2);
					}
				
			}
		});
		btnNewButton_2.setBounds(288, 413, 381, 50);
		panel_1.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 116, 840, 248);
		panel_1.add(scrollPane);
		btnNewButton.setBounds(294, 368, 134, 40);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Main Menu");
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				MainMenu mm = new MainMenu();
				mm.frmMainMenu.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(528, 368, 134, 40);
		panel.add(btnNewButton_1);
	}
}
