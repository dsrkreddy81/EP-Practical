package com.market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.market.bean.MarketBean;
import com.market.connection.MarketDBUtil;

public class MarketDAO {
	public int MarketInsert(MarketBean market) throws ClassNotFoundException, SQLException {
		Connection con=MarketDBUtil.DBConnection();
		PreparedStatement ps=con.prepareStatement("insert into market values(?,?,?)");
		ps.setInt(1, market.getItemid());
		ps.setString(2, market.getItemname());
		ps.setInt(3, market.getItemcost());
		int i=ps.executeUpdate();
		con.close();
		return i;
	}
	public  void Retrive() throws ClassNotFoundException, SQLException {
		Connection con=MarketDBUtil.DBConnection();
		PreparedStatement ps=con.prepareStatement("select * from market");
		ResultSet rst=ps.executeQuery();
		System.out.println("Itemid\t\tItemname\t\tItemcost");
		while(rst.next()) {
			System.out.println(rst.getInt(1)+"\t\t"+rst.getString(2)+"\t\t\t"+rst.getInt(3));
		}
		con.close();
	}
	public void Totalcost() throws ClassNotFoundException, SQLException {
		Connection con=MarketDBUtil.DBConnection();
		PreparedStatement ps=con.prepareStatement("select sum(itemcost) from market");
		ResultSet rst=ps.executeQuery();
		System.out.println("Itemcost");
		while(rst.next()) {
			System.out.println(rst.getInt(1));
		}
		con.close();
	}

}
