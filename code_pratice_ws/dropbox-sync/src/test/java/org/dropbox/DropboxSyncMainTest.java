package org.dropbox;

import org.junit.Test;

public class DropboxSyncMainTest {

	@Test
	public void testSync() throws Exception {
		System.setProperty("https.proxyHost", "10.144.8.20");
		System.setProperty("https.proxyPort", "8080");
		new DropboxSyncMain().setUpAndSync();
	}
}