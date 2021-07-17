package org.github.chajian.matchgame.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.github.chajian.matchgame.data.IntegralPO;

@Mapper
public interface IntegralMapper {
    IntegralPO selectByPlayerAndGameId(@Param("playerName") String playerName, @Param("gameName") String gameName);
}
