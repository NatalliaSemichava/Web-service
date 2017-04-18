package com.epam.handler.imphandler;

import java.io.IOException;

import com.epam.handler.IHandle;
import com.epam.method.Request;
import com.epam.method.Response;

public class GetCertainBook implements IHandle {

	public String handle(Request rq, Response rp) throws IOException {
		return rp.toString();
	}

}
