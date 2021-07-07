package com.qcby.lxt.mybatis.executor.statement;

import com.qcby.lxt.mybatis.exception.LxtBatisException;
import com.qcby.lxt.mybatis.executor.Executor;
import com.qcby.lxt.mybatis.executor.parameter.ParameterHandler;
import com.qcby.lxt.mybatis.executor.resultset.ResultSetHandler;
import com.qcby.lxt.mybatis.mapping.MappedStatement;
import com.qcby.lxt.mybatis.session.Configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @className: SimpleStatementHandler
 * @description:
 * @author: lxt
 * @create: 2021-07-07 22:55
 **/
public class SimpleStatementHandler implements StatementHandler{

    protected ResultSetHandler resultSetHandler;
    protected ParameterHandler parameterHandler;
    protected Configuration configuration;
    protected Executor executor;
    protected MappedStatement mappedStatement;

    public SimpleStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject) {
        this.executor = executor;
        this.mappedStatement = mappedStatement;
        this.configuration = mappedStatement.getConfiguration();
        this.parameterHandler = configuration.newParameterHandler(mappedStatement, parameterObject);
        this.resultSetHandler = configuration.newResultSetHandler(executor, mappedStatement, parameterHandler);
    }

    @Override
    public Statement prepare(Connection connection) {
        Statement statement = null;
        try {
            statement = connection.prepareStatement(mappedStatement.getSqlSource().getMapperSql());
            return statement;
        } catch (Exception e) {
            throw new LxtBatisException("Error preparing statement.  Cause: " + e, e);
        }
    }

    @Override
    public <E> List<E> query(Statement statement) throws SQLException {
        statement.execute(mappedStatement.getSqlSource().getMapperSql());
        return resultSetHandler.handleResultSets(statement);
    }
}
