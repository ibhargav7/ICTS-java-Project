package HoD;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connection.databaseConnect;
import home.MainMenu;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HoD extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public static String user_id2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HoD frame = new HoD();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	databaseConnect dbc= new databaseConnect();
	
	public HoD() {
		setTitle("HoD's Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Requests Need to be Approved");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(38, 49, 348, 35);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(38, 124, 904, 303);
		contentPane.add(scrollPane_1);
		connection=dbc.getconnection();
		table = new JTable();
		try {
			String query = "select request_id,request_name,department,request_approval from request_details where department=(select department from hod where hod_id=?) and request_approval = 'approval pending';";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, user_id2);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			}
			
		catch(Exception e2){
			JOptionPane.showMessageDialog(null,e2);
			}
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					connection=dbc.getconnection();
					int row = table.getSelectedRow();
					String res = (table.getModel().getValueAt(row,0)).toString();
					int request_id=Integer.parseInt(res);
					JButton btnNewButton = new JButton("Approve Selected");
					btnNewButton.setBackground(new Color(192, 192, 192));
					btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
					btnNewButton.setBounds(231, 483, 179, 40);
					contentPane.add(btnNewButton);
					JButton btnNewButton_1 = new JButton("Disapprove Selected");
					btnNewButton_1.setBackground(new Color(192, 192, 192));
					btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
					btnNewButton_1.setBounds(588, 483, 197, 40);
					contentPane.add(btnNewButton_1);
					contentPane.revalidate();
					contentPane.repaint();
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								
								String query = "update request_details set request_approval='approved' where request_id="+request_id+"";
								PreparedStatement pst = connection.prepareStatement(query);
								pst.executeUpdate();
								
								
						}catch(Exception e2){
							JOptionPane.showMessageDialog(null,e2);
							}
							contentPane.revalidate();
							contentPane.repaint();
						try {
							String query = "select request_id,request_name,department,request_approval from request_details where department=(select department from hod where hod_id=?) and request_approval = 'approval pending';";
							PreparedStatement pst = connection.prepareStatement(query);
							pst.setString(1, user_id2);

							ResultSet rs = pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
							
							}
							
						catch(Exception e2){
							JOptionPane.showMessageDialog(null,e2);
							}
						contentPane.revalidate();
						contentPane.repaint();
						}
					});
					
					btnNewButton_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								
								String query = "update request_details set request_approval='disapproved' where request_id="+request_id+"";
								PreparedStatement pst = connection.prepareStatement(query);
								pst.executeUpdate();
								
								
						}catch(Exception e2){
							JOptionPane.showMessageDialog(null,e2);
						}
						try {
							String query = "select request_id,request_name,department,request_approval from request_details where department=(select department from hod where hod_id=?) and request_approval = 'approval pending';";
							PreparedStatement pst = connection.prepareStatement(query);
							pst.setString(1, user_id2);

							ResultSet rs = pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
							}
							
						catch(Exception e2){
							JOptionPane.showMessageDialog(null,e2);
							}
						contentPane.revalidate();
						contentPane.repaint();
						}
					});
						
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null,e2);
				}
			}
		});
		scrollPane_1.setViewportView(table);
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 124, 904, 303);
		contentPane.add(scrollPane);
		
		JButton btnNewButton_2 = new JButton("Back to Main Menu");
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				
				MainMenu mm = new MainMenu();
				mm.frmMainMenu.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(760, 49, 148, 35);
		contentPane.add(btnNewButton_2);
		
		JButton Refresh = new JButton("Refresh");
		Refresh.setBackground(new Color(192, 192, 192));
		Refresh.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select request_id,request_name,department,request_approval from request_details where department=(select department from hod where hod_id=?) and request_approval = 'approval pending';";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, user_id2);
					
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					}
					
				catch(Exception e2){
					JOptionPane.showMessageDialog(null,e2);
					}
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		Refresh.setBounds(554, 49, 136, 35);
		contentPane.add(Refresh);
	}
}
