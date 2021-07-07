package com.qcby.lxt.ibatis.scripting;

import com.qcby.lxt.ibatis.builder.SqlSourceBuilder;
import com.qcby.lxt.ibatis.session.Configuration;

import java.util.HashMap;

/**
 * @className: RawSqlSource
 * @description:
 * @author: lxt
 * @create: 2021-07-06 23:58
 **/
public class RawSqlSource implements SqlSource{

    private  SqlSource sqlSource;

    public RawSqlSource(Configuration configuration, String sql) {
        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        sqlSource = sqlSourceParser.parse(sql, new HashMap<>());
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public void setSqlSource(SqlSource sqlSource) {
        this.sqlSource = sqlSource;
    }

    @Override
    public String getMapperSql() {
        return this.sqlSource.getMapperSql();
    }
}
