package com.example.majun.sqlitetest;

import java.io.Serializable;

/**
 * Created by majun on 16/12/15.
 */
public class Person implements Serializable {
    public int id;
    public String name;
    public int age;
    public String info;

    public Person(String name, int age, String info) {
        this.age = age;
        this.name = name;
        this.info = info;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
