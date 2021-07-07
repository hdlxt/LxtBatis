package com.qcby.lxt.mybatis.scripting;

import com.qcby.lxt.mybatis.session.Configuration;

/**
 * @className: StaticSqlSource
 * @description:
 * @author: lxt
 * @create: 2021-07-07 00:38
 **/
public class StaticSqlSource implements SqlSource{

    private  String sql;
    private  Configuration configuration;


    public StaticSqlSource(Configuration configuration, String sql) {
        this.sql = sql;
        this.configuration = configuration;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String getMapperSql() {
        return this.sql;
    }
}
