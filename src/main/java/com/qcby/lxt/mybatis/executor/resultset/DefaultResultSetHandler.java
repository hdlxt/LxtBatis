package com.qcby.lxt.mybatis.executor.resultset;

import com.qcby.lxt.mybatis.exception.LxtBatisException;
import com.qcby.lxt.mybatis.executor.Executor;
import com.qcby.lxt.mybatis.executor.parameter.ParameterHandler;
import com.qcby.lxt.mybatis.mapping.MappedStatement;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: DefaultResultSetHandler
 * @description:
 * @author: lxt
 * @create: 2021-07-07 23:04
 **/
public class DefaultResultSetHandler implements ResultSetHandler{
    private Executor executor;
    private MappedStatement mappedStatement;
    private ParameterHandler parameterHandler;

    public DefaultResultSetHandler(Executor executor, MappedStatement mappedStatement, ParameterHandler parameterHandler) {
        this.executor = executor;
        this.mappedStatement = mappedStatement;
        this.parameterHandler = parameterHandler;
    }

    @Override
    public List<Object> handleResultSets(Statement statement) throws SQLException {
        // 获取数据库返回的结果集
        ResultSet resultSet  = statement.getResultSet();
        return handle(resultSet,mappedStatement.getResultType());
    }


    public List<Object> handle(ResultSet resultSet, Class<?> type)  {
        List<Object> resultList = new ArrayList<>();
        try {
            while (resultSet.next()){
                Object object = type.newInstance();
                for (Field field : object.getClass().getDeclaredFields()) {
                    setValue(object, field, resultSet);
                }
                resultList.add(object);
            }
        } catch (Exception e) {
            throw new LxtBatisException(e.getMessage(),e);
        }
        return resultList;
    }


    /**
     * 根据反射判断类型，从ResultSet中取对应类型参数
     */
    private Object getResult(ResultSet rs, Field field) throws SQLException {
        Class type = field.getType();
        String dataName = HumpToUnderline(field.getName()); // 驼峰转下划线
//        String dataName = field.getName(); // 驼峰转下划线
        if (Integer.class == type ) {
            return rs.getInt(dataName);
        }else if (String.class == type) {
            return rs.getString(dataName);
        }else if(Long.class == type){
            return rs.getLong(dataName);
        }else if(Boolean.class == type){
            return rs.getBoolean(dataName);
        }else if(Double.class == type){
            return rs.getDouble(dataName);
        }else{
            return rs.getString(dataName);
        }
    }


    /**
     * 通过反射给属性赋值
     */
    private void setValue(Object pojo, Field field, ResultSet rs)  {
        try{
            Method setMethod = pojo.getClass().getMethod("set" + firstWordCapital(field.getName()), field.getType());
            setMethod.invoke(pojo, getResult(rs, field));
        }catch (Exception e){
            throw  new LxtBatisException(e.getMessage(),e);

        }
    }

    // 数据库下划线转Java驼峰命名
    public static String HumpToUnderline(String para){
        StringBuilder sb=new StringBuilder(para);
        int temp=0;
        if (!para.contains("_")) {
            for(int i=0;i<para.length();i++){
                if(Character.isUpperCase(para.charAt(i))){
                    sb.insert(i+temp, "_");
                    temp+=1;
                }
            }
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 单词首字母大写
     */
    private String firstWordCapital(String word){
        String first = word.substring(0, 1);
        String tail = word.substring(1);
        return first.toUpperCase() + tail;
    }
}
