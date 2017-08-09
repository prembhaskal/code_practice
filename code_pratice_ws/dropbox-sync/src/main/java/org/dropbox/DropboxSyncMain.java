package org.dropbox;

import com.dropbox.core.v2.DbxClientV2Base;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DropboxSyncMain {

	public static void main(String[] args) {
		new DropboxSyncMain().setUpAndSync();
	}

	private static final String ACCESS_TOKEN = "1N5AsbT_-N4AAAAAAAACuC7yby6f16y3K5PrmSlHYGCzy-N4LaH6lr8LfeaejF1p";

	public void setUpAndSync() {
		try {
			DropboxClient dropboxClient = new DropboxClient();
			DbxClientV2Base dbxClient = dropboxClient.createDropboxClient(getDefinedProxy(), ACCESS_TOKEN);
			Path confPath = getConfPath();;
			List<UploadData> uploadDataList = new UploadConfiguration(confPath).readConfigurationFile();
			uploadFiles(uploadDataList, new DropboxUploader(dbxClient));
		}
		catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error performing the sync with dropbox");
		}
	}

	private Path getConfPath() {
		try {
			URL resource = getClass().getResource("/dropbox_sync.properties"); // file should be on filesystem and not inside any jar.
			if (resource != null) {
				return Paths.get(resource.toURI());
			}
			else {
				throw new RuntimeException("no configuration file found: dropbox_sync.properties");
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException("error parsing file path: dropbox_sync.properties.");
		}
	}

	private void uploadFiles(List<UploadData> uploadDataList, DropboxUploader dropboxUploader) {
		for (UploadData uploadData : uploadDataList) {

			System.out.println("uploading data : " + uploadData.toString());

			File localFile = new File(uploadData.getFilePath());
			if (localFile.exists()) {
				try {
					dropboxUploader.uploadFileOverWrite(localFile.toPath(), uploadData.getDestinationPath());
				}
				catch (IOException | UploadException e) {
					System.err.println("error uploading the file " + localFile + " skipping it.");
					e.printStackTrace();
				}
			}
			else {
				System.err.println("skipping data . local file does not exists " + localFile.getAbsolutePath());
			}

		}

	}

	private Proxy getDefinedProxy() {
		// https proxy
		String host = System.getProperty("https.proxyHost");
		Integer port = getIntValue(System.getProperty("https.proxyPort"));

		if (host != null && !host.isEmpty()
				&& port != null) {
			return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
		}

		host = System.getProperty("http.proxyHost");
		port = getIntValue(System.getProperty("http.proxyPort"));

		if (host != null && !host.isEmpty()
				&& port != null) {
			return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
		}

		return null;
	}

	private Integer getIntValue(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return null;
		}
	}

}
