package com.epam.runner;

import java.io.IOException;

import com.epam.Server;
import com.epam.constants.CommonConstants;
import com.epam.handler.imphandler.*;

public class Main {

    public static void main(String[] args) throws IOException {

	Server webServer = new Server(8081, 20);
	//webServer.addHandler(CommonConstants.GET, "/book", new GetAllBooks());
	webServer.addHandler(CommonConstants.GET, "/book", new GetCertainBook());
	webServer.addHandler(CommonConstants.POST, "/book", new AddBook());
	webServer.addHandler(CommonConstants.DELETE, "/book", new DeleteBook());
	webServer.addHandler(CommonConstants.PUT, "/book", new UpdateBook());
	webServer.start();
    }
}
