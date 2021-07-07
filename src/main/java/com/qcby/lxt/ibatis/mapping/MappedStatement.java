package com.qcby.lxt.ibatis.mapping;

import com.qcby.lxt.ibatis.scripting.SqlSource;
import com.qcby.lxt.ibatis.session.Configuration;

import javax.crypto.KeyGenerator;

/**
 * @className: MappedStatement
 * @description:
 * @author: lxt
 * @create: 2021-07-07 00:47
 **/
public class MappedStatement {

    private String resource;
    private Configuration configuration;
    private String id;
    private SqlSource sqlSource;
    private SqlCommandType sqlCommandType;
    private Class<?> resultType;

    public MappedStatement(Configuration configuration, String id, SqlSource sqlSource, SqlCommandType sqlCommandType, Class<?> resultType) {
        this.configuration = configuration;
        this.id = id;
        this.sqlSource = sqlSource;
        this.sqlCommandType = sqlCommandType;
        this.resultType = resultType;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public void setSqlSource(SqlSource sqlSource) {
        this.sqlSource = sqlSource;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public void setResultType(Class<?> resultType) {
        this.resultType = resultType;
    }
}
