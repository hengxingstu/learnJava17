package org.hengxing.exer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderTest {
    public static final String file = "save.bin";
    public static final String path = "data";

    public static void main(String[] args) throws IOException {
        //先让用户填写订单，存到order对象中
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> list = new ArrayList<>();
        Product p = new Product();
        String confirm = "123";
        do {

            System.out.println("please input product name:");
            p.setName(scanner.nextLine());
            System.out.println("please input product price:");
            p.setPrice(scanner.nextDouble());
            System.out.println("please input product quantity:");
            p.setQuantity(scanner.nextInt());
            list.add(p);
            confirm = scanner.nextLine();//为什么这里的nextLine不执行？
            System.out.println("Continue?(Y/N):");
        }while ("y".equals(confirm.toLowerCase()));

        System.out.println(list);

        scanner.close();
        //初始化文件，打开流
//        File f = new File(path, file);
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
//        oos.writeObject();
    }


}
