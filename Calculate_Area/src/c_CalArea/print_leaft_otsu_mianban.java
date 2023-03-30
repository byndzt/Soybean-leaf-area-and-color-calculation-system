package c_CalArea;

import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class print_leaft_otsu_mianban extends JFrame {

	public print_leaft_otsu_mianban() {
		// use the constructor to unit a Frame
		this.setBounds(0, 0, 500, 600);
		this.setLocationRelativeTo(null);// ����
		this.setVisible(true);
		this.setTitle("叶子OTSU图");
		String path1 = Thread.currentThread().getContextClassLoader().getResource("images/yezi1.png").getPath();
		ImageIcon logo1 = new ImageIcon(path1);
		Toolkit tk = Toolkit.getDefaultToolkit();
		java.awt.Image img = tk.getImage(path1);
		this.setIconImage(img);
		Container contentPane = this.getContentPane();
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// get url ʹ��
		// ��ǰ����.class.getResource("");������Ե�ַ�õ����Ե�ַ.//�޷�ʹ�ã��˴�ʹ�þ���·��
		String url = mainBody.getadr2();
		// ʹ��url�õ�һ�� Image �Ķ���
		ImageIcon imageIcon = new ImageIcon(url);
		// ����һ��label ����url�������ݸ�label,��������ʾ���鿴Դ����Եó���3��������String
		// Icon �Ͷ��뷽ʽ
		JLabel label = new JLabel(imageIcon, SwingConstants.CENTER);
		label.setBackground(Color.WHITE);
		// add label to contentPane
		contentPane.add(label);
	}

	public static void main(String[] args) {
		new print_leaft_otsu_mianban();
	}
}
