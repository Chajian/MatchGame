package org.github.chajian.matchgame.game;

import org.bukkit.entity.Player;

/**
 * 小游戏统一接口
 */
public interface GameApi {

    void joinGame(Player player);
    void joinGame(Player player,Team team);


}
