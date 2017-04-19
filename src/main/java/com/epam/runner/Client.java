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

            //InputStream sin = socket.getInputStream();
            //DataInputStream in = new DataInputStream(sin);
            //System.out.println(in.readUTF());

            /*out.writeUTF("GET /book HTTP/1.1\n"); // отсылаем введенную строку текста серверу.
            out.flush();*/
            out.writeUTF("POST /book HTTP/1.1\n" +
                    "book.xml");
            out.flush();

        } catch (Exception x) {
        }
    }
}
