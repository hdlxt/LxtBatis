package com.qcby.lxt.ibatis.binding;

import com.qcby.lxt.ibatis.exception.LxtBatisException;
import com.qcby.lxt.ibatis.session.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: MapperRegistry
 * @description:
 * @author: lxt
 * @create: 2021-07-06 16:07
 **/
public class MapperRegistry {

    private final Configuration configuration;

    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();


    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }


    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            if (hasMapper(type)) {
                throw new LxtBatisException("Type " + type + " is already known to the MapperRegistry.");
            }
            boolean loadCompleted = false;
            try {
                knownMappers.put(type, new MapperProxyFactory<>(type));
                loadCompleted = true;
            } finally {
                if (!loadCompleted) {
                    knownMappers.remove(type);
                }
            }
        }
    }



}
