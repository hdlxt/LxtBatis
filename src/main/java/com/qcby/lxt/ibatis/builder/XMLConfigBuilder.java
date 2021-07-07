package com.qcby.lxt.ibatis.builder;

import com.qcby.lxt.ibatis.exception.LxtBatisException;
import com.qcby.lxt.ibatis.io.Resources;
import com.qcby.lxt.ibatis.parsing.XNode;
import com.qcby.lxt.ibatis.parsing.XPathParser;
import com.qcby.lxt.ibatis.session.Configuration;

import java.io.InputStream;

/**
 * @className: XmlConfiguationBuilder
 * @description:
 * @author: lxt
 * @create: 2021-07-06 10:58
 **/
public class XMLConfigBuilder extends BaseBuilder{

    private XPathParser parser;

    public XMLConfigBuilder(InputStream inputStream) {
        super(new Configuration());
        this.parser = new XPathParser(inputStream);
    }

    public Configuration parse() {
        parseConfiguration(parser.evalNode("/configuration"));
        return configuration;
    }


    private void parseConfiguration(XNode xNode) {
        mapperElement(xNode.evalNode("mappers"));
    }

    private void mapperElement(XNode parent){
        if(parent != null){
            for (XNode child : parent.getChildren()) {
                /**
                 *  支持：
                 *      1.  <mapper resource="org/mybatis/builder/AuthorMapper.xml"/>
                 *      2.  <mapper class="org.mybatis.builder.AuthorMapper"/>
                 *      3.  <package name="org.mybatis.builder"/>
                 */
                if("package".equals(parent.getName())){

                }else{
                    String resource = child.getStringAttribute("resource");
                    String mapperClass = child.getStringAttribute("class");
                    if (resource != null && mapperClass == null) {
                        InputStream inputStream = Resources.getResourceAsStream(resource);
                        XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration,resource);
                        mapperParser.parse();
                    } else if (resource == null  &&  mapperClass != null) {
                        Class<?> mapperInterface = Resources.classForName(mapperClass);
                        configuration.addMapper(mapperInterface);
                    } else {
                        throw new LxtBatisException("A mapper element may only specify a url, resource or class, but not more than one.");
                    }
                }
            }
        }
    }
}
