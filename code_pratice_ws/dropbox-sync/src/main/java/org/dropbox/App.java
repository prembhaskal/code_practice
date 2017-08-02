package org.dropbox;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.http.HttpRequestor;
import com.dropbox.core.http.StandardHttpRequestor;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		try {
			new App().testDropboxConnection();
		}
		catch (DbxException e) {
			e.printStackTrace();
		}
	}

	private static final String ACCESS_TOKEN = "1N5AsbT_-N4AAAAAAAACuC7yby6f16y3K5PrmSlHYGCzy-N4LaH6lr8LfeaejF1p";

	public void testDropboxConnection() throws DbxException {
		System.setProperty("https.proxyHost", "10.144.8.20");
		System.setProperty("https.proxyPort", "8080");

		// Create Dropbox client
		StandardHttpRequestor.Config.Builder httpConfigBuild = StandardHttpRequestor.Config.builder();
		addProxyIfPresent(httpConfigBuild);
		StandardHttpRequestor.Config httpConfig = httpConfigBuild.build();
		HttpRequestor httpRequestor = new StandardHttpRequestor(httpConfig);
//		DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
		DbxRequestConfig.Builder builder = DbxRequestConfig.newBuilder("dropbox/java-tutorial")
				.withUserLocale("en_US")
				.withHttpRequestor(httpRequestor);
		DbxRequestConfig config = builder.build();
		DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

		// Get current account info
		FullAccount account = client.users().getCurrentAccount();
		System.out.println(account.getName().getDisplayName());


		// Get files and folder metadata from Dropbox root directory
		ListFolderResult result = client.files().listFolder("/work");
		while (true) {
			for (Metadata metadata : result.getEntries()) {
				System.out.println(metadata.getPathLower());
			}

			if (!result.getHasMore()) {
				break;
			}

			result = client.files().listFolderContinue(result.getCursor());
		}
	}

	private void addProxyIfPresent(StandardHttpRequestor.Config.Builder httpConfigBuild) {
		String proxyAddr = System.getProperty("https.proxyHost") != null ? System.getProperty("https.proxyHost") : System.getProperty("http.proxyHost");
		String proxyPort = System.getProperty("https.proxyPort") != null ? System.getProperty("https.proxyPort") : System.getProperty("http.proxyPort");
		int proxyPorrInt = proxyPort != null ? Integer.parseInt(proxyPort) : 0;
		if (proxyAddr != null && proxyPort != null) {
			SocketAddress addr = new InetSocketAddress(proxyAddr, proxyPorrInt);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
			httpConfigBuild.withProxy(proxy);
		}
	}

}
