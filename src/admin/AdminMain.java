package admin;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import main.DL;

public class AdminMain extends JFrame {
    private JPanel contentPane;
    private CardLayout cardLayout;

    public AdminMain() {
        setTitle("管理员界面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        // 创建一个新的JPanel作为主页面Pane
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        
        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new GridLayout(6, 1)); 
        
        // 创建按钮
        JButton userManagementButton = new JButton("用户管理");
        JButton studentManagementButton = new JButton("学生管理");
        JButton courseManagementButton = new JButton("课程管理");
        JButton gradeManagementButton = new JButton("成绩管理");
        JButton resetPasswordButton = new JButton("重置密码");
        JButton logoutButton = new JButton("注销"); 

        //将按钮添加到导航面板
        navigationPanel.add(userManagementButton);
        navigationPanel.add(studentManagementButton);
        navigationPanel.add(courseManagementButton);
        navigationPanel.add(gradeManagementButton);
        navigationPanel.add(resetPasswordButton);
        navigationPanel.add(logoutButton); 

        // 将导航面板添加到内容面板的西侧
        contentPane.add(navigationPanel, BorderLayout.WEST);

        cardLayout = new CardLayout();
        JPanel cardsPanel = new JPanel(cardLayout);
        contentPane.add(cardsPanel, BorderLayout.CENTER);

        /// 创建各个模块的面板
        JPanel userManagementPanel = new UserManagement();
        JPanel studentManagementPanel = new StudentManagement();
        JPanel courseManagementPanel = new CourseManagement();
        JPanel gradeManagementPanel = new GradeManagement();
        JPanel resetPasswordPanel = new ResetPassword();

        // 将这些面板添加到 cardsPanel 中
        cardsPanel.add(userManagementPanel, "User Management");
        cardsPanel.add(studentManagementPanel, "Student Management");
        cardsPanel.add(courseManagementPanel, "Course Management");
        cardsPanel.add(gradeManagementPanel, "Grade Management");
        cardsPanel.add(resetPasswordPanel, "Reset Password");

        // 添加按钮的动作监听器
        userManagementButton.addActionListener(e -> cardLayout.show(cardsPanel, "User Management"));
        studentManagementButton.addActionListener(e -> cardLayout.show(cardsPanel, "Student Management"));
        courseManagementButton.addActionListener(e -> cardLayout.show(cardsPanel, "Course Management"));
        gradeManagementButton.addActionListener(e -> cardLayout.show(cardsPanel, "Grade Management"));
        resetPasswordButton.addActionListener(e -> cardLayout.show(cardsPanel, "Reset Password"));

        // 注销按钮的动作监听器
        logoutButton.addActionListener(e -> {
            dispose(); // Close the current admin window
            EventQueue.invokeLater(() -> {
                try {
                    DL loginFrame = new DL(); // Create and show the login window
                    loginFrame.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        });

        // 默认显示
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
