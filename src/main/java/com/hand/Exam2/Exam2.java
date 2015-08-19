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
			
<<<<<<< HEAD
			String sql = "SELECT last_name,first_name,f.film_id,title,rental_date"
					+ " FROM customer c,film f,rental r"
					+ " where c.customer_id = 571"
					+ " AND film_id in (select film_id from inventory where inventory_id in"
					+ " (select inventory_id from rental where rental_id in"
					+ " (select rental_id from rental where customer_id = 571)))"
					+ " AND c.customer_id=r.customer_id"
					+ " ORDER BY rental_date DESC";

=======
			String sql = "select name,f.film_id,title,rental_date "
						+ "from customer_list cl,film f,rental r "
						+ "where cl.id = " + id 
						+ " and film_id in "
						+ "(select film_id from inventory "
						+ "where inventory_id in "
						+ "(select inventory_id from rental where customer_id = " + id +"))"
						+ " group by film_id"
						+ " order by rental_date,f.film_id desc";
>>>>>>> 192ca2280a08bb444df4a00ee35c24db70fe7645
			ResultSet rs = st.executeQuery(sql);
			Boolean bool = true;
			while(rs.next()){
				if(bool){
					System.out.println(rs.getString(2) + " " + rs.getString(1) + " 租用的 Film->");
					System.out.println("Film ID \t" + "| \t" + "Film 名称 \t" + "| \t" + "租用时间");
					System.out.print(rs.getInt(3)+ " \t");
					System.out.print("| \t");
					System.out.print(rs.getString(4)+ " \t");
					System.out.print("| \t");
<<<<<<< HEAD
					System.out.println(rs.getTimestamp(5)+ " \t");
=======
					System.out.println(rs.getTimestamp(4)+ " \t");
>>>>>>> 192ca2280a08bb444df4a00ee35c24db70fe7645
					bool = false;
				}else{
					System.out.print(rs.getInt(3)+ " \t");
					System.out.print("| \t");
					System.out.print(rs.getString(4)+ " \t");
					System.out.print("| \t");
<<<<<<< HEAD
					System.out.println(rs.getTimestamp(5)+ " \t");
=======
					System.out.println(rs.getTimestamp(4)+ " \t");
>>>>>>> 192ca2280a08bb444df4a00ee35c24db70fe7645
				}
			}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
