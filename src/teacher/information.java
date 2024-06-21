package teacher;

import java.awt.BorderLayout;
import main.*;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
//import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class information extends JFrame {
	Vector data;
	Vector title;
	private JPanel contentPane;
	//表格
	DefaultTableModel dtm=new DefaultTableModel();
	JTable tbl=new JTable(dtm);
	int row=0;
	int type;
	int flag;
	JTableHeader th=tbl.getTableHeader();
	JScrollPane scp = new JScrollPane();
	private JTextField txtteaName;
	private JTextField txtteaID;
	private JTextField txtteaSub;
	private ButtonGroup bg = new ButtonGroup();
	private JRadioButton radnull;
	private JRadioButton radMan;
	private JRadioButton radWoman;
	private JTextField txtteaTel;
	private JTextField txtteaEma;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					information frame = new information();
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
	/**
	 * Create the frame.
	 */
	public void showTextData(int index){
//		添加数据
            Vector line=(Vector) data.get(index);
            DL d = new DL();   
            txtteaName.setText(line.get(1).toString());
            txtteaID.setText(line.get(0).toString());
            txtteaSub.setText(line.get(3).toString());
            txtteaTel.setText(line.get(4).toString());
            txtteaEma.setText(line.get(5).toString());
            
            if(line.get(2).toString().equals("男"))
            {
            	radMan.setSelected(true);
            }
        	else if(line.get(2).toString().equals("女"))
        		{
        			radWoman.setSelected(true);
        		}
        		else
        		{
        			radnull.setSelected(true);
        		}
        }
   
//	鼠标点击
	public void tblClicked(java.awt.event.MouseEvent evt) {
		
		row=tbl.getSelectedRow();
		showTextData(row);
	}
	
	
	public information() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblteaName = new JLabel("\u59D3\u540D");
		
		JLabel lblteaSex = new JLabel("\u6027\u522B");
		
		 radnull = new JRadioButton("\u4E0D\u9650");
		 radnull.setEnabled(false);
		 
		 radWoman = new JRadioButton("\u5973");
		 radWoman.setEnabled(false);
		 
		 radMan = new JRadioButton("\u7537");
		 radMan.setEnabled(false);
		 
		bg.add(radMan);
		bg.add(radWoman);
		bg.add(radnull);

		DL d = new DL();
		txtteaName = new JTextField();
		txtteaName.setText(d.username);
		txtteaName.setColumns(10);
		txtteaName.setEditable(false);
		
		JLabel lblteaID = new JLabel("\u5DE5\u53F7");
		
		txtteaID = new JTextField();
		txtteaID.setColumns(10);
		txtteaID.setEditable(false);
		
		JLabel lblteaSub = new JLabel("\u90E8\u95E8");
		
		txtteaSub = new JTextField();
		txtteaSub.setColumns(10);
		txtteaSub.setEditable(false);
		
//		导入数据
		data=DBCon.queryData0("Select * from teachers");
//		getTextData(0);
		
		for(int i = 0;i<data.size();i++) {
			String[] a = getIDData(row+i);
			if(d.username.toString().equals(a[0].toString())) {
	            txtteaName.setText(a[1]);
	            txtteaID.setText(d.username);
	            txtteaSub.setText(a[3]);
//	            txtteaTel.setText(a[4]);
//	            txtteaEma.setText(a[5]);
	            if(a[2].toString().equals("男"))
	            {
	            	radMan.setSelected(true);
	            }
	        	else if(a[2].toString().equals("女"))
	        		{
	        			radWoman.setSelected(true);
	        		}
	        		else
	        		{
	        			radnull.setSelected(true);
	        		}
			}
		}
		

     	title=new Vector();
		title.add("工号");
		title.add("姓名");
		title.add("性别");
		title.add("部门");
		title.add("电话");
		title.add("邮箱");
		
		tbl.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tblClicked(evt);
			}
		});
		
		
//		表格放到滚动面板上
		scp.setViewportView(tbl);
