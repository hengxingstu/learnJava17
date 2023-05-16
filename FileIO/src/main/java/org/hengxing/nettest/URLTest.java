package org.hengxing.nettest;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class URLTest {
    /**
     * 将url资源下载到本地
     */
    @Test
    public void test01() {
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            //1. 获取URL实例
            URL url = new URL("http://localhost:8080/examples/test.png");
            //2. 建立与服务器的连接
            URLConnection urlConnection = url.openConnection();

            //3. 获取输入流，创建输出流
            is = urlConnection.getInputStream();
            File img = new File("myimg.png");
            fos = new FileOutputStream(img);

            //4. 读写数据
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1 ) {
                fos.write(buffer,0,len);
            }
            System.out.println("文件下载完成。。");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5. 关闭流
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
        }


    }
}
