package com.twr.query;

import com.twr.GalaxyConverter;
import com.twr.GalaxyConverterBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.junit.Test;

public class DataProcessorTest {

	private String ROMAN_TYPE = "ROMAN";

	@Test
	public void romanInputParserIntegrationTest() throws Exception {

		// read the input from a text file.
		File inputFile = new File("files/input.txt");
		InputStream inputStream = getClass().getResourceAsStream(inputFile.getPath());
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		// create the DataProcessor
		GalaxyConverter galaxyConverter = new GalaxyConverterBuilder().getConverter(ROMAN_TYPE);
		DataProcessor dataProcessor = new DataProcessor(galaxyConverter);

		System.out.println("\n************************* INTEGRATION TEST STARTED *********************\n");

		String input;
		while ((input = reader.readLine()) != null) {
			dataProcessor.processInput(input);
		}

		System.out.println("\n************************* INTEGRATION TEST COMPLETED ********************\n");

	}
}
