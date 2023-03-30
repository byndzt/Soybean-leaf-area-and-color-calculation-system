package c_CalArea;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.painter.border.StandardBorderPainter;
import org.pushingpixels.substance.api.shaper.ClassicButtonShaper;

import Calcute_Area_1.otsu_test;
import ConvertImg_Array.imgToArray;
import c_ImgProcess.c_GetArea;
import c_ImgProcess.c_OtsuYuZhi;
import home_page.home_page;
import home_page.instruction;
import login.main_Login_Register;
import mseMove.DragDao;
import window.Printf_Area;
import window.Printf_HSL;
import window.Printf_RGB;

public class mainBody extends JFrame implements ActionListener {

	
	public DragDao drag = new DragDao();
	private JLabel lbl_1 = null; // 文件所在位置文本提示
	private JLabel lbl_2 = null;// 输入矩形块的面积文本提示
	private JLabel lbl_3 = null;// hsl文本提示
	private JLabel label1 = null;
	/**
	 * 以上三个都是文本提示框
	 */
	private JTextField txt_Numb1 = null;// 文件所在地址框
	private JTextField txt_NumbArea = null;// 显示面积框
	private JTextField txt_juxingkuai = null;// 文字显示输入矩形快的面积框
	 private JTextField txt_hsl = null;// 显示hsl框
	private JTextField txt_H = null;// 显示HSL中的H框
	private JTextField txt_S = null;// 显示HSL中的S框
	private JTextField txt_L = null;// 显示HSL中的L框
	private JTextField Pixel_average = null;// 显示平均像素框
	/**
	 * 以上都是显示框
	 */
	private JButton btn_Openfile = null; // 确定按钮
	private JButton btn_CutFig = null; // 确定标记点
	private JButton btn_SAThings = null; // 确定识别物
	private JButton input_rec_area = null;
	private JButton btn_CalculateArea = null; // 方框获取计算面积
	private JButton btn_CalculatePixel = null; // 计算像素平均值
	 private JButton btn_CalculateHSL = null; // 计算HSL
	private JButton btn_CalculateH = null; // 计算hsl中的H
	private JButton btn_CalculateS = null; // 计算hsl中的S
	private JButton btn_CalculateL = null; // 计算hsl中的L
	private JButton btn_original_image = null; // 打开所选叶子区域原始图
	private JButton btn_otsu_image = null; // 打开所选叶子区域otsu图
	private JButton btn_treated_image = null; // 打开所选叶子区域处理图
	private JButton btn_again = null; // 重输或者再一次计算
	/**
	 * 以上都是按钮
	 */
	private JPanel p;
	private JPanel panel;
	// 声明工具�?
	private JToolBar toolBar;
	private JButton btn_Home, btn_Refresh, btn_Instruction, btn_Qiehuan, btn_Exit, btn_White, btn_White1;
	/**
	 * �?始尝试加工具�?
	 */

	int[][] img = null;// 读入图像数组
	int[][] img_TiQu = null;// 提取出来的数组(选区内的)
	int[] cunshu = null;// 用来存数
	JLabel labelPic = new JLabel(); // 把标签的大小位置设置为图片刚好填充整个面
	public static int iLeaf = 0, irect = 0;
	ArrayList averagepixel;
	String adr;
	static String adr1;
	static String adr2;
	static String adr3;
	static double dbArea;
	static ActionEvent arg1;
	public int Lx, Ly, Rx, Ry;
	imgToArray im2Ar = new imgToArray();// 图片与二维数组相互转换
	ImageIcon bg = null;
	int int_Shink = 2;// 从这里可以控制叶子缩放从而显示大小，数越小图越大
	private double MAX;
	private double h;
	private double l;
	private double s;
	double a = 0;
	double b = 0;
	double c = 0;
	public Object frame;
	public JMenuItem fileMenu1;
	private double b1;
	private double c1;
	private JLabel label_ori_image;
	private static String hsl;
	private static String rgb;
	static ActionListener action;

	//
	private void Init_txtFile() {
		txt_Numb1 = new JTextField(10);
		txt_Numb1.enable(false);
		txt_NumbArea = new JTextField(10);
		txt_NumbArea.enable(true);
		txt_juxingkuai = new JTextField(10);
		txt_juxingkuai.enable(true);
		// txt_hsl=new JTextField(10);
		// txt_hsl.enable(true);
		txt_H = new JTextField(10);
		txt_H.enable(true);
		txt_S = new JTextField(10);
		txt_S.enable(true);
		txt_L = new JTextField(10);
		txt_L.enable(true);
		Pixel_average = new JTextField(10);
		Pixel_average.enable(true);
	}

	// 对标签进行初始化
	private void Init_Lbl() {
		lbl_1 = new JLabel("显示文件所在位置:");
		lbl_2 = new JLabel("3.请输入矩形标记块的面积?(单位cm²):");
	}

	// 对按钮进行初始化
	private void Init_Btn() {
		btn_Openfile = new JButton("打开文件");// 确定按钮初始化
		btn_CutFig = new JButton("1.确定标记块");// 确定标记点
		btn_SAThings = new JButton("2.确定识别物");// 确定识别物
		input_rec_area = new JButton("3.输入标记块面积进行计算");
		btn_CalculateArea = new JButton("计算所选叶片的面积");// 计算面积
		btn_CalculatePixel = new JButton("计算选叶子像素RGB平均值");// 计算像素平均值
		 btn_CalculateHSL=new JButton("RGB转为HSL");// 计算HSL
		btn_CalculateH = new JButton("所选叶子像素RGB转化为H");// 计算H
		btn_CalculateS = new JButton("所选叶子像素RGB转化为S");// 计算H
		btn_CalculateL = new JButton("所选叶子像素RGB转化为L");// 计算H
		btn_original_image = new JButton("查看所选叶片区域图");//
		btn_otsu_image = new JButton("查看所选叶片otsu分割图");
		btn_treated_image = new JButton("查看所选叶片保留图");
		btn_again = new JButton("重新确定");
	}

	// 对按钮绑定监听
	private void BindListener() {
		btn_Openfile.addActionListener(this);// <确定>按钮绑定监听对象
		btn_CutFig.addActionListener(this);// <确定>按钮绑定监听对象
		btn_SAThings.addActionListener(this);// <确定>按钮绑定监听对象
		input_rec_area.addActionListener(this);// <确定>按钮绑定监听对象
		btn_CalculateArea.addActionListener(this);// <确定>按钮绑定监听对象
		btn_CalculatePixel.addActionListener(this);// <确定>按钮绑定监听对象
		 btn_CalculateHSL.addActionListener(this);// <确定>按钮绑定监听对象
		btn_CalculateH.addActionListener(this);// <确定>按钮绑定监听对象
		btn_CalculateS.addActionListener(this);// <确定>按钮绑定监听对象
		btn_CalculateL.addActionListener(this);// <确定>按钮绑定监听对象
		btn_CalculateH.addActionListener(this);// <确定>按钮绑定监听对象
		btn_CalculateS.addActionListener(this);// <确定>按钮绑定监听对象
		btn_CalculateS.addActionListener(this);// <确定>按钮绑定监听对象
		btn_original_image.addActionListener(this);
		btn_otsu_image.addActionListener(this);
		btn_treated_image.addActionListener(this);
		btn_again.addActionListener(this);
		input_rec_area.addActionListener(this);
	}

