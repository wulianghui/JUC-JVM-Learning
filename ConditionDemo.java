package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
    private int number = 1;//A:1  B:2  C:3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

   /* public void print5(){
        lock.lock();
        try{
            //1 判断
            while(number != 1){
                //wait
                c1.await();
            }
            //2 干活
            for(int i = 0; i<5; i++){
                System.out.println(Thread.currentThread().getName()+"\t" + i);
            }

            //3 通知
            number = 2;
            c2.signal();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try{
            //1 判断
            while(number != 2){
                //wait
                c2.await();
            }
            //2 干活
            for(int i = 0; i<10; i++){
                System.out.println(Thread.currentThread().getName()+"\t" + i);
            }

            //3 通知
            number = 3;
            c3.signal();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try{
            //1 判断
            while(number != 3){
                //wait
                c3.await();
            }
            //2 干活
            for(int i = 0; i<15; i++){
                System.out.println(Thread.currentThread().getName()+"\t" + i);
            }

            //3 通知
            number = 1;
            c1.signal();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }*/

    public void printn(int n){
        lock.lock();
        try{
            while (number !=1){
                c1.await();
            }
            while  (number !=2){
                c2.await();
            }
            while  (number !=3){
                c3.await();
            }

            for (int i =0 ;i<n;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }

            if( n == 5){
                number = 2;
                c2.signal();
            }else if (n == 10){
                number = 3;
                c3.signal();
            }else if(n == 15){
                number = 1;
                c1.signal();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}

/**
 *
 * 备注：多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 *
 */
public class ConditionDemo
{
    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        new Thread(()->{
            for(int i = 0; i<10; i++){
                shareData.printn(5);
            }
        },"A").start();

        new Thread(()->{
            for(int i = 0; i<10; i++){
                shareData.printn(10);
            }
        },"B").start();

        new Thread(()->{
            for(int i = 0; i<10; i++){
                shareData.printn(15);
            }
        },"C").start();

    }
}
