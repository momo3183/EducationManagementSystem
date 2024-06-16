package teacher;

import java.awt.BorderLayout;
import main.*;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;


import main.DBCon;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class stu extends JFrame {
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
	
	
	static private JTextField txtstuName;
	static private JTextField txtstuID;
	static private JTextField txtstuSub;
	static private JTextField txtstuClass;
//	static private JLabel lblstuName;
//	static private JLabel lblstuID;
//	static private JLabel lblstuSub;
	static private JLabel lblstuClass;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stu frame = new stu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
//	鼠标
	public void tblClicked(java.awt.event.MouseEvent evt) {
		row=tbl.getSelectedRow();
		showTextData(row);
	}
	
	public void showTableData() {
		dtm.setDataVector(data,title);
	}
	/**
	 * Create the frame.
	 */
	
	public void showTextData(int index){
//      if(data==null || data.size()==0){
//          JOptionPane.showMessageDialog(null, "鐩墠鏁版嵁涓虹┖","绯荤粺鎻愮ず",JOptionPane.WARNING_MESSAGE);
//      }else{
          //鍏堣幏寰椾竴琛屾暟鎹殑Vector
          Vector line1=(Vector) data.get(index);
          txtstuName.setText(line1.get(1).toString());
          txtstuID.setText(line1.get(0).toString());
          txtstuSub.setText(line1.get(6).toString());
          txtstuClass.setText(line1.get(7).toString());
	}
	
	public stu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		data=DBCon.queryData22("Select * from students");
//		getTextData(0);
     	showTextData(row);
//		tbl.setBounds(5, 200, 800, 150);
//		th.setBounds(5, 100, 800, 150);
//		this.getContentPane().add(tbl);
//		this.getContentPane().add(th);
//		表头
     	title=new Vector();
		title.add("学号");
		title.add("姓名");
		title.add("学院");
		title.add("班级");
		
//		鼠标点击
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
		
		
		
		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teacher i = new teacher();
//				i.setSize(800,600);
				i.setVisible(true);
				JFrame jframe = (JFrame)SwingUtilities.getWindowAncestor(btnNewButton);
				jframe.dispose();
			}
		});
		
		JLabel lblstuName = new JLabel("\u59D3\u540D");
		
		txtstuName = new JTextField();
		txtstuName.setColumns(10);
		
		JLabel lblstuID = new JLabel("\u5B66\u53F7");
		
		txtstuID = new JTextField();
		txtstuID.setColumns(10);
		
		JLabel lblstuSub = new JLabel("\u5B66\u9662");
		
		txtstuSub = new JTextField();
		txtstuSub.setColumns(10);
		
		lblstuClass = new JLabel("\u73ED\u7EA7");
		
		txtstuClass = new JTextField();
		txtstuClass.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblstuName)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtstuName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblstuSub)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtstuSub, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblstuClass)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtstuClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(445))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblstuID)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtstuID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 365, Short.MAX_VALUE)
							.addComponent(btnNewButton)
							.addGap(23))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblstuName)
						.addComponent(txtstuName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblstuID)
						.addComponent(txtstuID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblstuSub)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtstuSub, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblstuClass)
							.addComponent(txtstuClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(430, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		showTableData();
	}
}
