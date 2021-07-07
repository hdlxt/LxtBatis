package com.qcby.lxt.mybatis.session;

import com.qcby.lxt.mybatis.exception.LxtBatisException;
import com.qcby.lxt.mybatis.executor.Executor;


/**
 * @className: DefaultSqlSessionFactory
 * @description:
 * @author: lxt
 * @create: 2021-07-06 11:12
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration config) {
        this.configuration = config;
    }


    @Override
    public SqlSession openSession() {
        return openSessionFromDataSource();
    }

    private SqlSession openSessionFromDataSource() {
        try {
            final Executor executor = configuration.newExecutor();
            return new DefaultSqlSession(configuration, executor);
        } catch (Exception e) {
            throw new LxtBatisException("Error opening session.  Cause: " + e, e);
        }
    }
}