	// 设置控件位置
	private void SetControlPosition() {
		/**
		 * setBounds(x位置，y位置，宽，高);
		 */
		lbl_1.setBounds(10, 20, 130, 30);// 文件所在位置的提示文本
		lbl_2.setBounds(1000, 22, 200, 30);// 请输入矩形块的面积的提示文本
		/**
		 * 以上放置文本提示框
		 */
		txt_Numb1.setBounds(120, 22, 230, 30);// 显示文件位置框
		txt_juxingkuai.setBounds(1200, 22, 180, 30);// 显示矩形块面积框
		txt_NumbArea.setBounds(1030, 100, 350, 30);// 显示面积框
		// txt_hsl.setBounds(1030,160,350,30);//显示HSL值框
		txt_H.setBounds(1030, 220, 350, 30);// 显示H值框
		txt_S.setBounds(1030, 280, 350, 30);// 显示S值框
		txt_L.setBounds(1030, 340, 350, 30);// 显示L值框
		// txt_hsl.setBounds(210,160,350,30);//显示HSL值框
		Pixel_average.setBounds(1030, 160, 350, 30);// 显示平均像素的值框

		// Pixel_average.setBounds(210,100,350,30);//显示平均像素的值框
		/**
		 * 以上显示框框
		 */
		// drag.setLocation(600, 55);//将组件移到新位置，用x和 y参数来指定新位置的左上角
		drag.setLocation(5, 55);// 将组件移到新位置，用x和y 参数来指定新位置的左上角
		btn_Openfile.setBounds(360, 22, 100, 30);// 打开文件
		// btn_CalculatePixel.setBounds(350,100,200,30);//计算叶子像素RGB按钮
		btn_CalculatePixel.setBounds(750, 160, 220, 30);// 计算叶子像素RGB按钮
		 btn_CalculateHSL.setBounds(350,160,200,30);//计算hsl按钮
		// btn_CalculateHSL.setBounds(800,160,220,30);//计算hsl按钮
		btn_CalculateH.setBounds(750, 220, 220, 30);// 计算h按钮
		btn_CalculateS.setBounds(750, 280, 220, 30);// 计算s按钮
		btn_CalculateL.setBounds(750, 340, 220, 30);// 计算l按钮
		btn_original_image.setBounds(750, 500, 200, 30);// 查看所选叶子原始图
		btn_otsu_image.setBounds(970, 500, 200, 30);// 查看所选叶子分割后的图
		btn_treated_image.setBounds(1200, 500, 200, 30);// 查看分割保留图
		btn_again.setBounds(500, 100, 220, 30);// 重新确定
		btn_CutFig.setBounds(500, 22, 220, 30);// 确定标记点
		btn_SAThings.setBounds(750, 22, 220, 30);// 确定识别物
		input_rec_area.setBounds(1000, 22, 200, 30);// 确定标记块面积
		btn_CalculateArea.setBounds(750, 100, 220, 30);// 计算叶片面积
	}

	// 将控件添加至窗体
	private void AddCtrl() {
		this.add(lbl_1);
		this.add(txt_Numb1);
		this.add(txt_NumbArea);
		this.add(btn_Openfile);
		this.add(lbl_2);// 测试矩形块标签
		this.add(txt_juxingkuai);// 输入矩形块的值
		// this.add(txt_hsl);//hsl值
		this.add(txt_H);// hsl值
		this.add(txt_S);// hsl值
		this.add(txt_L);// hsl值
		this.add(btn_CutFig);
		this.add(btn_SAThings);
		this.add(input_rec_area);
		this.add(btn_CalculateArea);
		this.add(btn_CalculatePixel);
		 this.add(btn_CalculateHSL);
		this.add(btn_CalculateH);
		this.add(btn_CalculateS);
		this.add(btn_CalculateL);
		this.add(btn_original_image);
		this.add(btn_otsu_image);
		this.add(btn_treated_image);
		this.add(btn_again);
		this.add(Pixel_average);
		drag.setOpaque(false);// 层透明
		this.add(drag, BorderLayout.CENTER);

	}

