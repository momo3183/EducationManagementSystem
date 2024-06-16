package student;




import main.DBCon;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EditStudentDialog extends JDialog {
    private JTextField numberField;
    private JTextField nameField;
    private JTextField sexField;
    private JTextField ageField;
    private JTextField areField;
    private JTextField nationField;
    private JTextField collegeField;
    private JTextField classField;
    private JTextField phoneField;
    private JTextField emailField;





    private Student student;

    private DBCon dbCon = new DBCon();

    public EditStudentDialog(JFrame parent , Student student) {
        super(parent, "修改学生信息页面", true);
        setSize(450, 600);
        setLocationRelativeTo(parent);

        this.student = student;

        JPanel inputPanel = new JPanel(new GridLayout(10, 2));

        JLabel idLabel = new JLabel("学号:");
        numberField = new JTextField(String.valueOf(student.getStuid()));
        inputPanel.add(idLabel);
        inputPanel.add(numberField);

        JLabel nameLabel = new JLabel("姓名:");
        nameField = new JTextField(student.getName());
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JLabel sexLabel = new JLabel("性别:");
        sexField = new JTextField(student.getGender());
        inputPanel.add(sexLabel);
        inputPanel.add(sexField);

        JLabel ageLabel = new JLabel("出生日期:");
        ageField = new JTextField(student.getB());
        inputPanel.add(ageLabel);
        inputPanel.add(ageField);

        JLabel areLabel = new JLabel("地区:");
        areField = new JTextField(student.getRegion());
        inputPanel.add(areLabel);
        inputPanel.add(areField);

        JLabel nationLabel = new JLabel("民族:");
        nationField = new JTextField(student.getMz());
        inputPanel.add(nationLabel);
        inputPanel.add(nationField);

        JLabel collegeLabel = new JLabel("学院:");
        collegeField = new JTextField(student.getCollege());
        inputPanel.add(collegeLabel);
        inputPanel.add(collegeField);

        JLabel classLabel = new JLabel("班级:");
        classField = new JTextField(student.getClassName());
        inputPanel.add(classLabel);
        inputPanel.add(classField);

        JLabel phoneLabel = new JLabel("电话:");
        phoneField = new JTextField(student.getPhone());
        inputPanel.add(phoneLabel);
        inputPanel.add(phoneField);

        JLabel emailLabel = new JLabel("邮箱:");
        emailField = new JTextField(String.valueOf(student.getEmail()));
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);

        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton saveButton = new JButton("保存");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                student.setStuid(Long.valueOf(numberField.getText()));
                student.setName(nameField.getText());
                student.setGender(sexField.getText());
                student.setB(ageField.getText());
                student.setRegion(areField.getText());
                student.setMz(nationField.getText());
                student.setCollege(collegeField.getText());
                student.setClassName(classField.getText());
                student.setPhone(phoneField.getText());
                student.setEmail(emailField.getText());
                DBCon.update(student);
                dispose();
            }
        });
        buttonPanel.add(saveButton);

        JButton cancelButton = new JButton("取消");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}

