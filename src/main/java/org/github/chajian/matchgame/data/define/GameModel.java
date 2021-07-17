package org.github.chajian.matchgame.data.define;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 游戏模式
 *
 * confrontation
 *
 * @author Chajian
 */
@AllArgsConstructor
public enum GameModel {
    CONFRONTATION("对抗模式",0);

    //模式名称显示给用户
    private String name;
    //模式代码存储在数据库中,和用于逻辑判断
    private int code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