	public mainBody() {
		//////////////////
		/**
		 * 尝试写工具栏
		 */
		this.repaint();
		this.setTitle("工具栏");
		// super("工具栏");
		p = new JPanel();
		// p.setBackground(Color.green);
		toolBar = new JToolBar();

		// 将工具栏对象添加到窗体的上方（北面）
		this.add(toolBar, BorderLayout.NORTH);

		// 创建按钮对象，按钮上有图片
		String btn_home = Thread.currentThread().getContextClassLoader().getResource("images/home1.jpg").getPath();
		String btn_refresh = Thread.currentThread().getContextClassLoader().getResource("images/refresh.png").getPath();
		String btn_qiehuan = Thread.currentThread().getContextClassLoader().getResource("images/qiehuan_user.png")
				.getPath();
		String btn_instruction = Thread.currentThread().getContextClassLoader().getResource("images/wenhao.png")
				.getPath();
		String btn_exit = Thread.currentThread().getContextClassLoader().getResource("images/exit.png").getPath();
		String btn_white = Thread.currentThread().getContextClassLoader().getResource("images/white.png").getPath();
		String btn_white1 = Thread.currentThread().getContextClassLoader().getResource("images/white.png").getPath();
		// String =
		// Thread.currentThread().getContextClassLoader().getResource("images/qiehuan_user.png").getPath();
		// String btnCut1 =
		// Thread.currentThread().getContextClassLoader().getResource("images/paste.png").getPath();
		btn_Home = new JButton(new ImageIcon(btn_home));
		btn_Refresh = new JButton(new ImageIcon(btn_refresh));
		btn_Qiehuan = new JButton(new ImageIcon(btn_qiehuan));
		btn_Instruction = new JButton(new ImageIcon(btn_instruction));
		btn_Exit = new JButton(new ImageIcon(btn_exit));
		btn_White = new JButton(new ImageIcon(btn_white));
		btn_White1 = new JButton(new ImageIcon(btn_white1));
		/**
		 * 设置背景btn_Home, btn_Refresh, btn_Instruction, btn_Qiehuan, btn_Exit
		 */
		btn_Home.setBackground(Color.WHITE);
		btn_Refresh.setBackground(Color.WHITE);
		btn_Instruction.setBackground(Color.WHITE);
		btn_Qiehuan.setBackground(Color.WHITE);
		btn_Exit.setBackground(Color.WHITE);
		btn_White.setBackground(Color.WHITE);
		btn_White1.setBackground(Color.WHITE);

		/**
		 * 添加监听
		 * 
		 */
		btn_Home.addActionListener(this);
		btn_Refresh.addActionListener(this);
		btn_Qiehuan.addActionListener(this);
		btn_Instruction.addActionListener(this);
		btn_Exit.addActionListener(this);

		// 设置按钮的工具提示文�?
		btn_Home.setToolTipText("首页");
		btn_Refresh.setToolTipText("刷新");
		btn_Qiehuan.setToolTipText("切换用户");
		btn_Instruction.setToolTipText("使用说明");
		btn_Exit.setToolTipText("关闭系统");
		// btn_White.setToolTipText("填充");		
		// 将按钮添加到工具栏中
		toolBar.add(btn_Home);

		toolBar.add(btn_Refresh);

		toolBar.add(btn_Qiehuan);

		toolBar.add(btn_Instruction);

		toolBar.add(btn_Exit);
		toolBar.setBackground(Color.WHITE);
		this.add(p);
		this.setSize(1366, 40);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//////////////////
		this.setLayout(null);
		Init_Lbl();// 调用标签初始化方法
		Init_txtFile();// 调用输入框初始化方法
		Init_Btn();// 调用按钮初始化方法
		BindListener();// 绑定监听
		// SetControlPosition();// 设定控件位置
		btn_CutFig.setEnabled(false);
		btn_SAThings.setEnabled(false);
		input_rec_area.setEnabled(true);
		btn_CalculateArea.setEnabled(false);
		btn_CalculatePixel.setEnabled(false);
		 btn_CalculateHSL.setEnabled(false);
		btn_CalculateH.setEnabled(false);
		btn_CalculateS.setEnabled(false);
		btn_CalculateL.setEnabled(false);
		btn_original_image.setEnabled(false);
		btn_otsu_image.setEnabled(false);
		btn_treated_image.setEnabled(false);
		// btn_again.setEnabled(false);
		btn_again.setEnabled(true);

		AddCtrl();// 将控件添加至窗体
		/**
		 * JFrame.EXIT_ON_CLOSE 窗体关闭退出虚拟机 JFrame.DISPOSE_ON_CLOSE 窗体关闭则销毁窗体
		 * JFrame.HIDE_ON_CLOSE 窗体关闭则隐藏窗体 JFrame.DO_NOTHING_ON_CLOSE 什么也不做
		 */
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setBackground(Color.white);
		this.setSize(1366, 768);// 显示界面大小
		this.setLocationRelativeTo(null);
		this.setTitle("系统主界面");
		// 尝试显示背景
		JPanel Panel = (JPanel) this.getContentPane();// 注意内容面板必须强转为JPanel才可以实现下面的设置透明
		Panel.setOpaque(false);// 将内容面板设为透明
/////////////
		// 添加背景
		/**
		 * 在src级下的类的路径叫做类路径，也是该类的根路径，如果某类在src下，要寻找该的路径也可以使用另外一个通用的方法。就是获取该项目在本机中的当前的绝对路径。不是自己写上去的绝对路径，而是通过类加载器去动态获取
		 * 获取图片相对路径
		 */
//		String path2 = Thread.currentThread().getContextClassLoader().getResource("images/ppt1.png").getPath();
//		ImageIcon background = new ImageIcon(path2);
//		JLabel label = new JLabel(background);
//		label.setBounds(0, 0, this.getWidth(), this.getHeight());// 设置标签位置大小，记得大小要和窗口一样大
//		background.setImage(
//				background.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));// 图片自�?�应标签大小
//		this.add(label);
//		this.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));// 标签添加到层面板

		/////////////////////////////////////////////////////
		/**
		 * 设置窗体图标
		 */
		String path1 = Thread.currentThread().getContextClassLoader().getResource("images/yezi1.png").getPath();
		ImageIcon logo1 = new ImageIcon(path1);

		// 修改图标
		Toolkit tk = Toolkit.getDefaultToolkit();
		// 获取图片 三种图片格式都可
		java.awt.Image img = tk.getImage(path1);
		// 给窗体设置图标
		this.setIconImage(img);

////////////
		JMenuBar menuBar = new JMenuBar(); // 菜单栏模板
		JMenu fileMenu = new JMenu("打开文件"); // 补充一个文件菜单菜单
		JMenu selectArea = new JMenu("选取区域"); // 第一个菜单
		JMenu calcAreaMenu = new JMenu("计算面积"); // 第一个菜单
		JMenu calcRGBMenu = new JMenu("计算颜色值"); // 第二个菜单
		JMenu ViewImageMenu = new JMenu("查看图片"); // 第二个菜单
		JMenu Restart = new JMenu("重新运行"); // 第二个菜单

		menuBar.add(fileMenu);
		menuBar.add(selectArea);
		menuBar.add(calcAreaMenu);
		menuBar.add(calcRGBMenu);
		menuBar.add(ViewImageMenu);
		menuBar.add(Restart);

		Dimension dimMenu = new Dimension(90, 30);
		fileMenu.setPreferredSize(dimMenu);
		selectArea.setPreferredSize(dimMenu);
		calcAreaMenu.setPreferredSize(dimMenu);
		calcRGBMenu.setPreferredSize(dimMenu);
		ViewImageMenu.setPreferredSize(dimMenu);
		Restart.setPreferredSize(dimMenu);
		/**
		 * 以下为大菜单下的下拉菜单操作
		 */
		/**
		 * 文件位置操作
		 * 
		 */
		fileMenu1 = new JMenuItem("选择文件");// 创建菜单项对象
		// fileMenu1.addActionListener(new ItemListener1());// 为菜单项添加事件监听�?
		fileMenu1.addActionListener(this);// 为菜单项添加事件监听器
		fileMenu.add(fileMenu1);// 将菜单项对象添加到菜单对象中
		this.setJMenuBar(menuBar);
		/**
		 * 设置图标
		 */
		String path = Thread.currentThread().getContextClassLoader().getResource("images/file.png").getPath();
		ImageIcon logo = new ImageIcon(path);
		// 图片压缩，只需改动20与15自行设置
		Image oldLogo = logo.getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH);
		ImageIcon newLogo = new ImageIcon(oldLogo);
		fileMenu1.setIcon(newLogo);
		/**
		 * 选取区域
		 */
		JMenuItem selectArea1 = new JMenuItem("区域选取");// 创建菜单项对象
		selectArea1.addActionListener(new ItemListener2());// 为菜单项添加事件监听器
		selectArea.add(selectArea1);// 将菜单项对象添加到菜单对象中
		this.setJMenuBar(menuBar);
		/**
		 * 设置图标
		 */
		String path3 = Thread.currentThread().getContextClassLoader().getResource("images/xuanze_kuang.png").getPath();
		ImageIcon logo3 = new ImageIcon(path3);
		// 图片压缩，只需改动20与15自行设置
		Image oldLogo3 = logo3.getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH);
		ImageIcon newLogo3 = new ImageIcon(oldLogo3);
		selectArea1.setIcon(newLogo3);
		/**
		 * 计算面积
		 */
		JMenuItem calcAreaMenu1 = new JMenuItem("计算面积");// 创建菜单项对象
		calcAreaMenu1.addActionListener(new ItemListener3());// 为菜单项添加事件监听事件
		calcAreaMenu.add(calcAreaMenu1);// 将菜单项对象添加到菜单对象中
		this.setJMenuBar(menuBar);
		/**
		 * 设置图标
		 */
		String path4 = Thread.currentThread().getContextClassLoader().getResource("images/jisuanqi.png").getPath();
		ImageIcon logo4 = new ImageIcon(path4);
		// 图片压缩，只需改动20与15自行设置
		Image oldLogo4 = logo4.getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH);
		ImageIcon newLogo4 = new ImageIcon(oldLogo4);
		calcAreaMenu1.setIcon(newLogo4);
		/**
		 * 计算RGB与hsl
		 */
		JMenuItem calcRGBMenu1 = new JMenuItem("计算RGB与HSL");// 创建菜单项对象
		calcRGBMenu1.addActionListener(new ItemListener4());// 为菜单项添加事件监听器
		calcRGBMenu.add(calcRGBMenu1);// 将菜单项对象添加到菜单对象中
		this.setJMenuBar(menuBar);
		/**
		 * 设置图标
		 */
		String path5 = Thread.currentThread().getContextClassLoader().getResource("images/RGB.png").getPath();
		ImageIcon logo5 = new ImageIcon(path5);
		// 图片压缩，只需改动20与15自行设置
		Image oldLogo5 = logo5.getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH);
		ImageIcon newLogo5 = new ImageIcon(oldLogo5);
		calcRGBMenu1.setIcon(newLogo5);
		/**
		 * 查看图片
		 */
		JMenuItem image_yuanshi = new JMenuItem("查看所选叶子原始图");// 创建菜单项对象
		image_yuanshi.addActionListener(new ItemListener5());// 为菜单项添加事件监听器
		ViewImageMenu.add(image_yuanshi);// 将菜单项对象添加到菜单对象中
		this.setJMenuBar(menuBar);
		JMenuItem image_otsu = new JMenuItem("查看所选叶子分割后的图");// 创建菜单项对象
		image_otsu.addActionListener(new ItemListener6());// 为菜单项添加事件监听器
		ViewImageMenu.add(image_otsu);// 将菜单项对象添加到菜单对象中
		this.setJMenuBar(menuBar);
		JMenuItem otsu_baoliu = new JMenuItem("查看分割保留图");// 创建菜单项对象
		otsu_baoliu.addActionListener(new ItemListener7());// 为菜单项添加事件监听器
		ViewImageMenu.add(otsu_baoliu);// 将菜单项对象添加到菜单对象中
		this.setJMenuBar(menuBar);
		/**
		 * 设置图标
		 */
		String path6 = Thread.currentThread().getContextClassLoader().getResource("images/chakan.png").getPath();
		ImageIcon logo6 = new ImageIcon(path6);
		// 图片压缩，只需改动20与15自行设置
		Image oldLogo6 = logo6.getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH);
		ImageIcon newLogo6 = new ImageIcon(oldLogo6);
		image_yuanshi.setIcon(newLogo6);
		image_otsu.setIcon(newLogo6);
		otsu_baoliu.setIcon(newLogo6);
		/**
		 * 重新运行
		 */
		JMenuItem restart = new JMenuItem("重新运行");// 创建菜单项对象
		restart.addActionListener(new ItemListener8());// 为菜单项添加事件监听器
		Restart.add(restart);// 将菜单项对象添加到菜单对象中
		this.setJMenuBar(menuBar);
		/**
		 * 设置图标
		 */
		String path7 = Thread.currentThread().getContextClassLoader().getResource("images/restart.png").getPath();
		ImageIcon logo7 = new ImageIcon(path7);
		// 图片压缩，只需改动20与15自行设置
		Image oldLogo7 = logo7.getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH);
		ImageIcon newLogo7 = new ImageIcon(oldLogo7);
		restart.setIcon(newLogo7);
		this.setVisible(true);// 开始绘制
	}

	/**
	 * 下拉菜单响应监听之选取区域
	 * 
	 * @author Administrator
	 *
	 */
	private class ItemListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			btn_CutFig.setBounds(600, 40, 200, 30);// 确定标记点
			btn_SAThings.setBounds(840, 40, 200, 30);// 确定识别物
			// lbl_2.setBounds(1020, 22, 200, 30);// 请输入矩形块的面积的提示文本
			input_rec_area.setBounds(1080, 40, 200, 30);// 标记快按钮
			// txt_juxingkuai.setBounds(1200, 22, 180, 30);// 显示矩形块面积框
