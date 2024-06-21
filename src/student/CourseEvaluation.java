package student;

import main.DBCon;
import main.DL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseEvaluation extends JFrame {
    private JPanel contentPane;
    private JComboBox<String> courseComboBox;
    private JTextField scoreTextField;

    public CourseEvaluation() {
    	
    	DL studentId = new DL();
        setTitle("课程评价");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(3, 2));

        JLabel courseLabel = new JLabel("选择课程:");
        contentPane.add(courseLabel);

        courseComboBox = new JComboBox<>(getCourses(studentId.username));
        contentPane.add(courseComboBox);

        JLabel scoreLabel = new JLabel("评分:");
        contentPane.add(scoreLabel);

        scoreTextField = new JTextField();
        contentPane.add(scoreTextField);

        JButton submitButton = new JButton("提交");
        contentPane.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitEvaluation();
            }
        });
    }

      
    private String[] getCourses(String studentId) {
        List<String> courses = new ArrayList<>();
        try {
            Connection conn = DBCon.JDBCon();
            String sql = "SELECT `课程名` FROM `score` WHERE `学号` = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, studentId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String courseName = rs.getString("课程名");
                courses.add(courseName);
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return courses.toArray(new String[0]);
    }

    private void submitEvaluation() {
        String selectedCourse = (String) courseComboBox.getSelectedItem();
        int score = Integer.parseInt(scoreTextField.getText());

        try {
            Connection conn = DBCon.JDBCon();
            String sql = "INSERT INTO `evaluation` (`课程名`, `分数`) VALUES (?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, selectedCourse);
            pst.setInt(2, score);
            pst.executeUpdate();

            pst.close();
            conn.close();

            JOptionPane.showMessageDialog(this, "评价提交成功");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "评价提交失败");
        }
    }
}
