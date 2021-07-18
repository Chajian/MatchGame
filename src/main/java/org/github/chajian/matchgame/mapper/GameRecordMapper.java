package org.github.chajian.matchgame.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.github.chajian.matchgame.data.GameRecordPO;

/**
 * 游戏记录
 * @author Chajian
 */
@Mapper
public interface GameRecordMapper {
    /**
     * 通过玩家和游戏获取游戏记录
     * @param playerName 玩家名
     * @param gameName 游戏名
     * @return 游戏记录
     */
    GameRecordPO selectGameRecordByPlayerAndGame(@Param("playerName") String playerName,@Param("gameName") String gameName);

    /**
     * 通过id查询游戏记录
     * @param id id
     * @return 游戏记录
     */
    GameRecordPO selectGameRecordById(@Param("id") int id);
}
