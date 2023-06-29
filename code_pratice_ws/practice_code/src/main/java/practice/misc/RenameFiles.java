package practice.misc;

import java.io.*;

public class RenameFiles {

	private String srcDirectoryName = "C:\\Users\\premkumarbhaskal\\Downloads";
	private String destinationDirName = "temp";

	// removes the %20 and other characters and replaces them with their proper symbol or - in case symbol is restricted.
	public void renameFiles() throws IOException {
		File srcDirectory = new File(srcDirectoryName);

		File destDirectory = new File(srcDirectory, destinationDirName);
		if (!destDirectory.exists()) {
			destDirectory.mkdir();
		}

		for (File file : srcDirectory.listFiles()) {
			if (file.isDirectory()) {
				continue;
			}

			if (!file.getName().endsWith("mp4")) {
				continue;
			}

//			if (!file.getName().contains("Expl")) {
//				continue;
//			}

			String oldName = file.getName();
			String newName = getNewName(oldName);

			System.out.println("OLD-NAME: " + oldName +
							   "\n" +
							   "NEW NAME: " + newName);


			// create the new file
			File newFile = new File(destDirectory, newName);
			copyFile(file, newFile);
			System.out.println("copied file " + newFile.getName());
			System.out.println("");
		}
	}

	private String getNewName(String oldName) {
		StringBuilder sb = new StringBuilder(oldName);
		while (true) {
			int idx;
			if ((idx = sb.indexOf("%")) != -1) {

				// getting actual character for the code.
				String code = sb.substring(idx + 1, idx + 3);
				int codeInt = Integer.parseInt(code, 16);
				char ch = (char)codeInt;
//				System.out.println("code " + code + " replaced by -->" + ch + "<--");

				sb.replace(idx, idx +3, String.valueOf(ch));

			} else {
				break;
			}

		}

		String newName = removeRestrictedCharacters(sb.toString());
		return newName;
	}

	private String removeRestrictedCharacters(String name) {
		name = name.replaceAll("\\:", "-");
		name = name.replaceAll("\\?", "-");
		name = name.replaceAll("\\<", "-");
		name = name.replaceAll("\\>", "-");
		name = name.replaceAll("\\*", "-");
		name = name.replaceAll("\\/", "-");
		name = name.replaceAll("\\\\", "-");
		name = name.replaceAll("\\\"", "-");
		name = name.replaceAll("\\|", "-");
		return name;
	}

	private void copyFile(File srcFile, File destFile) throws IOException {
		BufferedInputStream inputStream = null;
		BufferedOutputStream outputStream = null;

		try {
			inputStream = new BufferedInputStream(new FileInputStream(srcFile));
			outputStream = new BufferedOutputStream(new FileOutputStream(destFile));

			byte[] buf = new byte[8192];
			int n;

			while ((n = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, n);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}
}
