/*package admin;

import java.awt.*;
import javax.swing.*;

import main.DBCon;

import java.awt.event.*;
import java.util.Vector;

public class CourseManagement extends JPanel {
    private JTable courseTable;
    private JTextField searchField;

    public CourseManagement() {
        setLayout(new BorderLayout());

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchField = new JTextField(20);
        JButton searchButton = new JButton("����");
        searchPanel.add(new JLabel("�����γ�:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // Course Table
        Vector<String> columnNames = new Vector<>();
        columnNames.add("�γ�ID");
        columnNames.add("�γ�����");
        columnNames.add("��ʦ");
        columnNames.add("ѧ��");

        Vector<Vector<String>> data = DBCon.queryData2("SELECT * FROM course");
        courseTable = new JTable(data, columnNames);
        add(new JScrollPane(courseTable), BorderLayout.CENTER);

        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        JButton addButton = new JButton("���");
        JButton editButton = new JButton("�༭");
        JButton deleteButton = new JButton("ɾ��");
        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Add Button Action
        addButton.addActionListener(e -> {
            // Add Course Code
        });

        // Edit Button Action
        editButton.addActionListener(e -> {
            // Edit Course Code
        });

        // Delete Button Action
        deleteButton.addActionListener(e -> {
            // Delete Course Code
        });

        // Search Button Action
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            Vector<Vector<String>> searchData = DBCon.queryData2("SELECT * FROM course0  WHERE courseName LIKE '%" + searchText + "%'");
            courseTable.setModel(new javax.swing.table.DefaultTableModel(searchData, columnNames));
        });
    }
}*/

package admin;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import main.DBCon;

public class CourseManagement extends JPanel {
    private JTable courseTable;
    private JTextField searchField;

    public CourseManagement() {
        setLayout(new BorderLayout());

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchField = new JTextField(20);
        JButton searchButton = new JButton("����");
        searchPanel.add(new JLabel("�����γ�:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // Course Table
        Vector<String> columnNames = new Vector<>();
        columnNames.add("�γ̺�");
        columnNames.add("�γ���");
        columnNames.add("��ʱ");
        columnNames.add("ѧ��");
        columnNames.add("��ʦ");

        Vector<Vector<String>> data = DBCon.queryData2("SELECT * FROM course");
        courseTable = new JTable(data, columnNames);
        add(new JScrollPane(courseTable), BorderLayout.CENTER);

        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        JButton addButton = new JButton("���");
        JButton editButton = new JButton("�༭");
        JButton deleteButton = new JButton("ɾ��");
        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Add Button Action
        addButton.addActionListener(e -> {
            // Add Course Code
            AddCourseDialog addCourseDialog = new AddCourseDialog();
            addCourseDialog.setVisible(true);
            refreshCourseTable();
        });

        // Edit Button Action
        editButton.addActionListener(e -> {
            // Edit Course Code
            int selectedRow = courseTable.getSelectedRow();
            if (selectedRow != -1) {
                String courseId = (String) courseTable.getValueAt(selectedRow, 0);
                String teacher = (String) courseTable.getValueAt(selectedRow, 4);
                EditCourseDialog editCourseDialog = new EditCourseDialog(courseId,teacher);
                editCourseDialog.setVisible(true);
                refreshCourseTable();
            } else {
                JOptionPane.showMessageDialog(this, "��ѡ��Ҫ�༭�Ŀγ�");
            }
        });

        // Delete Button Action
        deleteButton.addActionListener(e -> {
            // Delete Course Code
            int selectedRow = courseTable.getSelectedRow();
            if (selectedRow != -1) {
                String courseId = (String) courseTable.getValueAt(selectedRow, 0);
                String teacher = (String) courseTable.getValueAt(selectedRow, 4);
                int confirm = JOptionPane.showConfirmDialog(this, "ȷ��ɾ���γ̣�", "ɾ��ȷ��", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String deleteSql = "DELETE FROM course WHERE `�γ̺�` = '" + courseId + "' AND `��ʦ` = '" + teacher + "'";
                    DBCon.executeUpdate(deleteSql);
                    refreshCourseTable();
                }
            } else {
                JOptionPane.showMessageDialog(this, "��ѡ��Ҫɾ���Ŀγ�");
            }
        });

        // Search Button Action
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            Vector<Vector<String>> searchData = DBCon.queryData2(
                "SELECT * FROM course WHERE `�γ̺�` LIKE '%" + searchText + "%' OR `�γ���` LIKE '%" + searchText + "%' OR `��ʦ` LIKE '%" + searchText + "%'"
            );
            courseTable.setModel(new javax.swing.table.DefaultTableModel(searchData, columnNames));
        });
    }

