package com.qcby.lxt.ibatis.binding;

import com.qcby.lxt.ibatis.exception.LxtBatisException;
import com.qcby.lxt.ibatis.mapping.MappedStatement;
import com.qcby.lxt.ibatis.mapping.SqlCommandType;
import com.qcby.lxt.ibatis.reflection.ParamNameResolver;
import com.qcby.lxt.ibatis.session.Configuration;
import com.qcby.lxt.ibatis.session.SqlSession;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @className: MapperMethod
 * @description:
 * @author: lxt
 * @create: 2021-07-06 16:37
 **/
public class MapperMethod {

    private MappedStatement ms;
    private boolean returnsMany;
    private ParamNameResolver paramNameResolver;

    public <T> MapperMethod(Class<T> mapperInterface, Method method, Configuration configuration) {
        paramNameResolver = new ParamNameResolver(configuration,method);
        ms = resolveMappedStatement(mapperInterface,method.getName(),configuration);
        this.returnsMany = true;
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        Object result = null;
        switch (ms.getSqlCommandType()) {
            case INSERT:
                break;
            case UPDATE:
                break;
            case DELETE:
                break;
            case SELECT:
                if (returnsMany) {
                    Object param = paramNameResolver.getNamedParams(args);
                    result = sqlSession.selectList(ms.getId(), param);
                }else{
                    Object param = paramNameResolver.getNamedParams(args);
                    result = sqlSession.selectOne(ms.getId(),param);
                }
                break;
            default:
                throw new LxtBatisException("Unknown execution method for: " + ms.getId());
        }
        return result;
    }

    private MappedStatement resolveMappedStatement(Class<?> mapperInterface, String methodName,Configuration configuration) {
        String statementId = mapperInterface.getName() + "." + methodName;
        if (configuration.hasStatement(statementId)) {
            return configuration.getMappedStatement(statementId);
        }
        return null;
    }
}
