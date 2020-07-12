package com.atguigu.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Runnable{

    @Override
    public void run() {

    }
}



class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("come in call ******");
        return 1024;
    }
}

/**
 * @author zzyy
 * @create 2019-04-13 16:37
 */

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Thread t1 = new Thread(new MyThread(),"A");
//        t1.start();
        FutureTask<Integer> futureTask = new FutureTask(new MyThread2());

        new Thread(futureTask,"A").start();

        Integer result = futureTask.get();
        System.out.println(result);

    }
}
