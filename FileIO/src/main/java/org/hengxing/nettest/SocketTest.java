package org.hengxing.nettest;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTest {
    private int port = 8090;
    @Test
    public void client() {
        Socket socket = null;
        OutputStream os = null;
        try {
            //get An IP ,where you want to send?
            InetAddress ip = InetAddress.getByName("127.0.0.1");
            //get a socket with port
            socket = new Socket(ip, port);

            //send data
            os = socket.getOutputStream();
            os.write("hello,I am Client.How are you? 这是中文。人之初性本善".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //close the stream
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Test
    public void server() {
        ServerSocket serverSocket = null;
        Socket socket = null;//阻塞式的方法
        InputStream is = null;
        try {
            //create a serverSocket
            InetAddress localHost = InetAddress.getLocalHost();
            serverSocket = new ServerSocket(port);

            //call accept(), receive socket from server
            System.out.println("server is running, waiting for data from client.");
            socket = serverSocket.accept();
            System.out.println("Receive connection form " + socket.getInetAddress().getHostName());

            //receive
            is = socket.getInputStream();
            byte[] buffer = new byte[5];
            int len;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //临时创建这个字节数组输出流，内部维护了一个byte[]
            while ((len = is.read(buffer)) != -1) {
                //错误的，可能会出现乱码，因为拆开了单个字符
//                String s = new String(buffer, 0, len);
//                System.out.print(s);
                //正确的，用baos包装
                baos.write(buffer,0,len);

            }
            System.out.println(baos.toString());

            System.out.println("\nreceive data complete!!!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
