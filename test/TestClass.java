import com.epam.Store;
import com.epam.model.Book;
import com.epam.runner.Main;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class TestClass {
    private DataOutputStream out;
    private DataInputStream in;
    private  List<Book> books;


    @BeforeMethod
    public void getSocket(){
        books = Store.getAllBook();

        int serverPort = 8081;
        String address = "127.0.0.1";
        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, serverPort);

            OutputStream sout = socket.getOutputStream();
            out = new DataOutputStream(sout);
            InputStream sin = socket.getInputStream();
            in = new DataInputStream(sin);
        }
        catch (IOException e){
        }
    }

    @Test
    public void GETRealBooktst() throws IOException{
        out.writeUTF("GET /book/2 HTTP/1.1");
        String S = in.readUTF();
        System.out.println(S);
        Assert.assertTrue(S.contains(books.get(1).toString()));
        out.flush();
    }

    @Test
    public void GETUnrealBooktst2() throws IOException{
        out.writeUTF("GET /book/7 HTTP/1.1");
        String S = in.readUTF();
        System.out.println(S);
        Assert.assertTrue(S.contains("400"));
        out.flush();
    }

    @Test
    public void POSTBook() throws IOException {
        out.writeUTF("POST /book HTTP/1.1\n" +
                "book.xml");
        String S = in.readUTF();
        System.out.println(S);
        Assert.assertTrue(S.contains("201"));
        out.flush();
    }

    @Test
    public void POSTMusic() throws IOException {
        out.writeUTF("POST /book HTTP/1.1\n" +
                "music.xml");
        String S = in.readUTF();
        System.out.println(S);
        Assert.assertTrue(S.contains("400"));
        out.flush();
    }

    @Test
    public void DELETEBook() throws IOException {
        out.writeUTF("DELETE /book/2 HTTP/1.1");
        String S = in.readUTF();
        System.out.println(S);
        Assert.assertTrue(S.contains("200"));
        out.flush();
    }

    @Test
    public void DELETEUnrealBook() throws IOException {
        out.writeUTF("DELETE /book/7 HTTP/1.1");
        String S = in.readUTF();
        System.out.println(S);
        Assert.assertTrue(S.contains("400"));
        out.flush();
    }

    @Test
    public void PUTBook() throws IOException {
        out.writeUTF("PUT /book/1 HTTP/1.1\n" +
                "author update");
        String S = in.readUTF();
        System.out.println(S);
        Assert.assertTrue(S.contains("200"));
        out.flush();
    }

    @Test
    public void PUTUnrealBook() throws IOException {
        out.writeUTF("PUT /book/1 HTTP/1.1\n" +
                "dfg update");
        String S = in.readUTF();
        System.out.println(S);
        Assert.assertTrue(S.contains("400"));
        out.flush();
    }
}
