package org.hengxing.nettest;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 客户端发送文件给服务端，服务端将文件保存在本地。
 */
public class TCPTest2 {
    private int port = 8090;

    @Test
    public void client() {
        Socket socket = null;
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            //1. 创建socket
            socket = new Socket(InetAddress.getLocalHost(), port);

            //2. 创建file实例，打开file流
            File file = new File("test.png");
            fis = new FileInputStream(file);

            //3. 通过socket的输出流输出
            os = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {//这里读
                os.write(buffer,0,len);//这里写出
            }
            System.out.println("数据发送完毕");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4. 关闭流
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (socket == null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Test
    public void server() throws IOException {
        //1. 创建serversocket
        ServerSocket serverSocket = new ServerSocket(port);

        //2. 通过accept()，接收客户端消息
        System.out.println("服务端启动，开始接收消息！========");
        Socket accept = serverSocket.accept();
        //3. 通过socket获取输入流
        InputStream is = accept.getInputStream();

        //4. 创建file实例，打开file流
        File file = new File("test_copy.png");
        FileOutputStream fos = new FileOutputStream(file);

        //5. 写出
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            fos.write(buffer,0,len);
        }
        System.out.println("接收完毕，写入成功");

        //6. 关闭流
        try {
            if (fos != null) {
                fos.close();
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
        try {
            if (accept != null) {
                accept.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
