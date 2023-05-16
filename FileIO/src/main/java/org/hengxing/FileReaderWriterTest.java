package org.hengxing;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderWriterTest {

    /**
     * 读取hello.txt中的内容，显示在控制台上
     */
    @Test
    public void test01() throws IOException {
        File file = new File("hello.txt");
        FileReader fr = new FileReader(file);

        int h ;
        /**
         * 写法一：do-while
         * 前面这总写法会多输出最后的EOF
         * 因为你是先读了字符，输出后，再判断是否为文件结束符的
         * 假设极端情况，文件是空的，你也会读出EOF并输出，所以着用写法是不对的
         *
         */
//        do {
//            h = fr.read();
//            System.out.print((char) h);
//
//        } while (h != -1);

        /**
         * 写法二：直接读取，判断
         * 这种才是正确的，优雅
         */
        while ((h = fr.read()) != -1) {
            System.out.print((char) h);
        }
        //记得关闭流
        fr.close();

    }

    /**
     * 刚刚的写法有一些线程安全方面的问题
     * 1. 如果我们在read时出现了异常，方法会直接中止并抛出异常。那么最后的`fr.close();`关闭流语句就没有被执行，产生了内存泄漏 -> 通过try-catch-finally去解决
     * 2. 解决了内存泄漏之后。如果我们在new输入流的时候出现了异常，那么输入流对象根本就没有创建，是为null的状态。我们的close()会产生空指针异常。 -> if (fr != null) fr.close();判断不为空时再关闭即可
     */
    @Test
    public void test02() {

        File file = new File("hello.txt");

        FileReader fr = null;
        try {
            fr = new FileReader(file);

            int h ;

            while ((h = fr.read()) != -1) {
                System.out.print((char) h);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //记得关闭流
            try {
                if (fr != null) fr.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * 通过缓存提高速率，我们知道磁盘io性能很差，内存与磁盘交互太多次会严重拖慢程序速度。
     * 所以使用缓存数组，每次多加载一些字符，减少总体的加载次数
     */
    @Test
    public void test03() {
        File file = new File("hello.txt");

        FileReader fr = null;
        try {
            fr = new FileReader(file);
            char[] cBuffer = new char[5];
            int length;
            while ((length = fr.read(cBuffer)) != -1) {
                for (int i = 0; i < length; i++) {
                    System.out.print(cBuffer[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) fr.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void test04() {
        File src = new File("hello.txt");
        File dest = new File("hello_copy.txt");

        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(src);
            fw = new FileWriter(dest);

            char[] cBuffer = new char[5];
            //读文件
            int len;
            while ((len = fr.read(cBuffer)) != -1) {
                //在这里直接写文件
                fw.write(cBuffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fw != null) fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
