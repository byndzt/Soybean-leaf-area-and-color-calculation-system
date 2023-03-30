
package Calcute_Area_1;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
*获取一张二值化图片
* @param bufferImg
* @return
*/
public class otsu_test {
	public static BufferedImage binaryImage(BufferedImage bufferImg) {
		int height = bufferImg.getHeight();
		int width = bufferImg.getWidth();
		int rgb = bufferImg.getRGB(0, 0);
		int rgbArray[][] = new int[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				rgbArray[i][j] = getImageRgb(bufferImg.getRGB(i, j));
			}
		}

		int threshold = otsuThreshold(rgbArray, height, width);

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);//  构造一个类型为预定义图像类型之一的 BufferedImage，TYPE_BYTE_BINARY（表示一个不透明的以字节打包的 1、2 或 4 位图像。）

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (rgbArray[i][j] > threshold) {
					int black = new Color(255, 255, 255).getRGB();
					bufferedImage.setRGB(i, j, black);
				} else {
					int white = new Color(0, 0, 0).getRGB();
					bufferedImage.setRGB(i, j, white);
				}
			}
		}

		try {
			ImageIO.write(bufferedImage, "jpg", new File("D:" + File.separator + "new123.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bufferedImage;
	}

	/**
	 * /* ��ȡ��ĻҶ�ֵ
	 * 
	 * @param i
	 * @return
	 */
	public static int getImageRgb(int i) {
		String argb = Integer.toHexString(i); // 将十进制的颜色值转为十六进制
		// argb分别代表透明,红,绿,蓝 分别占16进制2位
		int r = (int) (Integer.parseInt(argb.substring(2, 4), 16) * 1.1 + 30);// �������Ϊʹ�ý���
		int g = (int) (Integer.parseInt(argb.substring(4, 6), 16) * 1.1 + 30);
		int b = (int) (Integer.parseInt(argb.substring(6, 8), 16) * 1.1 + 30);
		if (r > 255) {
			r = 255;
		}
		if (g > 255) {
			g = 255;
		}
		if (b > 255) {
			b = 255;
		}
		int result = (int) Math.pow((Math.pow(r, 2.2) * 0.2973 + Math.pow(g, 2.2) * 0.6274 + Math.pow(b, 2.2) * 0.0753),
				1 / 2.2);

		return result;
	}

    //自己加周围8个灰度值再除以9，算出其相对灰度值ֵ
	public static int getGray(int gray[][], int x, int y, int w, int h) {
		int rs = gray[x][y] + (x == 0 ? 255 : gray[x - 1][y]) + (x == 0 || y == 0 ? 255 : gray[x - 1][y - 1])
				+ (x == 0 || y == h - 1 ? 255 : gray[x - 1][y + 1]) + (y == 0 ? 255 : gray[x][y - 1])
				+ (y == h - 1 ? 255 : gray[x][y + 1]) + (x == w - 1 ? 255 : gray[x + 1][y])
				+ (x == w - 1 || y == 0 ? 255 : gray[x + 1][y - 1])
				+ (x == w - 1 || y == h - 1 ? 255 : gray[x + 1][y + 1]);
		return rs / 9;
	}

	 /**
     * 通过OTSU大津算法计算分割阈值
     * @param rgbArray
     * @return
     */
	public static int otsuThreshold(int rgbArray[][], int height, int width) {
		final int GrayScale = 256;
	    // 每个灰度像素的数量
		int[] pixelCount = new int[GrayScale];
		 // 每个像素点所占的比例
		float[] pixelPro = new float[GrayScale];
		  // 像素点的数量
		int pixelSum = height * width;
	     // 分割的阈值点
		int threshold = 0;

		//=========================统计灰度级中每个像素在整幅图像中的个数===============
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				pixelCount[rgbArray[i][j]]++;
			}
		}

		/// ========================计算每个像素在整幅图像中的比例=========================
		float maxPro; //
		int kk = 0;
		for (int i = 0; i < GrayScale; i++) {
			pixelPro[i] = (float) pixelCount[i] / pixelSum;
		}
		// ===========================遍历灰度级[0,255]======================================
        // backgroundRatio为背景像素点占整幅图像的比例
        // prospectRatio为前景像素点占整幅图像的比例
        // backGrayAverage为背景像素的平均灰度
        // proGrayAverage为前景像素的平均灰度
        // grayAverage为整幅图像的平均灰度
        // 公式:g = backgroundRatio * pow((backGrayAverage - grayAverage), 2) + prospectRatio * pow((proGrayAverage - grayAverage), 2)
        // deltaTmp，deltaMax记录中间值与结果最大值
		float backgroundRatio, prospectRatio, u0tmp, u1tmp, backGrayAverage, proGrayAverage, grayAverage;
		double deltaTmp, deltaMax = 0;
		for (int i = 0; i < GrayScale; i++) // i作为阈值ֵ
		{  // 初始化
			backgroundRatio = prospectRatio = u0tmp = u1tmp = backGrayAverage = proGrayAverage = grayAverage = 0;
			deltaTmp = 0;
			for (int j = 0; j < GrayScale; j++) {
				if (j <= i) //背景部分
				{
					backgroundRatio += pixelPro[j];
					u0tmp += j * pixelPro[j]; // u0tmp=像素的灰度*像素占的比例
				} else //前景部分
				{
					prospectRatio += pixelPro[j];
					u1tmp += j * pixelPro[j];
				}
			}
			backGrayAverage = u0tmp / backgroundRatio;
			proGrayAverage = u1tmp / prospectRatio;
			grayAverage = u0tmp + u1tmp;
			deltaTmp = backgroundRatio * pow((backGrayAverage - grayAverage), 2)
					+ prospectRatio * pow((proGrayAverage - grayAverage), 2);
			if (deltaTmp > deltaMax) {
				deltaMax = deltaTmp;
				threshold = i;
			}
		}

		return threshold;
	}

	private static float pow(float f, int i) {
		// TODO Auto-generated method stub
		return 0;
	}

}
