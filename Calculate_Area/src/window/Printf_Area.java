package window;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import c_CalArea.mainBody;

public class Printf_Area extends JFrame { // 需要继承JFrame
	public Printf_Area(String title)

	{
		JFrame frame = new JFrame(title);
		/**
		 * 设置窗体图标
		 */
		String path1 = Thread.currentThread().getContextClassLoader().getResource("images/yezi1.png").getPath();
		ImageIcon logo1 = new ImageIcon(path1);
		// 修改图标
		Toolkit tk = Toolkit.getDefaultToolkit();
		// 获取图片 三种图片格式都可以
		java.awt.Image img = tk.getImage(path1);
		// 给窗体设置图标
		frame.setIconImage(img);
		frame.setBackground(Color.GREEN);// 设置窗体的背景颜色
		frame.setBounds(736,210,500,600); // 设置窗口的属性 窗口位置以及窗口的大小
		//frame.setLocationRelativeTo(null);// 居中
		frame.setVisible(true);// 设置窗口可见
		frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
//		JPanel Panel = (JPanel) frame.getContentPane();// 注意内容面板必须强转为JPanel才可以实现下面的设置透明
//		Panel.setOpaque(false);// 将内容面板设为透明
//		String path2 = Thread.currentThread().getContextClassLoader().getResource("images/panda.jpg").getPath();
//		ImageIcon icon = new ImageIcon(path2);
//		JLabel label = new JLabel(icon);// 往一个标签中加入图片
//		label.setBounds(0, 0, frame.getWidth(), frame.getHeight());//
//
//		icon.setImage(icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));// 图片自适应标签大小
//		frame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));// 标签添加到层面板

		Double a = mainBody.getArea();
		JLabel Label1 = new JLabel("该叶子的面积为:" + String.valueOf(a) + "cm²"); // 创建一个标签 并设置初始内容
		Label1.setHorizontalAlignment(JLabel.CENTER);
		// Label1.setLayout(null);
		// Label1.setLocation(100, 50);
		Label1.setFont(new Font("宋体", Font.BOLD, 32));
		Label1.setForeground(Color.black);
		frame.getContentPane().add(Label1);// 将一个标签添加到内容面板
		frame.setVisible(true);// 设置窗口可见
	}

	public static void main(String[] args) {
//
		new Printf_Area("叶片面积显示"); // 创建窗口
//
	}

}
