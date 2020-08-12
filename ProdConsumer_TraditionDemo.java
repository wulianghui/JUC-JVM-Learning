package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareDate{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try{
            //1 判断
            while(number != 0){
                //等待，不能生产
                condition.await();
            }
            //2 干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t"+number);
            //3 通知环形
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }

    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try{
            //1 判断
            while(number == 0){
                //等待，不能生产
                condition.await();
            }
            //2 干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t"+number);
            //3 通知环形
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }


    }
}

//题目：一个初始值为零的变量，两个线程对其交替操作，一个加1一个减1，来5轮

//1 线程      操作      资源类
//2 判断      干活      通知
//3 防止虚假唤醒机制

public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {

        ShareDate shareDate = new ShareDate();

        new Thread(()->{
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareDate.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
          },"AA").start();

        new Thread(()->{
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareDate.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareDate.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
          },"CC").start();

        new Thread(()->{
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareDate.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();

    }
}
