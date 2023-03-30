package c_CalArea;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ReadColorTest {

	private int leaft1;
	private int leaft_sum;
	private int gray;

	/**
	 * 读取一张图片的RGB值
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public ArrayList getImagePixel(String image) throws Exception {
		ArrayList list = new ArrayList();
		int[] rgb = new int[3];
		int number = 0;
		int average = 0;
		int flag = 0;
		int number_sum = 0;
		int sumR = 0;
		int sumG = 0;
		int sumB = 0;
		File file = new File(image);
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		System.out.println("width=" + width + ",height=" + height + ".");
		System.out.println("minx=" + minx + ",miniy=" + miny + ".");
		// c_From leaft=new c_From();
		leaft_sum = mainBody.getA();
		// leaft_sum=leaft.getA();
//		leaft1=leaft.getA();
//		System.out.println("测试"+leaft1);

		for (int i = minx; i < width; i++) {
			for (int j = miny; j < height; j++) {
				int pixel = bi.getRGB(i, j);// 下面三行代码将一个数字转换为RGB数字
				// System.out.println(bi.getRGB(i, j)+"测试");
				/**
				 * 0xff，即二进制的1序列比如11111111，是滤码。 (0xABCDEF &0xFF0000 )>>16 (0xABCDEF &0xFF00)>>8
				 * 0xABCDEF &0xFF 分别获得原数据的AB、CD、EF不同位置的数据 分别对应RGB三色中的红色R为AB，绿色G为CD，蓝色B为EF
				 */
				rgb[0] = (pixel & 0xff0000) >> 16;// R
				rgb[1] = (pixel & 0xff00) >> 8;// G
				rgb[2] = (pixel & 0xff);// B
			     gray = (int)(rgb[0] * 0.299 + rgb[1] * 0.587 + rgb[2] * 0.114);
			     System.out.println("以下为测试：");
			     System.out.println("R="+"  "+rgb[0]+"G="+"  "+rgb[1]+"B="+"  "+rgb[2]);
			     System.out.println("灰度值为: "+gray);
			     

				if (rgb[0] == 255 && rgb[1] == 255 && rgb[2] == 255) {
					flag = 1;
					rgb[0] = 0;
					rgb[1] = 0;
					rgb[2] = 0;
					// System.out.println("����仰�Ĳ���");
				}


				sumR = sumR + rgb[0];
				sumG = sumG + rgb[1];
				sumB = sumB + rgb[2];
				// System.out.println("i=" + i + ",j=" + j + ":(" + rgb[0] + ","
				// + rgb[1] + "," + rgb[2] + ")");
			}
		}
		sumR = sumR / leaft_sum;
		sumG = sumG / leaft_sum;
		sumB = sumB / leaft_sum;
		average = (sumR + sumG + sumB) / 3;
		System.out.println("R：      " + sumR);
		System.out.println("G：      " + sumG);
		System.out.println("B：      " + sumB);
		System.out.println("R:  " + sumR + "  G    " + sumG + "  B    " + sumB);
		// System.out.println("叶子像素个数"+number);
				// System.out.println("总像素个数为:"+number_sum);
				// System.out.println("总平均值为："+average);
		list.add(sumG);
		list.add(sumB);
		return list;
	}

	/**
	 * 返回屏幕色彩值
	 * 
	 * @param x
	 * @param y
	 * @return
	 * @throws AWTException
	 */
	public int getScreenPixel(int x, int y) throws AWTException { // 函数返回值为颜色的RGB值
		Robot rb = null; // java.awt.image包中的类，可以用来抓取屏幕，即截屏。
		rb = new Robot();
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Dimension di = tk.getScreenSize(); 
		System.out.println(di.width);
		System.out.println(di.height);
		Rectangle rec = new Rectangle(0, 0, di.width, di.height);
		BufferedImage bi = rb.createScreenCapture(rec);
		int pixelColor = bi.getRGB(x, y);
		return 16777216 + pixelColor; // pixelColor的值为负，经过实践得出：加上颜色最大值就是实际颜色值。
	}


}