    private void refreshCourseTable() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("�γ̺�");
        columnNames.add("�γ���");
        columnNames.add("��ʱ");
        columnNames.add("ѧ��");
        columnNames.add("��ʦ");
        Vector<Vector<String>> data = DBCon.queryData2("SELECT * FROM course");
        courseTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    class AddCourseDialog extends JDialog {
        private JTextField courseIdField;
        private JTextField courseNameField;
        private JTextField hoursField;
        private JTextField creditsField;
        private JTextField teacherField;

        public AddCourseDialog() {
            setTitle("��ӿγ�");
            setSize(400, 300);
            setLayout(new GridLayout(6, 2));
            setLocationRelativeTo(null);

            add(new JLabel("�γ̺�:"));
            courseIdField = new JTextField();
            add(courseIdField);

            add(new JLabel("�γ���:"));
            courseNameField = new JTextField();
            add(courseNameField);

            add(new JLabel("��ʱ:"));
            hoursField = new JTextField();
            add(hoursField);

            add(new JLabel("ѧ��:"));
            creditsField = new JTextField();
            add(creditsField);

            add(new JLabel("��ʦ:"));
            teacherField = new JTextField();
            add(teacherField);

            JButton addButton = new JButton("���");
            addButton.addActionListener(e -> {
                String courseId = courseIdField.getText();
                String courseName = courseNameField.getText();
                String hours = hoursField.getText();
                String credits = creditsField.getText();
                String teacher = teacherField.getText();
                if (!courseId.isEmpty() && !courseName.isEmpty() && !hours.isEmpty() && !credits.isEmpty() && !teacher.isEmpty()) {
                    String selectSql = "SELECT * FROM course WHERE `�γ̺�` = '" + courseId + "' AND `��ʦ` = '" + teacher + "'";
                    Vector<Vector<String>> existingCourse = DBCon.queryData2(selectSql);
                    if (existingCourse.isEmpty()) {
                        String insertSql = "INSERT INTO course (`�γ̺�`, `�γ���`, `��ʱ`, `ѧ��`, `��ʦ`) VALUES ('" + courseId + "', '" + courseName + "', '" + hours + "', '" + credits + "', '" + teacher + "')";
                        DBCon.executeUpdate(insertSql);
                        JOptionPane.showMessageDialog(this, "�γ���ӳɹ�");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "�γ̺��Ѵ��ڣ�����������");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "����д�����ֶ�");
                }
            });
            add(addButton);
        }
    }

    class EditCourseDialog extends JDialog {
        private JTextField courseIdField;
        private JTextField courseNameField;
        private JTextField hoursField;
        private JTextField creditsField;
        private JTextField teacherField;
        private String courseId;
        private String teacher;

        public EditCourseDialog(String courseId, String teacher) {
            this.courseId = courseId;
            this.teacher = teacher;
            setTitle("�༭�γ�");
            setSize(400, 300);
            setLayout(new GridLayout(6, 2));
            setLocationRelativeTo(null);

            Vector<Vector<String>> courseData = DBCon.queryData2("SELECT * FROM course WHERE `�γ̺�` = '" + courseId + "' AND `��ʦ` = '" + teacher + "'");
            if (!courseData.isEmpty()) {
                Vector<String> course = courseData.get(0);

                add(new JLabel("�γ̺�:"));
                courseIdField = new JTextField(course.get(0));
                add(courseIdField);

                add(new JLabel("�γ���:"));
                courseNameField = new JTextField(course.get(1));
                add(courseNameField);

                add(new JLabel("��ʱ:"));
                hoursField = new JTextField(course.get(2));
                add(hoursField);

                add(new JLabel("ѧ��:"));
                creditsField = new JTextField(course.get(3));
                add(creditsField);

                add(new JLabel("��ʦ:"));
                teacherField = new JTextField(course.get(4));
                add(teacherField);

                JButton editButton = new JButton("ȷ��");
                editButton.addActionListener(e -> {
                    String newCourseId = courseIdField.getText();
                    String newCourseName = courseNameField.getText();
                    String newHours = hoursField.getText();
                    String newCredits = creditsField.getText();
                    String newTeacher = teacherField.getText();
                    if (!newCourseId.isEmpty() && !newCourseName.isEmpty() && !newHours.isEmpty() && !newCredits.isEmpty() && !newTeacher.isEmpty()) {
                        String updateSql = "UPDATE course SET `�γ̺�` = '" + newCourseId + "', `�γ���` = '" + newCourseName + "', `��ʱ` = '" + newHours + "', `ѧ��` = '" + newCredits + "', `��ʦ` = '" + newTeacher + "' WHERE `�γ̺�` = '" + courseId + "'";
                        DBCon.executeUpdate(updateSql);
                        JOptionPane.showMessageDialog(this, "�γ���Ϣ���³ɹ�");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "����д�����ֶ�");
                    }
                });
                add(editButton);
            } else {
                JOptionPane.showMessageDialog(this, "δ�ҵ��γ�����");
            }
        }
    }
}

