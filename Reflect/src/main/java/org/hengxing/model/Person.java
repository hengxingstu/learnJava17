package org.hengxing.model;

public class Person {
    private String name;
    public int age;

    public Person() {
        System.out.println("Person 的构造方法。。。");
    }

    public Person(int age) {
        this.age = age;
    }

    private Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void show() {
        System.out.println("你好，我是一个person");
    }

    private String showNation(String nation) {
        return "我的国籍是" + nation;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
