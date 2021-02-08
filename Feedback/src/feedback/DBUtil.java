package feedback;

import java.sql.*;

public class DBUtil {
	final static String forNameURL="com.mysql.cj.jdbc.Driver";
	final static String dbURL="jdbc:mysql://localhost:3306";
	final static String username="root";
	final static String password="190030430";
	public static Connection DBConnection() throws SQLException,ClassNotFoundException{
		Class.forName(forNameURL);
		Connection con=DriverManager.getConnection(dbURL,username,password);
		return con;
	}

}
