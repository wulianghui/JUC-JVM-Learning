package com.atguigu.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ContainerNotSafeDemo {

    public static void main(String[] args) {
        Map<String,String> map = new ConcurrentHashMap<>();


        for (int i = 1; i <=30 ; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }



    }

    private static void notsafelist() {
        List<String> list = new CopyOnWriteArrayList<>();


        for (int i = 1; i <=30 ; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
              },String.valueOf(i)).start();
        }

        /*
        不要只是会用，底层原理
         */

        //1.故障现象:java.util.ConcurrentModificationException

        //2.导致原因
        //并发争抢修改导致，参考我们的花名册签名情况
        //

        //3.解决方案
        //3.1 new Vector<>();
        //3.2 Collections.synhronizedList(new ArrayList<>());
        //3.3


        //4.优化建议（同样的错误不犯第2次）
    }
}
