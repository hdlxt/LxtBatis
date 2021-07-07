package com.qcby.lxt.mybatis.scripting;

import com.qcby.lxt.mybatis.session.Configuration;

/**
 * @className: DynamicSqlSource
 * @description:
 * @author: lxt
 * @create: 2021-07-07 00:08
 **/
public class DynamicSqlSource implements SqlSource{

    private Configuration configuration;

    public DynamicSqlSource(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String getMapperSql() {
        return null;
    }
}
