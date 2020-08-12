package com.atguigu.juc;

import java.util.concurrent.atomic.AtomicReference;

class User{
    String name;
    int age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {

        User z3 = new User("z3",22);
        User li4 = new User("li4",25);


        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3,li4)+"\t"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3,li4)+"\t"+atomicReference.get().toString());
    }
}
