package org.github.chajian.matchgame.game;

import org.bukkit.entity.Player;
import org.github.chajian.matchgame.bar.NoteBar;

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
    /*提醒消息*/
    private NoteBar noteBar;


}
