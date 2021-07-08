package com.qcby.lxt.mybatis.binding;

import com.qcby.lxt.mybatis.exception.LxtBatisException;
import com.qcby.lxt.mybatis.mapping.MappedStatement;
import com.qcby.lxt.mybatis.reflection.ParamNameResolver;
import com.qcby.lxt.mybatis.session.Configuration;
import com.qcby.lxt.mybatis.session.SqlSession;
import sun.plugin2.main.server.ResultHandler;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Optional;

/**
 * @className: MapperMethod
 * @description:
 * @author: lxt
 * @create: 2021-07-06 16:37
 **/
public class MapperMethod {

    private MappedStatement ms;
    private MethodSignature method;

    public <T> MapperMethod(Class<T> mapperInterface, Method method, Configuration configuration) {
        ms = resolveMappedStatement(mapperInterface, method.getName(), configuration);
        this.method = new MethodSignature(configuration, mapperInterface, method);
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
                if (method.returnsMany()) {
                    Object param = method.convertArgsToSqlCommandParam(args);
                    result = sqlSession.selectList(ms.getId(), param);
                } else {
                    Object param = method.convertArgsToSqlCommandParam(args);
                    result = sqlSession.selectOne(ms.getId(), param);
                }
                break;
            default:
                throw new LxtBatisException("Unknown execution method for: " + ms.getId());
        }
        return result;
    }

    private MappedStatement resolveMappedStatement(Class<?> mapperInterface, String methodName, Configuration configuration) {
        String statementId = mapperInterface.getName() + "." + methodName;
        if (configuration.hasStatement(statementId)) {
            return configuration.getMappedStatement(statementId);
        }
        return null;
    }


    public static class MethodSignature {
        private final boolean returnsMany;
        private final Class<?> returnType;
        private final ParamNameResolver paramNameResolver;
        public MethodSignature(Configuration configuration, Class<?> mapperInterface, Method method) {
            Type returnType = method.getGenericReturnType();;
            if (returnType instanceof Class<?>) {
                this.returnType = (Class<?>) returnType;
            } else {
                this.returnType = method.getReturnType();
            }
            this.returnsMany = isCollection(this.returnType) || this.returnType.isArray();
            this.paramNameResolver = new ParamNameResolver(configuration, method);
        }

        public <T> boolean isCollection(Class<T> type) {
            return Collection.class.isAssignableFrom(type);
        }
        public Class<?> getReturnType() {
            return returnType;
        }

        public boolean returnsMany() {
            return returnsMany;
        }

        public Object convertArgsToSqlCommandParam(Object[] args) {
            return paramNameResolver.getNamedParams(args);
        }

    }

}
