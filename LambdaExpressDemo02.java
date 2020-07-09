package com.atguigu.juc;

//函数式编程有且仅有一个方法

@FunctionalInterface
interface Foo{
    public void sayHello();
    //public int add(int x,int y);
    public default void mul(){
        System.out.println("默认方法1");;
    }
    public default int mul1(int x,int y){
        System.out.println("默认方法2");
        return x*y;
    }
    public static int div(int x,int y){
        return x/y;
    }

}

/**
 * @author wlh
 *
 * 1 函数式编程
 *  int age = 23;
 *
 *  y = kx + 1;
 *
 */

/**
 * 1 拷贝小括号，写死右箭头，落地大括号
 * 2 @FunctionalInterface
 * 3 default
 * 4 static
 */
public class LambdaExpressDemo02 {
    public static void main(String[] args) {
        /*Foo foo = new Foo(){

            @Override
            public void sayHello() {
                System.out.println("hello 1205");
            }

            @Override
            public int add(int x, int y) {
                return x+y;
            }
        };
        foo.sayHello();*/

        /*Foo foo = (int x,int y) -> {System.out.println("come in add method");
        return x+y;};
        System.out.println(foo.add(2,3));*/

        Foo foo =() -> { System.out.println("hello"); };
        System.out.println(Foo.div(4,5));
    }
}
