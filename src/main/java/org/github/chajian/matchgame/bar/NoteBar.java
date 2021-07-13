package org.github.chajian.matchgame.bar;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.*;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Player;
import org.github.chajian.matchgame.MatchGame;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 提醒bar
 * - 用于提醒玩家匹配即将开始，或者重要的信息
 * @author Chajian
 */
public class NoteBar {
    /*bar存在的时间*/
    int maxProgress;
    /*bar的实体对象*/
    BossBar bossBar;
    /*字符串格式*/
    String titleFormat;
    /*命名空间*/
    NamespacedKey namespacedKey;

    int currentProcess;
    double once;

    public NoteBar(String title, int maxProgress, BarColor barColor,String titleFormat) {
        namespacedKey = new NamespacedKey(MatchGame.getMatchGame(),"note");
        this.maxProgress = maxProgress;
        currentProcess = maxProgress;
        this.titleFormat = titleFormat;
        this.bossBar = Bukkit.createBossBar(namespacedKey,title,barColor,BarStyle.SOLID,BarFlag.DARKEN_SKY);
        this.bossBar.setProgress(0.1);
    }

    public BossBar getBossBar() {
        return bossBar;
    }

    //递减bar存在的时间
    public synchronized boolean decrease(){
        currentProcess --;
        setProgress(currentProcess);
        updateTitle();
        return currentProcess == 0;
    }

    /**
     * 设置bar的进度条
     * @param num 数值
     */
    public synchronized void setProgress(int num){
        once = 1/maxProgress;
        bossBar.setProgress(once*num);
    }

    /**
     * 更新Title
     */
    public  synchronized void updateTitle(){
        String title = String.format(titleFormat,currentProcess);
        bossBar.setTitle(title);
    }

    /**
     * 销毁bar
     */
    public synchronized void destory(){
        getBossBar().removeAll();
        getBossBar().setVisible(false);
        Bukkit.removeBossBar(namespacedKey);
    }
}