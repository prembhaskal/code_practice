package practice.misc;

import java.io.*;

public class ExportKanbanData {

	public void convertToOneTaskPerFile(File exportedFile, File outputDirectory) {
		String taskFileName = "task_";
		try(BufferedReader reader = new BufferedReader(new FileReader(exportedFile))) {
			String firstLine = reader.readLine();

			String line;
			int fileNo = 1;
			while ((line = reader.readLine()) != null) {
				line = line.replaceFirst("Design", "Implementation");
				File taskFile = new File(outputDirectory, taskFileName + fileNo + ".csv");
				System.out.println("creating the task file no. " + fileNo + " and file name " + taskFile.getName());
				try (BufferedWriter writer = new BufferedWriter(new FileWriter(taskFile))) {
					writer.write(firstLine); writer.newLine();
					writer.write(line); writer.newLine();
				}

				fileNo++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
