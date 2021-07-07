package com.qcby.lxt.mybatis.binding;

import com.qcby.lxt.mybatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @className: MapperProxy
 * @description:
 * @author: lxt
 * @create: 2021-07-06 16:25
 **/
public class MapperProxy<T> implements InvocationHandler {

    private final SqlSession sqlSession;

    private final Class<T> mapperInterface;

    private  Map<Method, MapperMethod> methodCache;


    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final MapperMethod mapperMethod = cachedMapperMethod(method);
        return mapperMethod.execute(sqlSession, args);
    }



    private MapperMethod cachedMapperMethod(Method method) {
        // 这三行和return后的一行代码作用一致=> jdk8  新增Map =>  computeIfAbsent方法
//        MapperMethod mapperMethod = methodCache.get(method);
//        if(mapperMethod == null){
//            methodCache.put(method,new MapperMethod(mapperInterface, method, sqlSession.getConfiguration()));
//        }
        return methodCache.computeIfAbsent(method,
                k -> new MapperMethod(mapperInterface, method, sqlSession.getConfiguration()));
    }
}
