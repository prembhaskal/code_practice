package sample.code.isometrics;

import common.util.InputReader;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.InputStream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.omg.CORBA.PUBLIC_MEMBER;
import sample.code.Point;

public class DrawTiles extends JFrame {

	private Point[][] coordinates;
	private int rows;
	private int columns;

	public DrawTiles(Point[][] coordinates, int rows, int columns) {
		this.coordinates = coordinates;
		this.rows = rows;
		this.columns = columns;
		initUI();
	}

	private void initUI() {

		setTitle("Lines");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(new Surface(coordinates, rows, columns));

		setSize(350, 250);
		setLocationRelativeTo(null);
	}

	private class Surface extends JPanel {
		/**
		 * co-ordinates should be the given format
		 * see co-ordinates are used because this will be used/tested for transformation later.
		 * each row gives points(x,y) for that row.
		 */
		private Point[][] coordinates;

		private int rows;
		private int cols;

		public Surface (Point [][] coordinates, int rows, int cols) {
			this.coordinates = coordinates;
			this.rows = rows;
			this.cols = cols;
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D graphics2D = (Graphics2D)g;

			// DRAW one axis.
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols - 1; j++) {
					Point from = coordinates[i][j];
					Point to = coordinates[i][j+1];
					graphics2D.drawLine(from.x, from.y, to.x, to.y);
				}
			}

			// DRAW other axis.
			for (int i = 0; i < rows - 1; i++) {
				for (int j = 0; j < cols; j++) {
					Point from = coordinates[i][j];
					Point to = coordinates[i+1][j];
					graphics2D.drawLine(from.x, from.y, to.x, to.y);
				}
			}
		}
	}
}
