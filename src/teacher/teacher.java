package teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class teacher extends JFrame {

	private JPanel contentPane;
	Vector data;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					teacher frame = new teacher();
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
	public teacher() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("\u4E2A\u4EBA\u4FE1\u606F");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				information i = new information();
				i.setSize(1000,600);
				i.setVisible(true);
				JFrame jframe = (JFrame)SwingUtilities.getWindowAncestor(btnNewButton);
				jframe.dispose();
			}
		});
		
		JButton btnNewButton_1 = new JButton("\u5B66\u751F\u7BA1\u7406");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 t_c_info i = new  t_c_info();
				i.setSize(800,600);
				i.setVisible(true);
				JFrame jframe = (JFrame)SwingUtilities.getWindowAncestor(btnNewButton_1);
				jframe.dispose();
			}
		});
		
		JButton btnNewButton_2 = new JButton("\u6210\u7EE9\u7BA1\u7406");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 score i= new  score();
					i.setSize(800,600);
					i.setVisible(true);
					JFrame jframe = (JFrame)SwingUtilities.getWindowAncestor(btnNewButton_2);
					jframe.dispose();
			}
		});
		
		JButton btnNewButton_3 = new JButton("\u4FEE\u6539\u5BC6\u7801");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exchange_passwork i = new  exchange_passwork();
				i.setSize(800,600);
				i.setVisible(true);
				JFrame jframe = (JFrame)SwingUtilities.getWindowAncestor(btnNewButton_3);
				jframe.dispose();
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
					.addGap(67))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(227)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(260, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