//		设置滚动条位置大小
		scp.setBounds(new Rectangle(5,180,780,600));
		this.getContentPane().add(scp);
		
		JButton btnNewButton_4 = new JButton("<<");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = 0;
				showTextData(row);
			}
		});
		
		JButton btnNewButton_5 = new JButton("<");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(row>0) {
					row--;
					showTextData(row);
				}else {
					JOptionPane.showMessageDialog(null, "已经是第一条数据了","系统提示",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		JButton btnNewButton_6 = new JButton(">");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(row<data.size()-1) {
					row++;
					showTextData(row);
				}else {
					JOptionPane.showMessageDialog(null, "已经是最后一条数据了","系统提示",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		JButton btnNewButton_7 = new JButton(">>");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = data.size()-1;
				showTextData(row);
			}
		});
//		修改按钮并解锁
		JButton btnupdate = new JButton("\u4FEE\u6539");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				txtteaName.setEditable(true);
//				txtteaID.setEditable(true);
				DL d =new DL();
//				判断是否为自己信息
			if(txtteaID.getText().toString().equals(d.username)) {
				txtteaSub.setEditable(true);
				txtteaTel.setEditable(true);
				txtteaEma.setEditable(true);
				
			}
			else
			{
				JOptionPane.showMessageDialog(null, "不能修改别人的信息哦","系统提示", JOptionPane.ERROR_MESSAGE);
			}
				

			}
		});
		
		JButton btnsave = new JButton("\u4FDD\u5B58");
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				data = DBCon.queryData0("SELECT * FROM teachers");
				for(int i=0;i<data.size();i++) {
					row = 0;
					String[] a = getIDData(row+i);
					if(a[0].equals(txtteaID.getText().toString())) {
						a[1] = txtteaName.getText().toString();
						a[2] = txtteaSub.getText().toString();
						a[3] = txtteaSub.getText().toString();
						a[4] = txtteaTel.getText().toString();
						a[5] = txtteaEma.getText().toString();
						
						//此处修改
						int result = DBCon.executeUpdate2("UPDATE teachers SET 教师='"+a[1]+"',部门='"+a[3]+"',电话='"+a[4]+"',邮箱='"+a[5]+"' WHERE 工号="+a[0]);
			            if(result > 0) {
			                JOptionPane.showMessageDialog(null, "修改成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
			            } else {
			                JOptionPane.showMessageDialog(null, "修改失败","系统提示",JOptionPane.ERROR_MESSAGE);
			            }
			            

						
					}
				}
				
			}
		});
	
//		取消
		JButton btncancle = new JButton("\u53D6\u6D88");
		btncancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtteaSub.setEditable(false);
				txtteaTel.setEditable(false);
				txtteaEma.setEditable(false);
				data = DBCon.queryData0("SELECT * FROM teachers");
				
				showTableData();

				
				
			}
		});
		
		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teacher i = new teacher();
				i.setVisible(true);
				JFrame jframe = (JFrame)SwingUtilities.getWindowAncestor(btnNewButton);
				jframe.dispose();
			}
		});
		
		JLabel lblteaTel = new JLabel("\u7535\u8BDD");
		
		txtteaTel = new JTextField();
		txtteaTel.setColumns(10);
		txtteaTel.setEditable(false);
		
		JLabel lblteaEma = new JLabel("\u90AE\u7BB1");
		
		txtteaEma = new JTextField();
		txtteaEma.setColumns(10);
		txtteaEma.setEditable(false);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblteaName)
							.addGap(10)
							.addComponent(txtteaName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(lblteaID))
						.addComponent(lblteaSex))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtteaID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblteaSub)
							.addGap(10)
							.addComponent(txtteaSub, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblteaTel)
							.addGap(18)
							.addComponent(txtteaTel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblteaEma)
							.addGap(18)
							.addComponent(txtteaEma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(103))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnupdate)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnNewButton_4)
									.addGap(18)
									.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnNewButton_6, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnNewButton_7))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnsave)
									.addGap(18)
									.addComponent(btncancle)))
							.addPreferredGap(ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
							.addComponent(btnNewButton)
							.addGap(26))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addComponent(radMan)
					.addGap(2)
					.addComponent(radWoman)
					.addComponent(radnull))
				.addComponent(scp, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(lblteaName))
								.addComponent(txtteaName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(lblteaID))
								.addComponent(txtteaID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(lblteaSub))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtteaSub, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblteaTel)
									.addComponent(txtteaTel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblteaEma)
									.addComponent(txtteaEma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblteaSex)
								.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_6, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_7, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(radMan)
								.addComponent(radWoman)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(radnull)
									.addComponent(btnupdate)
									.addComponent(btnsave)
									.addComponent(btncancle)))
							.addGap(29))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addGap(37)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scp, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
					.addGap(245))
		);
		contentPane.setLayout(gl_contentPane);
		showTableData();
	}

	public String[] getIDData(int index) {
		 if (index < 0 || index >= data.size()) {
		        // 处理错误情况，可能是显示错误信息或者返回一个特殊值
		        return null; // 或者其他的适当处理
		    }
		Vector line = (Vector)data.get(index);	
		String[] a = {line.get(0).toString(),line.get(1).toString(),line.get(2).toString(),line.get(3).toString(),line.get(4).toString(),line.get(5).toString()};
		return a;

		}
}