//    		input_rec_area.setBorder(BorderFactory.createRaisedBevelBorder());//按钮突出
//    		input_rec_area.setBackground(Color.ORANGE);
//    		input_rec_area.setForeground(Color.blue);
//    		btn_CutFig.setBorder(BorderFactory.createRaisedBevelBorder());//按钮突出
//        	btn_CutFig.setBackground(Color.ORANGE);
//        	btn_CutFig.setForeground(Color.blue);
//        	btn_SAThings.setBorder(BorderFactory.createRaisedBevelBorder());//按钮突出
//        	btn_SAThings.setBackground(Color.ORANGE);
//        	btn_SAThings.setForeground(Color.blue);	
		}
	}

	/**
	 * 下拉菜单响应监听之计算面积
	 * 
	 * @author Administrator
	 *
	 */
	private class ItemListener3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			btn_CutFig.setBounds(0, 0,0, 0);// 确定标记点
			btn_SAThings.setBounds(0, 0, 0, 0);// 确定识别物
			input_rec_area.setBounds(0, 0, 0, 0);// 标记快按钮
			btn_CalculateArea.setBounds(700, 40, 200, 30);// 计算叶片面积
			txt_NumbArea.setBounds(1000, 40, 200, 30);// 显示面积值

		}
	}

	/**
	 * 下拉菜单响应监听之计算RGB与HSL
	 * 
	 * @author Administrator
	 *
	 */
	private class ItemListener4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			btn_CalculateArea.setBounds(0, 0,0, 0);// 计算叶片面积
			txt_NumbArea.setBounds(0, 0,0, 0);// 显示面积值
			btn_CalculatePixel.setBounds(700, 40, 200, 30);// 计算叶子像素RGB按钮
//			btn_CalculateH.setBounds(840, 235, 200, 30);// 计算h按钮
//			btn_CalculateS.setBounds(840, 295, 200, 30);// 计算s按钮
//			btn_CalculateL.setBounds(840, 355, 200, 30);// 计算l按钮
			btn_CalculateHSL.setBounds(1000,40,200,30);
//			txt_H.setBounds(1080, 235, 200, 30);// 显示H值框
//			txt_S.setBounds(1080, 295, 200, 30);// 显示S值框
//			txt_L.setBounds(1080, 355, 200, 30);// 显示L值框
			//Pixel_average.setBounds(1080, 175, 200, 30);// 显示平均像素的�?�框
		}
	}

	/**
	 * 下拉菜单响应监听之输出图片
	 * 
	 * @author Administrator
	 *
	 */
	private class ItemListener5 implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			print_leaft_yuanshi shuchu = new print_leaft_yuanshi();// 把图片输出出�?
			shuchu.getBounds();
		}
	}

	private class ItemListener6 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			print_leaft_otsu shuchu1 = new print_leaft_otsu();// 把图片输出出�?
			shuchu1.getBounds();

		}
	}

	private class ItemListener7 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			print_leaft_shangse shuchu2 = new print_leaft_shangse();// 把图片输出出�?
			shuchu2.getBounds();

		}
	}

	private class ItemListener8 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
