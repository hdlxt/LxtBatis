package com.qcby.lxt.ibatis.binding;

import com.qcby.lxt.ibatis.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * @className: MapperProxyFactory
 * @description:
 * @author: lxt
 * @create: 2021-07-06 16:09
 **/
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public Class<T> getMapperInterface() {
        return mapperInterface;
    }


    protected T newInstance(SqlSession sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, mapperProxy);
    }
}
