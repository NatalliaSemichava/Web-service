package com.epam.method;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.epam.constants.CommonConstants;
import com.epam.utils.HttpMethodUtils;
import com.epam.utils.SplitUtils;

public class Request {

	private String method;
	private String path;
	private String version;
	private String accept;
	private String body;

	public Request(){}

	public Request(String[] array){parseRequest(array);}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	private void parseRequest(String[] array) {
		body="";
		String[] head = array[0].split(" ");


		if (head[0].startsWith(CommonConstants.GET)) {
			method = CommonConstants.GET;
		} else if (head[0].startsWith(CommonConstants.POST)) {
			method = CommonConstants.POST;
		}


		path = head[1];
		version = head[2];

		if (array.length > 1) {
			for (int i = 1; i < array.length; i++) {
				body += array[i];
			}
		}

	}

}
