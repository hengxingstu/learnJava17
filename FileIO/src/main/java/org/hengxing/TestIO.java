package org.hengxing;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class TestIO {
    File file = new File("C:\\Users\\hengxing\\Desktop\\AI绘图作品\\商场女孩精细面部，白T，蝴蝶结");

    @Test
    public void test() {
        File abc = new File("abc");
        System.out.println("abc.getAbsolutePath() = " + abc.getAbsolutePath());
    }

    @Test
    public void test02() {
        File f = new File("D:\\Documents\\CodeSpace\\IdeaProjects\\learningJava17\\FileIO\\abc");
        System.out.println("f.getName() = " + f.getName());
        System.out.println("f.getPath() = " + f.getPath());
        System.out.println("f.getAbsolutePath() = " + f.getAbsolutePath());
        System.out.println("f.getAbsoluteFile() = " + f.getAbsoluteFile());
        System.out.println("f.getParent() = " + f.getAbsoluteFile().getParent());
        System.out.println("f.length() = " + f.length());
        System.out.println("f.lastModified() = " + f.lastModified());
    }

    @Test
    public void test03() {
        File f = new File("D:\\Documents\\CodeSpace\\IdeaProjects\\learningJava17\\FileIO\\abc\\abc.txt");
        System.out.println("f.getName() = " + f.getName());
        System.out.println("f.getPath() = " + f.getPath());
        System.out.println("f.getAbsolutePath() = " + f.getAbsolutePath());
        System.out.println("f.getAbsoluteFile() = " + f.getAbsoluteFile());
        System.out.println("f.getParent() = " + f.getAbsoluteFile().getParent());
        System.out.println("f.length() = " + f.length());
        System.out.println("f.lastModified() = " + f.lastModified());
    }

    @Test
    public void test04() {
        File file = new File("D:\\Documents\\CodeSpace\\IdeaProjects\\learningJava17\\FileIO\\abc");
        String[] list = file.list();
        for (String f :
                list) {
            System.out.println(f);
        }
        for (File listFile : file.listFiles()) {
            System.out.println(listFile.getName());
        }

    }

    @Test
    public void test05() {
        File file = new File("D:\\Documents\\CodeSpace\\IdeaProjects\\learningJava17\\FileIO\\abc");
        File file1 = new File("hengxing" + file.getName());

        boolean isRenameSuccess = file.renameTo(file1);
        System.out.println(isRenameSuccess ? "重命名成功":"重命名失败");
        file.exists();
        file.isDirectory();
        file.isFile();
        file.canRead();
        file.canWrite();
        file.isHidden();
    }

    @Test
    public void test06() throws IOException {
        File file = new File("D:\\Documents\\CodeSpace\\IdeaProjects\\learningJava17\\FileIO\\hengxingabc\\1314.txt");
//        System.out.println(file.createNewFile()? "createNewFile successful":"createNewFile failed");
        System.out.println(file.mkdir()? "mkdir successful":"mkdir failed");
//        file.mkdirs();
//        System.out.println(file.delete()? "delete successful":"delete failed");
    }

    @Test
    public void test07() {
        File file = new File("D:\\temp\\iotest\\io1");
        System.out.println("file.mkdir() = " + file.mkdir());
        File file2 = new File("D:\\temp\\iotest\\io2");
        System.out.println("file2.mkdirs() = " + file2.mkdirs());

    }
    @Test
    public void test08() {
        File file = new File("hello.txt");

        File file1 = new File(file.getAbsoluteFile().getParent(), "abc.txt");
        System.out.println("file1.getAbsolutePath() = " + file1.getAbsolutePath());
//        System.out.println(file1.mkdir() ? "mk file1 successful" : "mk file1 failed");
    }

    @Test
    public void test09() {
        File file = new File("C:\\Users\\hengxing\\Desktop\\AI绘图作品");
        String[] list = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".png");
            }
        });
        System.out.println("一共有 " + list.length + "张图片。");
        for (String s :
                list) {
            System.out.println("这是一个图片：" + s);
        }
    }

    @Test
    public void test10() {
        printFileName(file);
    }

    public void printFileName(File file) {
        if (file.isFile()) {
            System.out.println(file.getName());
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f :
                    files) {
                System.out.println(f.getName());
            }
        }
    }

    /**
     * 删除指定目录及其下所有文件
     */
    @Test
    public void test11() {
        File f = new File("D:\\Documents\\CodeSpace\\IdeaProjects\\learningJava17\\FileIO\\hengxingabc");
        delDir(f);
    }
    public void delDir(File file){
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f :
                    files) {
                delDir(f);
            }
        }
        file.delete();
    }

    @Test
    public void test12() {
        /**
         * 计算指定目录占空间的大小
         */
        File f = new File("D:\\Documents\\CodeSpace\\IdeaProjects\\learningJava17\\FileIO\\hengxingabc");
        System.out.println("文件夹的大小 = " + getDirSize(f));
    }
    public long getDirSize(File file){
        long sum = 0;
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                sum += getDirSize(f);
            }
            return sum;
        } else return file.length();

    }
}
