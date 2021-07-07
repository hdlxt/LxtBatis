package com.qcby.lxt.mybatis.executor;

import com.qcby.lxt.mybatis.mapping.MappedStatement;

import java.sql.SQLException;
import java.util.List;

public interface Executor {


    <E> List<E> query(MappedStatement ms, Object parameter) throws SQLException;
}
