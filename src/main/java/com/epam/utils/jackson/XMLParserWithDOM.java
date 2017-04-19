package com.epam.utils.jackson;

import com.epam.model.Book;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

public class XMLParserWithDOM {
    private static Book book = new Book();

    public static Book readXMLFile(String url) throws IOException, SAXException {
        DOMParser parser = new DOMParser();
        parser.parse(url);
        Document document = parser.getDocument();
        Element root = document.getDocumentElement();

        NodeList bookSet = root.getElementsByTagName("book");
        for (int i = 0; i < bookSet.getLength(); i++) {
            Node n = bookSet.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                book = getBook(n);
            }
        }
        return book;
    }

    private static Book getBook(Node n) {
        Book book = new Book();
        book.setId(Integer.parseInt(getText("id", n)));
        book.setEdition(getText("edition", n));
        book.setAuthor(getText("author", n));
        book.setLanguage(getText("language", n));
        book.setDate(getText("create_date", n));
        return book;
    }

    private static String getText(String nameTag, Node n) {
        return getTextByTag((Element) n, nameTag);
    }


    private static String getTextByTag(Element el, String tagName) {
        NodeList nodeList = el.getElementsByTagName(tagName);
        return nodeList.item(0).getTextContent().trim();
    }
}
