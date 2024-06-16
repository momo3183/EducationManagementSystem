package admin;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AdminMain extends JFrame {
    private JPanel contentPane;
    private CardLayout cardLayout;

    public AdminMain() {
        setTitle("管理员界面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        // Create a new JPanel as the main contentPane
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        // Create navigation panel
        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new GridLayout(5, 1));
        JButton userManagementButton = new JButton("用户管理");
        JButton studentManagementButton = new JButton("学生管理");
        JButton courseManagementButton = new JButton("课程管理");
        JButton gradeManagementButton = new JButton("成绩管理");
        JButton resetPasswordButton = new JButton("重置密码");

        navigationPanel.add(userManagementButton);
        navigationPanel.add(studentManagementButton);
        navigationPanel.add(courseManagementButton);
        navigationPanel.add(gradeManagementButton);
        navigationPanel.add(resetPasswordButton);

        // Add navigationPanel to the west side of contentPane
        contentPane.add(navigationPanel, BorderLayout.WEST);

        cardLayout = new CardLayout();
        JPanel cardsPanel = new JPanel(cardLayout);
        contentPane.add(cardsPanel, BorderLayout.CENTER);

        // Create panels for each module
        JPanel userManagementPanel = new UserManagement();
        JPanel studentManagementPanel = new StudentManagement();
        JPanel courseManagementPanel = new CourseManagement();
        JPanel gradeManagementPanel = new GradeManagement();
        JPanel resetPasswordPanel = new ResetPassword();

        // Add panels to cardsPanel
        cardsPanel.add(userManagementPanel, "User Management");
        cardsPanel.add(studentManagementPanel, "Student Management");
        cardsPanel.add(courseManagementPanel, "Course Management");
        cardsPanel.add(gradeManagementPanel, "Grade Management");
        cardsPanel.add(resetPasswordPanel, "Reset Password");

        // Add action listeners to buttons
        userManagementButton.addActionListener(e -> cardLayout.show(cardsPanel, "User Management"));
        studentManagementButton.addActionListener(e -> cardLayout.show(cardsPanel, "Student Management"));
        courseManagementButton.addActionListener(e -> cardLayout.show(cardsPanel, "Course Management"));
        gradeManagementButton.addActionListener(e -> cardLayout.show(cardsPanel, "Grade Management"));
        resetPasswordButton.addActionListener(e -> cardLayout.show(cardsPanel, "Reset Password"));

        // Default view
        cardLayout.show(cardsPanel, "User Management");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AdminMain frame = new AdminMain();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
