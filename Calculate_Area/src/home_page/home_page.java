package home_page;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import c_CalArea.mainBody;
import login.Login;
import login.User;
import login.UserToRegister;
import login.main_Login_Register;

public class home_page extends JFrame implements ActionListener {

	private JButton button_instrcution;
	private JButton button_entry;
	private JButton button_qiehuan;
	private JButton button_exit;

	public home_page() {
		init();
	}

	// 界面初始化
	public void init() {
		JFrame frame = new JFrame("首页");
		frame.setLayout(null);
		/**
		 * 设置图标
		 */
		String path1 = Thread.currentThread().getContextClassLoader().getResource("images/leaf.png").getPath();
		ImageIcon logo = new ImageIcon(path1);
		// 修改图标
		Toolkit tk = Toolkit.getDefaultToolkit();
		// 获取图片 三种图片格式都可以
		java.awt.Image img = tk.getImage(path1);
		// 给窗体设置图标
		frame.setIconImage(img);
		JLabel title = new JLabel("欢迎使用，请选择您的操作", JLabel.CENTER);
		title.setBounds(100, 30, 300, 25);
		title.setFont(new Font("仿宋", Font.BOLD, 22));
		title.setForeground(Color.black);
		frame.add(title);

		frame.setBounds(500, 100, 500, 600);// 整个布局大小
		frame.setLocationRelativeTo(null);// 居中
		/**
		 * 进入系统
		 * 
		 */
		button_entry = new JButton("进入系统");

		button_entry.setForeground(Color.BLACK);
		button_entry.setBackground(Color.WHITE);
		button_entry.setBounds(145, 100, 200, 35);
		button_entry.addActionListener(this);
		button_entry.setFont(new Font("仿宋", Font.BOLD, 20));
		frame.add(button_entry);
		/**
		 * 使用说明
		 * 
		 */
		button_instrcution = new JButton("使用说明");
		button_instrcution.setForeground(Color.BLACK);
		button_instrcution.setBackground(Color.WHITE);
		button_instrcution.setBounds(145, 160, 200, 35);
		button_instrcution.addActionListener(this);
		button_instrcution.setFont(new Font("仿宋", Font.BOLD, 20));
		frame.add(button_instrcution);
		/**
		 * 切换用户
		 * 
		 */
		button_qiehuan = new JButton("切换用户");
		button_qiehuan.setForeground(Color.BLACK);
		button_qiehuan.setBackground(Color.WHITE);
		button_qiehuan.setBounds(145, 220, 200, 35);
		button_qiehuan.addActionListener(this);
		button_qiehuan.setFont(new Font("仿宋", Font.BOLD, 20));
		frame.add(button_qiehuan);
		/**
		 * 退出
		 * 
		 */
		button_exit = new JButton("退出");
		button_exit.setForeground(Color.BLACK);
		button_exit.setBackground(Color.WHITE);
		button_exit.setBounds(145, 280, 200, 35);
		button_exit.addActionListener(this);
		button_exit.setFont(new Font("仿宋", Font.BOLD, 20));
		frame.add(button_exit);

		// 添加背景
		/**
		 * 在src级下的类的路径叫做类路径，也是该类的根路径，如果某类在src下，要寻找该的路径也可以使用另外一个通用的方法。就是获取该项目在本机中的当前的绝对路径。不是自己写上去的绝对路径，而是通过类加载器去动态获取。
		 * 获取图片相对路径
		 */
//		String path = Thread.currentThread().getContextClassLoader().getResource("images/ppt1.png").getPath();
//		ImageIcon background = new ImageIcon(path);
//		JLabel label = new JLabel(background);//images/blue.jpeg
//		label.setBounds(0, 0, 500, 600);
//		frame.add(label);
		////////////////////////////

		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		// 主程序
		// 登录窗口
		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());// 当前系统风格
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");//Motif风格，是蓝黑
			// UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());//跨平台的Java风格
			// UIManager.setLookAndFeel("javax.swing.plaf.windows.WindowsLookAndFeel");//windows风格
			// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");//java风格

			// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");//更换为metal风格(默认)：
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//

		} catch (Exception e) {
			e.printStackTrace();
		}
		home_page home_page = new home_page();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == button_entry) {
			this.repaint();
			new mainBody();
		}
		if (arg0.getSource() == button_instrcution) {
			this.repaint();
			new instruction();
		}
		if (arg0.getSource() == button_qiehuan) {
			this.repaint();
			new main_Login_Register();
		}
		if (arg0.getSource() == button_exit) {
			this.repaint();
			System.exit(0);
		}
	}

}
