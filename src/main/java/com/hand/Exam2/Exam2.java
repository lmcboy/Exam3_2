package com.hand.Exam2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

public class Exam2 {

	static Connection getConn(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","mc","123456");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			return conn;
		}
	}
	
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.print("请输入 Customer ID：");
			int id = sc.nextInt();
			Connection conn = getConn();
			Statement st = (Statement) conn.createStatement();
			
			String sql = "select distinct cl.name,fid,title,rental_date " 
						+ "from customer_list cl,film_list fl,rental r,inventory i,store st "
						+ "where cl.id = " + id 
						+ " and cl.id = r.customer_id"
						+ " and r.staff_id = st.manager_staff_id"
						+ " and st.store_id = i.store_id"
						+ " and i.film_id = fl.fid"
						+ " order by rental_date DESC";
			ResultSet rs = st.executeQuery(sql);
			Boolean bool = true;
			while(rs.next()){
				if(bool){
					System.out.println(rs.getString("name") + " 租用的 Film->");
					System.out.println("Film ID \t" + "| \t" + "Film 名称 \t" + "| \t" + "租用时间");
					System.out.print(rs.getInt(2)+ " \t");
					System.out.print("| \t");
					System.out.print(rs.getString(3)+ " \t");
					System.out.print("| \t");
					System.out.println(rs.getDate(4)+ " \t");
					bool = false;
				}else{
					System.out.print(rs.getInt(2)+ " \t");
					System.out.print("| \t");
					System.out.print(rs.getString(3)+ " \t");
					System.out.print("| \t");
					System.out.println(rs.getDate(4)+ " \t");
				}
			}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}