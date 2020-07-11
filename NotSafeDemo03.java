package com.atguigu.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author wlh
 * @create 2020-7-9
 *
 * 1故障现象
 * java.util.ConcurrentModificationException
 *
 * 2导致原因
 * 多线程争抢同一个资源类
 *
 * 3解决方法
 * 3.1 new Vector<>
 * 3.2 Collections.
 *
 * 4优化建议（同样的错误不犯第二次）
 */

public class NotSafeDemo03 {
    public static void main(String[] args) {

       Map<String,String> map = new ConcurrentHashMap<>();
       for(int i = 1;i<=30;i++){
           new Thread(()-> {
               map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
               System.out.println(map);
           },String.valueOf(i)).start();
       }
    }

    private static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i=0; i<=30;i++){
            new Thread(() -> {
            set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);},String.valueOf(i)).start();
        }
    }
    //list.forEach(System.out::println);



    public static void listNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>();   //Collections.synchronizedList(new ArrayList<>());   //vector //new ArrayList<>();


        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
