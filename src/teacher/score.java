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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class score extends JFrame {
	
	private JPanel contentPane;
	

	Vector data;
	Vector title;
	
//	滚动条初始化
	JScrollPane scp = new JScrollPane();
//	表格模式
	DefaultTableModel dtm = new DefaultTableModel();
//	表格
	JTable tbl = new JTable(dtm);
	private int row=0;
	private int flag;
	
	private JTextField txtteaID;
	private JTextField txtstuID;
	private JTextField txtstuName;
	private JTextField txtstuSco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					score frame = new score();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void showTableData() {
		dtm.setDataVector(data,title);
//      锁住表格
		tbl.setDefaultEditor(Object.class, null);
	}
	
	public void showTextData(int index){
        Vector line=(Vector) data.get(index);
        DL d = new DL();   
        txtstuID.setText(line.get(0).toString());
        txtstuName.setText(line.get(1).toString());
        txtstuSco.setText(line.get(4).toString());
       
        
	}
	
public void tblClicked(java.awt.event.MouseEvent evt) {
		
		row=tbl.getSelectedRow();
		showTextData(row);
	}
	
	/**
	 * Create the frame.
	 */
	public score() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u5DE5\u53F7");
		
		txtteaID = new JTextField();
		txtteaID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u5B66\u53F7");
		
		txtstuID = new JTextField();
		txtstuID.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u59D3\u540D");
		
		txtstuName = new JTextField();
		txtstuName.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u6210\u7EE9");
		
		txtstuSco = new JTextField();
		txtstuSco.setColumns(10);
		
		//在输入框展示工号
				DL d = new DL();
				txtteaID.setText(d.username);
				txtteaID.setEditable(false);

				tbl.addMouseListener(new java.awt.event.MouseAdapter(){
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						tblClicked(evt);
					}
				});
				
//				表格放到滚动面板上
				scp.setViewportView(tbl);
//				设置滚动条位置大小
				scp.setBounds(new Rectangle(5, 180, 775, 380));
				this.getContentPane().add(scp);
		
		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 teacher i= new  teacher();
					i.setSize(800,600);
					i.setVisible(true);
					JFrame jframe = (JFrame)SwingUtilities.getWindowAncestor(btnNewButton);
					jframe.dispose();	
			}
		});
		
		JButton btnNewButton_1 = new JButton("\u67E5\u8BE2");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stuID=txtstuID.getText();
				String stuName=txtstuName.getText();
				String stuSco=txtstuSco.getText();
				String sql = "SELECT score.学号,students.姓名,course.课程号, score.课程名,成绩\r\n" + 
						"FROM score\r\n" + 
						"JOIN students ON score.学号 = students.学号\r\n" + 
						"JOIN course ON score.课程名 = course.课程名\r\n" + 
						"JOIN teachers ON course.教师 = teachers.教师\r\n";
				
				String query="";
				if(stuID.length()>0) {
					query="and score.学号 like '%"+stuID+"%'";
				}
				if(stuName.length()>0) {
					if(query.length()>0)
						query=query+"and students.姓名 like '%"+stuName+"%'";
					else
						query="and students.姓名 like '%"+stuName+"%'";
				}
				if(stuSco.length()>0) {
					if(query.length()>0)
						query=query+"and 成绩 = '"+stuSco+"'";
					else
						query="and 成绩 ='"+stuSco+"'";
				}
				
				
				if(query.length()>0) {
					sql=sql+" where 工号 = ?  "+query;
				}else {
					sql = sql+" where 工号 = ?";
				}
//				执行
				data=DBCon.queryData_score(sql,d.username);
				dtm.setDataVector(data, title);
				
			}
		});
		
		JButton btnNewButton_2 = new JButton("\u4FDD\u5B58");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for(int i=0;i<data.size();i++) {
					row = 0;
					String[] a = getIDData(row+i);
					if(a[0].equals(txtstuID.getText().toString())) {
//						a[1] = txtstuID.getText().toString();
//						a[2] = txtstuName.getText().toString();
						a[4] = txtstuSco.getText().toString();
						
						//此处修改
						int result = DBCon.executeUpdate2("UPDATE score SET 成绩='"+a[4]+"'WHERE 学号="+a[0]);
			            if(result > 0) {
			                JOptionPane.showMessageDialog(null, "修改成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
			            } else {
			                JOptionPane.showMessageDialog(null, "修改失败","系统提示",JOptionPane.ERROR_MESSAGE);
			            }
			}
					
				}
			}
		});
		
		JButton btnNewButton_3 = new JButton("\u5237\u65B0");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "SELECT score.学号, students.姓名, course.课程号, score.课程名, 成绩\r\n" + 
						"FROM score\r\n" + 
						"JOIN students ON score.学号 = students.学号\r\n" + 
						"JOIN course ON score.课程名 = course.课程名\r\n" + 
						"JOIN teachers ON course.教师 = teachers.教师\r\n" + 
						"where 工号 = ?";
				data = DBCon.queryData_score(sql, d.username);
				showTableData();
				txtstuID.setText("");
				txtstuName.setText("");
				txtstuSco.setText("");
			}
		});
		
		JButton btnNewButton_4 = new JButton("\u5411\u4E0B\u67E5\u8BE2");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stuSco=txtstuSco.getText();
				String sql = "SELECT score.学号,students.姓名,course.课程号, score.课程名,成绩\r\n" + 
						"FROM score\r\n" + 
						"JOIN students ON score.学号 = students.学号\r\n" + 
						"JOIN course ON score.课程名 = course.课程名\r\n" + 
						"JOIN teachers ON course.教师 = teachers.教师\r\n";
