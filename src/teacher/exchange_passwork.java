package teacher;

import java.awt.BorderLayout;

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
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class exchange_passwork extends JFrame {

	Vector data;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private int row = 0;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					exchange_passwork frame = new exchange_passwork();
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
	public exchange_passwork() {
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
		System.out.println(d.username);
		JLabel lblNewLabel_1 = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4\u5BC6\u7801");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			data = DBCon.queryData3("SELECT * FROM tusers");
			for(int i=0;i<data.size();i++) {
				String[] a = getIDData(row+i);
				if(a[0].equals(textField.getText().toString())) {
					if(!textField_1.getText().trim().isEmpty()) {//判断不为空
						//此处修改密码
						int result = DBCon.executeUpdate2("UPDATE tusers SET 密码='"+a[1]+"' WHERE 工号='"+a[0]+"'");
			            if(result > 0) {
			                JOptionPane.showMessageDialog(null, "修改成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
			                teacher t = new teacher();
							t.setSize(800,600);
							t.setVisible(true);
							dispose();
			            } else {
			                JOptionPane.showMessageDialog(null, "修改失败","系统提示",JOptionPane.ERROR_MESSAGE);
			            }
					}else {
						JOptionPane.showMessageDialog(null, "密码不能为空","系统提示",JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
			}
			});
			
			btnNewButton_1 = new JButton("\u8FD4\u56DE");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					teacher z = new teacher();
					z.setSize(800,600);
					z.setVisible(true);
					JFrame jframe = (JFrame)SwingUtilities.getWindowAncestor(btnNewButton_1);
					jframe.dispose();
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
								.addGap(63)
								.addComponent(btnNewButton)
								.addGap(18)
								.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(113, Short.MAX_VALUE))
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
						.addGap(26)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(85, Short.MAX_VALUE))
			);
			contentPane.setLayout(gl_contentPane);
		}
		
		public String[] getIDData(int index) {
			Vector line = (Vector)data.get(index);	
			String[] a = {line.get(0).toString(),line.get(1).toString()};
			return a;

			}
	}