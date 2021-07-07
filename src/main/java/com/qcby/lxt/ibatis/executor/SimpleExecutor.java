package com.qcby.lxt.ibatis.executor;

import com.qcby.lxt.ibatis.executor.statement.StatementHandler;
import com.qcby.lxt.ibatis.mapping.MappedStatement;
import com.qcby.lxt.ibatis.session.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
    public <E> List<E> query(MappedStatement ms, Object parameter) throws SQLException {
        Statement stmt = null;
        Configuration configuration = ms.getConfiguration();
        StatementHandler handler = configuration.newStatementHandler(this,ms, parameter);
        stmt = prepareStatement(handler);
        return handler.query(stmt);
    }

    private Statement prepareStatement(StatementHandler handler) {
        Statement stmt;
        Connection connection = getConnection();
        stmt = handler.prepare(connection);
        return stmt;
    }

    protected Connection getConnection(){
        String driver = "com.mysql.jdbc.Driver";
        String url =  "jdbc:mysql://localhost:3306/qcby_temp";
        String username = "root";
        String password = "";
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
