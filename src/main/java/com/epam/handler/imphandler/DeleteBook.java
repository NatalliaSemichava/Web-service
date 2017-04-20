package com.epam.handler.imphandler;

import com.epam.Store;
import com.epam.constants.ResponseConstants;
import com.epam.handler.IHandle;
import com.epam.method.Request;
import com.epam.method.Response;
import com.epam.model.Book;
import com.epam.model.BookPojo;
import com.epam.utils.jackson.JsonUtils;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class DeleteBook implements IHandle {

    public String handle(Request rq, Response rp) throws IOException {
        String acceptType = rq.getAccept();

        try {
            response(rq, rp, acceptType);
        } catch (JAXBException e) {
        }
        return rp.toString();
    }

    private void response(Request rq, Response rp, String acceptType) throws JAXBException {
        String body = "";
        int number = Character.getNumericValue(rq.getPath().charAt(rq.getPath().length()-1));

        try {
            Store.deleteBook(number);
            rp.setStatusCode(ResponseConstants.STATUS_CODE_200_OK);
            System.out.println("Deleted!");
        }
        catch (Exception e){
            rp.setStatusCode(ResponseConstants.STATUS_CODE_400_BAD_REQUEST);
        }

        rp.setBody(body);

        try {
            rp.write();
        } catch (IOException e) {
        }
    }
}
