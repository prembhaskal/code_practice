package topcoder.srm.s566iv2;


// SRM 566 DIV2 question1...PASSED SYSTEM TEST
public class PenguinTiles {

	public int minMoves(String[] tiles){
		int moves = 0;

		int length = tiles.length;

		String tile = "";

		if (length <= 0)
			return 0;

		tile = tiles[0];

		int stringLength = tile.length();

		if (tiles[length-1].charAt(stringLength-1)=='.')
			return 0;

		if (tiles[length-1].contains("."))
			return 1;


		for (int i =0 ;i <length;i++) {
			tile = tiles[i];
			if (tiles[i].charAt(stringLength-1)=='.')
				return 1;
		}

		return 2;
	}
}
