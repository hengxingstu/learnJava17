package org.hengxing.nettest.chatroom;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    //在自己这台机器上启动，所以ip就获取本机即可
    public static final String PORT = "8090";
    //定义一个集合存储在线的客户端
    static ArrayList<Socket> online = new ArrayList<Socket>();

    public static void main(String[] args) throws IOException {
        //启动服务器，绑定端口
        ServerSocket server = new ServerSocket(Integer.valueOf(PORT));

        //2. 有多少请求，就开多少连接
        while (true) {
            Socket accept = server.accept();

            online.add(accept);//加入到online列表中

            //启动消息处理线程，并重新等待新的连接
            MessageHandler messageHandler = new MessageHandler(accept);
            messageHandler.start();
        }

    }

    static class MessageHandler extends Thread {
        private Socket socket;
        private String ip;

        public MessageHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String ip;
            try {
                ip = socket.getInetAddress().getHostName();

                //给所有客户端转发”上线了“
                sendToOther(ip + "上线了");

                //(1)接收该客户端发送的消息
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                String msg;
                while ((msg = br.readLine()) != null) {
                    //(2)转发给其他客户端
                    sendToOther(ip + ":" + msg);
                }

                //能出来说明客户端关闭了流，提示下线，并从在线列表中移除
                sendToOther(ip + "下线了。。");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                online.remove(socket);//移除该人员
            }
        }
    }

    /**
     * 给所有客户端发送消息
     * @param msg 消息
     * @throws IOException
     */
    public static void sendToOther(String msg) throws IOException {
        for (Socket s :
                online) {
            OutputStream os = s.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.println(msg);
        }
    }
}
