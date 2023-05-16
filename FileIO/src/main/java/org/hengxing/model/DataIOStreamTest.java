package org.hengxing.model;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

public class DataIOStreamTest {

    int age = 300;
    char gender = '男';
    int energy = 5000;
    double price = 75.5;
    boolean relive = true;

    String name = "巫师";
//    Student stu = new Student("张三",23,89);

    File data = new File("data.dat");

    @Test
    public void test01() throws IOException {
        ObjectOutputStream dos = new ObjectOutputStream(new FileOutputStream(data));

        dos.writeUTF("人之劳苦，何益之有。彼造万物，皆适其时，置永恒之念于人心。然其所为，人莫能测。");
        dos.flush();

        dos.writeObject("枯木生于不毛之地");
        dos.close();
    }

    @Test
    public void test02() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(data));
        String s = ois.readUTF();
        System.out.println("s = " + s);

        String o = (String) ois.readObject();
        System.out.println("o = " + o);

        ois.close();
    }

    @Test
    public void test03() throws IOException {
        ObjectOutputStream dos = new ObjectOutputStream(new FileOutputStream(data));
        ArrayList<Student> list = new ArrayList<>();
//        list.add(new Student("coco", 3, 52,"百毒不侵！"));
//        list.add(new Student("hengxing", 19, 90,"百毒不侵！"));
//        list.add(new Student("tifa", 24, 62,"百毒不侵！"));

//        Student.setWord("百毒不侵！");
        dos.writeObject(list);
        dos.flush();
        dos.close();
    }


    @Test
    public void test04() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(data));
        ArrayList<Student> list = (ArrayList<Student>) ois.readObject();
        System.out.println("list = " + list);
    }

    @Test
    public void test05() throws IOException {
        ObjectOutputStream dos = new ObjectOutputStream(new FileOutputStream(data));

//        dos.writeObject(new Student("coco", 3, 52));
//        dos.writeObject(new Student("hengxing", 19, 90,"百毒不侵！"));
//        dos.writeObject(new Student("tifa", 24, 62,"百毒不侵！"));

        dos.flush();
        dos.close();
    }

    @Test
    public void test06() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(data));
        Student s = (Student) ois.readObject();
        System.out.println("s = " + s);
    }

    @Test
    public void test07() throws IOException {
        Student student = new Student("hengxing", 22, 98, "生而不同", new account(9000));
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(data));
        oos.writeObject(student);
        oos.close();
    }

    @Test
    public void test08() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(data));
        Student s = (Student) ois.readObject();
        System.out.println("s = " + s);
    }

    @Test
    public void test09() throws FileNotFoundException {
        PrintStream ps = new PrintStream("io.txt");
        ps.println("hello");
        ps.println("鸡汤来咯~~~");

        System.setOut(ps);
        System.out.println("你好呀陌生人");
        ps.close();
    }
}
