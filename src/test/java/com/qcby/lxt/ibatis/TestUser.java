package com.qcby.lxt.ibatis;

import com.qcby.lxt.ibatis.application.entity.User;
import com.qcby.lxt.ibatis.application.mapper.UserMapper;
import com.qcby.lxt.ibatis.builder.SqlSessionFactoryBuilder;
import com.qcby.lxt.ibatis.io.Resources;
import com.qcby.lxt.ibatis.session.SqlSession;
import com.qcby.lxt.ibatis.session.SqlSessionFactory;
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


    }
}
