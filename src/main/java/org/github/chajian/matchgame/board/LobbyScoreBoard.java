package org.github.chajian.matchgame.board;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.github.chajian.matchgame.MatchGame;

/**
 * 大厅计分板
 * - 当玩家进行匹配时显示
 * - 显示玩家的段位，胜率，杀敌，死亡等
 * - 显示当前匹配状态，匹配人数等
 *
 * @author Chajian
 */
public class LobbyScoreBoard extends BaseScore{


    @Override
    void init() {
        scoreboard = MatchGame.getMatchGame().getServer().getScoreboardManager().getNewScoreboard();
        Objective title = scoreboard.registerNewObjective("title","title");

        title.setDisplaySlot(DisplaySlot.SIDEBAR);
        title.setDisplayName("匹配中请稍候");
    }

    @Override
    void show(Player player) {
        player.setScoreboard(scoreboard);
    }

    @Override
    void hide(Player player) {
        player.setScoreboard(null);
    }

    @Override
    void update() {

    }

    @Override
    void readSql() {

    }
}
