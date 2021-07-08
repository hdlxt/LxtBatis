package com.qcby.lxt.mybatis.builder;

import com.qcby.lxt.mybatis.exception.LxtBatisException;
import com.qcby.lxt.mybatis.io.Resources;
import com.qcby.lxt.mybatis.mapping.MappedStatement;
import com.qcby.lxt.mybatis.mapping.SqlCommandType;
import com.qcby.lxt.mybatis.parsing.XNode;
import com.qcby.lxt.mybatis.scripting.SqlSource;
import com.qcby.lxt.mybatis.session.Configuration;

import java.util.Locale;

/**
 * @className: XMLStatementBuilder
 * @description:
 * @author: lxt
 * @create: 2021-07-06 23:47
 **/
public class XMLStatementBuilder extends BaseBuilder{
    private XNode context;

    public XMLStatementBuilder(Configuration configuration, XNode context) {
        super(configuration);
        this.context = context;
    }

    public void parseStatementNode(String namespace) {

        String id = context.getStringAttribute("id");

        String nodeName = context.getNode().getNodeName();

        SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));

        String keyStatementId = namespace + "." + id;

        SqlSource sqlSource = createSqlSource(configuration, context);

        String resultType = context.getStringAttribute("resultType");

        Class<?> resultTypeClass = resolveClass(resultType);

        addMappedStatement(keyStatementId, sqlSource, sqlCommandType,resultTypeClass);
    }

    public MappedStatement addMappedStatement(
            String id,
            SqlSource sqlSource,
            SqlCommandType sqlCommandType,
            Class<?> resultType) {

//        id = applyCurrentNamespace(id, false);
        boolean isSelect = sqlCommandType == SqlCommandType.SELECT;
        MappedStatement statement= new MappedStatement(configuration, id, sqlSource, sqlCommandType,resultType);

        configuration.addMappedStatement(statement);
        return statement;
    }

//
//    public String applyCurrentNamespace(String base) {
//        if (base.startsWith(currentNamespace + ".")) {
//            return base;
//        }
//        if (base.contains(".")) {
//            throw new BuilderException("Dots are not allowed in element names, please remove it from " + base);
//        }
//        return currentNamespace + "." + base;
//    }


    public SqlSource createSqlSource(Configuration configuration, XNode script) {
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script);
        return builder.parseScriptNode();
    }
    protected Class<?> resolveClass(String alias) {
        try {
            return Resources.classForName(alias);
        } catch (Exception e) {
            throw new LxtBatisException("Error resolving class. Cause: " + e, e);
        }
    }


}
