package com.qcby.lxt.ibatis.executor;

import com.qcby.lxt.ibatis.mapping.MappedStatement;

import java.sql.SQLException;
import java.util.List;

public interface Executor {


    <E> List<E> query(MappedStatement ms, Object parameter) throws SQLException;
}
