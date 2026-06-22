package com.database.homework.pangdonglaishop.util;

@SuppressWarnings("unchecked")
public class ThreadLocalUtil {
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();
    //获取THREAD_LOCAL中的值
    public static<T> T get(){
        return (T) THREAD_LOCAL.get();
    }
    //设置THREAD_LOCAL中的值
    public static<T> void set(Object value){
        THREAD_LOCAL.set(value);
    }

    //移除THREAD_LOCAL,防止内存泄漏问题
    public static void remove(){
        THREAD_LOCAL.remove();
    }
}
