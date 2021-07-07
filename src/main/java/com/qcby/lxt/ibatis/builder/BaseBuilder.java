package com.qcby.lxt.ibatis.builder;

import com.qcby.lxt.ibatis.session.Configuration;

/**
 * @className: BaseBuilder
 * @description:
 * @author: lxt
 * @create: 2021-07-07 00:02
 **/
public abstract class BaseBuilder {

    protected  Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }
}
