package org.github.chajian.matchgame.data.mysql;


import org.apache.ibatis.session.SqlSession;
import org.github.chajian.matchgame.data.IntegralPO;
import org.github.chajian.matchgame.mapper.IntegralMapper;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

public class MySqlManagerTest {
    MySqlManager mySqlManager;

    @Test
    public void test(){
        try {
            mySqlManager = MySqlManager.getMySqlManager();
            IntegralMapper integralMapper = mySqlManager.getSqlSessionFactory().openSession().getMapper(IntegralMapper.class);
            IntegralPO integralPO = integralMapper.selectByPlayerAndGameId("997","bedwar");
            System.out.println(integralPO.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testInsertByMapper(){
        try {
            mySqlManager = MySqlManager.getMySqlManager();
            SqlSession sqlSession = mySqlManager.getSqlSessionFactory().openSession();
            IntegralMapper integralMapper = sqlSession.getMapper(IntegralMapper.class);
            IntegralPO integralPO = new IntegralPO();
            integralPO.setPlayerName("9990");
            integralPO.setGameId("bedwar");
            integralMapper.registerIntegral(integralPO);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
