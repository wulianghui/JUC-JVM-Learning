package com.atguigu.juc;


class Aircondition{

    private int number = 0;

    public synchronized void increment() throws Exception{

        //1 判断
        if(number != 0){
            this.wait();
        }
        //2 干活
        number ++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);

        //3 通知
        this.notifyAll();
    }

    public synchronized void decrement() throws Exception{
        number --;
    }
}
/**
 *
 * 题目：现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对变量加1，一个线程对该变量减1，
 * 实现交替，来10轮，变量初始值为零
 *
 * 1    高聚低合前提下，线程操作资源类
 * 2    判断/干活/通知
 */
public class ProdConsumerDemo04 {


}
