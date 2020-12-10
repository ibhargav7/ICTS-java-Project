package ICTS_Staff;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connection.databaseConnect;
import home.MainMenu;
import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Staff extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	public static String user_id3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Staff frame = new Staff();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	databaseConnect dbc= new databaseConnect();
	
	public Staff() {
		setTitle("ICTS Staff");
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
		tabbedPane.addTab("Take a Request", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select the Request that you want to take in your account");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(48, 37, 659, 37);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 107, 855, 297);
		panel.add(scrollPane);
		connection=dbc.getconnection();
		table = new JTable();
		try {
			String query = "select request_details.request_id,request_name, department,request_approval, request_status from request_details where staff_name='not yet assigned' and request_approval='approved'";
			PreparedStatement pst = connection.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			}
			
		catch(Exception e2){
			JOptionPane.showMessageDialog(null,e2);
			}
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String res = (table.getModel().getValueAt(row,0)).toString();
				int request_id=Integer.parseInt(res);
				
				JButton btnNewButton = new JButton("Take a Request");
				btnNewButton.setBackground(new Color(192, 192, 192));
				btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
				btnNewButton.setBounds(305, 445, 139, 37);
				panel.add(btnNewButton);
				contentPane.revalidate();
				contentPane.repaint();
				btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						
						String query = "update request_details set staff_name=(select staff_name from staff where staff_id = ?) where request_id = ?";
						PreparedStatement pst = connection.prepareStatement(query);
						pst.setString(1, user_id3);
						pst.setInt(2, request_id);
						pst.executeUpdate();
						
					}catch(Exception e2){
						JOptionPane.showMessageDialog(null,e2);
					}
					try {
						String query = "select request_details.request_id,request_name, department,request_approval, request_status from request_details where staff_name='not yet assigned' and request_approval='approved'";
						PreparedStatement pst = connection.prepareStatement(query);
						
						ResultSet rs = pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						}
						
					catch(Exception e2){
						JOptionPane.showMessageDialog(null,e2);
						}
					}
				});
			}
		});
		scrollPane.setViewportView(table);

		JButton btnNewButton_1 = new JButton("Back to Main Menu");
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				MainMenu mm = new MainMenu();
				mm.frmMainMenu.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(752, 41, 154, 37);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Request Handling", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Handling of Request");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblNewLabel_1.setBounds(34, 37, 304, 32);
		panel_1.add(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(34, 100, 890, 293);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		try {
			String query = "select request_details.request_id,request_name,department,request_approval, request_status from request_details natural join staff where staff_id=? and request_status != 'completed' ";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, user_id3);
			ResultSet rs1 = pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs1));
			}
			
		catch(Exception e2){
			JOptionPane.showMessageDialog(null,e2);
			}
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
					int row = table_1.getSelectedRow();
					String res = (table_1.getModel().getValueAt(row,0)).toString();
					int request_id=Integer.parseInt(res);
					
					JButton button2 = new JButton("Set as completed");
					button2.setFont(new Font("Times New Roman", Font.BOLD, 14));
					button2.setBackground(new Color(192, 192, 192));
					button2.setBounds(383, 436, 203, 42);
					panel_1.add(button2);

					contentPane.revalidate();
					contentPane.repaint();
					
					button2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
							connection=dbc.getconnection();
							String query = "update request_details set request_status='completed' where request_id = ?";
							PreparedStatement pst = connection.prepareStatement(query);
							
							pst.setInt(1, request_id);
							pst.executeUpdate();
							}catch(Exception e2){
								JOptionPane.showMessageDialog(null,e2);
							}
							try {
								String query = "select request_details.request_id,request_name,department,request_approval, request_status from request_details natural join staff where staff_id=? and request_status != 'completed' ";
								PreparedStatement pst = connection.prepareStatement(query);
								pst.setString(1, user_id3);
								ResultSet rs1 = pst.executeQuery();
								table_1.setModel(DbUtils.resultSetToTableModel(rs1));
								}
								
							catch(Exception e2){
								JOptionPane.showMessageDialog(null,e2);
								}
							
						}
					});

			}
		});
		scrollPane_1.setViewportView(table_1);
		
		JButton btnNewButton_2 = new JButton("Refresh");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select request_details.request_id,request_name,department,request_approval, request_status from request_details natural join staff where staff_id=? and request_status != 'completed' ";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, user_id3);
					ResultSet rs1 = pst.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs1));
					}
					
				catch(Exception e2){
					JOptionPane.showMessageDialog(null,e2);
					}
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.setBounds(729, 37, 101, 32);
		panel_1.add(btnNewButton_2);

	}
}