//        	lbl_1.setBounds(10, 20, 130, 30);// 文件所在位置的提示文本
//        	txt_Numb1.setBounds(120, 22, 230, 30);// 显示文件位置框
//        	btn_Openfile.setBounds(360, 22, 100, 30);// 打开文件 
//        	btn_CutFig.setBounds(500, 22, 220, 30);// 确定标记点
//    		btn_SAThings.setBounds(750, 22, 220, 30);// 确定识别物
//    		lbl_2.setBounds(1000, 22, 200, 30);// 请输入矩形块的面积的提示文本
//    		txt_juxingkuai.setBounds(1200, 22, 180, 30);// 显示矩形块面积框
//        	btn_CalculateArea.setBounds(750, 100, 220, 30);// 计算叶片面积
//    		txt_NumbArea.setBounds(1030, 100, 350, 30);// 显示面积值
//        	btn_CalculatePixel.setBounds(750, 160, 220, 30);// 计算叶子像素RGB按钮
//    		btn_CalculateH.setBounds(750, 220, 220, 30);// 计算h按钮
//    		btn_CalculateS.setBounds(750, 280, 220, 30);// 计算s按钮
//    		btn_CalculateL.setBounds(750, 340, 220, 30);// 计算l按钮
//    		txt_H.setBounds(1030, 220, 350, 30);// 显示H值框
//    		txt_S.setBounds(1030, 280, 350, 30);// 显示S值框
//    		txt_L.setBounds(1030, 340, 350, 30);// 显示L值框
//    		Pixel_average.setBounds(1030, 160, 350, 30);// 显示平均像素的框
//        	btn_original_image.setBounds(750, 500, 200, 30);// 查看所选叶子原始图
//    		btn_otsu_image.setBounds(970, 500, 200, 30);// 查看所选叶子分割后的图
//    		btn_treated_image.setBounds(1200, 500, 200, 30);// 查看分割保留图
			// btn_again.setBounds(500, 100, 220, 30);// 重新确定
			int choose = JOptionPane.showConfirmDialog(null, "yes          or          no ?", "是否重新运行",
					JOptionPane.YES_NO_OPTION);
			if (choose == 0)// 如果确定
			{
				mainBody b = new mainBody();
			}

		}
	}

	/**
	 * 对图片进行缩小
	 * 
	 * @param originalImage 原始图片
	 * @param times         缩小倍数
	 * @return 缩小后的Image
	 */
	public static BufferedImage zoomOutImage(BufferedImage originalImage, Integer times) {
		int width = originalImage.getWidth() / times;
		int height = originalImage.getHeight() / times;
		BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());
		Graphics g = newImage.getGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		return newImage;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btn_Openfile) {
//			this.repaint();// 页面刷新
//			lbl_1.setBounds(0, 0, 0, 0);
//			txt_Numb1.setBounds(0, 0, 0, 0);// 显示文件位置
//			btn_Openfile.setBounds(0, 0, 0, 0);// 打开文件
//			JFileChooser jfc = new JFileChooser();
//			jfc.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));// 设置选择器的过滤器
//			jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);// 打开文件选择位置
//			jfc.showDialog(new JLabel(), "选择");
//			File file = jfc.getSelectedFile();
//			ImageIcon bg = null;
//			bg = new ImageIcon(file.getAbsolutePath());
//			bg.setImage(bg.getImage().getScaledInstance(bg.getIconWidth() / int_Shink, bg.getIconHeight() / int_Shink,
//					Image.SCALE_DEFAULT));
//
//			labelPic.removeAll();
//			this.repaint();// 页面刷新
//
//			labelPic.setIcon(bg);
//
//			labelPic.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight()); // 添加图片到frame的第二层 (显示图片位置与图片大�?) //5,55
//
//			drag.setSize(bg.getIconWidth(), bg.getIconHeight());// 鼠标可�?�框的范围要与图片的范围�?�?
//			super.add(labelPic);
//
//			int[] rgb = new int[3];
//			BufferedImage bi = null;
//			adr = file.getAbsolutePath();
//			System.out.println(adr);
//			txt_Numb1.setText(file.getAbsolutePath());
//			try {
//				bi = ImageIO.read(file);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			this.repaint();// 页面刷新
//			img = imgToArray.convertImageToArray(bi);
//			btn_CutFig.setEnabled(true);
		}
		if (arg0.getSource() == btn_CutFig)// 确定矩形框
		{

			Lx = drag.Lx;
			Ly = drag.Ly;
			Rx = drag.Rx;
			Ry = drag.Ry;
			Lx = Lx * int_Shink;
			Ly = Ly * int_Shink;
			Rx = Rx * int_Shink;
			Ry = Ry * int_Shink;
			System.out.println("Lx:" + Lx);
			System.out.println("Ly:" + Ly);
			System.out.println("Rx:" + Rx);
			System.out.println("Ry:" + Ry);
			// 计算截取图形的大小， 长度 ， 宽度
			int PicWidth = 0, PicHeight = 0;
			PicWidth = (Rx - Lx);
			PicHeight = (Ry - Ly);
			img_TiQu = new int[PicHeight][PicWidth];
			System.out.println(img.length);
			for (int i = 0; i < PicHeight; i++) {
				for (int j = 0; j < PicWidth; j++) {
					img_TiQu[i][j] = img[Ly + i][Lx + j];
				}
			}
			imgToArray.writeImageFromArray("D:\\矩形标记块选区.jpg", "jpg", img_TiQu);
			c_OtsuYuZhi Pro_fig = new c_OtsuYuZhi();// otsu 阈值分割
			otsu_test otsu = new otsu_test();
			int T = 0;// 阈值

/////////////////////////////////////*****************转化为灰度图**********************////////////////////////////////////
			/*
			 * BufferedImage image = null; File file = null; try { file = new File(adr1);
			 * image = ImageIO.read(file);
			 * 
			 * int width = image.getWidth(); int height = image.getHeight();
			 * 
			 * for (int j = 0; j < height; j++) { for (int i = 0; i < width; i++) { int p =
			 * image.getRGB(i, j);
			 * 
			 * int a = (p >> 24) & 0xff; int r = (p >> 16) & 0xff; int g = (p >> 8) & 0xff;
			 * int b = p & 0xff; int avg = (r + g + b) / 3; p = (a << 24) | (avg << 16) |
			 * (avg << 8) | avg; image.setRGB(i, j, p); } } file = new File("D:\\gray.jpg");
			 * ImageIO.write(image, "jpg", file); } catch (IOException e) {
			 * System.out.println(e); }
			 */
////////////////////////////*****************^-^*  *^-^*  *^-^*  **********************////////////////////////////////////			
			T = Pro_fig.otsuThresh(img_TiQu, PicWidth, PicHeight);
			// T=otsu.otsuThreshold(rgbArray, PicWidth, PicHeight);//这里�?要灰度图
			int pic[][] = new int[PicHeight][PicWidth];
			// pic=Pro_fig.thSegment(img_TiQu, PicWidth, PicHeight, T);
			pic = Pro_fig.thSegment(img_TiQu, PicWidth, PicHeight, 133);
			imgToArray.writeImageFromArray("D:\\标记块阈值分割后选区.jpg", "jpg", pic);

////////////////////////////*****************^-^*  *^-^*  *^-^*  **********************////////////////////////////////////
			c_GetArea getArea = new c_GetArea();
			irect = getArea.CountPix(pic, PicWidth, PicHeight);
			System.out.println("矩形像素点个数:" + irect);

			btn_CutFig.setEnabled(false);
			btn_SAThings.setEnabled(true);
			btn_CalculateArea.setEnabled(false);
			btn_again.setEnabled(true);
		}
		if (arg0.getSource() == btn_SAThings)// 确定标记�?
		{
			Lx = drag.Lx;
			Ly = drag.Ly;
			Rx = drag.Rx;
			Ry = drag.Ry;
			Lx = Lx * int_Shink;
			Ly = Ly * int_Shink;
			Rx = Rx * int_Shink;
			Ry = Ry * int_Shink;
			System.out.println("Lx:" + Lx);
			System.out.println("Ly:" + Ly);
			System.out.println("Rx:" + Rx);
			System.out.println("Ry:" + Ry);
			// 计算截取图形的大小， 长度 ， 宽度
			int PicWidth = 0, PicHeight = 0;
			PicWidth = (Rx - Lx);
			PicHeight = (Ry - Ly);
			img_TiQu = new int[PicHeight][PicWidth];
			System.out.println(img.length);
			for (int i = 0; i < PicHeight; i++) {
				for (int j = 0; j < PicWidth; j++) {
					img_TiQu[i][j] = img[Ly + i][Lx + j];
				}
			}

			// imgToArray.writeImageFromArray("D:\\3.jpg", "jpg", img_TiQu);
//^_^			print_leaft_yuanshi shuchu=new print_leaft_yuanshi();//把图片输出出来
//^_^			shuchu.getBounds();
			int T = 0;// 阈值
			c_OtsuYuZhi Pro_fig = new c_OtsuYuZhi();// otsu 阈值分割
			// T=Pro_fig.otsuThresh(img_TiQu, PicWidth, PicHeight);//这个不靠谱不如设定固定阈值
			int pic[][] = new int[PicHeight][PicWidth];
			pic = Pro_fig.thSegment(img_TiQu, PicWidth, PicHeight, 134);
			// pic=Pro_fig.thSegment(img_TiQu, PicWidth, PicHeight, T);
			int pic_leaft[][] = new int[PicHeight][PicWidth];// 获取叶子区域,没用
			int img_TiQu_baoliu[][] = new int[PicHeight][PicWidth];// 复制个image_tiqu
		
			int count = 0;
			for (int i = 0; i < PicHeight; i++)//
			{
				for (int j = 0; j < PicWidth; j++) {

					img_TiQu_baoliu[i][j] = img_TiQu[i][j];

				}
			}
			for (int i = 0; i < PicHeight; i++)//
			{
				for (int j = 0; j < PicWidth; j++) {
					if (pic[i][j] != 0)// 判断不是叶子选区
					{
						img_TiQu_baoliu[i][j] = -1;// 原来叶子的除叶子之外的区域为白色，即只保留叶子区域
					}
				}
			}
////////////////////////////*****************^-^*  *^-^*  *^-^*  **********************////////////////////////////////////
//			System.out.println(count+"ceshi shuliang ");
			// System.out.println("2次阈�?:"+T);
////////////////////////////*****************^-^*  *^-^*  *^-^*  **********************////////////////////////////////////
			/**
			 * 以下开始测试文件功能
			 */
			/**
			 * (1)默认构造函数：JFileChooser（）：
			 * (2)参数为currentDirectory:JFileChooser（currentDirectory），参数表示的意思是打开文件选取器时默认显示的文件夹（默认为用户文件夹）
			 * (3)参数为currentDirectoryPath：JFileChooser（currentDirectoryPath），参数表示的意思是打开文件选取器时默认显示的文件夹（默认为用户文件夹）
			 */
			int choose = JOptionPane.showConfirmDialog(null, "yes    or    no ?", "确定一个保存位置?否则将默认保存D盘",
					JOptionPane.YES_NO_OPTION);
			if (choose == 0) {
				/**
				 * 这是第一个选择保存位置
				 */
				JOptionPane.showMessageDialog(null, "接下来选择叶片选区保存位置图?", "叶片选区保存", JOptionPane.INFORMATION_MESSAGE);
				JFileChooser jfc = new JFileChooser();
//jfc.setSelectedFile(new File("测试.jpg"));
				jfc.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));// 设置选择器的过滤器
				/**
				 * void setFileSelectionMode(int mode) 该方法用于设置文件的打开模式，一般有以下三种文件打开模式�?
				 * (1）JFileChooser.FILES_ONLY: 只能选文件； (2) JFileChooser.DIRECTORIES_ONLY:
				 * 只能选文件夹:(3)JFileChooser.FILES_AND_DIRECTORIES: 文件和文件夹都可以�?��??
				 */

				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);// 该方法用于设置文件的打开模式,文件和文件夹都可以选
				int result = jfc.showDialog(new JLabel(), "保存时类型已默认后缀.jpg");
				if (result == JFileChooser.APPROVE_OPTION) {
//如果点击了"确认或保存", 则获取选择的保存路径
					File file1 = jfc.getSelectedFile();
					adr1 = file1.getAbsolutePath() + ".jpg";// 我是天才
					imgToArray.writeImageFromArray(adr1, "jpg", img_TiQu);
//JOptionPane.showMessageDialog(null, "已保存","格式错误",JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(null, "已保存到：" + adr1, "恭喜", JOptionPane.INFORMATION_MESSAGE);
				} else if (result == JFileChooser.CANCEL_OPTION) {
//如果点击了“取消或关闭", 则进行提示
					JOptionPane.showMessageDialog(null, "未确认保存位置，系统将保存到默认的D:\\叶片二值化.jpg", "默认位置保存",
							JOptionPane.WARNING_MESSAGE);
					imgToArray.writeImageFromArray("D:\\叶片二值化.jpg", "jpg", img_TiQu);
					adr1 = "D:\\叶片二值化.jpg";
				}
				/**
				 * 这是第二个选择保存位置
				 */
				JOptionPane.showMessageDialog(null, "接下来选择叶片二值化保存位置?", "叶片选区保存", JOptionPane.INFORMATION_MESSAGE);
				JFileChooser jfc1 = new JFileChooser();
