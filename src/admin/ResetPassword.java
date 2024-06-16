package admin;

import java.awt.*;
import javax.swing.*;
import main.DBCon;
import java.awt.event.*;

public class ResetPassword extends JPanel {
    private JTextField userIdField;

    public ResetPassword() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // ����û�ID��ǩ���ı���
        JPanel userIdPanel = new JPanel();
        userIdPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        userIdPanel.add(new JLabel("�û�ID:"));
        userIdField = new JTextField(15);  // �����ı���Ŀ��
        userIdPanel.add(userIdField);
        add(userIdPanel);

        // ����������밴ť
        JButton resetButton = new JButton("��������");
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT); // ��ť����
        resetButton.addActionListener(e -> {
            String userId = userIdField.getText();
            if (!userId.isEmpty()) {
                String updateSql;
                if (userId.length() >= 10) {
                    updateSql = "UPDATE users SET `����` = '123456' WHERE `ѧ��` = '" + userId + "'";
                } else {
                    updateSql = "UPDATE tusers SET `����` = '123456' WHERE `����` = '" + userId + "'";
                }
                DBCon.executeUpdate(updateSql);
                JOptionPane.showMessageDialog(this, "�������óɹ�");
            } else {
                JOptionPane.showMessageDialog(this, "�������û�ID");
            }
        });
        add(resetButton);

        // ������±߾�
        add(Box.createVerticalStrut(10));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("��������");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new ResetPassword());
        frame.pack();
        frame.setLocationRelativeTo(null); // ���ھ���
        frame.setVisible(true);
    }
}
