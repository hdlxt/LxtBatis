package com.qcby.lxt.ibatis.scripting;

import com.qcby.lxt.ibatis.session.Configuration;

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

}
