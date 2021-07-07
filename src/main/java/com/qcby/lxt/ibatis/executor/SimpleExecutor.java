package com.qcby.lxt.ibatis.executor;

import com.qcby.lxt.ibatis.mapping.MappedStatement;
import com.qcby.lxt.ibatis.session.Configuration;

import java.sql.Statement;
import java.util.List;

/**
 * @className: SimpleExecutor
 * @description:
 * @author: lxt
 * @create: 2021-07-07 11:41
 **/
public class SimpleExecutor implements Executor{

    private Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter) {
        Statement stmt = null;
//        Configuration configuration = ms.getConfiguration();
//        StatementHandler handler = configuration.newStatementHandler(wrapper, ms, parameter, rowBounds, resultHandler, boundSql);
//        stmt = prepareStatement(handler, ms.getStatementLog());
//        return handler.query(stmt, resultHandler);
        return null;
    }
}
