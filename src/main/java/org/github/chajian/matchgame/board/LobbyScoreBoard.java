package org.github.chajian.matchgame.board;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.github.chajian.matchgame.MatchGame;
import org.github.chajian.matchgame.data.MatchVariable;
import org.github.chajian.matchgame.data.config.Configurator;
import org.github.chajian.matchgame.data.define.PoolStatus;
import sun.security.krb5.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
    }

    /**
     * 向玩家展示score信息
     * @param player 玩家对象
     * @param title score标题
     * @param lore score消息主题
     */
    @Override
    public void show(Player player,String title,List<String> lore) {
        scoreboard = MatchGame.getMatchGame().getServer().getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("obj","obj",title);
        for(int i = 0 ; i < lore.size() ; i++){
            String oldS = lore.get(i);
            String newS = MatchVariable.getMatchVariable("bedwar").replace(oldS,player.getName(),"bedwar");
            Score score = null;
            if (newS != null)
                score = objective.getScore(newS);
            else
                score = objective.getScore(oldS);
            score.setScore(i);
        }
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(scoreboard);

    }

    @Override
    public void showByStatus(Player player, PoolStatus poolStatus, Map<String,Object> board) {
        String title = "";
        List<String> lore = new ArrayList<>();
        Map<String,Object> body = (Map<String, Object>) board.get(poolStatus.name());
        switch (poolStatus){
            case WAITING:
                title = (String) body.get("title");
                lore = (List<String>) body.get("lore");
                show(player,title,lore);
                break;

            case BEFORE_START:

                break;

            case START:

                break;

            case ENDING:

                break;
        }
    }

    @Override
    public void hide(Player player) {
        player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
    }

    @Override
    public void update() {

    }

}
