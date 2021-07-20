package org.github.chajian.matchgame.board;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.github.chajian.matchgame.data.define.PoolStatus;

import java.util.List;

/**
 * 基础的记分板
 * @author Chajian
 */
public abstract class BaseScore {
    Scoreboard scoreboard;
    List<Objective> objectives;
    List<List<Score>> scores;

    //初始化
    abstract void init();
    //展示给玩家
    public abstract void show(Player player,String title,List<String> lore);
    //根据匹配池状态反馈score给玩家
    public abstract void showByStatus(Player player, PoolStatus poolStatus);
    //隐藏记分板
    public abstract void hide(Player player);
    //更新记分板
    public abstract void update();

}
