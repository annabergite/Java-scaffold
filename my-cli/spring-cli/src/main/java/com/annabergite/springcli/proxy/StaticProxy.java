package com.annabergite.springcli.proxy;

/**
 * @PACKAGE_NAME: com.annabergite.springcli.proxy
 * @USER: annabergite
 * @Description TODO
 * @Date 2024/1/21 17:31
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/
//代理类
class ProxyClothFactory implements ClothFactory {
    ClothFactory cf;

    //创建代理类对象时，实际传入一个被代理类的对象
    public ProxyClothFactory(ClothFactory cf) {
        this.cf = cf;
    }

    @Override
    public void protectCloth() {
        System.out.println("代理费100，代理类开始执行...");
        cf.protectCloth();
    }

}


public class StaticProxy {

    public static void main(String[] args) {
        NikeClothFactory nc = new NikeClothFactory();//创建被代理类对象
        ProxyClothFactory pc = new ProxyClothFactory(nc);//创建代理类对象

        pc.protectCloth();//此时调用的方法为被代理类中的方法


    }

}
