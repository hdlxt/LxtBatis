package com.qcby.lxt.ibatis.session;

import com.qcby.lxt.ibatis.binding.MapperRegistry;
import com.qcby.lxt.ibatis.executor.Executor;
import com.qcby.lxt.ibatis.executor.SimpleExecutor;
import com.qcby.lxt.ibatis.mapping.MappedStatement;

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

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
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

    public boolean hasStatement(String statementName) {
        return mappedStatements.containsKey(statementName);
    }


    public MappedStatement getMappedStatement(String statementId) {
        return mappedStatements.get(statementId);
    }
}
