package com.qcby.lxt.ibatis.session;

import java.util.List;

public interface SqlSession {


    <T> T selectOne(String statement,Object object);

    <E> List<E> selectList(String statement, Object parameter);

    <T> T getMapper(Class<T> type);

    Configuration getConfiguration();
}
