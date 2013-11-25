package test_generator;

import common.util.InputReader;
import java.io.PrintWriter;

public class MazeLevelGenerator {

	public void generateLevel(InputReader in, PrintWriter out) {
		int level = in.nextInt();
		int rows = in.nextInt();
		int columns = in.nextInt();

		char[][] staticArray = new char[rows][columns];
		char[][] movableArray = new char[rows][columns];

		for (int i = 0; i < rows; i++) {
			String line = in.next();
			staticArray[i] = line.toCharArray();
		}

		for (int i = 0; i < rows; i++) {
			String line = in.next();
			movableArray[i] = line.toCharArray();
		}

		out.println("// -------- Maze " + level + " ---------------------");
		String univDimensionVar = "universalDimension";
		String dimensionVar = "dimension" + level;
		String staticVar = "level" + level + "Static";
		String movableVar = "level" + level + "Movable";
		String rawMazeVar = "rawMaze" + level;
		out.println("var " + dimensionVar + " = new MazeDimension(" + columns + ", " + rows + ");");

		// for static items.
		String str;
		out.println("var " + staticVar + " = [");
		for (int i = 0; i < rows; i++) {
			str = "\t[";
			for (int j = 0; j < columns; j++) {
				str += "'" + staticArray[i][j] + "',";
			}
			str = str.substring(0, str.length()-1); // remove the last comma
			str += "]";
			if (i < rows-1)
				str += ",";

			out.println(str);
		}

		out.println("];");

		out.println();

		out.println("var " + movableVar + " = [");
		// for movable items.
		for (int i = 0; i < rows; i++) {
			str = "\t[";
			for (int j = 0; j < columns; j++) {
				str += "'" + movableArray[i][j] + "',";
			}
			str = str.substring(0, str.length()-1); // remove the last comma
			str += "]";
			if (i < rows-1)
				str += ",";

			out.println(str);
		}

		out.println("];");
		out.println();

		out.println(staticVar + " = convertToUniversal(" + dimensionVar + ", " + staticVar + ");");
		out.println(movableVar + " = convertToUniversal(" + dimensionVar + ", " + movableVar + ");");
		out.println("var " + rawMazeVar + " = new RawMaze(" + univDimensionVar + ", " + staticVar + ", " + movableVar + ");");
		out.println("rawMazes.push(" + rawMazeVar + ");");

	}
}
