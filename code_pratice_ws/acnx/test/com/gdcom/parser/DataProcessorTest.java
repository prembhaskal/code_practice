package com.gdcom.parser;

import com.gdcom.marshal.NodeMarshaller;
import com.gdcom.marshal.XmlMarshaller;
import com.gdcom.rawdata.RawDataConverter;
import com.gdcom.rawdata.SpaceTokenizer;
import com.gdcom.tree.FixAttributes;
import com.gdcom.tree.TreeCreator;
import java.io.*;
import org.junit.After;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

// TODO tests here should just test that the treeCreator, fixAttributes are called with proper values, no comparing of xml should be done here.
// TODO write a separate integration test to do that.
public class DataProcessorTest {

	RawDataConverter rawDataConverter = new RawDataConverter(new SpaceTokenizer());
	TreeCreator treeCreator = new TreeCreator();
	FixAttributes fixAttributes = new FixAttributes();
	XmlMarshaller xmlMarshaller = new NodeMarshaller();

	DataProcessor dataProcessor = new DataProcessor(rawDataConverter, treeCreator, fixAttributes, xmlMarshaller);

	File inputFile1 = new File(getClass().getResource("files/parsed_input_1.txt").getFile());
	File expectedOutputFile1 = new File(getClass().getResource("files/expected_output_1.xml").getFile());

	File inputFile2 = new File(getClass().getResource("files/parsed_input_2.txt").getFile());
	File expectedOutputFile2 = new File(getClass().getResource("files/expected_output_2.xml").getFile());

	File actualOutputFile = new File("processed_output_file.xml");

	@After
	public void cleanUp() {
		actualOutputFile.delete();
	}

	@Test
	public void testDataIsParsedCorrectly1() throws Exception {
		BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFile1));
		PrintWriter out = new PrintWriter(actualOutputFile);
		dataProcessor.processData(inputFileReader, out);

		out.close();
		inputFileReader.close();

		assertTrue("encoded xml is not proper", compareFiles(expectedOutputFile1, actualOutputFile));
	}

	@Test
	public void testDataIsParsedCorrectly2() throws Exception {
		BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFile2));
		PrintWriter out = new PrintWriter(actualOutputFile);
		dataProcessor.processData(inputFileReader, out);

		out.close();
		inputFileReader.close();

		assertTrue("encoded xml is not proper", compareFiles(expectedOutputFile2, actualOutputFile));
	}


	private boolean compareFiles(File expectedFile, File actualFile) throws IOException {
		BufferedReader expFileReader = new BufferedReader(new FileReader(expectedFile));
		BufferedReader actFileReader = new BufferedReader(new FileReader(actualFile));

		boolean fileEqual = true;

		char[] chars1 = new char[1024];
		char[] chars2 = new char[1024];

		while (true) {
			int len1 = expFileReader.read(chars1);
			int len2 = actFileReader.read(chars2);

			if (len1 != len2) {
				fileEqual = false;
				break;
			}

			if (len1 < 0)
				break;

			// compare the read characters.
			for (int i = 0; i < chars1.length; i++) {
				if (chars1[i] != chars2[i]) {
					fileEqual = false;
					break;
				}
			}
		}

		actFileReader.close();
		expFileReader.close();

		return fileEqual;
	}
}
