package org.dropbox;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

public class UploadConfiguration {

	private final Path confPath;

	public UploadConfiguration(Path confPath) {
		Objects.requireNonNull(confPath, "configuration path is null");
		if (Files.notExists(confPath)) {
			throw new IllegalArgumentException("configuration file does not exist - given path: " + confPath.toAbsolutePath());
		}
		this.confPath = confPath;
	}

	public List<UploadData> readConfigurationFile() throws IOException {
		Properties properties = new Properties();
		try (Reader reader = Files.newBufferedReader(confPath, StandardCharsets.UTF_8)) {
			properties.load(reader);
		}

		return properties.entrySet().stream()
				.map((entry) -> {
					String srcPath = (String) entry.getKey();
					String destPath = (String) entry.getValue();
					return new UploadData(srcPath, destPath);
				})
				.collect(Collectors.toList());
	}
}
