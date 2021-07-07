package com.qcby.lxt.ibatis.scripting;

import com.qcby.lxt.ibatis.session.Configuration;

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
