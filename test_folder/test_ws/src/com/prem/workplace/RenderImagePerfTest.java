package com.prem.workplace;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class RenderImagePerfTest {


	public static void main(String[] s) {
		RenderImagePerfTest imagePerfTest = new RenderImagePerfTest();
//		String uri = "http://localhost:8080/Entel_ROCRevenueAssurance4.1-Client/ra/resources/images/subex_logo_smaller.gif";
		String uri = "http://200.87.142.159:8080/Entel_ROCRA/ra/resources/images/auditimages/AuditImage_6.png";
		imagePerfTest.resolveAndOpenStream(uri);
		uri = "http://200.87.142.159:8080/Entel_ROCRA/ra/resources/images/roc-help-logo.png";
		imagePerfTest.resolveAndOpenStream(uri);
		uri = "http://200.87.142.159:8080/Entel_ROCRA/ra/resources/images/subex_logo_smaller.gif";
		imagePerfTest.resolveAndOpenStream(uri);
	}

	private InputStream resolveAndOpenStream(String uri) {
		java.io.InputStream is = null;
		uri = resolveURI(uri);
		try {
			is = new URL(uri).openStream();
		} catch (java.net.MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return is;
	}

	public String resolveURI(String uri) {
		if (uri == null) return null;
		String baseUrl = null;
		String ret = null;
			try {
				URL result = new URL(uri);
				baseUrl = result.toExternalForm();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			try {
				URL result = new URL(new URL(baseUrl), uri);
				ret = result.toString();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		return ret;
	}
}
