package KTCK;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class knoi {
	public static void main(String[] args) {
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-0CGBO3O\\SQLEXPRESS:1433;databaseName=STU","sa","12345");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (con!=null)
			System.out.println("Bus");
	}

}


