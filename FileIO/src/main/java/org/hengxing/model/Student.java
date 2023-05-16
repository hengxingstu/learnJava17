package org.hengxing.model;

import java.io.Serializable;

public class Student implements Serializable {
    String name;
    Integer age;
    Integer score;
    String word;
    account acct;

    public static final long serialVersionUID = 1314L;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", word='" + word + '\'' +
                ", acct=" + acct +
                '}';
    }

    public Student() {
    }

    public Student(String name, Integer age, Integer score, String word, account acct) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.word = word;
        this.acct = acct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public account getAcct() {
        return acct;
    }

    public void setAcct(account acct) {
        this.acct = acct;
    }
}

class account implements Serializable{
    double balance;
    static final long serialVersionUID = 112314L;

    @Override
    public String toString() {
        return "account{" +
                "balance=" + balance +
                '}';
    }

    public account() {
    }

    public account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
