package com.collabera.jdbcprintable;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class TablePrint {
	
	static final String URL = "jdbc:mysql://localhost:3306/sakila";
	static final String USERNAME = "root";
	static final String PASSWORD = "root";
	
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Success! Connection was made");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void main(String[] args) {
		
		try {
		
			runSelectQuery("select * from actor");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	
public static void runSelectQuery(String query) throws SQLException {
	
	Connection conn = TablePrint.getConnection();

	Statement stm = conn.createStatement();
	
	ResultSet result= stm.executeQuery(query);
	ResultSetMetaData rmeta= result.getMetaData();
	
	int columns= rmeta.getColumnCount();
	
	while(result.next()) {
		for(int i=1; i<=columns; i++) {
			System.out.print(result.getString(i) + "       ");
		}
		System.out.println();
	}
	
	stm.close();

//close the connection
	conn.close();
}
}
