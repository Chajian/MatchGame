package org.github.chajian.matchgame.board;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.github.chajian.matchgame.MatchGame;
import org.github.chajian.matchgame.data.MatchVariable;
import org.github.chajian.matchgame.data.config.Configurator;

import java.util.List;
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
    Objective title;

    @Override
    void init() {
    }

    @Override
    public void show(Player player) {
        scoreboard = MatchGame.getMatchGame().getServer().getScoreboardManager().getNewScoreboard();
        title = scoreboard.registerNewObjective("title","title",Configurator.getConfigurator().getConfig().getString("lobbyscore.title"));
        //读取lore信息并转成对应玩家的数据
        List<String> list = (List<String>) Configurator.getConfigurator().getConfig().getList("lobbyscore.lore");
        for(int i = 0 ; i < list.size() ; i++){
            String s = list.get(i);
            String info = MatchVariable.getMatchVariable("bedwar").replace(s,player.getName(),"bedwar");
            scoreboard.registerNewObjective("lore"+i,"lore"+i,info);
        }
        player.setScoreboard(scoreboard);
    }

    @Override
    public void hide(Player player) {
        player.setScoreboard(null);
    }

    @Override
    public void update() {

    }

}
