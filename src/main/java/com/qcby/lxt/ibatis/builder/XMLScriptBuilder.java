package com.qcby.lxt.ibatis.builder;

import com.qcby.lxt.ibatis.parsing.XNode;
import com.qcby.lxt.ibatis.scripting.DynamicSqlSource;
import com.qcby.lxt.ibatis.scripting.RawSqlSource;
import com.qcby.lxt.ibatis.scripting.SqlSource;
import com.qcby.lxt.ibatis.session.Configuration;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

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
