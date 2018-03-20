package com.fallballrs.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class test2 {

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException{
		final String USER = "root";
		final String PASSWORD = "derby";
		
		Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
		
		String sql = String.format("SELECT * FROM App.SampleTable");
		
		// TODO Auto-generated method stub
		try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample");
				Statement st = conn.createStatement()){
			st.execute(sql);
			
			try(ResultSet rs = st.getResultSet()){
				while(rs.next())
					System.out.printf("Id: %d - Desc: %s - Body: %s\n", rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			
		} catch(SQLException x) {
			System.out.println(x);
		}
		
		//FILE NAME WITHOUT EXTENSION, ALMOST TREATING IT LIKE A DIRECTORY
		ResourceBundle bundle = ResourceBundle.getBundle("config");
		Enumeration<String> iter = bundle.getKeys();
		String temp = "";
		
		while((temp = iter.nextElement()) != null && temp.length() > 0) {
			System.out.println(temp);
		}
		
	}

}
