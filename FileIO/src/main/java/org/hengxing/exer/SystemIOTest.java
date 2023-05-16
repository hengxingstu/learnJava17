package org.hengxing.exer;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemIOTest {
    @Test
    public static void main(String[] args) {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        String s = null;
        System.out.println("输入你的字符：");
        try {
            while ((s = br.readLine()) != null) {
                if ("e".equalsIgnoreCase(s) || "exit".equalsIgnoreCase(s)) {
                    System.out.println("要出来了");
                    break;
                }
                System.out.println("s = " + s.toUpperCase());

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
