package com.qcby.lxt.ibatis;

import com.qcby.lxt.ibatis.builder.SqlSessionFactoryBuilder;
import com.qcby.lxt.ibatis.session.SqlSessionFactory;
import org.junit.Test;

/**
 * @className: TestUser
 * @description:
 * @author: lxt
 * @create: 2021-07-06 11:22
 **/
public class TestUser {


    @Test
    public void test(){
//        System.out.println("hello world!");
        // 配置文件名称
        String config = "Mybatis-Config.xml";

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);


    }
}
