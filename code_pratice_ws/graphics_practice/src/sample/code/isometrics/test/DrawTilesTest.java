package sample.code.isometrics.test;

import common.util.InputReader;
import java.io.InputStream;
import javax.swing.SwingUtilities;
import sample.code.Point;
import sample.code.isometrics.DrawTiles;

public class DrawTilesTest {

	// --------------- TESTING METHODS

	private int rows;
	private int columns;
	private Point[][] points;

	public static void main(String[] s) throws Exception {
		DrawTilesTest drawTilesTest = new DrawTilesTest();
		drawTilesTest.testNormalGrid();
	}

	private void testNormalGrid() throws Exception {
		InputStream inputStream = getClass().getResourceAsStream("data.txt");
		InputReader in = new InputReader(inputStream);
		readData(in);

		inputStream.close();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DrawTiles drawTiles = new DrawTiles(points, rows, columns);
				drawTiles.setVisible(true);
			}
		});
	}

	private void readData(InputReader in) {
		rows = in.nextInt();
		columns = in.nextInt();
		points = new Point[rows][columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				int x = in.nextInt();
				int y = in.nextInt();
				points[i][j] = new Point(x, y);
			}
		}
	}

}