//jfc.setSelectedFile(new File("测试.jpg"));
				jfc1.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));// 设置选择器的过滤�?
				/**
				 void setFileSelectionMode(int mode) 该方法用于设置文件的打开模式，一般有以下三种文件打开模式：
				 * （1）JFileChooser.FILES_ONLY: 只能选文件； （2） JFileChooser.DIRECTORIES_ONLY: 只能选文件夹；
				 * （3）JFileChooser.FILES_AND_DIRECTORIES: 文件和文件夹都可以选。
				 */

				jfc1.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);// 该方法用于设置文件的打开模式,文件和文件夹都可以�??
				int result1 = jfc1.showDialog(new JLabel(), "保存时类型已默认后缀.jpg");
				if (result1 == JFileChooser.APPROVE_OPTION) {
					//如果点击了"确认或保存", 则获取选择的保存路径
					File file2 = jfc1.getSelectedFile();
					adr2 = file2.getAbsolutePath() + ".jpg";// 天才
					imgToArray.writeImageFromArray(adr2, "jpg", pic);
//JOptionPane.showMessageDialog(null, "已保存","格式错误",JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(null, "已保存到：" + adr2, "恭喜", JOptionPane.INFORMATION_MESSAGE);
				} else if (result1 == JFileChooser.CANCEL_OPTION) {
//如果点击了"取消或关闭", 则进行提示
					JOptionPane.showMessageDialog(null, "未确认保存位置，系统将保存到默认的D:\\叶片二值化.jpg", "默认位置保存",
							JOptionPane.WARNING_MESSAGE);
					imgToArray.writeImageFromArray("D:\\叶片二值化.jpg", "jpg", pic);
					adr2 = "D:\\叶片二值化.jpg";
				}
				
				JOptionPane.showMessageDialog(null, "接下来选择叶片保留图保存位置！", "叶片保留图保存?", JOptionPane.INFORMATION_MESSAGE);
				JFileChooser jfc2 = new JFileChooser();
