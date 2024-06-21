package admin;

import java.awt.*;
import javax.swing.*;

import main.DBCon;
import main.Users;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Vector;

public class UserManagement extends JPanel {
    private JTable userTable;
    private JTextField searchField;

    public UserManagement() {
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchField = new JTextField(20);
        JButton searchButton = new JButton("搜索");
        searchPanel.add(new JLabel("搜索用户:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        Vector<String> columnNames = new Vector<>();
        columnNames.add("用户名");
        columnNames.add("密码");
        

        Vector<Vector<String>> data = DBCon.queryData2("SELECT * FROM users UNION ALL SELECT * FROM tusers");
        userTable = new JTable(data, columnNames);
        add(new JScrollPane(userTable), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        JButton addButton = new JButton("添加");
        JButton editButton = new JButton("编辑");
        JButton deleteButton = new JButton("删除");
        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);
        add(controlPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
        	AddUserDialog addUserDialog = new AddUserDialog();
            addUserDialog.setVisible(true);
            refreshUserTable();
        });
        
        
        editButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                String userId = (String) userTable.getValueAt(selectedRow, 0);
                Vector<Vector<String>> userData = DBCon.queryData2("SELECT * FROM users WHERE `学号` LIKE '%" + userId + "%' UNION ALL SELECT * FROM tusers WHERE `工号` LIKE '%" + userId + "%'");
                              
                
                if (!userData.isEmpty()) {
                    String username = userData.get(0).get(0);
                    EditUserDialog editUserDialog = new EditUserDialog(username);
                    editUserDialog.setVisible(true);
                    refreshUserTable();
                } else {
                    JOptionPane.showMessageDialog(this, "用户数据为空或不存在");
                }
            } else {
                JOptionPane.showMessageDialog(this, "请选择要编辑的用户");
            }
        });

        deleteButton.addActionListener(e -> {
        	int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                String userId = (String) userTable.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "确认删除用户？", "删除确认", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    	String deleteSql1 = "DELETE FROM users WHERE `学号` = '" + userId + "'";
                    	DBCon.executeUpdate(deleteSql1);
                    	
                    	String deleteSql2 = "DELETE FROM tusers WHERE `工号` = '" + userId + "'";
                    	DBCon.executeUpdate(deleteSql2);
                    	refreshUserTable();
                    	
                }
            } else {
                JOptionPane.showMessageDialog(this, "请选择要删除的用户");
            }
        });

        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            Vector<Vector<String>> searchData = 
            		DBCon.queryData2("SELECT * FROM users WHERE `学号` LIKE '%" + searchText + "%' UNION ALL SELECT * FROM tusers WHERE `工号` LIKE '%" + searchText + "%'");
            userTable.setModel(new javax.swing.table.DefaultTableModel(searchData, columnNames));
        });
    }
    
    private void refreshUserTable() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("ID");
        columnNames.add("用户名");
        
        Vector<Vector<String>> data = DBCon.queryData2("SELECT * FROM users");
        userTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    class AddUserDialog extends JDialog {
        private JTextField usernameField;
        private JPasswordField passwordField;
        private JComboBox<String> userTypeComboBox;

        public AddUserDialog() {
            setTitle("添加用户");
            setSize(300, 200);
            setLayout(new GridLayout(4, 2));
            setLocationRelativeTo(null);

            add(new JLabel("用户名:"));
            usernameField = new JTextField();
            add(usernameField);

            add(new JLabel("密码:"));
            passwordField = new JPasswordField();
            add(passwordField);

            
            JButton addButton = new JButton("添加");
            addButton.addActionListener(e -> {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                if (!username.isEmpty() && !password.isEmpty()) {
                	
                	// 检查用户是否存在于users表中
                    String selectUsersSql = "SELECT * FROM users WHERE 学号 = '" + username + "'";
                    Vector<Users> usersData = DBCon.queryData(selectUsersSql);
                    // 检查用户是否存在于tusers表中
                    String selectTUsersSql = "SELECT * FROM tusers WHERE 工号 = '" + username + "'";
                    Vector<Users> tUsersData = DBCon.queryData(selectTUsersSql);
                    if (usersData.isEmpty() && tUsersData.isEmpty()) {
                	
                    	if(username.length() >= 10) {
                    		String insertSql = "INSERT INTO users (`学号`, `密码`) VALUES ('" + username + "', '" + password + "')";
                    		DBCon.executeUpdate(insertSql);
                    	}else {
                    		String insertSql = "INSERT INTO tusers (`工号`, `密码`) VALUES ('" + username + "', '" + password + "')";
                    		DBCon.executeUpdate(insertSql);
                    	}
                    	JOptionPane.showMessageDialog(this, "用户添加成功");
                    	dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "用户名已存在，请重新输入用户名");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "请输入用户名和密码");
                }
            });
            add(addButton);
        }
    }

    class EditUserDialog extends JDialog {
        private JTextField usernameField;
        private JPasswordField passwordField;
        
        private String userId;

        public EditUserDialog(String userId) {
            this.userId = userId;
            setTitle("编辑用户");
            setSize(300, 200);
            setLayout(new GridLayout(3, 2));
            setLocationRelativeTo(null);
                                   
         
            Vector<Vector<String>> userData = DBCon.queryData2("SELECT * FROM users WHERE `学号` LIKE '%" + userId + "%'");            
                      
            if (!userData.isEmpty()) {
                Vector<String> user = userData.get(0);                             

                add(new JLabel("用户名:"));
                usernameField = new JTextField(user.get(0));
                add(usernameField);

                add(new JLabel("密码:"));
                passwordField = new JPasswordField(user.get(1));
                add(passwordField);

                JButton editButton = new JButton("确认");
                editButton.addActionListener(e -> {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    if (!username.isEmpty() && !password.isEmpty()) {
                        String updateSql;
                        if (username.length() >= 10) {
                            updateSql = "UPDATE users SET `学号` = '" + username + "', `密码` = '" + password + "' WHERE `学号` = '" + userId + "'";
                        } else {
                            updateSql = "UPDATE users SET `工号` = '" + username + "', `密码` = '" + password + "' WHERE `工号` = '" + userId + "'";
                        }
                        DBCon.executeUpdate(updateSql);
                        JOptionPane.showMessageDialog(this, "用户信息更新成功");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "请输入用户名和密码");
                    }
                });
                add(editButton);
            } else {
                JOptionPane.showMessageDialog(this, "未找到用户数据");
            }
        }
    }
}
