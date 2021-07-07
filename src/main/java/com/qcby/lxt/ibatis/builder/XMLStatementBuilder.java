package com.qcby.lxt.ibatis.builder;

import com.qcby.lxt.ibatis.exception.LxtBatisException;
import com.qcby.lxt.ibatis.io.Resources;
import com.qcby.lxt.ibatis.mapping.MappedStatement;
import com.qcby.lxt.ibatis.mapping.SqlCommandType;
import com.qcby.lxt.ibatis.parsing.XNode;
import com.qcby.lxt.ibatis.scripting.SqlSource;
import com.qcby.lxt.ibatis.session.Configuration;

import javax.crypto.KeyGenerator;
import java.util.Locale;

import static com.sun.beans.finder.ClassFinder.resolveClass;

/**
 * @className: XMLStatementBuilder
 * @description:
 * @author: lxt
 * @create: 2021-07-06 23:47
 **/
public class XMLStatementBuilder extends BaseBuilder{
    private XNode context;
    private Configuration configuration;

    public XMLStatementBuilder(Configuration configuration, XNode context) {
        super(configuration);
        this.context = context;
    }

    public void parseStatementNode() {
        String id = context.getStringAttribute("id");
        String nodeName = context.getNode().getNodeName();
        SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
        boolean isSelect = sqlCommandType == SqlCommandType.SELECT;

        String keyStatementId = id;

        SqlSource sqlSource = createSqlSource(configuration, context);

        String resultType = context.getStringAttribute("resultType");

        Class<?> resultTypeClass = resolveClass(resultType);

        addMappedStatement(id, sqlSource, sqlCommandType,resultTypeClass);
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
