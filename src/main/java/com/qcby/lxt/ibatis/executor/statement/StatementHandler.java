package com.qcby.lxt.ibatis.executor.statement;

import sun.plugin2.main.server.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface StatementHandler {

    Statement prepare(Connection connection);

    <E> List<E> query(Statement statement) throws SQLException;
}
