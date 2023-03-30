package c_ImgProcess;

import java.awt.image.ColorModel;

public class c_OtsuYuZhi {
	public int otsuThresh(int[] pix, int iw, int ih) {
		ColorModel cm = ColorModel.getRGBdefault();
		int wh = iw * ih;
		int[][] inIm = new int[iw][ih];
		int i, j, t;
		int L = 256;
		double[] p = new double[L];
		for (j = 0; j < ih; j++)
			for (i = 0; i < iw; i++)
				inIm[i][j] = pix[i + j * iw] & 0xff;
		for (i = 0; i < L; i++)
			p[i] = 0;
		 //计算各灰度出现次数  
		for (j = 0; j < ih; j++)
			for (i = 0; i < iw; i++)
				p[inIm[i][j]]++;
		 //计算各灰度级出现概率  
		for (int m = 0; m < L; m++)
			p[m] = p[m] / wh;
		double[] sigma = new double[L];
		for (t = 0; t < L; t++) {
			double w0 = 0;
			for (int m = 0; m < t + 1; m++)
				w0 += p[m];
			double w1 = 1 - w0;

			double u0 = 0;
			for (int m = 0; m < t + 1; m++)
				u0 += m * p[m] / w0;

			double u1 = 0;
			for (int m = t; m < L; m++)
				u1 += m * p[m] / w1;

			sigma[t] = w0 * w1 * (u0 - u1) * (u0 - u1);
		}
		double max = 0.0;
		int T = 0;
		for (i = 0; i < L - 1; i++) {
			if (max < sigma[i]) {
				max = sigma[i];
				T = i;
			}
		}
		return T;
	}

	public int otsuThresh(int[][] pix, int iw, int ih)// ����֮ǰд������ǻ�ȡ��ֵ
	{
		int wh = iw * ih;
		int[][] inIm = new int[ih][iw];
		int i, j, t;
		int L = 256;
		double[] p = new double[L];
		for (i = 0; i < iw; i++)
			for (j = 0; j < ih; j++) {
				inIm[j][i] = pix[j][i] & 0xff;
				// System.out.println(inIm[j][i]+"测试");
			}

		for (i = 0; i < L; i++)
			p[i] = 0;
		 //计算各灰度出现次数  
		for (i = 0; i < iw; i++)
			for (j = 0; j < ih; j++)
				p[inIm[j][i]]++;

		   //计算各灰度级出现概率
		for (int m = 0; m < L; m++)
			p[m] = p[m] / wh;
		double[] sigma = new double[L];
		for (t = 0; t < L; t++) {
			double w0 = 0;
			for (int m = 0; m < t + 1; m++)
				w0 += p[m];
			double w1 = 1 - w0;

			double u0 = 0;
			for (int m = 0; m < t + 1; m++)
				u0 += m * p[m] / w0;

			double u1 = 0;
			for (int m = t; m < L; m++)
				u1 += m * p[m] / w1;

			sigma[t] = w0 * w1 * (u0 - u1) * (u0 - u1);
		}
		double max = 0.0;
		int T = 0;
		for (i = 0; i < L - 1; i++) {
			if (max < sigma[i]) {
				max = sigma[i];
				T = i;
			}
		}
		return T;
	}

	//图像序列pix阈值分割    
	public int[] thSegment(int[] pix, int iw, int ih, int th) {
		int[] im = new int[iw * ih];
		int t;
		for (int i = 0; i < iw * ih; i++) {
			t = pix[i] & 0xff;

			if (t > th)
				im[i] = (255 << 24) | (255 << 16) | (255 << 8) | 255;//背景色  
			else
				im[i] = (255 << 24) | (0 << 16) | (0 << 8) | 0;    //前景色为  
		}
		return im;
	}

	//图像序列pix阈值分割 
	public int[][] thSegment(int[][] pix, int iw, int ih, int th) {
		int[][] im = new int[ih][iw];
		int t;
		for (int i = 1; i < iw; i++) {
			for (int j = 1; j < ih; j++) {
				t = pix[j][i] & 0xff;
				if (t > th) {
					im[j][i] = -1;// (255<<24)|(255<<16)|(255<<8)|255;//����ɫ��ɫ
				} else {
					im[j][i] = 0;// (255<<24)|(0<<16)|(0<<8)|0; //ǰ��ɫΪ��ɫ
				}
			}
		}
		return im;
	}
}
