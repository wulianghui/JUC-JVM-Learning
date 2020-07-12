package com.atguigu.juc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Aircondition{

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws  Exception{

        lock.lock();
        try{

            while(number != 0){
                condition.await();
            }

            number ++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            condition.signalAll();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void decrement() throws Exception{

        lock.lock();
        try{

            while(number == 0){
                condition.await();
            }

            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    /*public synchronized void increment() throws Exception{

        //1 判断
        while (number != 0){
            this.wait();
        }
        //2 干活
        number ++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);

        //3 通知
        this.notifyAll();
    }

    public synchronized void decrement() throws Exception{

        while (number == 0){
            this.wait();
        }

        number --;
        System.out.println(Thread.currentThread().getName() + "\t" + number);

        this.notifyAll();
    }*/


}
/**
 *
 * 题目：现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对变量加1，一个线程对该变量减1，
 * 实现交替，来10轮，变量初始值为零
 *
 * 1    高聚低合前提下，线程操作资源类
 * 2    判断/干活/通知
 * 3    todo 防止虚假唤醒
 *
 * 知识小总结： = 多线程编程套路 + while判断 + 新版写法
 */
public class ProdConsumerDemo04 {

    public static void main(String[] args) {

        Aircondition aircondition =new Aircondition();

        new Thread(()->{

            for (int i = 1; i<=10; i++){
                try {
                    Thread.sleep(200);
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1; i<=10; i++){
                try {
                    Thread.sleep(300);
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 1; i<=10; i++){
                try {
                    Thread.sleep(400);
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 1; i<=10; i++){
                try {
                    Thread.sleep(500);
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"D").start();


    }

}
