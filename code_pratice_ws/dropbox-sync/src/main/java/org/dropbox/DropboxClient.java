package org.dropbox;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.http.HttpRequestor;
import com.dropbox.core.http.StandardHttpRequestor;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.DbxClientV2Base;

import java.net.Proxy;

public class DropboxClient {

	public DbxClientV2Base createDropboxClient(Proxy httpProxy, String accountAccessToken) {
		System.out.println("creating dropbox client.");

		StandardHttpRequestor.Config.Builder httpConfigBuild = StandardHttpRequestor.Config.builder();
		if (httpProxy != null) {
			System.out.println("adding the given http proxy settings " + httpProxy.toString());
			httpConfigBuild = httpConfigBuild.withProxy(httpProxy);
		}
		else {
			System.out.println("connecting directly... no proxy defined.");
		}

		DbxRequestConfig.Builder builder = DbxRequestConfig.newBuilder("dropbox/java-tutorial")
				.withUserLocale("en_US")
				.withHttpRequestor(new StandardHttpRequestor(httpConfigBuild.build()));

		return new DbxClientV2(builder.build(), accountAccessToken);
	}
}
