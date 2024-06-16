package admin;

import java.awt.*;
import javax.swing.*;
import main.DBCon;
import java.awt.event.*;

public class ResetPassword extends JPanel {
    private JTextField userIdField;

    public ResetPassword() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // 添加用户ID标签和文本框
        JPanel userIdPanel = new JPanel();
        userIdPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        userIdPanel.add(new JLabel("用户ID:"));
        userIdField = new JTextField(15);  // 设置文本框的宽度
        userIdPanel.add(userIdField);
        add(userIdPanel);

        // 添加重置密码按钮
        JButton resetButton = new JButton("重置密码");
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT); // 按钮居中
        resetButton.addActionListener(e -> {
            String userId = userIdField.getText();
            if (!userId.isEmpty()) {
                String updateSql;
                if (userId.length() >= 10) {
                    updateSql = "UPDATE users SET `密码` = '123456' WHERE `学号` = '" + userId + "'";
                } else {
                    updateSql = "UPDATE tusers SET `密码` = '123456' WHERE `工号` = '" + userId + "'";
                }
                DBCon.executeUpdate(updateSql);
                JOptionPane.showMessageDialog(this, "密码重置成功");
            } else {
                JOptionPane.showMessageDialog(this, "请输入用户ID");
            }
        });
        add(resetButton);

        // 添加上下边距
        add(Box.createVerticalStrut(10));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("重置密码");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new ResetPassword());
        frame.pack();
        frame.setLocationRelativeTo(null); // 窗口居中
        frame.setVisible(true);
    }
}
