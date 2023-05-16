package org.hengxing.nettest;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddr {
    @Test
    public void test01() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("localHost = " + localHost);
    }

    @Test
    public void test02() throws UnknownHostException {
        InetAddress baidu = InetAddress.getByName("www.baidu.com");
        System.out.println("baidu = " + baidu);
    }

    @Test
    public void test03() throws UnknownHostException {
        byte[] addr = {(byte)192,(byte)168,24,56};
        InetAddress net = InetAddress.getByAddress(addr);

        System.out.println("net = " + net);
    }
}
