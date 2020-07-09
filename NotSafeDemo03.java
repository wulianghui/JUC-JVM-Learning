package com.atguigu.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author wlh
 * @create 2020-7-9
 *
 * 1故障现象
 * java.util.ConcurrentModificationException
 *
 * 2导致原因
 *
 * 3解决方法
 * 3.1 new Vector<>
 * 3.2 Collections.
 *
 * 4优化建议（同样的错误不犯第二次）
 */

public class NotSafeDemo03 {
    public static void main(String[] args) {

        List<String> list = new CopyOnWriteArrayList<>();   //Collections.synchronizedList(new ArrayList<>());   //vector //new ArrayList<>();


        for (int i = 1; i<=30; i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }


        //list.forEach(System.out::println);
    }
}
