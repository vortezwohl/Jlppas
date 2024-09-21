import org.junit.Test;

import java.io.*;
import java.net.*;

public class FileTcpTest {





    @Test
    public void client() throws IOException {

        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
        Socket socket = new Socket(inetAddress,8898);

//        InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress,8899);
//        socket.connect(inetSocketAddress);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("你好".getBytes());



    }

    @Test
    public void server() throws IOException {

        ServerSocket serverSocket = new ServerSocket(8898);

        while (true){
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            int len;
            byte[] buffer = new byte[1024];
            inputStream.read(buffer);
            byteArrayOutputStream.write(buffer);

            String s = byteArrayOutputStream.toString();
            System.out.println(s);

        }


    }
}
