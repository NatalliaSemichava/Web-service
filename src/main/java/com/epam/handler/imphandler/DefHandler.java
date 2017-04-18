package com.epam.handler.imphandler;

import com.epam.handler.IHandle;
import com.epam.method.Request;
import com.epam.method.Response;

public class DefHandler implements IHandle {

	public String handle(Request rq, Response rp) {
		return rp.toString();
	}

}
