package Connection;

import java.sql.*;
import javax.swing.*;


public class databaseConnect {
	private String url ="jdbc:postgresql://localhost:5432/ICTS_pw";
	private String username = "postgres";
	private String password = "bhargav";
	public	Connection getconnection() {
		Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
            return null;
        } 
	}
}
