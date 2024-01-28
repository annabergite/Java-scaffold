package com.annabergite.springcli.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @PACKAGE_NAME: com.annabergite.springcli.proxy
 * @USER: annabergite
 * @Description TODO
 * @Date 2024/1/21 17:30
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **///接口
interface Subject {
    void action();
}

//被代理类
class RealSubject implements Subject {

    @Override
    public void action() {
        System.out.println("我是被代理类，记得执行我...");
    }

}

//动态创建代理类，而不是直接实现Subject，想要动态创建代理类要使用到一个接口InvocationHandler
class MyInvocationHandler implements InvocationHandler {

    Object obj;//实现了接口的被代理类的对象的声明

    //该方法作用：①给被代理类的对象实例化②返回一个代理类对象
    public Object Blind(Object obj) {//该类对象调用该方法时，传进去参数为一个对象
        this.obj = obj;//实现了创建对象功能：父类的obj声明指向每某个子类对象
        //动态的返回一个代理类对象，要用到另一个类(Proxy)的静态方法(newProxyInstance)去创建
        //该方法第一个参数动态获取被代理类类加载器，第二个参数动态是被代理类实现什么接口，
        //第三个是实现了InvocationHandler接口的类的对象
        return Proxy.newProxyInstance(obj.getClass().
                getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    //当代理类的对象发起对被重写的方法调用时，都会转换成调用如下方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object retrunValue = method.invoke(obj, args);
        return retrunValue;
    }

}

//接口
interface ClothFactory {
    void protectCloth();
}

//被代理类
class NikeClothFactory implements ClothFactory {
    @Override
    public void protectCloth() {
        System.out.println("Nike工厂生产一批衣服");
    }
}

public class DynamicProxy {


    public static void main(String[] args) {
        //1.创建被代理类对象
        RealSubject rs = new RealSubject();
        //2.创建实现了InvocationHandler接口的类的对象，也就是MyInvocationHandler
        MyInvocationHandler mih = new MyInvocationHandler();
        //3.调用blind()方法，动态的返回一个同样的实现了-对象rs所在类 -实现的接口的代理类的对象
        //或则说动态返回一个对象，这个对象是和被代理类实现相同接口的代理类创建的
        Object obj = mih.Blind(rs);
        Subject sub = (Subject) obj;//此时sub就是代理类的对象
        sub.action();//调用action()方法，会自动转到实现InvocationHandler接口的实现 类中invoke方法的调用

        //再写一个例子，被代理类对象
        NikeClothFactory ncf = new NikeClothFactory();
        ClothFactory cf = (ClothFactory) mih.Blind(ncf);
        cf.protectCloth();

    }

}
