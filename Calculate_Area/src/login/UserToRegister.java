package login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
 * ����Աע�����
 * 
 */
public class UserToRegister extends JFrame {
	UserToRegister() {
		init();
	}

	void init() {
		JFrame frame = new JFrame("注册账号");
		frame.setLayout(null);

		JLabel nameStr = new JLabel("用户名:");
		nameStr.setBounds(110, 120, 100, 25);

		frame.add(nameStr);

		JLabel IDStr = new JLabel("账号:");
		IDStr.setBounds(110, 150, 100, 25);

		frame.add(IDStr);

		JLabel passwordStr = new JLabel("密码:");
		passwordStr.setBounds(110, 180, 100, 25);

		frame.add(passwordStr);

		JLabel confrimStr = new JLabel("确认密码:");
		confrimStr.setBounds(110, 210, 100, 30);

		frame.add(confrimStr);

		JTextField userName = new JTextField();
		userName.setBounds(175, 120, 150, 25);
		frame.add(userName);

		JTextField userID = new JTextField();
		userID.setBounds(175, 150, 150, 25);
		frame.add(userID);

		JPasswordField password = new JPasswordField();
		password.setBounds(175, 180, 150, 25);
		frame.add(password);

		JPasswordField confrimPassword = new JPasswordField();
		confrimPassword.setBounds(175, 210, 150, 25);
		frame.add(confrimPassword);

		JButton buttonregister = new JButton("注册");
		buttonregister.setBounds(175, 250, 75, 30);
		frame.add(buttonregister);
		frame.setBounds(500, 200, 450, 500);
		//////////////////////////////////////////
		JButton return_login = new JButton("返回");
		return_login.setBounds(255, 250, 75, 30);
		frame.add(return_login);

//            frame.setBounds(500, 200, 500, 500);//�������
//            frame.setLocationRelativeTo(null);//����
		//////////////////////////
		frame.setBounds(500, 100, 450, 500);
		frame.setLocationRelativeTo(null);// ����
		 //欢迎话语
		JLabel title = new JLabel("注           册");
		title.setBounds(170, 65, 300, 25);
		title.setFont(new Font("宋体", Font.BOLD, 20));
		title.setForeground(Color.BLACK);
		frame.add(title);
		//添加背景
        /**
         * 在src级下的类的路径叫做类路径，也是该类的根路径，如果某类在src下，要寻找该的路径也可以使用另外一个通用的方法。就是获取该项目在本机中的当前的绝对路径。不是自己写上去的绝对路径，而是通过类加载器去动态获取。
         * 获取图片相对路径
         */
//		String path = Thread.currentThread().getContextClassLoader().getResource("images/ppt2.png").getPath();
//		// System.out.println(path);
//		////////////////////////////////////////////////////
//		ImageIcon background = new ImageIcon(path);
//		JLabel label = new JLabel(background);
//		label.setBounds(0, 0, 500, 500);
//		frame.add(label);
//
//		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		 /**
         * 设置图标
         */
		String path1 = Thread.currentThread().getContextClassLoader().getResource("images/leaf.png").getPath();
		ImageIcon logo = new ImageIcon(path1);
		//修改图标
		Toolkit tk = Toolkit.getDefaultToolkit();
		  //获取图片 三种图片格式都可以  
		java.awt.Image img = tk.getImage(path1);
		 // 给窗体设置图标
		frame.setIconImage(img);
		frame.setVisible(true);
		 //为注册按钮增加监听器
		buttonregister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = userName.getText();
				String ID = userID.getText();
				String passwd = new String(password.getPassword());
				String confrimpasswd = new String(confrimPassword.getPassword());

				 //创建Register类
				Register register = new Register();
				register.setID(ID);
				register.setName(name);
				register.setPassword(passwd);
				register.setconfirmpasswd(confrimpasswd);

				 //如果注册成功，返回登录界面
				try {
					if (register.JudgeRegister()) {
						frame.setVisible(false);
						main_Login_Register login_register = new main_Login_Register();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
		return_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				//返回登录界面
				frame.setVisible(false);
				main_Login_Register return_login = new main_Login_Register();

			}

		});
	}
}
