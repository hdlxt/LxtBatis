package com.qcby.lxt.ibatis.builder;

import com.qcby.lxt.ibatis.exception.LxtBatisException;
import com.qcby.lxt.ibatis.io.Resources;
import com.qcby.lxt.ibatis.session.Configuration;
import com.qcby.lxt.ibatis.session.DefaultSqlSessionFactory;
import com.qcby.lxt.ibatis.session.SqlSessionFactory;

import java.io.InputStream;

/**
 * @className: SqlSessionFactoryBuilder
 * @description:
 * @author: lxt
 * @create: 2021-07-06 10:58
 **/
public class SqlSessionFactoryBuilder{

    public SqlSessionFactory build(InputStream inputStream) {
        try {
            XMLConfigBuilder parser = new XMLConfigBuilder(inputStream);
            return build(parser.parse());
        } catch (Exception e) {
            throw new LxtBatisException("Error building SqlSession.", e);
        }
    }


    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }


}
