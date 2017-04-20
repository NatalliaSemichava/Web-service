import org.testng.annotations.BeforeMethod;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TestClass {

    @BeforeMethod
    public void getSocket(){
        int serverPort = 8081;
        String address = "127.0.0.1";
        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, serverPort);

            OutputStream sout = socket.getOutputStream();
            DataOutputStream out = new DataOutputStream(sout);
        }
        catch (IOException e){

        }
    }
}
