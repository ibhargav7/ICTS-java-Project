package login;
import Connection.*;
import Person.*;
import HoD.*;
import ICTS_Staff.*;

import java.sql.*;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import Connection.databaseConnect;
import home.MainMenu;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	public String user;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	databaseConnect dbc= new databaseConnect();
	
	public LoginForm() {
		setFont(new Font("Times New Roman", Font.BOLD, 12));
		setTitle("Login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(477, 217, 243, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setBounds(335, 75, 282, 71);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username  : ");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1.setBounds(270, 213, 121, 32);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(477, 298, 243, 32);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("Password  :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_2.setBounds(270, 294, 121, 32);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection=dbc.getconnection();
				if(user=="Person") {
					connection=dbc.getconnection();
					try {
						String query = "select * from person_details where person_id= ? and password= ? ";
						PreparedStatement pst = connection.prepareStatement(query);
						String user_idx=textField.getText();
						pst.setString(1,user_idx);
						pst.setString(2,passwordField.getText());
						ResultSet rs= pst.executeQuery();
						int count=0;
						String name="";
						while(rs.next()) {
							name = rs.getString("person_name");
							count+=1;
						}
						if(count==1) {
						
							contentPane.setVisible(false);
							Person.user_id1=user_idx;
							Person ps = new Person();
							ps.setVisible(true);
						
							JOptionPane.showMessageDialog(null,"Logged In Successfully !!\n Welcome, "+name);
						}
						else {
							JOptionPane.showMessageDialog(null,"Incorrect username and password Try Again !!");
						}
					}
					catch(Exception e1){
						JOptionPane.showMessageDialog(null,e1);
					}
					
					finally {
						
					}
				}					
				if(user=="HoD") {
					connection=dbc.getconnection();
					try {
						String query = "select * from hod where hod_id= ? and hod_password= ? ";
						PreparedStatement pst = connection.prepareStatement(query);
						String user_idx=textField.getText();
						pst.setString(1,user_idx);
						pst.setString(2,passwordField.getText());
						ResultSet rs= pst.executeQuery();
						int count=0;
						String name="";
						while(rs.next()) {
							name = rs.getString("hod_name");
							count+=1;
						}
						if(count==1) {
						
							contentPane.setVisible(false);
							HoD.user_id2=user_idx;
							HoD hod = new HoD();
							hod.setVisible(true);
											
							JOptionPane.showMessageDialog(null,"Logged In Successfully !!\n Welcome, "+name);
						}
						else {
							JOptionPane.showMessageDialog(null,"Incorrect username and password Try Again !!");
						}
					}
					catch(Exception e1){
						JOptionPane.showMessageDialog(null,e1);
					}
					
					finally {
						
					}
				}
				
				if(user=="ICTS Staff") {
					connection=dbc.getconnection();
					try {
						String query = "select * from staff where staff_id= ? and password= ? ";
						PreparedStatement pst = connection.prepareStatement(query);
						String user_idx=textField.getText();
						pst.setString(1,user_idx);
						pst.setString(2,passwordField.getText());
						ResultSet rs= pst.executeQuery();
						int count=0;
						String name="";
						while(rs.next()) {
							name = rs.getString("staff_name");
							count+=1;
						}
						if(count==1) {
							
							contentPane.setVisible(false);
							Staff.user_id3=user_idx;
							Staff st = new Staff();
							st.setVisible(true);
							
							JOptionPane.showMessageDialog(null,"Logged In Successfully !!\n Welcome, "+name);
						}
						else {
							JOptionPane.showMessageDialog(null,"Incorrect username and password Try Again !!");
						}
					}
					catch(Exception e1){
						JOptionPane.showMessageDialog(null,e1);
					}
					
					finally {
						
					}
				}
			}
			
		});
		btnNewButton.setBounds(336, 396, 121, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Main Menu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				MainMenu mm = new MainMenu();
				mm.frmMainMenu.setVisible(true);
				
			}
		});
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_1.setBounds(570, 396, 121, 40);
		contentPane.add(btnNewButton_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(167, 54, 1, 457);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(308, 75, -135, 422);
		contentPane.add(separator_2);
	}
}
