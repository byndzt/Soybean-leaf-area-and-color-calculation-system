package c_ImgProcess;

public class c_GetArea {
	public double CalculateArea(int[][] pix, int iw, int ih) {
		for (int i = 0; i < iw; i++) {
			for (int j = 0; j < ih; j++) {
				pix[j][i] = pix[j][i] & 0xFF;
			}
		}
		double db_Area = 0;
		int iRect = 0, iLeaf = 0, iTemp = 0;
		int iTotalPix = 0;
		int iSw = iw / 2;
		int iSh = ih / 2;
		int[][] pix1 = new int[iSh][iSw];
		int[][] pix2 = new int[iSh][iSw];
		int[][] pix3 = new int[iSh][iSw];
		int[][] pix4 = new int[iSh][iSw];
		for (int i = 0; i < iSw; i++) {
			for (int j = 0; j < iSh; j++) {
				pix1[j][i] = pix[j][i];
				pix2[j][i] = pix[j][iSw + i];
				pix3[j][i] = pix[iSh + j][i];
				pix4[j][i] = pix[iSh + j][iSw + i];
			}
		}
		for (int i = 0; i < iw; i++) {
			for (int j = 0; j < ih; j++) {
				if (pix[j][i] == 0) {
					iTotalPix = iTotalPix + 1;
				}
			}
		}
		int[] iRectA = new int[4];
		iRectA[0] = FindRect(pix1, iSw, iSh, 1);
		iRectA[1] = FindRect(pix2, iSw, iSh, 2);
		iRectA[2] = FindRect(pix3, iSw, iSh, 3);
		iRectA[3] = FindRect(pix4, iSw, iSh, 4);
		int iOrder[] = new int[iRectA.length];
		for (int i = 0; i < 4; i++) {
			iOrder[i] = iRectA[i];
		}
		int temp = 0;
		for (int i = 0; i < 4 - 1; i++) {
			for (int j = 0; j < 4 - i - 1; j++) {
				if (iRectA[j] < iRectA[j + 1]) {
					temp = iRectA[j];
					iRectA[j] = iRectA[j + 1];
					iRectA[j + 1] = temp;
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			System.out.println("测试" + iRectA[i]);
		}

		iLeaf = iTotalPix - iRectA[0] - iRectA[1] - iRectA[2] - iRectA[3];
		db_Area = (iLeaf * 1.0) / (iRectA[0] * 1.0);
		return db_Area;
	}

	public int FindRect(int[][] Area, int iw, int ih, int iNumber)// iNumber��¼ͼƬ�ķ�λ ����1 ����Ϊ2 ����Ϊ3 ����Ϊ4
	{
		int iRectPix = 0;
		int[][] iOriArray = Area;
		int[][] iRule = new int[ih][1];
		int iIndex = 0;
		int[] count = new int[iw];
		for (int i = 0; i < ih; i++) {
			iRule[i][0] = 0;
		}
		if (iNumber == 1 || iNumber == 3) {
			for (int i = 0; i < iw; i++) {
				for (int j = 0; j < ih; j++) {
					if (Area[j][i] == iRule[j][0]) {
						iIndex = iIndex + 1;
					}
				}
				count[i] = iIndex;
				iIndex = 0;
			}
		}
		if (iNumber == 2 || iNumber == 4) {
			for (int i = iw - 1; i > 0; i--) {
				for (int j = 0; j < ih; j++) {
					if (Area[j][i] == iRule[j][0]) {
						iIndex = iIndex + 1;
					}
				}
				count[i] = iIndex;
				iIndex = 0;
			}
		}
		double dbk = 0;
		int iSameIndex = 0, iMarkI_start = 0, iMarkI_end = 0;
		for (int i = 0; i < (count.length) / 2 - 1; i++) {
			System.out.println(count[i]);
			dbk = (count[i + 1] - count[i]) / ((i + 1) - i);
			if (dbk != 0) {
				if (iMarkI_start == 0) {
					iMarkI_start = i;
				}
			} else {
				iMarkI_end = i;
			}
		}
		int iSum = 0, iStep = 0;
		for (int i = iMarkI_start; i < iMarkI_end; i++) {
			iSum = iSum + count[i];
			iStep = iStep + 1;
		}
		double dbAverage = 0;

		dbAverage = iSum / iStep;
		System.out.println("相同元素数:"+iSameIndex);
		System.out.println("图片标记:"+iNumber);
		System.out.println("裁剪图片的宽:"+iw);
		System.out.println("裁剪图片的高:"+ih);
		System.out.println("Rect（起始）:"+iMarkI_start);
		System.out.println("Rect（结束）:"+iMarkI_end);

		iRectPix = (int) (dbAverage * (iMarkI_end - iMarkI_start));
		System.out.println("rect面积:" + iRectPix);
		return iRectPix;
	}

	// ��¼���ص�
	public int CountPix(int[][] pix, int iw, int ih) {
		int iCountPix = 0;
		for (int i = 0; i < iw; i++) {
			for (int j = 0; j < ih; j++) {
				pix[j][i] = pix[j][i] & 0xFF;
			}
		}
		for (int i = 0; i < iw; i++) {
			for (int j = 0; j < ih; j++) {
				if (pix[j][i] == 0)//前景色为黑色
				{

					iCountPix = iCountPix + 1;
				}
				//System.out.println("前景色测试："+pix[j][i]);
			}
		}
		return iCountPix;
	}

}
