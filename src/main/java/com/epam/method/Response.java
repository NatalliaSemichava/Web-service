package com.epam.method;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import com.epam.constants.CommonConstants;
import com.epam.constants.ResponseConstants;

public class Response {

	private OutputStream os;
	private String version;
	private String statusCode;
	private String body;

	public String toString() {
		return ("\n" + version + " " + statusCode + " " + body);
	}

	public Response(OutputStream outputStream) {
		this.os = outputStream;
	}

	public void write() throws IOException {
		String respose = "";
		Map<String, String> responseMap = new LinkedHashMap<String, String>();

		responseMap.put(version, statusCode);

		if (body != null) {
			responseMap.put(ResponseConstants.BODY, body);
		}

		for (Map.Entry<String, String> pair : responseMap.entrySet()) {
			String key = pair.getKey();
			String value = pair.getValue();
			if (key.equals(ResponseConstants.BODY)) {
				respose += value;
			} else
				respose += key + value;
		}
		//os.write(respose.getBytes());
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
