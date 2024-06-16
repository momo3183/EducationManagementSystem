/*package main;
import java.sql.*;
import java.util.Vector;

public class DBCon {
    private static Connection connection;

    public static void connect() {
        try {
            // Replace with your database connection details
            String url = "jdbc:mysql://localhost:3306/your_database";
            String user = "your_username";
            String password = "your_password";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Vector<Vector<String>> queryData2(String query) {
        Vector<Vector<String>> data = new Vector<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getString(i));
                }
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void update(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeUpdate(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}*/

package main;

import java.sql.*;
import java.util.Vector;

import student.Student;

public class DBCon {
	public static Connection JDBCon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/db_ems?serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(url,"root","zxcvbnmasd"); 
			System.out.println("���ݿ����ӳɹ�");
			return conn;
		}catch (ClassNotFoundException e) {
			System.out.println("�Ҳ�����������,���ݿ�����ʧ��");
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
	
	//hzz
	public static Student find(String stuid) {
        Student student =null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// ��ȡ���ݿ�����
			conn = JDBCon();
			// SQL��ѯ��䣬ʹ��ռλ������ֹSQLע��
			String query = "SELECT * FROM students WHERE ѧ�� = ?";
			// Ԥ����SQL���
			stmt = conn.prepareStatement(query);
			stmt.setString(1, stuid);
			rs = stmt.executeQuery();
            if (rs.next()) {
                Long stuid1 = (Long) rs.getObject(1);
				String name = rs.getObject(2).toString();
				String sex =rs.getObject(3).toString();
				String age =rs.getObject(4).toString();
				String area = rs.getObject(5).toString();
				String nation = rs.getObject(6).toString();
				String college = rs.getObject(7).toString();
				String className = rs.getObject(8).toString();
				String phone = rs.getObject(9).toString();
				String email = rs.getObject(10).toString();
                student = new Student(stuid1, name, sex, age, area, nation,college, className, phone, email);
			}

		} catch (SQLException e) {
			System.out.println("Error checking credentials");
		} finally {
			// �رս�������������ӣ�ȷ����Դ�ͷ�
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}
        return student;
	}

    //ѧ��������Ϣ�޸�
	public static void update(Student student) {
		Connection conn = null;
		try {
			conn = JDBCon();
			String sql = "UPDATE students SET ����=?, �Ա�=?, ��������=?, ����=?, ����=?,ѧԺ=?, �༶=?, �绰=?, ����=? WHERE ѧ��=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, student.getName());
			stmt.setString(2, student.getGender());
			stmt.setString(3, student.getB());
			stmt.setString(4, student.getRegion());
			stmt.setString(5, student.getMz());
			stmt.setString(6, student.getCollege());
			stmt.setString(7, student.getClassName());
			stmt.setString(8, student.getPhone());
			stmt.setString(9, student.getEmail());
			stmt.setLong(10, student.getStuid());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
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
			public static Vector queryData22(String sql) {
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
	
	
	
	/*//��ѯ���ݷ���2����ά��Vector��zzwԭʼ����
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
		
	}*/
	
	//zjh�޸�
	public static Vector<Vector<String>> queryData2(String sql) {
        Connection conn = JDBCon();
        try {
            //�����Ự����
            Statement stmt = conn.createStatement();
            //ִ�в�ѯ��sql��䣬���ؽ����
            ResultSet rs = stmt.executeQuery(sql);

            Vector<Vector<String>> data = new Vector<>();
            while (rs.next()) {
                Vector<String> line = new Vector<>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    line.add(rs.getString(i));
                }
                data.add(line);
            }
            rs.close();
            stmt.close();
            conn.close();
            return data;

        } catch (SQLException e) {
            System.out.println("��ѯ����ʧ��");
            e.printStackTrace();
            return null;
        }
    }
	
	
	//zjh
	public static void update(String query) {
		Connection conn = JDBCon();
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	//zjh
    public static void executeUpdate(String query) {
    	Connection conn = JDBCon();
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
  //hzz�޸ı����Ϣsql����������
    public static int executeUpdate1(String sql) {
        Connection conn = JDBCon();
        try {
            // �����Ự����
            Statement stmt = conn.createStatement();
            // ִ�и��µ�sql��䣬����Ӱ�������
            int rowsAffected = stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
            return rowsAffected;
        } catch (SQLException e) {
            System.out.println("ִ�и��²���ʧ��");
            e.printStackTrace();
            return 0;
        }
    }

    
//	��ʦ������Ϣqh
	public static Vector queryData0(String sql) {
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
			line.add(rs.getObject(3).toString());//��ͷ�ڶ���
			line.add(rs.getObject(4).toString());//��ͷ�ڶ���
			line.add(rs.getObject(5).toString());//��ͷ�ڶ���
			line.add(rs.getObject(6).toString());//��ͷ�ڶ���
			
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
	
	//�޸�����qh
	public static Vector queryData3(String sql) {
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
//				line.add(rs.getObject(3).toString());//��ͷ�ڶ���
//				line.add(rs.getObject(4).toString());//��ͷ�ڶ���
				
				
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
    
    
    
    
    
    
        // �鿴ѧ����Ϣ�ķ�������ʦ��qh
 		public static Vector queryData_t_course(String sql, String Id) {
 		    Connection conn = JDBCon();
 		    try {
 		        // �����Ự����
 		        PreparedStatement pstmt = conn.prepareStatement(sql);
 		        // ���ò���
 		        pstmt.setString(1, Id);
 		        // ִ�в�ѯ��sql��䣬���ؽ����
 		        ResultSet rs = pstmt.executeQuery();
 		        
 		        Vector data = new Vector();
 		        int count = 0; // ���ڼ�¼��¼��
 		        while (rs.next()) { // ��ѯ�Ƿ�����һ����¼
 		            Vector line = new Vector();
 		            line.add(rs.getObject(1).toString()); // ��ͷ��һ��
 		            line.add(rs.getObject(2).toString()); // ��ͷ�ڶ���
 		            line.add(rs.getObject(3).toString()); // ��ͷ������
 		            line.add(rs.getObject(4).toString()); // ��ͷ������
 		            line.add(rs.getObject(5).toString()); // ��ͷ������
 		            line.add(rs.getObject(6).toString()); // ��ͷ������
// 		            line.add(rs.getObject(7).toString()); // ��ͷ������
 		            data.add(line);
 		            count++;
 		        }
// 		        System.out.println("Number of records returned: " + count);
 		        rs.close();
 		        pstmt.close();
 		        conn.close();
 		        return data;
 		        
 		    } catch (SQLException e) {
 		        System.out.println("��ѯ����ʧ��");
 		        e.printStackTrace();
 		        return null;
 		    }
 		}
    
    
    
    
    
        // ��ѯ��ʦ�ɼ�����ķ���qh
 		public static Vector queryData_score(String sql, String Id) {
 		    Connection conn = JDBCon();
 		    try {
 		        // �����Ự����
 		        PreparedStatement pstmt = conn.prepareStatement(sql);
 		        // ���ò���
 		        pstmt.setString(1, Id);
 		        // ִ�в�ѯ��sql��䣬���ؽ����
 		        ResultSet rs = pstmt.executeQuery();
 		        
 		        Vector data = new Vector();
 		        int count = 0; // ���ڼ�¼��¼��
 		        while (rs.next()) { // ��ѯ�Ƿ�����һ����¼
 		            Vector line = new Vector();
 		            line.add(rs.getObject(1).toString()); // ��ͷ��һ��
 		            line.add(rs.getObject(2).toString()); // ��ͷ�ڶ���
 		            line.add(rs.getObject(3).toString()); // ��ͷ������
 		            line.add(rs.getObject(4).toString()); // ��ͷ������
 		            line.add(rs.getObject(5).toString()); // ��ͷ������
// 		            line.add(rs.getObject(6).toString()); // ��ͷ������
// 		            line.add(rs.getObject(7).toString()); // ��ͷ������
 		            data.add(line);
 		            count++;
 		        }
// 		        System.out.println("Number of records returned: " + count);
 		        rs.close();
 		        pstmt.close();
 		        conn.close();
 		        return data;
 		        
 		    } catch (SQLException e) {
 		        System.out.println("��ѯ����ʧ��");
 		        e.printStackTrace();
 		        return null;
 		    }
 		}

    
    
    
    
  //�޸Ľ�ʦ��������Ϣsql����qh(class����ͻ)
    public static int executeUpdate2(String sql) {
        Connection conn = JDBCon();
        try {
            // �����Ự����
            Statement stmt = conn.createStatement();
            // ִ�и��µ�sql��䣬����Ӱ�������
            int rowsAffected = stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
            return rowsAffected;
        } catch (SQLException e) {
            System.out.println("ִ�и��²���ʧ��");
            e.printStackTrace();
            return 0;
        }
    }

}