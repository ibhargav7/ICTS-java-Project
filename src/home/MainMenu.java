package home;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;

import login.LoginForm;

public class MainMenu {

	public JFrame frmMainMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frmMainMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	 void initialize() {
		frmMainMenu = new JFrame();
		frmMainMenu.setTitle("Main Menu");
		frmMainMenu.getContentPane().setBackground(Color.WHITE);
		frmMainMenu.setBackground(Color.DARK_GRAY);
		frmMainMenu.setBounds(100, 100, 1000, 600);
		frmMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainMenu.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\bharg\\eclipse-workspace\\ICTS PW\\resources\\icts image.jpg"));
		lblNewLabel.setBounds(243, 45, 482, 188);
		frmMainMenu.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Login As ...");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1.setBounds(392, 282, 187, 57);
		frmMainMenu.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Initiator");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMainMenu.dispose();
				LoginForm lf = new LoginForm();
				lf.user="Person";
				lf.setVisible(true);
				
			}
		});
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setBounds(250, 404, 120, 40);
		frmMainMenu.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("HoD");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMainMenu.dispose();
				LoginForm lf = new LoginForm();
				lf.user="HoD";
				lf.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(446, 404, 120, 40);
		frmMainMenu.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("ICTS Staff");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMainMenu.dispose();
				LoginForm lf = new LoginForm();
				lf.user="ICTS Staff";
				lf.setVisible(true);
				
			}
		});
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_2.setBounds(625, 404, 120, 40);
		frmMainMenu.getContentPane().add(btnNewButton_2);
	}
}
