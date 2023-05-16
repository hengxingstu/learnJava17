package org.hengxing.nettest;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 从客户端发送文件给服务端，服务端保存到本地。并返回“发送成功”给客户端。并关闭相应的连接。
 */
public class TCPTest3 {
    private int port = 8090;

    @Test
    public void client() {
        Socket socket = null;
        FileInputStream fis = null;
        OutputStream os = null;
        InputStream is = null;
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
            //表明不再发送数据
            socket.shutdownOutput();

            //4. 等待来自服务端的确认
            is = socket.getInputStream();
            byte[] cBuffer = new byte[5];
            int len1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len1 = is.read(cBuffer)) != -1) {
                baos.write(cBuffer);
            }
            System.out.println(baos.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5. 关闭流
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }try {
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
                if (socket != null) {
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

        //6. 响应客户端：“已收到图片”
        OutputStream os = accept.getOutputStream();
        os.write("已收到图片".getBytes());

        //6. 关闭流
        try {
            if (os != null) {
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } try {
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
