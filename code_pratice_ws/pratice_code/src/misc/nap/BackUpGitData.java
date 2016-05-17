package misc.nap;

import java.io.*;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class BackUpGitData {
	private int rollOverCount = 5;
	private final String backupFileName = "dropboxgitbackup.zip";
	private File backUpDirectory;

	// arguments -- rolloverCount, dropBoxDirectory, backUpDirectory.
	public static void main(String[] args) {
		if (args == null || args.length < 3) {
			throw new IllegalArgumentException("please pass all arguments - rolloverCount, dropBoxDirectory, backUpDirectory");
		}

		System.out.println("Started the back up at " + new Date());

		int rollOverCount = Integer.parseInt(args[0]);
		System.out.println("Rollover count: " + rollOverCount);
		String dropBoxDirectory = args[1];
		System.out.println("Source Directory: " + dropBoxDirectory);
		String backUpLocation = args[2];
		System.out.println("BackUp Location: " + backUpLocation);

		BackUpGitData backUpGitData = new BackUpGitData();
		backUpGitData.rollOverCount = rollOverCount;
		backUpGitData.backUpDirectory = new File(backUpLocation);

		try {
			backUpGitData.backUp(new File(dropBoxDirectory));
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("error in taking backup.");
		}

		System.out.println("Completed the back up at " + new Date());
	}

	private void backUp(File dropBoxDirectory) throws Exception {
		// // sanity check
		if (dropBoxDirectory == null || !dropBoxDirectory.exists()) {
			throw new IllegalArgumentException("invalid directory " + dropBoxDirectory);
		}

		File newBackUpFile = rollOverBackUpAndCreateNewFile(rollOverCount, backUpDirectory, backupFileName);
		zipContentAndPutInFile(dropBoxDirectory, newBackUpFile);
	}

	private void zipContentAndPutInFile(File directory, File targetFile) throws IOException {
		OutputStream outputStream = null;
		ZipOutputStream zipOutputStream = null;

		try {
			outputStream = new BufferedOutputStream(new FileOutputStream(targetFile));
			zipOutputStream = new ZipOutputStream(outputStream);

			File[] listFiles = directory.listFiles();

			addFileRecursively(directory, directory.getName(), zipOutputStream);

		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (zipOutputStream != null) {
				zipOutputStream.close();
			}
		}
	}

	private void addFileRecursively(File directory, String path, ZipOutputStream zipOutputStream) throws IOException {
		for (File file : directory.listFiles()) {
			if (file.isFile()) {
				addFileToZip(file, path + "/" + file.getName(), zipOutputStream);
			}
			else if (file.isDirectory()) { // directory add a slash in the end.
				addFileToZip(file, path + "/" + file.getName() + "/", zipOutputStream);
				addFileRecursively(file, path + "/" + file.getName(), zipOutputStream);
			}
		}
	}

	private void addFileToZip(File file, String relativePath, ZipOutputStream zipOutputStream) throws IOException {
//		System.out.println("Adding the file " + relativePath + " to the zip");
		InputStream inputStream = null;
		try {
			byte[] buffer = new byte[1024];
			zipOutputStream.putNextEntry(new ZipEntry(relativePath));
			if (file.isFile()) {
				inputStream = new BufferedInputStream(new FileInputStream(file));
				int len;
				while ((len = inputStream.read(buffer)) != -1) {
					zipOutputStream.write(buffer, 0, len);
				}
			}

			zipOutputStream.closeEntry();
		}
		catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			if (inputStream!=null)
				inputStream.close();
		}
	}

	// 0 based counting
	private File rollOverBackUpAndCreateNewFile(int rolloverCount, File directory, String fileName) throws Exception {
		// sanity check
		if (directory == null || !directory.exists()) {
			throw new IllegalArgumentException("invalid directory" + directory);
		}

		// rename other files in descending order
		for (int i = rolloverCount - 1; i >= 0; i--) {
			File file_OldName = new File(directory, fileName + (i-1));
			File file_newName = new File(directory, fileName + i);

			// delete older file.
			if (file_newName.exists()) {
				file_newName.delete();
			}
			if (file_OldName.exists()) {
				System.out.println("renaming " + file_OldName.getName() + " to " + file_newName.getName());
				boolean success = file_OldName.renameTo(file_newName);
				if (!success) {
					throw new IllegalStateException("error renaming " + file_OldName + " to " + file_newName);
				}
			}
		}
		// create new empty origin file
		File currentLogFile = new File(directory, fileName + 0);
		currentLogFile.createNewFile();
		System.out.println("created new log file " + currentLogFile.getName());

		return currentLogFile;
	}
}
