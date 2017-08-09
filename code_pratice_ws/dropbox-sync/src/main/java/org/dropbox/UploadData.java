package org.dropbox;

import java.nio.file.Path;

public class UploadData {

	private final String filePath;
	private final String destinationPath;

	public UploadData(String filePath, String destinationPath) {
		this.filePath = filePath;
		this.destinationPath = destinationPath;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getDestinationPath() {
		return destinationPath;
	}

	@Override
	public String toString() {
		return "UploadData{" +
				"filePath='" + filePath + '\'' +
				", destinationPath='" + destinationPath + '\'' +
				'}';
	}
}
