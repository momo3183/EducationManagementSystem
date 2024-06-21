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

        // �������
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchField = new JTextField(20);
        JButton searchButton = new JButton("����");
        searchPanel.add(new JLabel("����ѧ��:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // ѧ��
        Vector<String> columnNames = new Vector<>();
        columnNames.add("ѧ��");
        columnNames.add("����");
        columnNames.add("�Ա�");
        columnNames.add("��������");
        columnNames.add("����");
        columnNames.add("����");
        columnNames.add("ѧԺ");
        columnNames.add("�༶");
        columnNames.add("�绰");
        columnNames.add("����");

        Vector<Vector<String>> data = DBCon.queryData2("SELECT * FROM students");
        studentTable = new JTable(data, columnNames);
        add(new JScrollPane(studentTable), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        JButton addButton = new JButton("���");
        JButton editButton = new JButton("�޸�");
        JButton deleteButton = new JButton("ɾ��");
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
                JOptionPane.showMessageDialog(this, "��ѡ��Ҫ�޸ĵ�ѧ��");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow != -1) {
                String studentId = (String) studentTable.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "ȷ��ɾ��ѧ����", "ɾ��ȷ��", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String deleteSql = "DELETE FROM students WHERE ѧ�� = '" + studentId + "'";
                    DBCon.executeUpdate(deleteSql);
                    refreshStudentTable();
                }
            } else {
                JOptionPane.showMessageDialog(this, "��ѡ��Ҫɾ����ѧ��");
            }
        });

        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            Vector<Vector<String>> searchData = DBCon.queryData2("SELECT * FROM students WHERE ѧ�� LIKE '%" + searchText + "%' OR ���� LIKE '%" + searchText + "%' OR ѧԺ LIKE '%" + searchText + "%' OR �༶ LIKE '%" + searchText + "%'");
            studentTable.setModel(new javax.swing.table.DefaultTableModel(searchData, columnNames));
        });
    }

    private void refreshStudentTable() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("ѧ��");
        columnNames.add("����");
        columnNames.add("�Ա�");
        columnNames.add("��������");
        columnNames.add("����");
        columnNames.add("����");
        columnNames.add("ѧԺ");
        columnNames.add("�༶");
        columnNames.add("�绰");
        columnNames.add("����");
        Vector<Vector<String>> data = DBCon.queryData2("SELECT * FROM students");
        studentTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    class AddStudentDialog extends JDialog {
        private JTextField studentIdField, nameField, birthDateField, regionField, ethnicityField, collegeField, classField, phoneField, emailField;
        private JComboBox<String> genderComboBox;

        public AddStudentDialog() {
            setTitle("���ѧ��");
            setSize(400, 400);
            setLayout(new GridLayout(11, 2));
            setLocationRelativeTo(null);

            add(new JLabel("ѧ��:"));
            studentIdField = new JTextField();
            add(studentIdField);

            add(new JLabel("����:"));
            nameField = new JTextField();
            add(nameField);

            add(new JLabel("�Ա�:"));
            genderComboBox = new JComboBox<>(new String[]{"��", "Ů"});
            add(genderComboBox);

            add(new JLabel("��������:"));
            birthDateField = new JTextField();
            add(birthDateField);

            add(new JLabel("����:"));
            regionField = new JTextField();
            add(regionField);

            add(new JLabel("����:"));
            ethnicityField = new JTextField();
            add(ethnicityField);

            add(new JLabel("ѧԺ:"));
            collegeField = new JTextField();
            add(collegeField);

            add(new JLabel("�༶:"));
            classField = new JTextField();
            add(classField);

            add(new JLabel("�绰:"));
            phoneField = new JTextField();
            add(phoneField);

            add(new JLabel("����:"));
            emailField = new JTextField();
            add(emailField);

            JButton addButton = new JButton("���");
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
                    String selectSql = "SELECT * FROM students WHERE ѧ�� = '" + studentId + "'";
                    Vector<Vector<String>> studentData = DBCon.queryData2(selectSql);
                    if (studentData.isEmpty()) {
                        String insertSql = "INSERT INTO students (`ѧ��`, `����`, `�Ա�`, `��������`, `����`, `����`, `ѧԺ`, `�༶`, `�绰`, `����`) VALUES ('" + studentId + "', '" + name + "', '" + gender + "', '" + birthDate + "', '" + region + "', '" + ethnicity + "', '" + college + "', '" + className + "', '" + phone + "', '" + email + "')";
                        DBCon.executeUpdate(insertSql);
                        JOptionPane.showMessageDialog(this, "ѧ����ӳɹ�");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "ѧ���Ѵ��ڣ�����������ѧ��");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "������ѧ�ź�����");
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
            setTitle("�޸�ѧ��");
            setSize(400, 400);
            setLayout(new GridLayout(11, 2));
            setLocationRelativeTo(null);

            Vector<Vector<String>> studentData = DBCon.queryData2("SELECT * FROM students WHERE ѧ�� = '" + studentId + "'");
            if (!studentData.isEmpty()) {
                Vector<String> student = studentData.get(0);

                add(new JLabel("ѧ��:"));
                studentIdField = new JTextField(student.get(0));
                studentIdField.setEditable(false);
                add(studentIdField);

                add(new JLabel("����:"));
                nameField = new JTextField(student.get(1));
                add(nameField);

                add(new JLabel("�Ա�:"));
                genderComboBox = new JComboBox<>(new String[]{"��", "Ů"});
                genderComboBox.setSelectedItem(student.get(2));
                add(genderComboBox);

                add(new JLabel("��������:"));
                birthDateField = new JTextField(student.get(3));
                add(birthDateField);

                add(new JLabel("����:"));
                regionField = new JTextField(student.get(4));
                add(regionField);

                add(new JLabel("����:"));
                ethnicityField = new JTextField(student.get(5));
                add(ethnicityField);

                add(new JLabel("ѧԺ:"));
                collegeField = new JTextField(student.get(6));
                add(collegeField);

                add(new JLabel("�༶:"));
                classField = new JTextField(student.get(7));
                add(classField);

                add(new JLabel("�绰:"));
                phoneField = new JTextField(student.get(8));
                add(phoneField);

                add(new JLabel("����:"));
                emailField = new JTextField(student.get(9));
                add(emailField);

                JButton editButton = new JButton("ȷ��");
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
                        String updateSql = "UPDATE students SET `����` = '" + name + "', `�Ա�` = '" + gender + "', `��������` = '" + birthDate + "', `����` = '" + region + "', `����` = '" + ethnicity + "', `ѧԺ` = '" + college + "', `�༶` = '" + className + "', `�绰` = '" + phone + "', `����` = '" + email + "' WHERE `ѧ��` = '" + studentId + "'";
                        DBCon.executeUpdate(updateSql);
                        JOptionPane.showMessageDialog(this, "ѧ����Ϣ���³ɹ�");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "����������");
                    }
                });
                add(editButton);
            } else {
                JOptionPane.showMessageDialog(this, "δ�ҵ�ѧ������");
            }
        }
    }
}
