package com.java.coffeetime.aop;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.AopProxyFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

public class AopTest {


    private static final Logger LOGGER = LoggerFactory.getLogger(AopTest.class);
    
    
    interface OrderService {
        //支付
        void pay();
        //退款
        void refund();
    }
    
    
    static class ProductOrder implements OrderService {
    
        @PointCutSign()
        @Override
        public void pay() {
            LOGGER.debug("ProductOrder paying");
        }
        
        @PointCutSign()
        @Override
        public void refund() {
            LOGGER.debug("ProductOrder refunding");
        }
    }


    public static void main(String[] args) {
        //创建 pointcut
        Pointcut pointcut = AnnotationMatchingPointcut.forMethodAnnotation(PointCutSign.class);

        
        //创建通知 advice
        Advice advice = (MethodInterceptor) invocation -> {
            String proxyMethodName = invocation.getMethod().getName();
            LOGGER.debug("before execute " + proxyMethodName);
            Object proceed = invocation.proceed();
            LOGGER.debug("after execute " + proxyMethodName);
            return proceed;    
        };

        
        //创建切面  Aspect
         DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
         advisor.setPointcut(pointcut);
         advisor.setAdvice(advice);
            
         
        //创建代理 proxy
        ProductOrder target = new ProductOrder();
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvisor(advisor);
        OrderService proxy = (OrderService) factory.getProxy();
        proxy.pay();
    }
}
