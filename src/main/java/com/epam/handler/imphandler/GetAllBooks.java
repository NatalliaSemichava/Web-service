package com.epam.handler.imphandler;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.epam.Store;
import com.epam.constants.CommonConstants;
import com.epam.constants.ResponseConstants;
import com.epam.handler.IHandle;
import com.epam.method.Request;
import com.epam.method.Response;
import com.epam.model.Book;
import com.epam.model.BooksPojo;
import com.epam.utils.jackson.JsonUtils;
import com.epam.utils.marshaller.MarshallerHelper;

public class GetAllBooks implements IHandle {

	public String handle(Request rq, Response rp) throws IOException {
		String acceptType = rq.getAccept();

		try {
			response(rq, rp, acceptType);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return rp.toString();
	}

	private void response(Request rq, Response rp, String acceptType) throws JAXBException {
		String body = "";
		List<Book> books = Store.getAllBook();

		rp.setVersion(rq.getVersion());
		rp.setStatusCode(ResponseConstants.STATUS_CODE_200_OK);

		BooksPojo book = new BooksPojo(books);

		//if (acceptType.equals(CommonConstants.ACCEPT_TYPE_XML)) {

			StringWriter writer = new StringWriter();
			MarshallerHelper.marshall(book, writer);

			body = writer.toString();

			rp.setBody(body);
		//} else {
			//body = JsonUtils.toJson(book);
			//rp.setContentLength(String.valueOf(body.getBytes().length));
			//rp.setBody(body);
		//}

		try {
			rp.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
