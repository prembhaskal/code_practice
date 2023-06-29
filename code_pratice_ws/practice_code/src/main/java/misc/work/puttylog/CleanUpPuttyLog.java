package misc.work.puttylog;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class CleanUpPuttyLog {

	private String log_directory_path = "C:\\cygwin64\\home\\bhaskal\\.ssh";
	private String log_file_name_pattern = "putty[\\d]{8}_[\\d]{6}_.*log";

	private final int RETAIN_DAYS = 7;
	private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


	public static void main(String[] args) {
		new CleanUpPuttyLog().cleanUpOlderFiles();
	}

	public void cleanUpOlderFiles() {
		File logDirectory = new File(log_directory_path);
		File[] listOfFiles = logDirectory.listFiles(
				(dir, filename) -> filename.matches(log_file_name_pattern));

		List<File> fileList = Arrays.asList(listOfFiles);

		System.out.println("Number of log files in the directory: " + log_directory_path + " -- " + fileList.size());

		LocalDateTime retainDateTime = LocalDate.now().minusDays(RETAIN_DAYS).atStartOfDay();

		System.out.println("deleting files older than " + retainDateTime.format(dateTimeFormatter));

		fileList.stream()
				.filter(createRetentionFilter(retainDateTime))
				.forEach(file -> {
					System.out.println("deleting file " + file.getName());
					file.delete();
				});
	}

	private Predicate<File> createRetentionFilter(LocalDateTime retainDateTime) {
		return file -> {
				long fileLastModifiedAt = file.lastModified()/1000;
				LocalDateTime fileModifiedTime = Instant.ofEpochSecond(fileLastModifiedAt).atZone(ZoneId.systemDefault()).toLocalDateTime();
				return fileModifiedTime.isBefore(retainDateTime);
			};
	}


	public void cleanUp() {
		File logDirectory = new File(log_directory_path);

		File[] listOfFiles = logDirectory.listFiles(
				(dir, filename) -> filename.matches(log_file_name_pattern));

		List<File> fileList = Arrays.asList(listOfFiles);

		System.out.println("size: " + fileList.size());

//		fileList.sort((f1, f2) -> {
//			if (f1.lastModified() < f2.lastModified()) return -1;
//			else return 1;
//		});
//
//
		LocalDateTime fiveDaysBackTime = LocalDate.now().minusDays(7).atStartOfDay();

		Predicate<File> ifFileOlderThanFiveDays = file -> {
			long fileLastModifiedAt = file.lastModified()/1000;
			LocalDateTime fileModifiedTime = Instant.ofEpochSecond(fileLastModifiedAt).atZone(ZoneId.systemDefault()).toLocalDateTime();

			return fileModifiedTime.isBefore(fiveDaysBackTime);
		};

		fileList.stream()
				.filter(ifFileOlderThanFiveDays).forEach(file -> System.out.println(file.getName()));


		System.out.println(fileList.get(0).getName());
		System.out.println(fileList.get(0).lastModified());
		long modifiedAtSeconds = fileList.get(0).lastModified()/1000;
		Instant fileModifiedInstant = Instant.ofEpochSecond(modifiedAtSeconds);
		LocalDateTime fileModifiedTime = LocalDateTime.ofInstant(fileModifiedInstant, ZoneId.systemDefault());
		System.out.println(fileModifiedTime.format(dateTimeFormatter));

		// now
		LocalDate today = LocalDate.now();
		// go back to 5 days back ... midnight, if today is 17th 5:30pm, go back to 12th 00:00 hours.
		LocalDateTime fiveDaysBack = today.minusDays(7).atStartOfDay();

		System.out.println(fiveDaysBack.format(dateTimeFormatter));

		System.out.println("is more than 5 days older " + fiveDaysBack.isAfter(fileModifiedTime));

		LocalDateTime moreThan5DaysBack = fiveDaysBack.minusSeconds(10);
		System.out.println(moreThan5DaysBack.format(dateTimeFormatter));
		System.out.println("is more than 5 days older " + fiveDaysBack.isAfter(moreThan5DaysBack));

		LocalDateTime lessThan5Days = fiveDaysBack.plusSeconds(10);
		System.out.println(lessThan5Days.format(dateTimeFormatter));
		System.out.println("is more than 5 days older " + fiveDaysBack.isAfter(lessThan5Days));
	}
}
