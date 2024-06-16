package student;

import main.DBCon;
import main.DL;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import student.Student;

public class CourseInfo extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JLabel gpaLabel;

    public CourseInfo() {
        setTitle("课程信息");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        String[] columnNames = {"课程号", "课程名", "课时", "学分", "教师", "成绩", "绩点"};
        Object[][] data = getCourseData();

        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        double gpa = calculateGPA(data);
        gpaLabel = new JLabel("综合绩点 (GPA): " + String.format("%.2f", gpa));
        contentPane.add(gpaLabel, BorderLayout.SOUTH);
    }
    
    

    private Object[][] getCourseData() {
        // 从数据库中获取课程数据
        Object[][] data = {};
        DL d = new DL();
        try {
            Connection conn = DBCon.JDBCon();
            String sql = "SELECT course.课程号, course.课程名, course.课时, course.学分, course.教师, score.成绩 " +
                    "FROM course JOIN score ON course.课程号 = score.课程号 " +
                    "WHERE score.学号 = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, d.username); // 设置参数
            ResultSet rs = pst.executeQuery();

            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            data = new Object[rowCount][7];
            int row = 0;
            while (rs.next()) {
            	String 课程号 = rs.getString("课程号");
                String 课程名 = rs.getString("课程名");
                int 课时 = rs.getInt("课时");
                int 学分 = rs.getInt("学分");
                String 教师 = rs.getString("教师");
                int 成绩 = rs.getInt("成绩");
                double 绩点 = (成绩 / 10.0) - 5;

                data[row][0] = 课程号;
                data[row][1] = 课程名;
                data[row][2] = 课时;
                data[row][3] = 学分;
                data[row][4] = 教师;
                data[row][5] = 成绩;
                data[row][6] = 绩点;

                row++;
            }

            rs.close();
            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    private double calculateGPA(Object[][] data) {
        double totalGPA = 0;
        double totalCredits = 0;

        for (Object[] row : data) {
            double gradePoint = (double) row[6];
            int credits = (int) row[3];

            totalGPA += gradePoint * credits;
            totalCredits += credits;
        }

        return totalCredits != 0 ? totalGPA / totalCredits : 0;
    }
}
