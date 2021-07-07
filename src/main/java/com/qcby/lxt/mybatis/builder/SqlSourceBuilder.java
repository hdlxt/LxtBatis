package com.qcby.lxt.mybatis.builder;

import com.qcby.lxt.mybatis.scripting.SqlSource;
import com.qcby.lxt.mybatis.scripting.StaticSqlSource;
import com.qcby.lxt.mybatis.session.Configuration;

import java.util.HashMap;

/**
 * @className: SqlSourceBuilder
 * @description:
 * @author: lxt
 * @create: 2021-07-07 00:01
 **/
public class SqlSourceBuilder extends BaseBuilder{
    public SqlSourceBuilder(Configuration configuration) {
        super(configuration);
    }

    public SqlSource parse(String originalSql, HashMap<Object, Object> objectObjectHashMap) {
//        GenericTokenParser parser = new GenericTokenParser("#{", "}", handler);
        String sql = originalSql;
        return new StaticSqlSource(configuration, sql);
    }
}
