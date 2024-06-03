package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DL extends JFrame {

	Vector data;
	
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JComboBox<String> userTypeComboBox;
	
	private int row = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DL frame = new DL();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DL() {
		setTitle("教务系统登录界面");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		
		userTypeComboBox = new JComboBox<>();
        userTypeComboBox.addItem("学生");
        userTypeComboBox.addItem("教师");
		
		JLabel lblNewLabel = new JLabel("学号/工号：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
		
		//data = DBCon.queryData2("Select * from users");
		
		JButton btnNewButton = new JButton("\u767B\u9646");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean exists = false;//标记
				int userTypeIndex = userTypeComboBox.getSelectedIndex();
				
				if (userTypeIndex == 0) { // 如果选择的是学生
		            data = DBCon.queryData2("SELECT * FROM users"); // 查询学生表
		        } else if (userTypeIndex == 1) { // 如果选择的是教师
		            data = DBCon.queryData2("SELECT * FROM tusers"); // 查询教师表
		        }
				
				
				for(int i=0;i<data.size()-1;i++) {
					String[] a = getIDData(row+i);
					//一、账号密码不为空
					if(textField.getText().length()>0&&new String(passwordField.getPassword()).length()>0) {
						//1、账号密码正确，进入界面
						if(textField.getText().toString().equals(a[0])&&(new String(passwordField.getPassword())).equals(a[1])) {
							exists = true;
							JOptionPane.showMessageDialog(null, "登陆成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
							
							break;
						}//2、密码错误
						else if(textField.getText().toString().equals(a[0])&&!(new String(passwordField.getPassword())).equals(a[1])) {
							exists = true;
							JOptionPane.showMessageDialog(null, "passwork is false","系统提示",JOptionPane.INFORMATION_MESSAGE);
							break;
						}//3、账号存在
						if((textField.getText().toString().equals(a[0]))){
							exists = true;
							break;
						}
					 }
					//二、账号为空
					else if(textField.getText().length()==0&&new String(passwordField.getPassword()).length()>0){
						exists = true;
						JOptionPane.showMessageDialog(null, "user is null","系统提示",JOptionPane.INFORMATION_MESSAGE);
						break;
					}
					//三、密码为空
					else if(textField.getText().length()>0&&new String(passwordField.getPassword()).length()==0) {
						exists = true;
						JOptionPane.showMessageDialog(null, "passwork is null","系统提示",JOptionPane.INFORMATION_MESSAGE);
						break;
					}
					//四、账号密码为空
					else if(textField.getText().length()==0&&new String(passwordField.getPassword()).length()==0){
						exists = true;
						JOptionPane.showMessageDialog(null, "user and passwork are null","系统提示",JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
				if (!exists) {//五、账号不存在
					JOptionPane.showMessageDialog(null, "user is not exits","系统提示",JOptionPane.INFORMATION_MESSAGE);
			    }
			}
		});
		
		JLabel label = new JLabel("教务管理系统");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(162)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(label, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
						.addComponent(userTypeComboBox, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE))
					.addGap(252))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(114)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(userTypeComboBox, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addGap(66))
		);
		contentPane.setLayout(gl_contentPane);
//		data = DBCon.queryData2("Select * from ѧ����");
//		getTextData(row);
	}
	
	public String[] getIDData(int index) {
		Vector line = (Vector)data.get(index);
		String[] a = {line.get(0).toString(),line.get(1).toString()};
		return a;

		}
}
