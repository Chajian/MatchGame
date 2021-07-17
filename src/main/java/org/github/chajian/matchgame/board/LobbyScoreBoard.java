package org.github.chajian.matchgame.board;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.github.chajian.matchgame.MatchGame;
import org.github.chajian.matchgame.data.config.Configurator;

import java.util.List;

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
        //读取config配置
        YamlConfiguration yamlConfiguration = Configurator.getConfigurator().getConfig();
        Objective title = scoreboard.registerNewObjective("title","title",yamlConfiguration.getString("lobbyscore.title"));
        List<String> infos = (List<String>) yamlConfiguration.getList("lobbyscore.lore");
        infos.forEach(s -> {
            Objective objective = scoreboard.registerNewObjective("item","item",s);
        });
        title.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    @Override
    public void show(Player player) {
        player.setScoreboard(scoreboard);
    }

    @Override
    public void hide(Player player) {
        player.setScoreboard(null);
    }

    @Override
    public void update() {

    }

    @Override
    public void readSql() {

    }
}
