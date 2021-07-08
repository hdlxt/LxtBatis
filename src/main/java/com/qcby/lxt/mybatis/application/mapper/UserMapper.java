package com.qcby.lxt.mybatis.application.mapper;

import com.qcby.lxt.mybatis.application.entity.User;

import java.util.List;

public interface UserMapper {

    List<User> listAll();

    User getById();
}
