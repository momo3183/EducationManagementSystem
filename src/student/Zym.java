package student;

import main.DBCon;
import main.DL;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class Zym extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zym frame = new Zym();
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
	public Zym() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);  // 增加窗口大小
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnPersonalInfo = new JButton("个人信息");
		btnPersonalInfo.setPreferredSize(new Dimension(150, 50));
		btnPersonalInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 在输入框展示用户名
				DL d = new DL();
				Student student = DBCon.find(d.username);
				new EditStudentDialog(getRootFrame(),  student);
			}
		});

		JButton btnCourseInfo = new JButton("课程信息");
		btnCourseInfo.setPreferredSize(new Dimension(150, 50));
		btnCourseInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CourseInfo courseInfo = new CourseInfo();
                courseInfo.setVisible(true);
            }
        });

		JButton btnCourseEvaluation = new JButton("课程评价");
		btnCourseEvaluation.setPreferredSize(new Dimension(150, 50));
		btnCourseEvaluation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CourseEvaluation courseEvaluation = new CourseEvaluation();
                courseEvaluation.setVisible(true);
            }
        });

		JButton btnChangePassword = new JButton("修改密码");
		btnChangePassword.setPreferredSize(new Dimension(150, 50));
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePasswork ep = new ChangePasswork(Zym.this); // Pass the current frame
				ep.setSize(800, 600);
				ep.setVisible(true);
				Zym.this.setVisible(false);
			}
		});
		
		JButton button = new JButton("\u6CE8\u9500");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DL i = new  DL();
				i.setSize(800,600);
				i.setVisible(true);
				JFrame jframe = (JFrame)SwingUtilities.getWindowAncestor(button);
				jframe.dispose();
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(53)
					.addComponent(btnPersonalInfo, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(btnCourseInfo, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(14)
							.addComponent(btnCourseEvaluation, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnChangePassword, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addComponent(button, Alignment.TRAILING))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(button)
					.addGap(164)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPersonalInfo, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCourseInfo, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCourseEvaluation, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnChangePassword, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(89, Short.MAX_VALUE))
		);

		contentPane.setLayout(gl_contentPane);
	}

	private JFrame getRootFrame() {
		return (JFrame) SwingUtilities.getWindowAncestor(this);
	}
}
