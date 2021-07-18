package org.github.chajian.matchgame.game.api;

import org.bukkit.entity.Player;
import org.github.chajian.matchgame.game.Team;

/**
 * 小游戏统一接口
 * - 获取目标当前游戏局的主要信息
 * @author chaJian
 */
public interface GameApi {

    void joinGame(Player player,String gameName);
    void joinGame(Player player, String team,String gameName);
    int getJoinedPlayer(String gameName);
    int getGameMaxPlayer(String gameName);
    int getKills(String gameName,Player player);
    int getDeaths(String gameName,Player player);
    /**
     * 获取游戏团队的数量
     * @param gameName 竞技场名
     * @return
     */
    int getGameTeams(String gameName);
    Object getGame(String gameName);

    /**
     * 获取所有的team
     * @param gameName 竞技场名
     * @return
     */
    Object getTeam(String gameName);

}
