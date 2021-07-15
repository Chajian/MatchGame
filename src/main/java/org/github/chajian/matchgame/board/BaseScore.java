package org.github.chajian.matchgame.board;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

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
    abstract void show(Player player);
    //隐藏记分板
    abstract void hide(Player player);
    //更新记分板
    abstract void update();
    //读取数据库数据
    abstract void readSql();

}
