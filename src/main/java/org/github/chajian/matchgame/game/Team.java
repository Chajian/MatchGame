package org.github.chajian.matchgame.game;

import org.bukkit.Color;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * 组队的团队
 * @author Chajian
 */
public class Team {
    /*队伍成员*/
    private List<Player> players = new ArrayList<Player>();
    /*队伍颜色*/
    private Color color;
}
