package com.workshop.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.workshop.bean.WorkshopBean;
import com.workshop.dao.WorkshopDAO;
import com.workshopnew.bean.WorkshopNewBean;

public class WorkshopMain {
	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		WorkshopBean wb=new WorkshopBean();
		WorkshopDAO wc=new WorkshopDAO();
		WorkshopNewBean wnb=new WorkshopNewBean();
		Scanner s=new Scanner(System.in);
		int c=1;
//		wc.createDatabase();
		wc.createStudentTable();
		System.out.println("The Student Table is Created now insert some details of the students to insert into table");
		while(true){
			System.out.println("Stu_ID "+c+" :");
			wb.setStu_ID(s.nextLong());
			System.out.println("Stu_Name "+c+" :");
			wb.setStu_Name(s.next());
			System.out.println("Stu_Email "+c+" :");
			wb.setStu_Email(s.next());
			System.out.println("Stu_DOB "+c+" :");
			wb.setStu_Dob(s.next());
			c+=1;
			int i=wc.insertStudentTable(wb);
			if(i>0)
				System.out.println("Insertion of Student "+(c-1)+" is complete");
			else
				System.out.println("Insertion is Failed");
			System.out.println("Do you want to \n1. Add another student\n2. Exit");
			int ch=s.nextInt();
			if(ch>=2 || ch<=0)
				break;
		}
		wc.createWorkshopTable();
		System.out.println("The Workshop Table is Created Now Insert the ID and Contact numbers of Interested Students");
		c=1;
		while(true)
		{
			System.out.println("Enter the ID of the Interested Student "+c+" :");
			wnb.setStu_ID(s.nextLong());
			System.out.println("Enter the Contact of the Interested Student "+c+" :");
			wnb.setStu_Phone(s.nextLong());
			int i=wc.insertWorkshopTable(wnb);
			if(i>0)
				System.out.println("Insertion of Interested Student "+c+" is completed");
			else
				System.out.println("Insertion is failed");
			c+=1;
			System.out.println("Do you want to \n1. Add another Interested Student\n2. Exit");
			int ch=s.nextInt();
			if(ch>=2 ||ch<=0)
				break;
		}
		System.out.println("Processing and displaying your new updated workshop table");
		wc.processworkshop();
		while(true){
			System.out.println("Do you want to \n1. Delete a student from workshop\n2. exit");
			int ch=s.nextInt();
			if(ch==1){
				System.out.println("Enter the ID of the student you want to delete");
				long Stu_ID=s.nextLong();
				wc.deleteworkshop(Stu_ID);
				System.out.println("--The updated Workshop Table is --");
				wc.displayworkshop();
			}
			else{
				break;
			}
		}
		s.close();
	}

}
