package com.qcby.lxt.ibatis.session;

/**
 * @className: SqlSessionFactory
 * @description:
 * @author: lxt
 * @create: 2021-07-06 11:00
 **/
public interface SqlSessionFactory {
    SqlSession openSession();
}
