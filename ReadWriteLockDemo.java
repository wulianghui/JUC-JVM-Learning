package com.atguigu.juc;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{ //资源类

    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
    //private Lock lock = null;

    public void put(String key,Object value){

        rwlock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t 正在写入："+key);
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            rwlock.writeLock().unlock();
        }


    }

    public void get(String key){
        rwlock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t 正在读取：");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成"+result);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            rwlock.readLock().unlock();
        }
    }

}

//仅可  读-读

public class ReadWriteLockDemo {
    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        for (int i = 1; i <= 5; i++) {
            final int tmpInt = i;
            new Thread(()->{
                myCache.put(tmpInt+"",tmpInt+"");
              },String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int tmpInt = i;
            new Thread(()->{
                myCache.get(tmpInt+"");
              },String.valueOf(i)).start();
        }
    }
}
