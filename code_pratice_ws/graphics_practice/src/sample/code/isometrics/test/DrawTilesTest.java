package sample.code.isometrics.test;

import common.util.InputReader;
import java.io.InputStream;
import javax.swing.SwingUtilities;
import sample.code.Point;
import sample.code.isometrics.CoordinateConverter;
import sample.code.isometrics.DrawTiles;

public class DrawTilesTest {

	// --------------- TESTING METHODS

	private int rows;
	private int columns;
	private Point[][] points;

	public static void main(String[] s) throws Exception {
		DrawTilesTest drawTilesTest = new DrawTilesTest();
//		drawTilesTest.testNormalGrid();
//		drawTilesTest.testNormalGrid2();
		drawTilesTest.testIsometricGrid();
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

	private void testNormalGrid2() {
		// create appropriate rows and columns
		rows = 6;
		columns = 6;
		createPoints();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DrawTiles drawTiles = new DrawTiles(points, rows, columns);
				drawTiles.setVisible(true);
			}
		});
	}

	private void createPoints() {
		int factor = 50;
		points = new Point[rows][columns];
		for (int i = 0; i < rows; i++) { // x points
			for (int j = 0; j < columns; j++) { // y points
				int x = factor * (i+1);
				int y = factor * (j+1);
				points[i][j] = new Point(x, y);
			}
		}

	}

	private void testIsometricGrid() {
		rows = columns = 5;

		int screenWidth = 640;
		int screenHeight = 480;

		createPoints();
		CoordinateConverter coordinateConverter = new CoordinateConverter(screenWidth, screenHeight);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Point isoPoint = coordinateConverter.normalToIsometric(points[i][j]);
				System.out.println("normal point " + points[i][j].x + " -- " + points[i][j].y);
				points[i][j] = isoPoint;
				System.out.println("iso point " + points[i][j].x + " -- " + points[i][j].y);
			}
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DrawTiles drawTiles = new DrawTiles(points, rows, columns);
				drawTiles.setVisible(true);
			}
		});
	}



}
