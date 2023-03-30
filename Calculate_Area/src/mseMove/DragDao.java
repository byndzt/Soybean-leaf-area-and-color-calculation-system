package mseMove;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class DragDao extends JPanel implements MouseMotionListener, MouseListener {
	public int Lx, Ly, Rx, Ry;

	/** 矩形的起点 左上角* */
	private Point rectStart = null;

	/** 矩形的终点 右下角* */
	private Point rectStop = null;

	/** 是否绘制虚线矩形* */
	private boolean dottedTag = true;

	public DragDao() {
		rectStart = new Point(0, 0);
		rectStop = new Point(0, 0);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		/** 矩形的宽度* */
		int w = Math.abs(rectStop.x - rectStart.x);
		/** 矩形的高度* */
		int h = Math.abs(rectStop.y - rectStart.y);

		/** 起点终点的最小值作为起点* */
		Point min = new Point(0, 0);
		min.x = Math.min(rectStop.x, rectStart.x);
		min.y = Math.min(rectStop.y, rectStart.y);
		/** 起点终点的最小值作为终点* */
		Point max = new Point(0, 0);
		max.x = Math.max(rectStop.x, rectStart.x);
		max.y = Math.max(rectStop.y, rectStart.y);
		Stroke dash = new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0.5f, new float[] { 5, 5, },
				0f);
		g2.setStroke(dash);
		/** 如果是绘制虚线矩形* */
		if (dottedTag) {
			g2.setStroke(dash);
			g2.setColor(Color.BLUE);
			g2.drawRect(min.x, min.y, w, h);
		} else {
			g2.setStroke(dash);
			// g2.setStroke(new BasicStroke());
			g2.setColor(Color.BLUE);
			g2.drawRect(min.x, min.y, w, h);
		}
	}

	/** *鼠标拖动 */
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		/** 随着鼠标的拖动 改变终点* */
		rectStop = arg0.getPoint();
		this.repaint();
	}

	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/** 鼠标按键在组件上单击（按下并释放）时调用* */
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/** 鼠标按键在组件上按下时调用 */
	public void mousePressed(MouseEvent arg0) {
		/** 设置可以进行绘制* */
		dottedTag = true;
		/** 记录起始点* */
		rectStart = arg0.getPoint();
		/** 记录起终点* */
		rectStop = rectStart;
		System.out.println("起始点:" + arg0.getPoint());
		Lx = arg0.getPoint().x;
		Ly = arg0.getPoint().y;
	}

	/** 鼠标按钮在组件上释放时调用* */
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		dottedTag = false;
		this.repaint();
		System.out.println("终点：" + arg0.getPoint());
		Rx = arg0.getPoint().x;
		Ry = arg0.getPoint().y;
	}

	/** 鼠标进入到组件上时调用* */
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/** 鼠标离开组件时调用* */
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
