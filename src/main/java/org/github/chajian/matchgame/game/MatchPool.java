package org.github.chajian.matchgame.game;

import org.bukkit.entity.Player;
import org.github.chajian.matchgame.bar.BaseBar;
import org.github.chajian.matchgame.board.BaseScore;
import org.screamingsandals.bedwars.api.BedwarsAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * 匹配池
 * @author Chajian
 */
public class MatchPool {
    /*玩家*/
    private List<Player> players = new ArrayList<Player>();
    /*池子名*/
    private String name;
    /*游戏id*/
    private String gameId;
    /*进度条*/
    private BaseBar baseBar;
    /*记分板*/
    private BaseScore baseScore;
    /*起床api*/
    BedwarsAPI bedwarsAPI;

    /*初始化*/
    public void init(String type){
    }

}
