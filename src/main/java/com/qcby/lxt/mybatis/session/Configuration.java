package com.qcby.lxt.mybatis.session;

import com.qcby.lxt.mybatis.binding.MapperRegistry;
import com.qcby.lxt.mybatis.executor.Executor;
import com.qcby.lxt.mybatis.executor.SimpleExecutor;
import com.qcby.lxt.mybatis.executor.parameter.DefaultParameterHandler;
import com.qcby.lxt.mybatis.executor.parameter.ParameterHandler;
import com.qcby.lxt.mybatis.executor.resultset.DefaultResultSetHandler;
import com.qcby.lxt.mybatis.executor.resultset.ResultSetHandler;
import com.qcby.lxt.mybatis.executor.statement.SimpleStatementHandler;
import com.qcby.lxt.mybatis.executor.statement.StatementHandler;
import com.qcby.lxt.mybatis.mapping.MappedStatement;
import sun.plugin2.main.server.ResultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: Configuration
 * @description:
 * @author: lxt
 * @create: 2021-07-06 11:03
 **/
public class Configuration {

    /**
     * mapper接口注册机
     */
    protected final MapperRegistry mapperRegistry = new MapperRegistry(this);
    /**
     *
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    protected boolean cacheEnabled = true;
    /**
     * 是否获取参数真实名字
     */
    protected boolean useActualParamName = true;


    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public boolean hasStatement(String statementName) {
        return mappedStatements.containsKey(statementName);
    }

    public Executor newExecutor() {
        // 目前支持最简单的执行器
        Executor executor = new SimpleExecutor(this);
        if (true) {
            // 缓存生效  装饰执行器
//            executor = new CachingExecutor(executor);
        }
        // 插件生效，生成代理对象
//        executor = (Executor) interceptorChain.pluginAll(executor);
        return executor;
    }

    public boolean isUseActualParamName() {
        return useActualParamName;
    }

    public void setUseActualParamName(boolean useActualParamName) {
        this.useActualParamName = useActualParamName;
    }




    public MappedStatement getMappedStatement(String statementId) {
        return mappedStatements.get(statementId);
    }

    public StatementHandler newStatementHandler(Executor executor,MappedStatement ms, Object parameter) {
        StatementHandler statementHandler = new SimpleStatementHandler(executor,ms, parameter);
//        statementHandler = (StatementHandler) interceptorChain.pluginAll(statementHandler);
        return statementHandler;
    }

    public ParameterHandler newParameterHandler(MappedStatement mappedStatement, Object parameterObject) {
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject);
//        parameterHandler = (ParameterHandler) interceptorChain.pluginAll(parameterHandler);
        return parameterHandler;
    }

    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, ParameterHandler parameterHandler) {
        ResultSetHandler resultSetHandler = new DefaultResultSetHandler(executor, mappedStatement, parameterHandler);
//        resultSetHandler = (ResultSetHandler) interceptorChain.pluginAll(resultSetHandler);
        return resultSetHandler;
    }
}
