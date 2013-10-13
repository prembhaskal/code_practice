package com.gdcom.parser;

import com.gdcom.marshal.NodeMarshaller;
import com.gdcom.marshal.XmlMarshaller;
import com.gdcom.rawdata.RawDataConverter;
import com.gdcom.rawdata.SpaceTokenizer;
import com.gdcom.tree.FixAttributes;
import com.gdcom.tree.TreeCreator;
import java.io.*;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class DataProcessorTest {

	RawDataConverter rawDataConverter = new RawDataConverter(new SpaceTokenizer());
	TreeCreator treeCreator = new TreeCreator();
	FixAttributes fixAttributes = new FixAttributes();
	XmlMarshaller xmlMarshaller = new NodeMarshaller();

	DataProcessor dataProcessor = new DataProcessor(rawDataConverter, treeCreator, fixAttributes, xmlMarshaller);

	File inputFile1 = new File(getClass().getResource("files/parsed_input_1.txt").getFile());
	File expectedOutputFile1 = new File(getClass().getResource("files/expected_output_1.xml").getFile());
	File actualOutputFile = new File("processed_output_file.xml");

	@Test
	public void testDataIsParsedCorrectly1() throws Exception {
		BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFile1));
		PrintWriter out = new PrintWriter(actualOutputFile);
		dataProcessor.processData(inputFileReader, out);

		out.close();
		inputFileReader.close();

		assertTrue("encoded xml is not proper", compareFiles(expectedOutputFile1, actualOutputFile));
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

		return fileEqual;
	}
}
