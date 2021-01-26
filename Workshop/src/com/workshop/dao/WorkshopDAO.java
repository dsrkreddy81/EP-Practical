package com.workshop.dao;
import com.workshop.bean.WorkshopBean;
import com.workshop.connection.WorkshopDBConn;
import com.workshopnew.bean.WorkshopNewBean;

import java.sql.*;
public class WorkshopDAO {
	public void createDatabase() throws SQLException, ClassNotFoundException{
		Connection con=WorkshopDBConn.DBConnection();
		Statement st=con.createStatement();
		st.execute("create database workshop");
		Statement st1=con.createStatement();
		st1.execute("use workshop");
	}
	public void createStudentTable() throws SQLException,ClassNotFoundException{
		Connection con=WorkshopDBConn.DBConnection();
		Statement st1=con.createStatement();
		st1.execute("use workshop");
		Statement st2=con.createStatement();
		st2.execute("create table if not exists Student(Stu_ID long,Stu_Name varchar(50),Stu_Email varchar(50),Stu_Dob varchar(20))");
	}
	public void createWorkshopTable() throws SQLException,ClassNotFoundException{
		Connection con=WorkshopDBConn.DBConnection();
		Statement st1=con.createStatement();
		st1.execute("use workshop");
		Statement st2=con.createStatement();
		st2.execute("create table if not exists Workshop(Stu_ID long,Stu_Phone long)");
	}
	public int insertStudentTable(WorkshopBean wb) throws SQLException,ClassNotFoundException{
		Connection con=WorkshopDBConn.DBConnection();
		Statement st1=con.createStatement();
		st1.execute("use workshop");
		PreparedStatement ps=con.prepareStatement("insert into Student values(?,?,?,?)");
		ps.setLong(1,wb.getStu_ID());
		ps.setString(2,wb.getStu_Name());
		ps.setString(3,wb.getStu_Email());
		ps.setString(4,wb.getStu_Dob());
		int i=ps.executeUpdate();
		con.close();
		return i;
	}
	public int insertWorkshopTable(WorkshopNewBean wnb) throws SQLException,ClassNotFoundException{
		Connection con=WorkshopDBConn.DBConnection();
		Statement st1=con.createStatement();
		st1.execute("use workshop");
		PreparedStatement ps=con.prepareStatement("insert into Workshop values(?,?)");
		ps.setLong(1,wnb.getStu_ID());
		ps.setLong(2,wnb.getStu_Phone());
		int i=ps.executeUpdate();
		con.close();
		return i;
	}
	public void processworkshop() throws SQLException, ClassNotFoundException{
		Connection con=WorkshopDBConn.DBConnection();
		Statement st0=con.createStatement();
		st0.execute("use workshop");
		Statement st=con.createStatement();
		st.execute("ALTER TABLE Workshop ADD COLUMN Stu_Name VARCHAR(50)");
		Statement st1=con.createStatement();
		st1.execute("ALTER TABLE Workshop ADD COLUMN Stu_Email VARCHAR(50)");
		Statement s3=con.createStatement();
		s3.execute("update Workshop w,Student s set w.Stu_Name=s.Stu_Name where w.Stu_ID=s.Stu_ID");
		Statement s4=con.createStatement();
		s4.execute("update Workshop w,Student s set w.Stu_Email=s.Stu_Email where w.Stu_ID=s.Stu_ID");
		displayworkshop();
	}
	public int deleteworkshop(long Stu_ID) throws ClassNotFoundException, SQLException{
		Connection con=WorkshopDBConn.DBConnection();
		Statement st1=con.createStatement();
		st1.execute("use workshop");
		PreparedStatement st=con.prepareStatement("delete from Workshop where Stu_ID="+Stu_ID);
		int i=st.executeUpdate();
		return i;
	}
	public void displayworkshop() throws ClassNotFoundException, SQLException{
		Connection con=WorkshopDBConn.DBConnection();
		Statement st1=con.createStatement();
		st1.execute("use workshop");
		PreparedStatement ps=con.prepareStatement("select * from Workshop");
		ResultSet rs=ps.executeQuery();
		System.out.println("ID\t\tPhone\t\tName\t\tEmail");
		while(rs.next()){
			System.out.println(rs.getLong(1)+"\t"+rs.getLong(2)+"\t"+rs.getString(3)+"\t\t"+rs.getString(4));
		}
	}

}
