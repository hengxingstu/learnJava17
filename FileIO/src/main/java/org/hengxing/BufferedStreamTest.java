package org.hengxing;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;

public class BufferedStreamTest {
    @Test
    public void test01() {
        File src = new File("test.png");
        File dest = new File("test_copy.png");

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            FileInputStream fis = new FileInputStream(src);
            FileOutputStream fos = new FileOutputStream(dest);

            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bis != null)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test02() {
        //测试两个方法的时间开销
        long start = System.currentTimeMillis();
        File src = new File("C:\\Users\\hengxing\\Desktop\\01video.mp4");
        File dest = new File("C:\\Users\\hengxing\\Desktop\\02video.mp4");
        copyFileWithFileStream(src,dest);//878
//        copyFileWithBufferStream(src,dest);//472
        long end = System.currentTimeMillis();
        System.out.println("Time consume:" + (end - start));
    }

    public void copyFileWithBufferStream(File src,File dest) {

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            FileInputStream fis = new FileInputStream(src);
            FileOutputStream fos = new FileOutputStream(dest);

            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bis != null)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void copyFileWithFileStream(File src,File dest){

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test03() throws IOException {
        /**
         * 读取文件内容
         */
        BufferedReader br = new BufferedReader(new FileReader(new File("hello.txt")));


        /*方式一
        char[] cBuffer = new char[50];
        int len;
        while ((len = br.read(cBuffer)) != -1) {
            for (int i = 0; i < len; i++) {
                System.out.print(cBuffer[i]);
            }
        }*/
        /*方式二
        String data;
        while ((data = br.readLine()) != null) {
            System.out.println(data);
        }*/

        br.close();
    }

    @Test
    public void test04() throws IOException {
        //实现文件复制
        File file = new File("hello.txt");
        File file1 = new File("hello_001.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(file1));

        //读文件，写文件
        /*char[] cBuffer = new char[5];
        int len;
        while ((len = br.read(cBuffer)) != -1) {
            for (int i = 0;i < len;i++) {
                bw.write(cBuffer[i]);
            }
        }*/
        //方式二
        String buffer;
        while ((buffer = br.readLine()) != null) {
            bw.write(buffer);
            bw.newLine();
            bw.flush();
        }

        //关闭流
//        bw.close();
//        br.close();
    }

    @Test
    public void test05() throws IOException {
        //统计所有的姓氏在文件中出现的次数
        HashMap<String, Integer> nameCount = new HashMap<>();

        File file = new File("stu.txt");
        BufferedReader fr = new BufferedReader(new FileReader(file));

        String line;
        while ((line = fr.readLine()) != null) {
            String[] s = line.split(" ");
//            nameCount.put(s[0],)
        }
    }
}
