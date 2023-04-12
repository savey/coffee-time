package com.java.coffeetime.aop;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Objects;

public class JdkProxy {
    
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JdkProxy.class);


    /**
     * 通过 Proxy.create 生成的对象是代理对象，基于 接口的代理对象，那么有以下几点是需要注意
     *  
     * 1：生成的 代理对象 是实现了 目标接口
     * 2：生成的 代理对象 与 代理目标 是兄弟关系 (都实现了同一个目标接口)
     * 
     */
    public static void main(String[] args) throws InterruptedException {
        
        //方便生成代理文件
        System.setProperty("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");

        // jdkProxy
        jdkProxyTest();
        
        //模拟代理类
//        simulateProxyTest();
        
    }
    
    
    public static void jdkProxyTest() throws InterruptedException {
        //类加载器，负责把生成的class($Proxy???)文件加载到JVM
        ClassLoader loader = JdkProxy.class.getClassLoader();

        //需要代理的目标(对象)
        BeautifulCountryTarget target = new BeautifulCountryTarget();

        //增强目标方法的函数
        InvocationHandler handler = (proxy, method, args) -> {

            LOGGER.debug("大哥你在旁边看着喝Coffee！，武器开给，我来打！");

            return method.invoke(target, args);

        };


        //创建代理人
        Fight w_k_l_Fight = (Fight)Proxy.newProxyInstance(
                loader,
                new Class[]{Fight.class},
                handler
        );
        
        //打印下类路径，方便使用 arthas 进行反张译
        LOGGER.debug("proxy<w_k_l_Fight> class {}", w_k_l_Fight.getClass());
        LOGGER.debug("w_k_l_Fight equals target ? {}", Objects.equals(w_k_l_Fight, target));
        LOGGER.debug("w_k_l_Fight == target ? {}", w_k_l_Fight == target);
        //代理人调用方法
        w_k_l_Fight.shot();
        w_k_l_Fight.bomb();
        
    }


    /**
     * 模拟一个代理类
     */
    public static void simulateProxyTest() {
        
        //需要代理的目标(对象)
        BeautifulCountryTarget target = new BeautifulCountryTarget();

        //增强目标方法的函数
        InvocationHandler handler = (proxy, method, args) -> {

            LOGGER.debug("大哥你在旁边看着喝Coffee！，武器开给，我来打！");

            return method.invoke(target, args);

        };
        
        //创建代理人 这里就相当于是JDK创建出来的代理类 $Proxy?? 
        Fight w_k_l_Fight = new SimulateProxy(handler);


        //代理人调用方法
        w_k_l_Fight.shot();
        w_k_l_Fight.bomb();
    }
    
    
    
    //模拟的和反编译出来的对比
    
}
