package com.qcby.lxt.mybatis.parsing;

import com.qcby.lxt.mybatis.exception.LxtBatisException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: XPathParser
 * @description: XPath解析器
 * @author: lxt
 * @create: 2021-07-06 10:16
 **/
public class XPathParser {
    /**
     *  Xml DOM 节点
     */
    private Document document;
    /**
     *  XPath 解析器，用于在XML文件中查找信息
     */
    private XPath xPath;

    public XPathParser(InputStream inputStream){
        XPathFactory factory = XPathFactory.newInstance();
        this.xPath = factory.newXPath();
        this.document = createDocument(new InputSource(inputStream));
    }

    /**
     *  根据流创建DOM节点
     * @param inputSource
     * @return
     */
    private Document createDocument(InputSource inputSource) {
        try {
            // 获取文档建造器工厂
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // 忽略注释
//            factory.setIgnoringComments(true);
            // 获取文档建造器
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new ErrorHandler() {
                @Override
                public void error(SAXParseException exception) throws SAXException {
                    throw exception;
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    throw exception;
                }

                @Override
                public void warning(SAXParseException exception) throws SAXException {
                    // NOP
                }
            });
            return builder.parse(inputSource);
        } catch (Exception e) {
            throw new LxtBatisException("Error creating document instance.  Cause: " + e, e);
        }
    }


    public XNode evalNode(String expression) {
        return evalNode(document, expression);
    }


    public XNode evalNode(Node n,String expression) {
        Node node = (Node) evalNode(expression,n, XPathConstants.NODE);
        if (node == null) {
            return null;
        }
        return new XNode(this,node);
    }



    public Object evalNode( String expression,Object root, QName returnType) {
        try {
            return  xPath.evaluate(expression, root, returnType);
        } catch (Exception e) {
            throw new LxtBatisException("Error evaluating XPath.  Cause: " + e, e);
        }
    }

    public List<XNode> evalNodes(Node root, String expression) {
        List<XNode> xnodes = new ArrayList<>();
        NodeList nodes = (NodeList) evalNode(expression, root, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i++) {
            xnodes.add(new XNode(this, nodes.item(i)));
        }
        return xnodes;
    }
}
