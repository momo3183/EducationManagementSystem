package student;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.*;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class ChangePasswork extends JFrame {

	Vector data;

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private int row = 0;
	private static JFrame previousFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePasswork frame = new ChangePasswork(previousFrame);
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
	public ChangePasswork(JFrame previousFrame) {
		setTitle("修改密码");
		this.previousFrame = previousFrame;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");

		textField = new JTextField();
		textField.setColumns(10);

		//在输入框展示用户名
		DL d = new DL();
		textField.setText(d.username);
		textField.setEditable(false);

		JLabel lblNewLabel_1 = new JLabel("\u65B0\u5BC6\u7801\uFF1A");

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("\u786E\u8BA4\u5BC6\u7801");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data = DBCon.queryData2("SELECT * FROM users");
				for(int i=0;i<data.size();i++) {
					String[] a = getIDData(row+i);
					if(a[0].equals(textField.getText().toString())) {
						if(!textField_1.getText().trim().isEmpty()) {//判断不为空
							//此处修改密码
							int result = DBCon.executeUpdate2("UPDATE users SET 密码='"+a[1]+"' WHERE 学号='"+a[0]+"'");
				            if(result > 0) {
				                JOptionPane.showMessageDialog(null, "修改成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
				                Zym t = new Zym();
								t.setSize(800,600);
								t.setVisible(true);
								dispose();
				            } else {
				                JOptionPane.showMessageDialog(null, "修改失败","系统提示",JOptionPane.ERROR_MESSAGE);
				            }
				            previousFrame.setVisible(true);
						}else {
							JOptionPane.showMessageDialog(null, "密码不能为空","系统提示",JOptionPane.ERROR_MESSAGE);
						}
			            

					}
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1))
							.addGap(56)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_1)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(138)
							.addComponent(btnNewButton)))
					.addContainerGap(99, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(44))
		);
		contentPane.setLayout(gl_contentPane);
	}

	public String[] getIDData(int index) {
		Vector line = (Vector)data.get(index);
		String[] a = {line.get(0).toString(),line.get(1).toString()};
		return a;

		}
}
