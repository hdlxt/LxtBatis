package com.qcby.lxt.ibatis.session;

import com.qcby.lxt.ibatis.binding.MapperRegistry;
import com.qcby.lxt.ibatis.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: Configuration
 * @description:
 * @author: lxt
 * @create: 2021-07-06 11:03
 **/
public class Configuration {

    /**
     * mapper接口注册机
     */
    protected final MapperRegistry mapperRegistry = new MapperRegistry(this);
    /**
     *
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();


    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }
}
