package main;

import java.sql.*;
import java.util.Vector;

public class DBCon {
	public static Connection JDBCon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/db_ems?serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(url,"root","zxcvbnmasd"); 
			System.out.println("数据库连接成功");
			return conn;
		}catch (ClassNotFoundException e) {
			System.out.println("驱动程序找不到,数据库连接失败");
			return null;
		}catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	//查询数据方法（一维的Vector）
	public static Vector queryData(String sql) {
		Connection conn = JDBCon();
		try {
			//创建会话对象
			Statement stmt = conn.createStatement();
			//执行查询的sql语句，返回结果集
			ResultSet rs = stmt.executeQuery(sql);
			
			Vector data = new Vector();
			while(rs.next()) {//查询是否还有下一条记录
				String ID = rs.getObject(1).toString();
				String PW = rs.getObject(2).toString();
				Users s = new Users(ID,PW);
				data.add(s);
			}
			rs.close();
			stmt.close();
			conn.close();
			return data;
			
		}catch(SQLException e) {
			System.out.println("查询数据失败");
			e.printStackTrace();
			return null;
		}
		
	}
	
	//查询数据方法2（二维的Vector）
	public static Vector queryData2(String sql) {
		Connection conn = JDBCon();
		try {
			//创建会话对象
			Statement stmt = conn.createStatement();
			//执行查询的sql语句，返回结果集
			ResultSet rs = stmt.executeQuery(sql);
			
			Vector data = new Vector();
			while(rs.next()) {//查询是否还有下一条记录
				Vector line = new Vector();
				line.add(rs.getObject(1).toString());//表头第一项
				line.add(rs.getObject(2).toString());//表头第二项
				data.add(line);
			}
			rs.close();
			stmt.close();
			conn.close();
			return data;
			
		}catch(SQLException e) {
			System.out.println("查询数据失败");
			e.printStackTrace();
			return null;
		}
		
	}

}