package org.github.chajian.matchgame.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.github.chajian.matchgame.data.define.GameModel;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * 比赛记录
 * @author YangLin
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameRecordPO {
    //玩家名称
    private String palyerName;
    //游戏id
    private String gameId;
    //得分
    private int score;
    //游戏模式
    private GameModel gameModel;
    //杀敌数
    private int kills;
    //死亡数
    private int deaths;
    //时间戳存玩家在游戏中的游玩时间
    private Timestamp timestamp;

    //其他信息
    private HashMap<String,Object> othersInfo = new HashMap<String, Object>();


}
