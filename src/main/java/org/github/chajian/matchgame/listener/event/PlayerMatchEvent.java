package org.github.chajian.matchgame.listener.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 玩家匹配事件
 * 需要在玩家匹配游戏的代码块中
 * 调用Bukkit.getPluginManager().callEvent(PlayerMatchEvent)监听才能生效
 * @author YangLin
 *
 */
public class PlayerMatchEvent extends Event implements Cancellable {

    private final String playerName;
    private final Player player;
    private boolean isCancelled;

    public PlayerMatchEvent(String playerName,Player player) {
        this.playerName = playerName;
        this.player = player;
        this.isCancelled = false;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public boolean isCancelled() {
        return this.isCancelled;
    }

    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
    }

    public Player getPlayer() {
        return player;
    }
}