//jfc.setSelectedFile(new File("测试.jpg"));
				jfc2.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));// 设置选择器的过滤�?
				/**
				  * void setFileSelectionMode(int mode) 该方法用于设置文件的打开模式，一般有以下三种文件打开模式：
				 * （1）JFileChooser.FILES_ONLY: 只能选文件； （2） JFileChooser.DIRECTORIES_ONLY: 只能选文件夹；
				 * （3）JFileChooser.FILES_AND_DIRECTORIES: 文件和文件夹都可以选。
				 */

				jfc2.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);// 该方法用于设置文件的打开模式,文件和文件夹都可以�??
				int result2 = jfc2.showDialog(new JLabel(), "保存时类型已默认后缀.jpg");
				if (result2 == JFileChooser.APPROVE_OPTION) {
					//如果点击了"确认或保存", 则获取选择的保存路径
					File file3 = jfc2.getSelectedFile();
					adr3 = file3.getAbsolutePath() + ".jpg";// 我是天才
					imgToArray.writeImageFromArray(adr3, "jpg", img_TiQu_baoliu);
//JOptionPane.showMessageDialog(null, "已保存","格式错误",JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(null, "已保存到：" + adr3, "恭喜", JOptionPane.INFORMATION_MESSAGE);
				} else if (result2 == JFileChooser.CANCEL_OPTION) {
//如果点击了"取消或关闭", 则进行提示
					JOptionPane.showMessageDialog(null, "未确认保存位置，系统将保存到默认的D:\\叶片保留图.jpg", "默认位置保存",
							JOptionPane.WARNING_MESSAGE);
					imgToArray.writeImageFromArray("D:\\叶片保留图.jpg", "jpg", img_TiQu_baoliu);
					adr3 = "D:\\叶片保留图.jpg";
				}
			} else {
//如果点击了"取消或关闭", 则进行提示
				JOptionPane.showMessageDialog(null, "未确认保存位置，系统将保存到默认的D盘下", "默认位置保存", JOptionPane.WARNING_MESSAGE);
				imgToArray.writeImageFromArray("D:\\叶片选区.jpg", "jpg", img_TiQu);
				adr1 = "D:\\叶片选区.jpg";
				imgToArray.writeImageFromArray("D:\\叶片二值化.jpg", "jpg", pic);
				adr2 = "D:\\叶片二值化.jpg";
				imgToArray.writeImageFromArray("D:\\叶片保留.jpg", "jpg", img_TiQu_baoliu);
				adr3 = "D:\\叶片保留.jpg";

			}
////////////////////////////*****************^-^*  *^-^*  *^-^*  **********************////////////////////////////////////	

			// imgToArray.writeImageFromArray("D:\\4.jpg", "jpg", pic);
			// print_leaft_otsu shuchu1=new print_leaft_otsu();//把图片输出出来
			// shuchu1.getBounds();
			// System.out.println("测试到了哈哈+ 哈哈哈哈");
			// imgToArray.writeImageFromArray("D:\\5.jpg", "jpg", img_TiQu);// 除了叶子都是白的
			// print_leaft_shangse shuchu2=new print_leaft_shangse();//把图片输出出来
			// shuchu2.getBounds();
			////////////////////////////////////////////////
			c_GetArea getArea = new c_GetArea();
			iLeaf = getArea.CountPix(pic, PicWidth, PicHeight);

			ReadColorTest readcolor = new ReadColorTest();// 调用计算RGB像素方法
			try {
				averagepixel = readcolor.getImagePixel(adr3);
				 rgb=(averagepixel.toString());//传RGB
				System.out.println("这是测试averagepixel   "+rgb);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
///////////////////////////////////////////
				/**
				 *      主页面显示处理过的叶子
				 */
			    this.repaint();// 页面刷新

				panel=new JPanel();
				panel.setBackground(Color.WHITE );
				panel.setBounds(700,100,500,580);
				panel.setVisible(true);
			    label1=new JLabel(new ImageIcon(adr3));
			    label1.setBounds(0, 0, 60, 60);
			    label1.setVisible(true);
			    label1.setBackground(Color.WHITE );
			    panel.add(label1);
			    this.repaint();// 页面刷新
			    this.add(panel);
			    this.repaint();// 页面刷新
			    System.out.println("执行到这里了");
			    
///////////////////////////////////////////

			
////////////////////////////*****************^-^*  *^-^*  *^-^*  **********************////////////////////////////////////

			System.out.println("叶子像素点?:" + iLeaf);

			btn_CutFig.setEnabled(false);
			btn_SAThings.setEnabled(false);
			input_rec_area.setEnabled(true);
			btn_CalculateArea.setEnabled(true);
		}
		if (arg0.getSource() == input_rec_area) // 写入矩形块的面积
		{
//			 this.repaint();// 页面刷新
//				label1.setBounds(0, 0, 0, 0);
//				 this.repaint();// 页面刷新
//				 label1.enable();
			new input_rec_Area();
			((Component) frame).setVisible(false);
			
		}

		if (arg0.getSource() == btn_CalculateArea) // 计算面积
		{
			
			btn_CalculatePixel.setEnabled(true);
			btn_CalculateHSL.setEnabled(true);
			// String a = txt_juxingkuai.getText();
			String a = input_rec_Area.chuancan();
			int b = Integer.parseInt(a);
			System.out.println("测试矩形快的面积为：" + a + "cm");
			dbArea = (iLeaf * 1.0) / ((irect * 1.0) / b);
			dbArea = (double) Math.round(dbArea * 100) / 100;
			System.out.println("叶子面积:" + dbArea);
			txt_NumbArea.setText("叶子面积:" + dbArea + "cm²");
			Printf_Area printf_area = new Printf_Area("叶片面积窗口");
//			img = null;// 读入图像数组
//			img_TiQu = null;// 提取出来的数组(选区内的)
//			iLeaf = 0;
//			irect = 0;
//			Lx = 0;
//			Ly = 0;
//			Rx = 0;
//			Ry = 0;
			im2Ar = null;
			bg = null;
			// int_Shink=0;
		}
		if (arg0.getSource() == btn_CalculatePixel)// 计算rgb和HSL
		{
			
			new Printf_RGB("叶片RGB均值显示");
			Pixel_average.setText("叶子rgb平均值为:" + averagepixel);
			// btn_CalculateHSL.setEnabled(true);
			btn_CalculateH.setEnabled(true);
			btn_CalculateS.setEnabled(true);
			btn_CalculateL.setEnabled(true);
			btn_original_image.setEnabled(true);
			btn_otsu_image.setEnabled(true);
			btn_treated_image.setEnabled(true);
			// System.out.println(averagepixel.get(0));
			double A1 = ((int) averagepixel.get(0));// 获取RGB�?
			double A2 = ((int) averagepixel.get(1));
			double A3 = ((int) averagepixel.get(2));
			// System.out.println("r "+A1+"g "+A2+"b "+A3);
			double R = ((int) averagepixel.get(0));// 获取RGB�?
			R = R / 255;
			double G = ((int) averagepixel.get(1));
			G = G / 255;
			double B = ((int) averagepixel.get(2));
			B = B / 255;
			
			
			
			/**
			 * 调用rgb转hsl函数但是不好用所以自己写了个直接计算
			 */
			// System.out.println("r "+R+"g "+G+"b "+B);
			double max = ((max = (R > B) ? R : B) > G ? max : G);
			double min = ((min = (R < B) ? R : B) < G ? min : G);
			System.out.println("max" + max + "min" + min);
			if (max == min) {
				h = 0;
			} else if (max == R && G >= B) {
				h = 60 * ((G - B) / (max - min)) + 0;
			} else if (max == R && G < B) {
				h = 60 * ((G - B) / (max - min)) + 360;
			} else if (max == G) {
				h = 60 * ((B - R) / (max - min)) + 120;
			} else if (max == B) {
				h = 60 * ((R - G) / (max - min)) + 240;
			}
			l = (max + min) / 2;

			if (l == 0 || max == min) {
				s = 0;
			} else if (l > 0 && l <= (0.5)) {
				s = (max - min) / (max + min);

			} else if (l > (0.5)) {
				s = (max - min) / (2 - (max + min));

			}

			a = (double) Math.round(h * 100) / 100;// 保留两位小数
			b = (double) Math.round(s * 10000) / 100;
			b1 = (double) Math.round(s * 100) / 100;
			c = (double) Math.round(l * 10000) / 100;
			c1 = (double) Math.round(l * 100) / 100;
			System.out.println("h=" + a + "°" + "    s=" + b + "%" + "      l=" + c + "%");
		    hsl=("h=" + a + "°" + "s=" + b1 + " l=" + c1);
//			img = null;// 读入图像数组
//			img_TiQu = null;// 提取出来的数组(选区内的)
//			iLeaf = 0;
//			irect = 0;
//			Lx = 0;
//			Ly = 0;
//			Rx = 0;
//			Ry = 0;
//			im2Ar = null;
//			bg = null;
			// int_Shink=0;
		}
