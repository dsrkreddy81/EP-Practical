package feedback;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.http.HttpServletResponse;

public class DAO {
	public void create() throws SQLException,ClassNotFoundException{
		Connection con=DBUtil.DBConnection();
		Statement st=con.createStatement();
		st.execute("create database if not exists registration");
		Statement st1=con.createStatement();
		st1.execute("use registration");
		Statement st2=con.createStatement();
		st2.execute("create table if not exists register(regno int primary key,email varchar(30),password varchar(10))");
		Statement st3=con.createStatement();
		st3.execute("create table if not exists feedback(regno int primary key,feedback varchar(50))");
		con.close();
	}
	public int insert(RegisterBean rb) throws SQLException,ClassNotFoundException{
		Connection con=DBUtil.DBConnection();
		Statement st=con.createStatement();
		st.execute("use registration");
		PreparedStatement ps=con.prepareStatement("insert into register values(?,?,?)");
		ps.setInt(1, rb.getRegno());
		ps.setString(2, rb.getEmail());
		ps.setString(3, rb.getPassword());
		int i=ps.executeUpdate();
		con.close();
		return i;
	}
	public boolean login(RegisterBean rb) throws SQLException,ClassNotFoundException{
		Connection con=DBUtil.DBConnection();
		Statement st=con.createStatement();
		st.execute("use registration");
		PreparedStatement ps=con.prepareStatement("select * from register");
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			if(rs.getString(2).equals(rb.getEmail()) && rs.getString(3).equals(rb.getPassword())){
				return true;
			}
		}
		con.close();
		return false;
	}
	public int getRegno(String email) throws SQLException,ClassNotFoundException{
		Connection con=DBUtil.DBConnection();
		Statement st=con.createStatement();
		st.execute("use registration");
		PreparedStatement ps=con.prepareStatement("select * from register where email=?");
		ps.setString(1,email);
		ResultSet rs=ps.executeQuery();
		int i=0;
		while(rs.next()) {
			i= rs.getInt(1);
		}
		return i;
	}
	public int feedback(int regno,String feedback) throws SQLException,ClassNotFoundException{
		Connection con=DBUtil.DBConnection();
		Statement st=con.createStatement();
		st.execute("use registration");
		PreparedStatement ps1=con.prepareStatement("insert into feedback values(?,?)");
		ps1.setInt(1, regno);
		ps1.setString(2, feedback);
		int i=ps1.executeUpdate();
		con.close();
		return i;
	}
	public void display(String feedback,HttpServletResponse response) throws SQLException,ClassNotFoundException, IOException{
		Connection con=DBUtil.DBConnection();
		Statement st=con.createStatement();
		st.execute("use registration");
		PreparedStatement ps=con.prepareStatement("select * from feedback");
		ResultSet rs=ps.executeQuery();
		PrintWriter pw=response.getWriter();
		while(rs.next()) {
			pw.print("--------Feedback---------");
			pw.print("\t"+rs.getInt(1)+"\t"+rs.getString(2)+"\n");
		}
		con.close();
	}
}
