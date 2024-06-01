package main;

import java.sql.*;
import java.util.Vector;

public class DBCon {
	public static Connection JDBCon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/db_ems?serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(url,"root","zxcvbnmasd"); 
			System.out.println("���ݿ����ӳɹ�");
			return conn;
		}catch (ClassNotFoundException e) {
			System.out.println("���������Ҳ���,���ݿ�����ʧ��");
			return null;
		}catch (SQLException e) {
			System.out.println("���ݿ�����ʧ��");
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	//��ѯ���ݷ�����һά��Vector��
	public static Vector queryData(String sql) {
		Connection conn = JDBCon();
		try {
			//�����Ự����
			Statement stmt = conn.createStatement();
			//ִ�в�ѯ��sql��䣬���ؽ����
			ResultSet rs = stmt.executeQuery(sql);
			
			Vector data = new Vector();
			while(rs.next()) {//��ѯ�Ƿ�����һ����¼
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
			System.out.println("��ѯ����ʧ��");
			e.printStackTrace();
			return null;
		}
		
	}
	
	//��ѯ���ݷ���2����ά��Vector��
	public static Vector queryData2(String sql) {
		Connection conn = JDBCon();
		try {
			//�����Ự����
			Statement stmt = conn.createStatement();
			//ִ�в�ѯ��sql��䣬���ؽ����
			ResultSet rs = stmt.executeQuery(sql);
			
			Vector data = new Vector();
			while(rs.next()) {//��ѯ�Ƿ�����һ����¼
				Vector line = new Vector();
				line.add(rs.getObject(1).toString());//��ͷ��һ��
				line.add(rs.getObject(2).toString());//��ͷ�ڶ���
				data.add(line);
			}
			rs.close();
			stmt.close();
			conn.close();
			return data;
			
		}catch(SQLException e) {
			System.out.println("��ѯ����ʧ��");
			e.printStackTrace();
			return null;
		}
		
	}

}