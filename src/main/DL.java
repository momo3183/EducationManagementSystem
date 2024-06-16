package main;

import admin.AdminMain;
import student.Zym;
import teacher.teacher;

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
import javax.swing.SwingUtilities;

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
	public static String username;//hzz修改
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
        userTypeComboBox.addItem("管理员");
		
		JLabel lblNewLabel = new JLabel("学号/工号：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
		
		//data = DBCon.queryData2("Select * from users");
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean exists = false;//标记
				int userTypeIndex = userTypeComboBox.getSelectedIndex();
				
				
				//zjh修改
				if (userTypeIndex == 0) { // 如果选择的是学生
		            data = DBCon.queryData2("SELECT * FROM users"); // 查询学生表
		        } else if (userTypeIndex == 1) { // 如果选择的是教师
		            data = DBCon.queryData2("SELECT * FROM tusers"); // 查询教师表
		        } else if(userTypeIndex == 2) { // 如果选择的是管理员
		            data = DBCon.queryData2("SELECT * FROM ausers"); // 查询管理员表
		        }
				
				
				for(int i=0;i<data.size();i++) {
					String[] a = getIDData(row+i);
					//一、账号密码不为空
					if(textField.getText().length()>0&&new String(passwordField.getPassword()).length()>0) {
						//1、账号密码正确，进入界面
						/*//zjh修改
						if(textField.getText().toString().equals(a[0])&&(new String(passwordField.getPassword())).equals(a[1])) {
							exists = true;
							JOptionPane.showMessageDialog(null, "登陆成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
							if ("admin".equals(a[0])) {
							    // 跳转到管理员界面
								AdminMain adminMain = new AdminMain();
		                        adminMain.setVisible(true);
		                        dispose();
							}*/
						
						if(textField.getText().toString().equals(a[0])&&(new String(passwordField.getPassword())).equals(a[1])) {
							exists = true;
							JOptionPane.showMessageDialog(null, "登陆成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
							
							if(userTypeIndex == 0) {// 如果选择的是学生
								Zym z = new Zym();
								z.setSize(800,600);
								z.setVisible(true);
								JFrame jframe = (JFrame)SwingUtilities.getWindowAncestor(btnNewButton);
								jframe.dispose();
								username = a[0];
								
								
							}else if(userTypeIndex == 1) {// 如果选择的是教师
								username = a[0];
								System.out.println(username);
							
								teacher t = new teacher();
								t.setSize(800,600);
								t.setVisible(true);
								JFrame jframe = (JFrame)SwingUtilities.getWindowAncestor(btnNewButton);
								jframe.dispose();
								
							}else if(userTypeIndex == 2) {// 如果选择的是管理员
								AdminMain adminMain = new AdminMain();
		                        adminMain.setVisible(true);
		                        dispose();
							}
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
						JOptionPane.showMessageDialog(null, "账号为空","系统提示",JOptionPane.INFORMATION_MESSAGE);
						break;
					}
					//三、密码为空
					else if(textField.getText().length()>0&&new String(passwordField.getPassword()).length()==0) {
						exists = true;
						JOptionPane.showMessageDialog(null, "密码为空","系统提示",JOptionPane.INFORMATION_MESSAGE);
						break;
					}
					//四、账号密码为空
					else if(textField.getText().length()==0&&new String(passwordField.getPassword()).length()==0){
						exists = true;
						JOptionPane.showMessageDialog(null, "账号密码为空","系统提示",JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
				if (!exists) {//五、账号不存在
					JOptionPane.showMessageDialog(null, "账号不存在","系统提示",JOptionPane.INFORMATION_MESSAGE);
			    }
			}
		});
		
		JLabel label = new JLabel("教务管理系统");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JButton btnNewButton_1 = new JButton("退出系统");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame jframe = (JFrame)SwingUtilities.getWindowAncestor(btnNewButton_1);
				jframe.dispose();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(151)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(label, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
						.addComponent(userTypeComboBox, 0, 252, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(270))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(73)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(userTypeComboBox, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(45, Short.MAX_VALUE))
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
