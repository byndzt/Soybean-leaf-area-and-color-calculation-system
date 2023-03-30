package c_CalArea;

import java.awt.Container;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class print_leaft_shangse extends JFrame {

	public print_leaft_shangse() {
		// use the constructor to unit a Frame
		this.setVisible(true);
		this.setBounds(0, 0, 500, 600);
		this.setLocationRelativeTo(null);// ����
		this.setTitle("叶片保留图");
		 /**
		 * JFrame.EXIT_ON_CLOSE 窗体关闭退出虚拟机
           JFrame.DISPOSE_ON_CLOSE 窗体关闭则销毁窗体
           JFrame.HIDE_ON_CLOSE 窗体关闭则隐藏窗体
           JFrame.DO_NOTHING_ON_CLOSE 什么也不做
		 */
		String path1 = Thread.currentThread().getContextClassLoader().getResource("images/yezi1.png").getPath();
		ImageIcon logo1 = new ImageIcon(path1);

		
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		java.awt.Image img = tk.getImage(path1);
		
		this.setIconImage(img);
		Container contentPane = this.getContentPane();
	
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		String url = mainBody.getadr3();
		
		ImageIcon imageIcon = new ImageIcon(url);
		
		JLabel label = new JLabel(imageIcon, SwingConstants.CENTER);
		
		contentPane.add(label);
	}


}
