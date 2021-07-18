package org.github.chajian.matchgame.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 积分
 * 主键 游戏id，用户名
 * @author Chajian
 *
 */
@Data
public class IntegralPO {
    int id;
    /*玩家名*/
    String playerName;
    /*游戏代码*/
    String gameId;
    /*积分*/
    int score;
    /*胜场*/
    int wins;
    /*败场*/
    int losers;

}
