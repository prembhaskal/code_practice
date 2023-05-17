package practice.algorithm.test;

import common.util.InputReader;
import org.junit.Test;
import practice.algorithm.AStarGraphSearch;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

public class AStarGraphSearchTest {

	@Test
	public void testGraphSearch1() throws Exception {
		InputStream inputStream = getClass().getResourceAsStream("files/astar_graphsearch1.txt");
		InputReader inputReader = new InputReader(inputStream);

		AStarGraphSearch starGraphSearch = new AStarGraphSearch();
		starGraphSearch.isDebug = true;
		starGraphSearch.solve(inputReader);

		inputReader.close();
	}

}