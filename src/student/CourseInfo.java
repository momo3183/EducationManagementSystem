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
        setTitle("�γ���Ϣ");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        String[] columnNames = {"�γ̺�", "�γ���", "��ʱ", "ѧ��", "��ʦ", "�ɼ�", "����"};
        Object[][] data = getCourseData();

        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        double gpa = calculateGPA(data);
        gpaLabel = new JLabel("�ۺϼ��� (GPA): " + String.format("%.2f", gpa));
        contentPane.add(gpaLabel, BorderLayout.SOUTH);
    }
    
    

    private Object[][] getCourseData() {
        // �����ݿ��л�ȡ�γ�����
        Object[][] data = {};
        DL d = new DL();
        try {
            Connection conn = DBCon.JDBCon();
            String sql = "SELECT course.�γ̺�, course.�γ���, course.��ʱ, course.ѧ��, course.��ʦ, score.�ɼ� " +
                    "FROM course JOIN score ON course.�γ̺� = score.�γ̺� " +
                    "WHERE score.ѧ�� = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, d.username); // ���ò���
            ResultSet rs = pst.executeQuery();

            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            data = new Object[rowCount][7];
            int row = 0;
            while (rs.next()) {
            	String �γ̺� = rs.getString("�γ̺�");
                String �γ��� = rs.getString("�γ���");
                int ��ʱ = rs.getInt("��ʱ");
                int ѧ�� = rs.getInt("ѧ��");
                String ��ʦ = rs.getString("��ʦ");
                int �ɼ� = rs.getInt("�ɼ�");
                double ���� = (�ɼ� / 10.0) - 5;

                data[row][0] = �γ̺�;
                data[row][1] = �γ���;
                data[row][2] = ��ʱ;
                data[row][3] = ѧ��;
                data[row][4] = ��ʦ;
                data[row][5] = �ɼ�;
                data[row][6] = ����;

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
