package com.qcby.lxt.mybatis.io;

import java.io.InputStream;

/**
 * @className: Resources
 * @description:
 * @author: lxt
 * @create: 2021-07-06 15:54
 **/
public class Resources {



    public static InputStream getResourceAsStream(String resource){
        return  Resources.class.getClassLoader().getResourceAsStream(resource);
    }

    public static Class<?> classForName(String className) {
        try {
            Class<?> c = Class.forName(className);
            if (null != c) {
                return c;
            }
        } catch (ClassNotFoundException e) {
            // we'll ignore this until all classloaders fail to locate the class
        }
        return null;
    }
}
