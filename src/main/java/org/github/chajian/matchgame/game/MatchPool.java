package org.github.chajian.matchgame.game;

import lombok.Data;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Player;
import org.github.chajian.matchgame.bar.BaseBar;
import org.github.chajian.matchgame.bar.NoteBar;
import org.github.chajian.matchgame.board.BaseScore;
import org.github.chajian.matchgame.board.LobbyScoreBoard;
import org.screamingsandals.bedwars.api.BedwarsAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * 匹配池
 * @author Chajian
 */
@Data
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

    public MatchPool(String type) {
        init(type);
    }

    /*初始化*/
    public void init(String type){
        switch (type){
            case "bedwar":
                name = "起床战争";
                gameId = "bedwar";

                break;

            case "pushcar":

                break;
        }
        baseBar = new NoteBar("起床战争",100, BarColor.BLUE,"剩余%s秒将开始!");
        baseScore = new LobbyScoreBoard();
    }

    /**
     * 玩家加入匹配
     * @param player
     */
    public void joinPlayer(Player player){
        baseScore.show(player);
        player.sendMessage("您已加入匹配!");
    }

}
