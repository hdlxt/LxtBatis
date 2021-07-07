package com.qcby.lxt.mybatis;

import com.qcby.lxt.mybatis.application.entity.User;
import com.qcby.lxt.mybatis.application.mapper.UserMapper;
import com.qcby.lxt.mybatis.builder.SqlSessionFactoryBuilder;
import com.qcby.lxt.mybatis.io.Resources;
import com.qcby.lxt.mybatis.session.SqlSession;
import com.qcby.lxt.mybatis.session.SqlSessionFactory;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

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
        InputStream inputStream = Resources.getResourceAsStream("Mybatis-Config.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> userList = mapper.listAll();

        System.out.println(userList);


    }
}
