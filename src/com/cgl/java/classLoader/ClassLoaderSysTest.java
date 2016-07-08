package com.cgl.java.classLoader;

/**
 * Created by chenguangliang on 9/15/15.
 */
public class ClassLoaderSysTest {
    public static void main(String[] args) {
//        URLClassLoader URLClassLoader = URLClassLoader
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("SystemClass loader:" + systemClassLoader);

        ClassLoader extensionLoader = systemClassLoader.getParent();
        System.out.println("ExtensionClass loader:" + extensionLoader);

        ClassLoader brostLoader = extensionLoader.getParent();
        System.out.println("Brost loader:" + extensionLoader);

        System.out.println("test0708");
    }
}
