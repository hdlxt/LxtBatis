package com.qcby.lxt.ibatis.application.entity;

/**
 * @className: User
 * @description:
 * @author: lxt
 * @create: 2021-07-07 14:37
 **/
public class User {

    private Long id;
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
