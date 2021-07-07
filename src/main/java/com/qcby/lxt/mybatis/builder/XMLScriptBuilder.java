package com.qcby.lxt.mybatis.builder;

import com.qcby.lxt.mybatis.parsing.XNode;
import com.qcby.lxt.mybatis.scripting.DynamicSqlSource;
import com.qcby.lxt.mybatis.scripting.RawSqlSource;
import com.qcby.lxt.mybatis.scripting.SqlSource;
import com.qcby.lxt.mybatis.session.Configuration;

/**
 * @className: XMLScriptBuilder
 * @description:
 * @author: lxt
 * @create: 2021-07-07 00:09
 **/
public class XMLScriptBuilder extends BaseBuilder{

    private boolean isDynamic;
    private XNode context;

    public XMLScriptBuilder(Configuration configuration, XNode context) {
        super(configuration);
        this.context = context;
    }


    public SqlSource parseScriptNode() {
        String sql = parseDynamicTags(context);
        SqlSource sqlSource;
        if (isDynamic) {
            sqlSource = new DynamicSqlSource(configuration);
        } else {
            sqlSource = new RawSqlSource(configuration,sql);
        }
        return sqlSource;
    }

    protected String parseDynamicTags(XNode node) {
        // 获取sql
        String sql = node.getBody().trim();
        // 判断是否动态sql
        if(sql.contains("#{") && sql.contains("}")){
            isDynamic = true;
        }
        return sql;
    }
}
