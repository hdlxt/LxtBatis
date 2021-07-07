package com.qcby.lxt.ibatis.builder;

import com.qcby.lxt.ibatis.exception.LxtBatisException;
import com.qcby.lxt.ibatis.io.Resources;
import com.qcby.lxt.ibatis.parsing.XNode;
import com.qcby.lxt.ibatis.parsing.XPathParser;
import com.qcby.lxt.ibatis.session.Configuration;

import java.io.InputStream;
import java.util.List;

/**
 * @className: XMLMapperBuilder
 * @description:
 * @author: lxt
 * @create: 2021-07-06 16:01
 **/
public class XMLMapperBuilder extends BaseBuilder{

    private XPathParser parser;
    private  String resource;
    private  String currentNamespace;
    public XMLMapperBuilder(InputStream inputStream, Configuration configuration,String resource) {
        super(configuration);
        this.parser = new XPathParser(inputStream);
        this.resource = resource;
    }

    public void parse() {
        configurationElement(parser.evalNode("/mapper"));
        bindMapperForNamespace();
    }

    private void configurationElement(XNode context) {
        try {
            String namespace = context.getStringAttribute("namespace");
            if (namespace == null || namespace.equals("")) {
                throw new LxtBatisException("Mapper's namespace cannot be empty");
            }
            setCurrentNamespace(namespace);
            buildStatementFromContext(context.evalNodes("select|insert|update|delete"));
        } catch (Exception e) {
            throw new LxtBatisException("Error parsing Mapper XML. The XML location is '" + resource + "'. Cause: " + e, e);
        }
    }

    private void buildStatementFromContext(List<XNode> list) {
        for (XNode context : list) {
            final XMLStatementBuilder statementParser = new XMLStatementBuilder(configuration, context);
            try {
                statementParser.parseStatementNode(currentNamespace);
            } catch (Exception e) {
                throw  new LxtBatisException(e.getMessage(),e);
            }
        }
    }

    private void bindMapperForNamespace() {
        if (currentNamespace != null) {
            Class<?> boundType = null;
            boundType = Resources.classForName(currentNamespace);
            if (boundType != null) {
                if (!configuration.hasMapper(boundType)) {
                    configuration.addMapper(boundType);
                }
            }
        }
    }

    public void setCurrentNamespace(String currentNamespace) {
        if (currentNamespace == null) {
            throw new LxtBatisException("The mapper element requires a namespace attribute to be specified.");
        }
        this.currentNamespace = currentNamespace;
    }
}
