package com.epam.handler.imphandler;

import java.io.IOException;

import com.epam.Store;
import com.epam.constants.ResponseConstants;
import com.epam.handler.IHandle;
import com.epam.method.Request;
import com.epam.method.Response;
import com.epam.model.Book;
import com.epam.utils.jackson.XMLParserWithDOM;

public class AddBook implements IHandle {

	public String handle(Request rq, Response rp) throws IOException {
		boolean isMap = true;
		rp.setVersion(rq.getVersion());

		Book bookCreate = null;
		try {
			bookCreate = XMLParserWithDOM.readXMLFile(rq.getBody());
			System.out.println(bookCreate.toString());
		} catch (Exception ex) {
			rp.setStatusCode(ResponseConstants.STATUS_CODE_400_BAD_REQUEST);
			isMap = false;
		}

		if (isMap) {
			Store.addBook(bookCreate);
			rp.setStatusCode(ResponseConstants.STATUS_CODE_201_CREATED);
			System.out.println("Added!");
		}

		rp.write();
		return rp.toString();
	}

}
