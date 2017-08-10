package org.dropbox;

import org.junit.Test;

public class DropboxSyncMainTest {

	@Test
	public void testSync() throws Exception {
		System.setProperty("https.proxyHost", "87.254.212.120");
		System.setProperty("https.proxyPort", "8080");
//		curl https://api.dropbox.com/1/account/info -H "Authorization:Bearer 1N5AsbT_-N4AAAAAAAACvEtZ8KXWOupMVAGbI6tK27-rcyjSfrMJkmCw3z8kOjPN"
		new DropboxSyncMain().setUpAndSync("1N5AsbT_-N4AAAAAAAACuC7yby6f16y3K5PrmSlHYGCzy-N4LaH6lr8LfeaejF1p");
	}
}