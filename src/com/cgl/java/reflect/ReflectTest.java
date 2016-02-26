package com.cgl.java.reflect;

import java.lang.reflect.Method;

/**
 * Created by chenguangliang on 9/18/15.
 */
public class ReflectTest {

    public static void main(String[] args) {
        ReflectTest reflectTest = new ReflectTest();
//        reflectTest.getMethods();
        reflectTest.getClassByNameAtRuntime("com.cgl.java.reflect.ForTest");
    }

    public void getMethods() {
        Method[] methods = ForTest.class.getMethods();//all public methods in Object is also inclued
        for(Method method : methods) {
            System.out.println("method name is:" + method.getName());
        }
    }

    public void getClassByNameAtRuntime(String className) {

        try {
            Class classObj = Class.forName(className);
            Method[] methods = classObj.getDeclaredMethods();
            for(Method method : methods) {
                System.out.println("method name is:" + method.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
