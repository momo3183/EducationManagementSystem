package admin;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import main.DL;

public class AdminMain extends JFrame {
    private JPanel contentPane;
    private CardLayout cardLayout;

    public AdminMain() {
        setTitle("����Ա����");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        // ����һ���µ�JPanel��Ϊ��ҳ��Pane
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        
        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new GridLayout(6, 1)); 
        
        // ������ť
        JButton userManagementButton = new JButton("�û�����");
        JButton studentManagementButton = new JButton("ѧ������");
        JButton courseManagementButton = new JButton("�γ̹���");
        JButton gradeManagementButton = new JButton("�ɼ�����");
        JButton resetPasswordButton = new JButton("��������");
        JButton logoutButton = new JButton("ע��"); 

        //����ť��ӵ��������
        navigationPanel.add(userManagementButton);
        navigationPanel.add(studentManagementButton);
        navigationPanel.add(courseManagementButton);
        navigationPanel.add(gradeManagementButton);
        navigationPanel.add(resetPasswordButton);
        navigationPanel.add(logoutButton); 

        // �����������ӵ�������������
        contentPane.add(navigationPanel, BorderLayout.WEST);

        cardLayout = new CardLayout();
        JPanel cardsPanel = new JPanel(cardLayout);
        contentPane.add(cardsPanel, BorderLayout.CENTER);

        /// ��������ģ������
        JPanel userManagementPanel = new UserManagement();
        JPanel studentManagementPanel = new StudentManagement();
        JPanel courseManagementPanel = new CourseManagement();
        JPanel gradeManagementPanel = new GradeManagement();
        JPanel resetPasswordPanel = new ResetPassword();

        // ����Щ�����ӵ� cardsPanel ��
        cardsPanel.add(userManagementPanel, "User Management");
        cardsPanel.add(studentManagementPanel, "Student Management");
        cardsPanel.add(courseManagementPanel, "Course Management");
        cardsPanel.add(gradeManagementPanel, "Grade Management");
        cardsPanel.add(resetPasswordPanel, "Reset Password");

        // ��Ӱ�ť�Ķ���������
        userManagementButton.addActionListener(e -> cardLayout.show(cardsPanel, "User Management"));
        studentManagementButton.addActionListener(e -> cardLayout.show(cardsPanel, "Student Management"));
        courseManagementButton.addActionListener(e -> cardLayout.show(cardsPanel, "Course Management"));
        gradeManagementButton.addActionListener(e -> cardLayout.show(cardsPanel, "Grade Management"));
        resetPasswordButton.addActionListener(e -> cardLayout.show(cardsPanel, "Reset Password"));

        // ע����ť�Ķ���������
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

        // Ĭ����ʾ
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
