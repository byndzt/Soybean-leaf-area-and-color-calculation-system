package home_page;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class instruction extends JFrame {
	public instruction() {
		init();
	}


	public void init() {
		JFrame frame = new JFrame("软件使用说明");
		frame.setLayout(null);
		
		String path1 = Thread.currentThread().getContextClassLoader().getResource("images/leaf.png").getPath();
		ImageIcon logo = new ImageIcon(path1);

		Toolkit tk = Toolkit.getDefaultToolkit();

		java.awt.Image img = tk.getImage(path1);

		frame.setIconImage(img);
		frame.setBounds(0, 0, 1066, 597);
		frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);

//		
		JPanel Panel = (JPanel) frame.getContentPane();//
		Panel.setOpaque(false);
		String path2 = Thread.currentThread().getContextClassLoader().getResource("images/instruction.png").getPath();
		ImageIcon icon = new ImageIcon(path2);
		JLabel label = new JLabel(icon);
		label.setBounds(0, 0, frame.getWidth(), frame.getHeight());//

		icon.setImage(icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
		frame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		instruction instruction = new instruction();
	}

}
