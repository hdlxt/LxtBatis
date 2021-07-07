package com.qcby.lxt.mybatis.builder;

import com.qcby.lxt.mybatis.exception.LxtBatisException;
import com.qcby.lxt.mybatis.session.Configuration;
import com.qcby.lxt.mybatis.session.DefaultSqlSessionFactory;
import com.qcby.lxt.mybatis.session.SqlSessionFactory;

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
