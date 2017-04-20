package com.epam.runner;
import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] ar) {
        int serverPort = 8081;
        String address = "127.0.0.1";
        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, serverPort);

            OutputStream sout = socket.getOutputStream();
            DataOutputStream out = new DataOutputStream(sout);

            /*out.writeUTF("POST /book HTTP/1.1\n" +
                    "book.xml");
            out.flush();*/
            out.writeUTF("GET /book/7 HTTP/1.1");
            out.flush();/*
            out.writeUTF("DELETE /book/2 HTTP/1.1");
            out.flush();
            out.writeUTF("PUT /book/1 HTTP/1.1\n" +
                    "dfg update");*/
            out.flush();

        } catch (Exception x) {
        }
    }
}
