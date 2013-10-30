package sample.code.zetcode;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DrawLine extends JFrame {

	public DrawLine() {
		initUI();
	}

	private void initUI() {

		setTitle("Lines");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(new Surface());

		setSize(350, 250);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				DrawLine drawLine = new DrawLine();
				drawLine.setVisible(true);
			}
		});
	}

	private class Surface extends JPanel {

		private void doDrawing(Graphics g) {

			Graphics2D g2d = (Graphics2D) g;

			g2d.drawLine(30, 30, 200, 30);
			g2d.drawLine(200, 30, 30, 200);
			g2d.drawLine(30, 200, 200, 200);
			g2d.drawLine(200, 200, 30, 30);

		}

		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			doDrawing(g);
		}
	}
}




