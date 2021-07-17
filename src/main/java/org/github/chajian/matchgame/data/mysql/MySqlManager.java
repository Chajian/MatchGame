package org.github.chajian.matchgame.data.mysql;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * mysql管理器
 * 单例模式
 * @author Chajian
 */
public class MySqlManager {
    private SqlSessionFactory sqlSessionFactory;
    private static MySqlManager mySqlManager;

    private MySqlManager() throws IOException {
        String resource = "org/mybatis/match/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    public static MySqlManager getMySqlManager() throws IOException {
        if (mySqlManager == null)
            mySqlManager = new MySqlManager();

        return mySqlManager;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