//				String query="";
//				if(stuSco.length()>0) {
//					if(query.length()>0)
//						query=query+"and 成绩 = '"+stuSco+"'";
//					else
//						query="and 成绩 ='"+stuSco+"'";
//				}
				
				String query="";
				if(stuSco.length()>0) {
					query="and 成绩 <='"+stuSco+"'";
				}
				
				if(query.length()>0) {
					sql=sql+" where 工号 = ?  "+query;
				}else {
					sql = sql+" where 工号 = ?";
				}
//				执行
				data=DBCon.queryData_score(sql,d.username);
				dtm.setDataVector(data, title);
				
			}
		});
		
		JButton btnNewButton_5 = new JButton("\u5411\u4E0A\u67E5\u8BE2");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stuSco=txtstuSco.getText();
				String sql = "SELECT score.学号,students.姓名,course.课程号, score.课程名,成绩\r\n" + 
						"FROM score\r\n" + 
						"JOIN students ON score.学号 = students.学号\r\n" + 
						"JOIN course ON score.课程名 = course.课程名\r\n" + 
						"JOIN teachers ON course.教师 = teachers.教师\r\n";
				
				String query="";
				if(stuSco.length()>0) {
					query="and 成绩 >='"+stuSco+"'";
				}
				
				if(query.length()>0) {
					sql=sql+" where 工号 = ?  "+query;
				}else {
					sql = sql+" where 工号 = ?";
				}
//				执行
				data=DBCon.queryData_score(sql,d.username);
				dtm.setDataVector(data, title);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(txtteaID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(txtstuID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_2)
							.addGap(18)
							.addComponent(txtstuName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_3)
							.addGap(18)
							.addComponent(txtstuSco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(320, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addGap(23))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(159)
					.addComponent(btnNewButton_1)
					.addGap(18)
					.addComponent(btnNewButton_2)
					.addGap(18)
					.addComponent(btnNewButton_3)
					.addGap(18)
					.addComponent(btnNewButton_4)
					.addGap(18)
					.addComponent(btnNewButton_5)
					.addContainerGap(198, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(txtteaID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(35)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(txtstuID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2)
								.addComponent(txtstuName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3)
								.addComponent(txtstuSco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_3)
						.addComponent(btnNewButton_4)
						.addComponent(btnNewButton_5))
					.addContainerGap(401, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		title = new Vector();
		
		title.add("学号");
		title.add("姓名");
		title.add("课程号");
		title.add("课程名");
		title.add("成绩");
//		title.add("班级");
		
		String sql = "SELECT score.学号, students.姓名, course.课程号, score.课程名, 成绩\r\n" + 
				"FROM score\r\n" + 
				"JOIN students ON score.学号 = students.学号\r\n" + 
				"JOIN course ON score.课程名 = course.课程名\r\n" + 
				"JOIN teachers ON course.教师 = teachers.教师\r\n" + 
				"where 工号 = ?";
		data = DBCon.queryData_score(sql, d.username);
		
		showTableData();
		
	}
	public String[] getIDData(int index) {
		if (data == null || data.size() == 0) {
	        // 处理错误情况，可能是显示错误信息或者返回一个特殊值
	        return null; // 或者其他的适当处理
	    }
		 if (index < 0 || index >= data.size()) {
		        // 处理错误情况，可能是显示错误信息或者返回一个特殊值
		        return null; // 或者其他的适当处理
		    }
		Vector line = (Vector)data.get(index);	
		String[] a = {line.get(0).toString(),line.get(1).toString(),line.get(2).toString(),line.get(3).toString(),line.get(4).toString()};
		return a;

		}
}
