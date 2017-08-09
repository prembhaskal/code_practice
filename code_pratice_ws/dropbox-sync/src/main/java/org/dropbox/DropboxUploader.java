package org.dropbox;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2Base;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.WriteMode;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.READ;

public class DropboxUploader {

	private final DbxClientV2Base clientV2Base;

	public DropboxUploader(DbxClientV2Base clientV2Base) {
		this.clientV2Base = clientV2Base;
	}

	public void uploadFileOverWrite(InputStream in, String destPath) throws IOException, UploadException {
		System.out.println("started uploading file to destination: " + destPath);

		try {
			FileMetadata fileMetadata = clientV2Base
					.files()
					.uploadBuilder(destPath)
					.withMode(WriteMode.OVERWRITE)
					.uploadAndFinish(in);

		} catch (DbxException e) {
			e.printStackTrace();
			System.err.println("Error in upload operation: " + e.getMessage());
			throw new UploadException("error in upload: " + e.getMessage());
		}

	}

	public void uploadFileOverWrite(Path path, String destPath) throws IOException, UploadException {
		System.out.println("started uploading file" + path.toString() + " to destination: " + destPath);
		try (InputStream inputStream = Files.newInputStream(path, READ)) {
			uploadFileOverWrite(inputStream, destPath);
		}
	}
}
