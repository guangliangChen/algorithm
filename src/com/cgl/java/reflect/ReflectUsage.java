package com.cgl.java.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * Created by chenguangliang on 2/25/16.
 */
public class ReflectUsage {
    public static void main(String[] args) {
        Foo f = new Foo();

        //1.get class name from object
        System.out.println(f.getClass().getName());

        //2.invoke method on unknown Class object
        java.lang.reflect.Method method;
        try {
            method = f.getClass().getMethod("print", new Class<?>[0]);
            method.invoke(f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //3.create object from Class instance
        Class<?> c = null;
        try {
            c = Class.forName("com.cgl.java.reflect.Foo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Foo f3 = null;
        try {
            f3 = (Foo)c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        f3.print();

        //4.get constructor and create instance
        Class<?> c4 = null;
        try {
            c4 = Class.forName("com.cgl.java.reflect.Foo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Foo f41 = null;
        Foo f42 = null;
//        c4.getInterfaces();
//        c4.getSuperclass();
//        c4.getDeclaredFields();
//        c4.getDeclaredMethods();
        Constructor<?>[] constructors = c4.getConstructors();
        try {
            f41 = (Foo)constructors[0].newInstance();
            f42 = (Foo)constructors[1].newInstance("abcd");
        } catch (Exception e) {
            e.printStackTrace();
        }
        f41.printS();
        f42.printS();

        //5.change array size though reflection
        int[] intArray = {1, 2, 3, 4, 5};
        int[] newIntArray = (int[])changeArraySize(intArray, 10);
        String[] strArray = {"a", "b", "c", "d", "e"};
        String[] newStrArray = (String[])changeArraySize(strArray, 10);
        print(newIntArray);
        print(newStrArray);
    }

    public static Object changeArraySize(Object obj, int len) {
        Class<?> c = obj.getClass();
        Object newInstance = Array.newInstance(c.getComponentType(), len);
        System.arraycopy(obj, 0, newInstance, 0, Array.getLength(obj));
        return newInstance;
    }

    public static void print(Object obj) {
        //prev check
        Class<?> c = obj.getClass();
        if(!c.isArray()) {
            return;
        }
        for(int i = 0; i < Array.getLength(obj); i++) {
            Object component = Array.get(obj, i);
//            System.out.print(component != null ? component.toString() : "null");
            System.out.print(component + " ");
        }
        System.out.print("\n");
    }

}
class Foo {
    String s;
    public Foo() {}
    public Foo(String s) {
        this.s = s;
    }
    public void print() {
        System.out.println("abc");
    }
    public void printS() {
        System.out.println(s);
    }
}