////////////////////////////*****************^-^*  *^-^*  *^-^*  **********************////////////////////////////////////
		if (arg0.getSource() == btn_CalculateHSL) //计算面积
		{
			new Printf_HSL("RGB转HSL显示");
			txt_hsl.setText("叶子rgb平均值转hsl为：h="+a+"°"+"    s="+b+"°"+"      l="+c+"%");
			img=null;//读入图像数组
			img_TiQu=null;//提取出来的数�?(选区内的)	
			iLeaf=0;
			irect=0;	
			Lx=0;
			Ly=0;
			Rx=0;
			Ry=0;
			im2Ar=null;
			bg=null;
			int_Shink=0;
		}
////////////////////////////*****************^-^*  *^-^*  *^-^*  **********************////////////////////////////////////
		if (arg0.getSource() == btn_CalculateH) // 计算H
		{

			txt_H.setText("叶子rgb平均值转h为：h=" + a + "°");

		}
		if (arg0.getSource() == btn_CalculateS) // 计算S
		{

			txt_S.setText("叶子rgb平均值转h为：s=" + b1);

		}
		if (arg0.getSource() == btn_CalculateL) // 计算L
		{

			txt_L.setText("叶子rgb平均值转L为：l=" + c1);

		}
		if (arg0.getSource() == btn_original_image) // 计算L
		{

			print_leaft_yuanshi shuchu = new print_leaft_yuanshi();// 把图片输出出�?
			shuchu.getBounds();
		}
		if (arg0.getSource() == btn_otsu_image) // 计算L
		{

			print_leaft_otsu shuchu1 = new print_leaft_otsu();// 把图片输出出�?
			shuchu1.getBounds();
		}
		if (arg0.getSource() == btn_treated_image) // 计算L
		{

			print_leaft_shangse shuchu2 = new print_leaft_shangse();// 把图片输出出�?
			shuchu2.getBounds();
		}
		if (arg0.getSource() == btn_again) // 计算L
		{

			mainBody b = new mainBody();
//			btn_CutFig.setEnabled(true);
//			btn_SAThings.setEnabled(true);
//			btn_CalculateArea.setEnabled(true);

		}
		// btn_Home, btn_Refresh, btn_Instruction,btn_Qiehuan,btn_Exit
		if (arg0.getSource() == btn_Home) //
		{
			new home_page();
		}
		if (arg0.getSource() == btn_Refresh) {
			new mainBody();
		}
		if (arg0.getSource() == btn_Instruction) {
			new instruction();
		}
		if (arg0.getSource() == btn_Qiehuan) {
			new main_Login_Register();
		}
		if (arg0.getSource() == btn_Exit) {
			System.exit(0);
		}
		if (arg0.getSource() == fileMenu1) {
			this.repaint();// 页面刷新
			lbl_1.setBounds(0, 0, 0, 0);
			txt_Numb1.setBounds(0, 0, 0, 0);// 显示文件位置
			btn_Openfile.setBounds(0, 0, 0, 0);// 打开文件
			JFileChooser jfc = new JFileChooser();
			jfc.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));// 设置选择器的过滤器
			jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);// 打开文件选择位置
			jfc.showDialog(new JLabel(), "选择");
			File file = jfc.getSelectedFile();
			ImageIcon bg = null;
			bg = new ImageIcon(file.getAbsolutePath());
			bg.setImage(bg.getImage().getScaledInstance(bg.getIconWidth() / int_Shink, bg.getIconHeight() / int_Shink,
					Image.SCALE_DEFAULT));

			labelPic.removeAll();
			this.repaint();// 页面刷新

			labelPic.setIcon(bg);

			labelPic.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight()); // 添加图片到frame的第二层 (显示图片位置与图片大小) //5,55

			drag.setSize(bg.getIconWidth(), bg.getIconHeight());// 鼠标可选框的范围要与图片的范围一致
			
			super.add(labelPic);

			int[] rgb = new int[3];
			BufferedImage bi = null;
			adr = file.getAbsolutePath();
			System.out.println(adr);
			txt_Numb1.setText(file.getAbsolutePath());
			try {
				bi = ImageIO.read(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.repaint();// 页面刷新
			img = imgToArray.convertImageToArray(bi);
			btn_CutFig.setEnabled(true);
		}
	}

	//////////////////////////////////
	/**
	 * 传参
	 * 
	 * @return
	 */
	public static int getA() {
		System.out.println("测试叶子像素值" + iLeaf);
		return iLeaf;

	}

	public static Double getArea() {
		System.out.println("测试叶子面积值" + dbArea);
		return dbArea;

	}
	public static String getRGB() {
		System.out.println("测试叶子RGB值" + rgb);
		//return ;
		return rgb;

	}
	public static String getHSL() {
		System.out.println("测试叶子HSL值" + hsl);
		//return ;
		return hsl;

	}

	/**
	 * 对叶子图片查询地址
	 * 
	 * @return
	 */
	public static String getadr1() {
		System.out.println("测试传adr1值" + adr1);
		return adr1;

	}

	public static String getadr2() {
		System.out.println("测试传adr2值" + adr2);
		return adr2;

	}

	public static String getadr3() {
		System.out.println("测试传adr3值" + adr3);
		return adr3;

	}

	public static void main(String[] args) {
		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());// 当前系统风格
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");//Motif风格，是蓝黑
			// UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());//跨平台的Java风格
			// UIManager.setLookAndFeel("javax.swing.plaf.windows.WindowsLookAndFeel");//windows风格
			// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");//java风格

			// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");//更换为metal风格(默认)�?
		} catch (Exception e) {
			e.printStackTrace();
		}

		new mainBody();

	}
}
