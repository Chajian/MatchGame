package org.github.chajian.matchgame.data.define;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 匹配模式
 * @author YangLin
 */
@AllArgsConstructor
@Slf4j
public enum MatchModel {
    VIOLENT("无脑模式","当人数满足x人条件就大批量向玩家发送是否进入游戏提示\n" + " * 如果玩家同意就立马将其传送到对应游戏的竞技场，并根据胜率分配队伍");


    private String name;
    private String des;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
