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
        JButton searchButton = new JButton("搜索");
        searchPanel.add(new JLabel("搜索课程:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // Course Table
        Vector<String> columnNames = new Vector<>();
        columnNames.add("课程ID");
        columnNames.add("课程名称");
        columnNames.add("教师");
        columnNames.add("学分");

        Vector<Vector<String>> data = DBCon.queryData2("SELECT * FROM course");
        courseTable = new JTable(data, columnNames);
        add(new JScrollPane(courseTable), BorderLayout.CENTER);

        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        JButton addButton = new JButton("添加");
        JButton editButton = new JButton("编辑");
        JButton deleteButton = new JButton("删除");
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
        JButton searchButton = new JButton("搜索");
        searchPanel.add(new JLabel("搜索课程:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // Course Table
        Vector<String> columnNames = new Vector<>();
        columnNames.add("课程号");
        columnNames.add("课程名");
        columnNames.add("课时");
        columnNames.add("学分");
        columnNames.add("教师");

        Vector<Vector<String>> data = DBCon.queryData2("SELECT * FROM course");
        courseTable = new JTable(data, columnNames);
        add(new JScrollPane(courseTable), BorderLayout.CENTER);

        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        JButton addButton = new JButton("添加");
        JButton editButton = new JButton("编辑");
        JButton deleteButton = new JButton("删除");
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
                JOptionPane.showMessageDialog(this, "请选择要编辑的课程");
            }
        });

        // Delete Button Action
        deleteButton.addActionListener(e -> {
            // Delete Course Code
            int selectedRow = courseTable.getSelectedRow();
            if (selectedRow != -1) {
                String courseId = (String) courseTable.getValueAt(selectedRow, 0);
                String teacher = (String) courseTable.getValueAt(selectedRow, 4);
                int confirm = JOptionPane.showConfirmDialog(this, "确认删除课程？", "删除确认", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String deleteSql = "DELETE FROM course WHERE `课程号` = '" + courseId + "' AND `教师` = '" + teacher + "'";
                    DBCon.executeUpdate(deleteSql);
                    refreshCourseTable();
                }
            } else {
                JOptionPane.showMessageDialog(this, "请选择要删除的课程");
            }
        });

        // Search Button Action
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            Vector<Vector<String>> searchData = DBCon.queryData2(
                "SELECT * FROM course WHERE `课程号` LIKE '%" + searchText + "%' OR `课程名` LIKE '%" + searchText + "%' OR `教师` LIKE '%" + searchText + "%'"
            );
            courseTable.setModel(new javax.swing.table.DefaultTableModel(searchData, columnNames));
        });
    }

    private void refreshCourseTable() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("课程号");
        columnNames.add("课程名");
        columnNames.add("课时");
        columnNames.add("学分");
        columnNames.add("教师");
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
            setTitle("添加课程");
            setSize(400, 300);
            setLayout(new GridLayout(6, 2));
            setLocationRelativeTo(null);

            add(new JLabel("课程号:"));
            courseIdField = new JTextField();
            add(courseIdField);

            add(new JLabel("课程名:"));
            courseNameField = new JTextField();
            add(courseNameField);

            add(new JLabel("课时:"));
            hoursField = new JTextField();
            add(hoursField);

            add(new JLabel("学分:"));
            creditsField = new JTextField();
            add(creditsField);

            add(new JLabel("教师:"));
            teacherField = new JTextField();
            add(teacherField);

            JButton addButton = new JButton("添加");
            addButton.addActionListener(e -> {
                String courseId = courseIdField.getText();
                String courseName = courseNameField.getText();
                String hours = hoursField.getText();
                String credits = creditsField.getText();
                String teacher = teacherField.getText();
                if (!courseId.isEmpty() && !courseName.isEmpty() && !hours.isEmpty() && !credits.isEmpty() && !teacher.isEmpty()) {
                    String selectSql = "SELECT * FROM course WHERE `课程号` = '" + courseId + "' AND `教师` = '" + teacher + "'";
                    Vector<Vector<String>> existingCourse = DBCon.queryData2(selectSql);
                    if (existingCourse.isEmpty()) {
                        String insertSql = "INSERT INTO course (`课程号`, `课程名`, `课时`, `学分`, `教师`) VALUES ('" + courseId + "', '" + courseName + "', '" + hours + "', '" + credits + "', '" + teacher + "')";
                        DBCon.executeUpdate(insertSql);
                        JOptionPane.showMessageDialog(this, "课程添加成功");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "课程号已存在，请重新输入");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "请填写所有字段");
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
            setTitle("编辑课程");
            setSize(400, 300);
            setLayout(new GridLayout(6, 2));
            setLocationRelativeTo(null);

            Vector<Vector<String>> courseData = DBCon.queryData2("SELECT * FROM course WHERE `课程号` = '" + courseId + "' AND `教师` = '" + teacher + "'");
            if (!courseData.isEmpty()) {
                Vector<String> course = courseData.get(0);

                add(new JLabel("课程号:"));
                courseIdField = new JTextField(course.get(0));
                add(courseIdField);

                add(new JLabel("课程名:"));
                courseNameField = new JTextField(course.get(1));
                add(courseNameField);

                add(new JLabel("课时:"));
                hoursField = new JTextField(course.get(2));
                add(hoursField);

                add(new JLabel("学分:"));
                creditsField = new JTextField(course.get(3));
                add(creditsField);

                add(new JLabel("教师:"));
                teacherField = new JTextField(course.get(4));
                add(teacherField);

                JButton editButton = new JButton("确认");
                editButton.addActionListener(e -> {
                    String newCourseId = courseIdField.getText();
                    String newCourseName = courseNameField.getText();
                    String newHours = hoursField.getText();
                    String newCredits = creditsField.getText();
                    String newTeacher = teacherField.getText();
                    if (!newCourseId.isEmpty() && !newCourseName.isEmpty() && !newHours.isEmpty() && !newCredits.isEmpty() && !newTeacher.isEmpty()) {
                        String updateSql = "UPDATE course SET `课程号` = '" + newCourseId + "', `课程名` = '" + newCourseName + "', `课时` = '" + newHours + "', `学分` = '" + newCredits + "', `教师` = '" + newTeacher + "' WHERE `课程号` = '" + courseId + "'";
                        DBCon.executeUpdate(updateSql);
                        JOptionPane.showMessageDialog(this, "课程信息更新成功");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "请填写所有字段");
                    }
                });
                add(editButton);
            } else {
                JOptionPane.showMessageDialog(this, "未找到课程数据");
            }
        }
    }
}

