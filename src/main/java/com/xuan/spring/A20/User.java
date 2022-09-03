package com.xuan.spring.A20;

public class User {
    User(String name, long age) {
        this.Name = name;
        this.age = age;
    }

    String Name;
    long age;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }
}
