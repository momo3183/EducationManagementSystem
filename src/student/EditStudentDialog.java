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
        super(parent, "�޸�ѧ����Ϣҳ��", true);
        setSize(450, 600);
        setLocationRelativeTo(parent);

        this.student = student;

        JPanel inputPanel = new JPanel(new GridLayout(10, 2));

        JLabel idLabel = new JLabel("ѧ��:");
        numberField = new JTextField(String.valueOf(student.getStuid()));
        inputPanel.add(idLabel);
        inputPanel.add(numberField);

        JLabel nameLabel = new JLabel("����:");
        nameField = new JTextField(student.getName());
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JLabel sexLabel = new JLabel("�Ա�:");
        sexField = new JTextField(student.getGender());
        inputPanel.add(sexLabel);
        inputPanel.add(sexField);

        JLabel ageLabel = new JLabel("��������:");
        ageField = new JTextField(student.getB());
        inputPanel.add(ageLabel);
        inputPanel.add(ageField);

        JLabel areLabel = new JLabel("����:");
        areField = new JTextField(student.getRegion());
        inputPanel.add(areLabel);
        inputPanel.add(areField);

        JLabel nationLabel = new JLabel("����:");
        nationField = new JTextField(student.getMz());
        inputPanel.add(nationLabel);
        inputPanel.add(nationField);

        JLabel collegeLabel = new JLabel("ѧԺ:");
        collegeField = new JTextField(student.getCollege());
        inputPanel.add(collegeLabel);
        inputPanel.add(collegeField);

        JLabel classLabel = new JLabel("�༶:");
        classField = new JTextField(student.getClassName());
        inputPanel.add(classLabel);
        inputPanel.add(classField);

        JLabel phoneLabel = new JLabel("�绰:");
        phoneField = new JTextField(student.getPhone());
        inputPanel.add(phoneLabel);
        inputPanel.add(phoneField);

        JLabel emailLabel = new JLabel("����:");
        emailField = new JTextField(String.valueOf(student.getEmail()));
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);

        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton saveButton = new JButton("����");
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

        JButton cancelButton = new JButton("ȡ��");
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

