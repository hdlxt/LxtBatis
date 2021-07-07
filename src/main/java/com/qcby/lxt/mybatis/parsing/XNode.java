package com.qcby.lxt.mybatis.parsing;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @className: XNode
 * @description: 节点信息
 * @author: lxt
 * @create: 2021-07-06 14:41
 **/
public class XNode {
    private  Node node;
    private  String name;
    private  String body;
    private  Properties attributes;
    private  XPathParser xpathParser;


    public XNode( XPathParser xpathParser,Node node) {
        this.node = node;
        this.name = node.getNodeName();
        this.body = node.getTextContent();
        this.attributes = parseAttr(node);
        this.xpathParser = xpathParser;
    }

    public  Properties parseAttr(Node node){
        Properties attributes = new Properties();
        NamedNodeMap namedNodeMap = node.getAttributes();
        if(namedNodeMap != null){
            for (int j = 0; j < namedNodeMap.getLength(); j++) {
                Node attribute = namedNodeMap.item(j);
                attributes.put(attribute.getNodeName(),attribute.getNodeValue());
            }
        }
        return attributes;
    }

    public List<XNode> getChildren() {
        List<XNode> children = new ArrayList<>();
        NodeList nodeList = node.getChildNodes();
        if (nodeList != null) {
            for (int i = 0, n = nodeList.getLength(); i < n; i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    children.add(new XNode(xpathParser,node));
                }
            }
        }
        return children;
    }

    public XNode evalNode(String expression) {
        return xpathParser.evalNode(node, expression);
    }

    public List<XNode> evalNodes(String expression) {
        return xpathParser.evalNodes(node, expression);
    }


    public String getStringAttribute(String name) {
        return attributes.getProperty(name);
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Properties getAttributes() {
        return attributes;
    }

    public void setAttributes(Properties attributes) {
        this.attributes = attributes;
    }

    public XPathParser getXpathParser() {
        return xpathParser;
    }

    public void setXpathParser(XPathParser xpathParser) {
        this.xpathParser = xpathParser;
    }
}
