package com.qcby.lxt.ibatis.session;

import com.qcby.lxt.ibatis.exception.LxtBatisException;
import com.qcby.lxt.ibatis.executor.Executor;
import com.qcby.lxt.ibatis.mapping.MappedStatement;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: DefaultSqlSession
 * @description:
 * @author: lxt
 * @create: 2021-07-07 11:20
 **/
public class DefaultSqlSession implements SqlSession{

    private  Configuration configuration;

    private Executor executor;


    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        // Popular vote was to return null on 0 results and throw exception on too many.
        List<T> list = this.selectList(statement, parameter);
        if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() > 1) {
            throw new LxtBatisException("Expected one result (or null) to be returned by selectOne(), but found: " + list.size());
        } else {
            return null;
        }
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        try {
            MappedStatement ms = configuration.getMappedStatement(statement);
            return executor.query(ms, wrapCollection(parameter));
        } catch (Exception e) {
            throw new LxtBatisException("Error querying database.  Cause: " + e, e);
        }
    }

    private Object wrapCollection(final Object object) {
        if (object instanceof Collection) {
            Map<String,Object> map = new HashMap<>();
            map.put("collection", object);
            if (object instanceof List) {
                map.put("list", object);
            }
            return map;
        } else if (object != null && object.getClass().isArray()) {
            Map<String,Object> map = new HashMap<>();
            map.put("array", object);
            return map;
        }
        return object;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
