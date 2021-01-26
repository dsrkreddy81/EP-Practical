package com.market.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.market.bean.MarketBean;
import com.market.dao.MarketDAO;

public class MarketMain {
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		MarketBean mb=new MarketBean();
		MarketDAO mi=new MarketDAO();
		int ch;
		while(true)
		{
			System.out.println("Select operation u want to do on database\n1.Insert\n2.Retrive\n3.Total cost\n4.Exit\nEnter your option:");
			ch=sc.nextInt();
			switch(ch)
			{
			case 1:
				System.out.println("Enter Itemid:");
				mb.setItemid(sc.nextInt());
				System.out.println("Enter Itemname:");
				mb.setItemname(sc.next());
				System.out.println("Enter Itemcost:");
				mb.setItemcost(sc.nextInt());
				int i=mi.MarketInsert(mb);
				if(i>0)
				{
					System.out.println("Insertion Successfull");
				}
				else
				{
					System.out.println("Insertion Failure");
				}
				break;
			
			case 2:
				mi.Retrive();
				break;
			case 3:
				mi.Totalcost();
				break;
			case 4:
				System.exit(0);
			}
			
		}
	}

}
