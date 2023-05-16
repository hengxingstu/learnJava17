package org.hengxing.nettest.chatroom;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static final String IP = "127.0.0.1";
    public static final String PORT = "8090";

    public static void main(String[] args) throws IOException, InterruptedException {
        //1. 连接服务器
        Socket socket = new Socket(IP, Integer.parseInt(PORT));

        //2. 开启两个线程
        //（1）接受消息
        Receive receive = new Receive(socket);
        receive.start();
        //（2）发送消息
        Send send = new Send(socket);
        send.start();

        //等待send执行完毕
        send.join();

        //关闭socket
        socket.close();
    }

}

/**
 * 接收线程
 */
class Receive extends Thread {

    private Socket socket;

    public Receive(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            Scanner input = new Scanner(is);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

/**
 * 发送线程
 */
class Send extends Thread {
    private Socket socket;

    public Send(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream os = socket.getOutputStream();
            PrintStream ps = new PrintStream(os);
            Scanner input = new Scanner(System.in);

            //从键盘输入==自己的话==，给服务器发送，由服务器给其他人转发
            while (true) {
                System.out.print("自己的话：");
                String line = input.nextLine();
                if ("bye".equals(line)) break;//如果输入bye就退出
                ps.println(line);
            }

            input.close();//关闭输入流，同时进程结束
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}