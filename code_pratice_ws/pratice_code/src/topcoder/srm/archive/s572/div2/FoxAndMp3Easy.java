package topcoder.srm.archive.s572.div2;

import java.util.Arrays;

public class FoxAndMp3Easy {
	public String[] playList(int n) {

		String[] fileNames = new String[n];

		for (int i=1;i<n;i++) {
			fileNames[i-1] = i + ".mp3";
		}

		Arrays.sort(fileNames);

		if (n <= 50) {
			return fileNames;
		} else {
			String[] newFileNames = new String[50];
			for (int i=0;i<50;i++)
				newFileNames[i] = fileNames[i];
			return newFileNames;
		}
	}
}
