package admin;

import main.DBCon;
import java.awt.*;
import javax.swing.*;
import main.DBCon;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Vector;

public class StudentManagement extends JPanel {
    private JTable studentTable;
    private JTextField searchField;

    public StudentManagement() {
        setLayout(new BorderLayout());

        // 搜索面板
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchField = new JTextField(20);
        JButton searchButton = new JButton("搜索");
        searchPanel.add(new JLabel("搜索学生:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // 学生
        Vector<String> columnNames = new Vector<>();
        columnNames.add("学号");
        columnNames.add("姓名");
        columnNames.add("性别");
        columnNames.add("出生日期");
        columnNames.add("地区");
        columnNames.add("民族");
        columnNames.add("学院");
        columnNames.add("班级");
        columnNames.add("电话");
        columnNames.add("邮箱");

        Vector<Vector<String>> data = DBCon.queryData2("SELECT * FROM students");
        studentTable = new JTable(data, columnNames);
        add(new JScrollPane(studentTable), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        JButton addButton = new JButton("添加");
        JButton editButton = new JButton("修改");
        JButton deleteButton = new JButton("删除");
        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);
        add(controlPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            AddStudentDialog addStudentDialog = new AddStudentDialog();
            addStudentDialog.setVisible(true);
            refreshStudentTable();
        });

        editButton.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow != -1) {
                String studentId = (String) studentTable.getValueAt(selectedRow, 0);
                EditStudentDialog editStudentDialog = new EditStudentDialog(studentId);
                editStudentDialog.setVisible(true);
                refreshStudentTable();
            } else {
                JOptionPane.showMessageDialog(this, "请选择要修改的学生");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow != -1) {
                String studentId = (String) studentTable.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "确认删除学生？", "删除确认", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String deleteSql = "DELETE FROM students WHERE 学号 = '" + studentId + "'";
                    DBCon.executeUpdate(deleteSql);
                    refreshStudentTable();
                }
            } else {
                JOptionPane.showMessageDialog(this, "请选择要删除的学生");
            }
        });

        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            Vector<Vector<String>> searchData = DBCon.queryData2("SELECT * FROM students WHERE 学号 LIKE '%" + searchText + "%' OR 姓名 LIKE '%" + searchText + "%' OR 学院 LIKE '%" + searchText + "%' OR 班级 LIKE '%" + searchText + "%'");
            studentTable.setModel(new javax.swing.table.DefaultTableModel(searchData, columnNames));
        });
    }

    private void refreshStudentTable() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("学号");
        columnNames.add("姓名");
        columnNames.add("性别");
        columnNames.add("出生日期");
        columnNames.add("地区");
        columnNames.add("民族");
        columnNames.add("学院");
        columnNames.add("班级");
        columnNames.add("电话");
        columnNames.add("邮箱");
        Vector<Vector<String>> data = DBCon.queryData2("SELECT * FROM students");
        studentTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    class AddStudentDialog extends JDialog {
        private JTextField studentIdField, nameField, birthDateField, regionField, ethnicityField, collegeField, classField, phoneField, emailField;
        private JComboBox<String> genderComboBox;

        public AddStudentDialog() {
            setTitle("添加学生");
            setSize(400, 400);
            setLayout(new GridLayout(11, 2));
            setLocationRelativeTo(null);

            add(new JLabel("学号:"));
            studentIdField = new JTextField();
            add(studentIdField);

            add(new JLabel("姓名:"));
            nameField = new JTextField();
            add(nameField);

            add(new JLabel("性别:"));
            genderComboBox = new JComboBox<>(new String[]{"男", "女"});
            add(genderComboBox);

            add(new JLabel("出生日期:"));
            birthDateField = new JTextField();
            add(birthDateField);

            add(new JLabel("地区:"));
            regionField = new JTextField();
            add(regionField);

            add(new JLabel("民族:"));
            ethnicityField = new JTextField();
            add(ethnicityField);

            add(new JLabel("学院:"));
            collegeField = new JTextField();
            add(collegeField);

            add(new JLabel("班级:"));
            classField = new JTextField();
            add(classField);

            add(new JLabel("电话:"));
            phoneField = new JTextField();
            add(phoneField);

            add(new JLabel("邮箱:"));
            emailField = new JTextField();
            add(emailField);

            JButton addButton = new JButton("添加");
            addButton.addActionListener(e -> {
                String studentId = studentIdField.getText();
                String name = nameField.getText();
                String gender = (String) genderComboBox.getSelectedItem();
                String birthDate = birthDateField.getText();
                String region = regionField.getText();
                String ethnicity = ethnicityField.getText();
                String college = collegeField.getText();
                String className = classField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();

                if (!studentId.isEmpty() && !name.isEmpty()) {
                    String selectSql = "SELECT * FROM students WHERE 学号 = '" + studentId + "'";
                    Vector<Vector<String>> studentData = DBCon.queryData2(selectSql);
                    if (studentData.isEmpty()) {
                        String insertSql = "INSERT INTO students (`学号`, `姓名`, `性别`, `出生日期`, `地区`, `民族`, `学院`, `班级`, `电话`, `邮箱`) VALUES ('" + studentId + "', '" + name + "', '" + gender + "', '" + birthDate + "', '" + region + "', '" + ethnicity + "', '" + college + "', '" + className + "', '" + phone + "', '" + email + "')";
                        DBCon.executeUpdate(insertSql);
                        JOptionPane.showMessageDialog(this, "学生添加成功");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "学号已存在，请重新输入学号");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "请输入学号和姓名");
                }
            });
            add(addButton);
        }
    }

    class EditStudentDialog extends JDialog {
        private JTextField studentIdField, nameField, birthDateField, regionField, ethnicityField, collegeField, classField, phoneField, emailField;
        private JComboBox<String> genderComboBox;
        private String studentId;

        public EditStudentDialog(String studentId) {
            this.studentId = studentId;
            setTitle("修改学生");
            setSize(400, 400);
            setLayout(new GridLayout(11, 2));
            setLocationRelativeTo(null);

            Vector<Vector<String>> studentData = DBCon.queryData2("SELECT * FROM students WHERE 学号 = '" + studentId + "'");
            if (!studentData.isEmpty()) {
                Vector<String> student = studentData.get(0);

                add(new JLabel("学号:"));
                studentIdField = new JTextField(student.get(0));
                studentIdField.setEditable(false);
                add(studentIdField);

                add(new JLabel("姓名:"));
                nameField = new JTextField(student.get(1));
                add(nameField);

                add(new JLabel("性别:"));
                genderComboBox = new JComboBox<>(new String[]{"男", "女"});
                genderComboBox.setSelectedItem(student.get(2));
                add(genderComboBox);

                add(new JLabel("出生日期:"));
                birthDateField = new JTextField(student.get(3));
                add(birthDateField);

                add(new JLabel("地区:"));
                regionField = new JTextField(student.get(4));
                add(regionField);

                add(new JLabel("民族:"));
                ethnicityField = new JTextField(student.get(5));
                add(ethnicityField);

                add(new JLabel("学院:"));
                collegeField = new JTextField(student.get(6));
                add(collegeField);

                add(new JLabel("班级:"));
                classField = new JTextField(student.get(7));
                add(classField);

                add(new JLabel("电话:"));
                phoneField = new JTextField(student.get(8));
                add(phoneField);

                add(new JLabel("邮箱:"));
                emailField = new JTextField(student.get(9));
                add(emailField);

                JButton editButton = new JButton("确认");
                editButton.addActionListener(e -> {
                    String name = nameField.getText();
                    String gender = (String) genderComboBox.getSelectedItem();
                    String birthDate = birthDateField.getText();
                    String region = regionField.getText();
                    String ethnicity = ethnicityField.getText();
                    String college = collegeField.getText();
                    String className = classField.getText();
                    String phone = phoneField.getText();
                    String email = emailField.getText();

                    if (!name.isEmpty()) {
                        String updateSql = "UPDATE students SET `姓名` = '" + name + "', `性别` = '" + gender + "', `出生日期` = '" + birthDate + "', `地区` = '" + region + "', `民族` = '" + ethnicity + "', `学院` = '" + college + "', `班级` = '" + className + "', `电话` = '" + phone + "', `邮箱` = '" + email + "' WHERE `学号` = '" + studentId + "'";
                        DBCon.executeUpdate(updateSql);
                        JOptionPane.showMessageDialog(this, "学生信息更新成功");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "请输入姓名");
                    }
                });
                add(editButton);
            } else {
                JOptionPane.showMessageDialog(this, "未找到学生数据");
            }
        }
    }
}
