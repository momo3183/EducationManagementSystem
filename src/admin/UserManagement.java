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
        JButton searchButton = new JButton("����");
        searchPanel.add(new JLabel("�����û�:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        Vector<String> columnNames = new Vector<>();
        columnNames.add("�û���");
        columnNames.add("����");
        

        Vector<Vector<String>> data = DBCon.queryData2("SELECT * FROM users UNION ALL SELECT * FROM tusers");
        userTable = new JTable(data, columnNames);
        add(new JScrollPane(userTable), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        JButton addButton = new JButton("���");
        JButton editButton = new JButton("�༭");
        JButton deleteButton = new JButton("ɾ��");
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
                Vector<Vector<String>> userData = DBCon.queryData2("SELECT * FROM users WHERE `ѧ��` LIKE '%" + userId + "%' UNION ALL SELECT * FROM tusers WHERE `����` LIKE '%" + userId + "%'");
                              
                
                if (!userData.isEmpty()) {
                    String username = userData.get(0).get(0);
                    EditUserDialog editUserDialog = new EditUserDialog(username);
                    editUserDialog.setVisible(true);
                    refreshUserTable();
                } else {
                    JOptionPane.showMessageDialog(this, "�û�����Ϊ�ջ򲻴���");
                }
            } else {
                JOptionPane.showMessageDialog(this, "��ѡ��Ҫ�༭���û�");
            }
        });

        deleteButton.addActionListener(e -> {
        	int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                String userId = (String) userTable.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "ȷ��ɾ���û���", "ɾ��ȷ��", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    	String deleteSql1 = "DELETE FROM users WHERE `ѧ��` = '" + userId + "'";
                    	DBCon.executeUpdate(deleteSql1);
                    	
                    	String deleteSql2 = "DELETE FROM tusers WHERE `����` = '" + userId + "'";
                    	DBCon.executeUpdate(deleteSql2);
                    	refreshUserTable();
                    	
                }
            } else {
                JOptionPane.showMessageDialog(this, "��ѡ��Ҫɾ�����û�");
            }
        });

        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            Vector<Vector<String>> searchData = 
            		DBCon.queryData2("SELECT * FROM users WHERE `ѧ��` LIKE '%" + searchText + "%' UNION ALL SELECT * FROM tusers WHERE `����` LIKE '%" + searchText + "%'");
            userTable.setModel(new javax.swing.table.DefaultTableModel(searchData, columnNames));
        });
    }
    
    private void refreshUserTable() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("ID");
        columnNames.add("�û���");
        
        Vector<Vector<String>> data = DBCon.queryData2("SELECT * FROM users");
        userTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    class AddUserDialog extends JDialog {
        private JTextField usernameField;
        private JPasswordField passwordField;
        private JComboBox<String> userTypeComboBox;

        public AddUserDialog() {
            setTitle("����û�");
            setSize(300, 200);
            setLayout(new GridLayout(4, 2));
            setLocationRelativeTo(null);

            add(new JLabel("�û���:"));
            usernameField = new JTextField();
            add(usernameField);

            add(new JLabel("����:"));
            passwordField = new JPasswordField();
            add(passwordField);

            
            JButton addButton = new JButton("���");
            addButton.addActionListener(e -> {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                if (!username.isEmpty() && !password.isEmpty()) {
                	
                	// ����û��Ƿ������users����
                    String selectUsersSql = "SELECT * FROM users WHERE ѧ�� = '" + username + "'";
                    Vector<Users> usersData = DBCon.queryData(selectUsersSql);
                    // ����û��Ƿ������tusers����
                    String selectTUsersSql = "SELECT * FROM tusers WHERE ���� = '" + username + "'";
                    Vector<Users> tUsersData = DBCon.queryData(selectTUsersSql);
                    if (usersData.isEmpty() && tUsersData.isEmpty()) {
                	
                    	if(username.length() >= 10) {
                    		String insertSql = "INSERT INTO users (`ѧ��`, `����`) VALUES ('" + username + "', '" + password + "')";
                    		DBCon.executeUpdate(insertSql);
                    	}else {
                    		String insertSql = "INSERT INTO tusers (`����`, `����`) VALUES ('" + username + "', '" + password + "')";
                    		DBCon.executeUpdate(insertSql);
                    	}
                    	JOptionPane.showMessageDialog(this, "�û���ӳɹ�");
                    	dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "�û����Ѵ��ڣ������������û���");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "�������û���������");
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
            setTitle("�༭�û�");
            setSize(300, 200);
            setLayout(new GridLayout(3, 2));
            setLocationRelativeTo(null);
                                   
         
            Vector<Vector<String>> userData = DBCon.queryData2("SELECT * FROM users WHERE `ѧ��` LIKE '%" + userId + "%'");            
                      
            if (!userData.isEmpty()) {
                Vector<String> user = userData.get(0);                             

                add(new JLabel("�û���:"));
                usernameField = new JTextField(user.get(0));
                add(usernameField);

                add(new JLabel("����:"));
                passwordField = new JPasswordField(user.get(1));
                add(passwordField);

                JButton editButton = new JButton("ȷ��");
                editButton.addActionListener(e -> {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    if (!username.isEmpty() && !password.isEmpty()) {
                        String updateSql;
                        if (username.length() >= 10) {
                            updateSql = "UPDATE users SET `ѧ��` = '" + username + "', `����` = '" + password + "' WHERE `ѧ��` = '" + userId + "'";
                        } else {
                            updateSql = "UPDATE users SET `����` = '" + username + "', `����` = '" + password + "' WHERE `����` = '" + userId + "'";
                        }
                        DBCon.executeUpdate(updateSql);
                        JOptionPane.showMessageDialog(this, "�û���Ϣ���³ɹ�");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "�������û���������");
                    }
                });
                add(editButton);
            } else {
                JOptionPane.showMessageDialog(this, "δ�ҵ��û�����");
            }
        }
    }
}
