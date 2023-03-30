package c_CalArea;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class MousePath extends JPanel {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				final MousePath mp = new MousePath();
				JFrame f = new JFrame(mp.getClass().getSimpleName());
				f.setSize(800, 600);
				f.getContentPane().add(mp, BorderLayout.CENTER);
				f.addMouseMotionListener(new MouseMotionListener() {

					@Override
					public void mouseDragged(MouseEvent arg0) {
					}

					@Override
					public void mouseMoved(MouseEvent arg0) {

						mp.add(arg0.getX(), arg0.getY());
						mp.repaint();
					}
				});
				f.setLocationRelativeTo(null);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setVisible(true);
			}
		});
	}

	private List<Point> path = new LinkedList<Point>();

	public void add(int x, int y) {

		path.add(new Point(x, y));
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D gg = (Graphics2D) g;

		if (path.size() > 1) {

			Point prev = path.get(0);
			for (Point p : path) {

				g.drawLine(prev.x, prev.y, p.x, p.y);
				prev = p;
			}
		}
	}
}
