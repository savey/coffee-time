package com.java.coffeetime.aop;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 模拟 通过 Proxy.create 出来的 $Proxy?? 类，
 * 
 * 实际可以通过 "arthas" 来 "DCompile" 出类文件
 */
public class SimulateProxy extends Proxy implements Fight {
    
    
    protected SimulateProxy(InvocationHandler h) {
        super(h);
    }
    
    
    /**
     * 射击
     */
    @Override
    public void shot() {
        try {
            // 代理的方法
            Method pMetod = Fight.class.getMethod("shot");

            //调用增强处理器
            super.h.invoke(this, pMetod, null);   
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 炸弹
     */
    @Override
    public void bomb() {
        try {
            // 代理的方法
            Method pMetod = Fight.class.getMethod("bomb");

            //调用增强处理器
            super.h.invoke(this, pMetod, null);   
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
