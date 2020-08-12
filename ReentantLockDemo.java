package com.atguigu.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone1 implements Runnable {
    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getName() + "\tinvoked sendSMS");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName() + "\tinvoked sendEmail");
    }

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\tinvoked get");
            set();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\tinvoked set()");

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}

//1.synchronized
//2.reentrantlock

public class ReentantLockDemo {

    public static void main(String[] args) {
        Phone1 p = new Phone1();

        new Thread(()->{
            try {
                p.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                p.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();


        Thread t3 = new Thread(p,"t3");
        Thread t4 = new Thread(p,"t4");

        t3.start();
        t4.start();
    }
}
