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
			System.out.println("数据库连接成功");
			return conn;
		}catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序,数据库连接失败");
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
	
	//hzz
	public static Student find(String stuid) {
        Student student =null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// 获取数据库连接
			conn = JDBCon();
			// SQL查询语句，使用占位符来防止SQL注入
			String query = "SELECT * FROM students WHERE 学号 = ?";
			// 预编译SQL语句
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
			// 关闭结果集、语句和连接，确保资源释放
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

    //学生个人信息修改
	public static void update(Student student) {
		Connection conn = null;
		try {
			conn = JDBCon();
			String sql = "UPDATE students SET 姓名=?, 性别=?, 出生日期=?, 地区=?, 民族=?,学院=?, 班级=?, 电话=?, 邮箱=? WHERE 学号=?";
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
			public static Vector queryData22(String sql) {
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
	
	
	
	/*//查询数据方法2（二维的Vector）zzw原始代码
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
		
	}*/
	
	//zjh修改
	public static Vector<Vector<String>> queryData2(String sql) {
        Connection conn = JDBCon();
        try {
            //创建会话对象
            Statement stmt = conn.createStatement();
            //执行查询的sql语句，返回结果集
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
            System.out.println("查询数据失败");
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
    
    
  //hzz修改表格信息sql方法！！！
    public static int executeUpdate1(String sql) {
        Connection conn = JDBCon();
        try {
            // 创建会话对象
            Statement stmt = conn.createStatement();
            // 执行更新的sql语句，返回影响的行数
            int rowsAffected = stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
            return rowsAffected;
        } catch (SQLException e) {
            System.out.println("执行更新操作失败");
            e.printStackTrace();
            return 0;
        }
    }

    
//	老师个人信息qh
	public static Vector queryData0(String sql) {
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
			line.add(rs.getObject(3).toString());//表头第二项
			line.add(rs.getObject(4).toString());//表头第二项
			line.add(rs.getObject(5).toString());//表头第二项
			line.add(rs.getObject(6).toString());//表头第二项
			
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
	
	//修改密码qh
	public static Vector queryData3(String sql) {
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
//				line.add(rs.getObject(3).toString());//表头第二项
//				line.add(rs.getObject(4).toString());//表头第二项
				
				
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
    
    
    
    
    
    
        // 查看学生信息的方法（教师）qh
 		public static Vector queryData_t_course(String sql, String Id) {
 		    Connection conn = JDBCon();
 		    try {
 		        // 创建会话对象
 		        PreparedStatement pstmt = conn.prepareStatement(sql);
 		        // 设置参数
 		        pstmt.setString(1, Id);
 		        // 执行查询的sql语句，返回结果集
 		        ResultSet rs = pstmt.executeQuery();
 		        
 		        Vector data = new Vector();
 		        int count = 0; // 用于记录记录数
 		        while (rs.next()) { // 查询是否还有下一条记录
 		            Vector line = new Vector();
 		            line.add(rs.getObject(1).toString()); // 表头第一项
 		            line.add(rs.getObject(2).toString()); // 表头第二项
 		            line.add(rs.getObject(3).toString()); // 表头第三项
 		            line.add(rs.getObject(4).toString()); // 表头第四项
 		            line.add(rs.getObject(5).toString()); // 表头第五项
 		            line.add(rs.getObject(6).toString()); // 表头第六项
// 		            line.add(rs.getObject(7).toString()); // 表头第七项
 		            data.add(line);
 		            count++;
 		        }
// 		        System.out.println("Number of records returned: " + count);
 		        rs.close();
 		        pstmt.close();
 		        conn.close();
 		        return data;
 		        
 		    } catch (SQLException e) {
 		        System.out.println("查询数据失败");
 		        e.printStackTrace();
 		        return null;
 		    }
 		}
    
    
    
    
    
        // 查询老师成绩管理的方法qh
 		public static Vector queryData_score(String sql, String Id) {
 		    Connection conn = JDBCon();
 		    try {
 		        // 创建会话对象
 		        PreparedStatement pstmt = conn.prepareStatement(sql);
 		        // 设置参数
 		        pstmt.setString(1, Id);
 		        // 执行查询的sql语句，返回结果集
 		        ResultSet rs = pstmt.executeQuery();
 		        
 		        Vector data = new Vector();
 		        int count = 0; // 用于记录记录数
 		        while (rs.next()) { // 查询是否还有下一条记录
 		            Vector line = new Vector();
 		            line.add(rs.getObject(1).toString()); // 表头第一项
 		            line.add(rs.getObject(2).toString()); // 表头第二项
 		            line.add(rs.getObject(3).toString()); // 表头第三项
 		            line.add(rs.getObject(4).toString()); // 表头第四项
 		            line.add(rs.getObject(5).toString()); // 表头第五项
// 		            line.add(rs.getObject(6).toString()); // 表头第六项
// 		            line.add(rs.getObject(7).toString()); // 表头第七项
 		            data.add(line);
 		            count++;
 		        }
// 		        System.out.println("Number of records returned: " + count);
 		        rs.close();
 		        pstmt.close();
 		        conn.close();
 		        return data;
 		        
 		    } catch (SQLException e) {
 		        System.out.println("查询数据失败");
 		        e.printStackTrace();
 		        return null;
 		    }
 		}

    
    
    
    
  //修改教师密码表格信息sql方法qh(class名冲突)
    public static int executeUpdate2(String sql) {
        Connection conn = JDBCon();
        try {
            // 创建会话对象
            Statement stmt = conn.createStatement();
            // 执行更新的sql语句，返回影响的行数
            int rowsAffected = stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
            return rowsAffected;
        } catch (SQLException e) {
            System.out.println("执行更新操作失败");
            e.printStackTrace();
            return 0;
        }
    }

}