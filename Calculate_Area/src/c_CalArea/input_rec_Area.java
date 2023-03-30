package c_CalArea;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import window.Printf_Area;

public class input_rec_Area extends JFrame implements ActionListener {

	JButton fanhui = null;
	JButton queding = null;
	static JTextField area_kuang = null;
	int flag = 0;
	static JFrame frame;
	static String a1 = null;

	public input_rec_Area() {
		frame = new JFrame("请输入标记块的面积");
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
		// 给窗体设置图标?
		frame.setIconImage(img);
		//////
		JLabel tishi = new JLabel("请您输入标记块面积:");
		// nameStr.setBounds(250, 200, 100, 25);
		
		tishi.setBounds(100, 120, 170, 25);
		tishi.setFont(new Font("仿宋", Font.BOLD, 15));// normal�? 正常的字体�?? bold�? 粗体。bolder�? 特粗体�?? lighter�? 细体
		tishi.setForeground(Color.BLACK);
		frame.add(tishi);

		JLabel cm = new JLabel("cm²");
		// nameStr.setBounds(250, 200, 100, 25);
		cm.setBounds(312, 120, 170, 25);
		cm.setFont(new Font("宋体", Font.BOLD, 15));// normal�? 正常的字体�?? bold�? 粗体。bolder�? 特粗体�?? lighter�? 细体
		cm.setForeground(Color.BLACK);
		frame.add(cm);

		area_kuang = new JTextField();
		area_kuang.enable(true);
		area_kuang.setBounds(250, 120, 60, 28);
		area_kuang.setForeground(Color.BLACK);
		frame.add(area_kuang);

		queding = new JButton("确定");
		queding.setBackground(Color.WHITE);
		queding.setBounds(110, 200, 70, 25);
		queding.setForeground(Color.BLACK);
		queding.addActionListener(this);
		frame.add(queding);

		// frame.setBounds(400, 100, 800, 640);
		//frame.setBounds(500, 100, 450, 500);
		frame.setBounds(736,210,500,500);
		//frame.setLocationRelativeTo(null);// 居中
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);// 关闭窗口则�??出虚拟机

		frame.setVisible(true);
	}

	public static void main(String[] args) {
		//
		new input_rec_Area(); // 创建窗口
		input_rec_Area.chuancan();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		a1 = area_kuang.getText();
		System.out.println(a1);
		if (arg0.getSource() == queding) {

			// System.out.println(a1);
			frame.setVisible(false);
			JOptionPane.showMessageDialog(null, "已输入标记块面积", "提示", JOptionPane.INFORMATION_MESSAGE);
			// frame.repaint();
			// System.exit(0);;
		}

	}

	public static String chuancan() {
		{

			// System.out.println("测试传矩形块面积�?" + chuancan());
			System.out.println(a1);
			return a1;
		}
	}
}
