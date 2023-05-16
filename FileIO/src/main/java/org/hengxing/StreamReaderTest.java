package org.hengxing;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class StreamReaderTest {

    @Test
    public void test01() throws IOException {
        File file = new File("dbcp_utf-8.txt");
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"utf-8");

        //读文件
        char[] cBuffer = new char[1024];
        int len;
        while ((len = isr.read(cBuffer)) != -1) {
            for (int i = 0; i < len; i++) {
                System.out.print(cBuffer[i]);
            }
        }

        isr.close();
    }

    @Test
    public void test02() throws IOException {
        File file = new File("dbcp_gbk.txt");
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"gbk");

        //读文件
        char[] cBuffer = new char[1024];
        int len;
        while ((len = isr.read(cBuffer)) != -1) {
            for (int i = 0; i < len; i++) {
                System.out.print(cBuffer[i]);
            }
        }

        isr.close();
    }
}


