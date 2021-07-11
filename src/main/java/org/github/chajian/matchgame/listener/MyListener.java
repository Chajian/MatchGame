package org.github.chajian.matchgame.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * 事件监听
 * @author YangLin
 */
public class MyListener implements Listener {

    /**
     * 玩家加入事件
     * @param playerJoinEvent 事件对象
     */
    @EventHandler
    public void plyerJoin(PlayerJoinEvent playerJoinEvent){

    }

    /**
     * 玩家被踢事件
     * @param kickEvent
     */
    @EventHandler
    public void playerKick(PlayerKickEvent kickEvent){

    }

    /**
     * 玩家退出服务器
     * @param quitEvent
     */
    @EventHandler
    public void playerQuit(PlayerQuitEvent quitEvent){

    }


}
