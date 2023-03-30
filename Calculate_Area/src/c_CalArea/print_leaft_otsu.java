package c_CalArea;

import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class print_leaft_otsu extends JFrame {

	public print_leaft_otsu() {
		// use the constructor to unit a Frame
		this.setBounds(0, 0, 500, 600);
		this.setLocationRelativeTo(null);// ����
		this.setVisible(true);
		this.setTitle("叶子OTSU图");
		/**
		 * 获取URL
		 */
		String path1 = Thread.currentThread().getContextClassLoader().getResource("images/yezi1.png").getPath();
		ImageIcon logo1 = new ImageIcon(path1);

		// 
		Toolkit tk = Toolkit.getDefaultToolkit();
		// 图标
		java.awt.Image img = tk.getImage(path1);
		// 设置图标
		this.setIconImage(img);
		Container contentPane = this.getContentPane();
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// get url 使用 当前类名.class.getResource("");传入相对地址得到绝对地址.//无法使用，此处使用绝对路径
		String url = mainBody.getadr2();
		//使用url得到一个 Image 的对象
		ImageIcon imageIcon = new ImageIcon(url);
		//创建一个label 并将url参数传递给label,并居中显示，查看源码可以得出有3个参数，String Icon 和对齐方式
		JLabel label = new JLabel(imageIcon, SwingConstants.CENTER);
		label.setBackground(Color.WHITE);

		// add label to contentPane
		contentPane.add(label);

	}

	public static void main(String[] args) {
		new print_leaft_otsu();
	}
}
