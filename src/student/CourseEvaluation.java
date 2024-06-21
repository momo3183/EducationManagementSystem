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
        setTitle("�γ�����");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(3, 2));

        JLabel courseLabel = new JLabel("ѡ��γ�:");
        contentPane.add(courseLabel);

        courseComboBox = new JComboBox<>(getCourses(studentId.username));
        contentPane.add(courseComboBox);

        JLabel scoreLabel = new JLabel("����:");
        contentPane.add(scoreLabel);

        scoreTextField = new JTextField();
        contentPane.add(scoreTextField);

        JButton submitButton = new JButton("�ύ");
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
            String sql = "SELECT `�γ���` FROM `score` WHERE `ѧ��` = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, studentId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String courseName = rs.getString("�γ���");
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
            String sql = "INSERT INTO `evaluation` (`�γ���`, `����`) VALUES (?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, selectedCourse);
            pst.setInt(2, score);
            pst.executeUpdate();

            pst.close();
            conn.close();

            JOptionPane.showMessageDialog(this, "�����ύ�ɹ�");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "�����ύʧ��");
        }
    }
}
