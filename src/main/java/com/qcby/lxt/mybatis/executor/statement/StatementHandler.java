package com.qcby.lxt.mybatis.executor.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface StatementHandler {

    Statement prepare(Connection connection);

    <E> List<E> query(Statement statement) throws SQLException;
}
