package teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import main.DBCon;
import main.DL;
//import student.*;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class t_c_info extends JFrame {

	Vector data;
	Vector title;
	
//	滚动条初始化
	JScrollPane scp = new JScrollPane();
//	表格模式
	DefaultTableModel dtm = new DefaultTableModel();
//	表格
	JTable tbl = new JTable(dtm);
	
	private JPanel contentPane;
	private int row=0;
	private int flag;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					t_c_info frame = new t_c_info();
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
	public t_c_info() {
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 618, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 550, Short.MAX_VALUE)
		);
		getContentPane().setLayout(groupLayout);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 647);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u5DE5\u53F7");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		//在输入框展示工号
		DL d = new DL();
		textField.setText(d.username);
		textField.setEditable(false);
		System.out.println(d.username);
		
		JButton button = new JButton("\u8FD4\u56DE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teacher t = new teacher();
				t.setSize(800,600);
				t.setVisible(true);
				JFrame jframe = (JFrame)SwingUtilities.getWindowAncestor(button);
				jframe.dispose();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scp, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 401, Short.MAX_VALUE)
							.addComponent(button)
							.addGap(33))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addComponent(scp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(122))
		);
		contentPane.setLayout(gl_contentPane);
		
		String sql = "SELECT score.学号, students.姓名, course.课程号, score.课程名, 学院, 班级\r\n" + 
				"FROM score\r\n" + 
				"JOIN students ON score.学号 = students.学号\r\n" + 
				"JOIN course ON score.课程名 = course.课程名\r\n" + 
				"JOIN teachers ON course.教师 = teachers.教师\r\n" + 
				"where 工号 = ?";
		data = DBCon.queryData_t_course(sql, d.username);

		title = new Vector();
		
		title.add("学号");
		title.add("姓名");
		title.add("课程号");
		title.add("课程名");
		title.add("学院");
		title.add("班级");

			
		showTableData(data);
		
	
	}
	// 表格显示数据	
	public void showTableData(Vector<Vector<String>> newData) {
		dtm.setDataVector(newData, title);
		
//      锁住表格
		tbl.setDefaultEditor(Object.class, null);
//		表格放到滚动面板上
		scp.setViewportView(tbl);
//		设置滚动条位置大小
		scp.setBounds(new Rectangle(20,300,380,80));
		this.getContentPane().add(scp);
		
		
		
	    System.out.println("newData:");
	    for (Vector<String> rowData : newData) {
	        System.out.println(rowData);
	    }
	}
}


