package sample.code.isometrics;

import sample.code.Point;

/**
 * Class copied from freelords....for testing isometric / diametric conversion
 *
 * Class that can convert between map and screen coordinates.
 *
 * Below will be the logic to do the conversion.
 *
 * Map:
 *    The original map would comprise of tiles (as usual), the origin at the top-left of the map.
 *    The y-coordinate is increasing in down direction, the x-coordinate increasing in the right direction.
 *
 * Isometric(Diametric) Map:
 *    The isometric map is formed by rotating the map, clockwise 45 degrees such that it rests on the
 *    bottom right of the original map.
 *    Thus now the isometric map has the origin at the top point.
 *    Also the map is squashed vertically by half (hence the name Diametric), keeping the original width intact,
 *    thus the new height is half of original rotated map.
 *
 * Screen / Sliding window:
 *    The map would be shown on the screen finally using a sliding rectangular window which will move over the isometric map.
 *    The origin of this window will on top-left (as is usually for GUI).
 *    The screen-y will be increasing in the downward direction and screen-x will be increasing in the right direction.
 *
 *    The relative movement of this sliding window over the isometric map, would be governed using an screen-offset.
 *    The screen-offset is defined as offset between the origin of sliding-window and origin of isometric map.
 *
 *    The screen-offset is zero when origin of both sliding-window and isometric map coincides with each other.
 *    The offset-X increases when the sliding window moves to the right and decreases when sliding window moves to the
 *    right of isometric map.
 *    The offset-Y increases when the sliding window moves to the bottom of isometric map.
 *
 *    This offset will be defined as offset-position --> Position(offset-X, offset-Y)
 *
 *    At any point of time, the screen will show the part of the isometric map as covered by this sliding window.
 *
 */
public class CoordinateConverter {

	private int screenHeight;
	private int screenWidth;

	// assuming unit size for the tile.
	private int TILE_SIZE = 1;
	private double ISO_TILE_WIDTH = TILE_SIZE * Math.sqrt(2);
	private double ISO_TILE_HEIGHT = TILE_SIZE /(Math.sqrt(2) * 2.0);

	// this factor comes from fact that the width remains same, thus isoTileWidth size varies.
	private double MULTIPLY_FACTOR1 = Math.sqrt(5)/Math.sqrt(8);
	// when factor is one, the map looks more like isometric than the diametric.
	private double MULTIPLY_FACTOR2 = 1.0;

	private Point offSetPoint;


	public CoordinateConverter(int screenWidth, int screenHeight) {
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		setDefaultOffset();
	}

	// default offset --> considering the screen covers the map entirely.
	private void setDefaultOffset() {
		offSetPoint = new Point(-screenWidth/2, 0);
	}

	public Point normalToIsometric(Point normalPoint) {
		int normX = normalPoint.x;
		int normY = normalPoint.y;

		double isoX = MULTIPLY_FACTOR1 * normX;
		double isoY = MULTIPLY_FACTOR1 * normY;

		int screenX = (int) ((isoX - isoY) * ISO_TILE_WIDTH - offSetPoint.x);
		int screenY = (int) ((isoX + isoY) * ISO_TILE_HEIGHT - offSetPoint.y);

		Point isometricPoint = new Point(screenX, screenY);

		return isometricPoint;
	}

}
