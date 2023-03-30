package login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class main_Login_Register extends JFrame {
	public main_Login_Register() {
		init();
	}

	// 登录界面初始化
	public void init() {
		JFrame frame = new JFrame("登录");

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

		//////
		JLabel nameStr = new JLabel("账号:");
		// nameStr.setBounds(250, 200, 100, 25);
		nameStr.setBounds(110, 120, 100, 25);
		nameStr.setFont(new Font("仿宋", Font.BOLD, 15));// normal： 正常的字体。 bold： 粗体。bolder： 特粗体。 lighter： 细体
		nameStr.setForeground(Color.BLACK);
		frame.add(nameStr);

		JLabel passwordStr = new JLabel("密码:");
		// passwordStr.setBounds(250, 250, 100, 25);
		passwordStr.setBounds(110, 180, 100, 25);
		passwordStr.setFont(new Font("仿宋", Font.BOLD, 15));
		passwordStr.setForeground(Color.BLACK);
		frame.add(passwordStr);

		JTextField userID = new JTextField();
		userID.setBounds(150, 120, 170, 28);
		userID.setForeground(Color.BLACK);
		frame.add(userID);

		JPasswordField password = new JPasswordField();
		password.setBounds(150, 180, 170, 28);
		password.setForeground(Color.BLACK);
		frame.add(password);

		JButton buttonlogin = new JButton("登录");
		buttonlogin.setBackground(Color.WHITE);
		buttonlogin.setBounds(145, 240, 70, 25);
		buttonlogin.setForeground(Color.BLACK);
		frame.add(buttonlogin);

		JButton buttonregister = new JButton("注册");
		buttonregister.setBackground(Color.WHITE);
		buttonregister.setBounds(250, 240, 70, 25);
		frame.add(buttonregister);

		// frame.setBounds(400, 100, 800, 640);
		JLabel title = new JLabel("欢迎使用叶面积与颜色计算系统");
		title.setBounds(90, 60, 300, 25);
		title.setFont(new Font("仿宋", Font.BOLD, 20));
		title.setForeground(Color.black);
		frame.add(title);

		frame.setBounds(500, 100, 450, 500);
		frame.setLocationRelativeTo(null);// 居中
		// 添加背景
		/**
		 * 在src级下的类的路径叫做类路径，也是该类的根路径，如果某类在src下，要寻找该的路径也可以使用另外一个通用的方法。就是获取该项目在本机中的当前的绝对路径。不是自己写上去的绝对路径，而是通过类加载器去动态获取。
		 * 获取图片相对路径
		 */
//		String path = Thread.currentThread().getContextClassLoader().getResource("images/ppt1.png").getPath();
//		// System.out.println(path);
//		////////////////////////////////////////////////////
//		ImageIcon background = new ImageIcon(path);
//		JLabel label = new JLabel(background);
//		label.setBounds(0, 0, 450, 500);
//		frame.add(label);
		frame.setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);

		// 为登录按钮添加监听器
		buttonlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = userID.getText();
				String passwd = new String(password.getPassword());

				// 创建一个Admin用户，把输入框中的用户名和密码提出来
				User admin = new User();
				admin.setID(ID);
				admin.setPassword(passwd);

				// 登录
				Login login = new Login();

				login.setAdmin(admin);

				if (login.JudgeAdmin() == 0) {
					// 弹出账号或密码错误的窗口
					JOptionPane.showMessageDialog(null, "账号或密码错误", "账号或密码错误", JOptionPane.WARNING_MESSAGE);
					// 清除密码框中的信息
					password.setText("");
					// 清除账号框中的信息
					userID.setText("");
					// System.out.println("登陆失败");
				} else {
					// 弹出登录成功的窗口
					JOptionPane.showMessageDialog(null, "登陆成功", "登陆成功", JOptionPane.NO_OPTION);
					// 点击确定后会跳转到主窗口
					frame.setVisible(false);
				}

			}
		});

		// 为注册按钮添加监听器
		buttonregister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 注册页面
				frame.setVisible(false);
				UserToRegister ar = new UserToRegister();
			}
		});
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
		main_Login_Register login_register = new main_Login_Register();
	}

}
