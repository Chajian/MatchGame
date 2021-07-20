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
 * - 将重要的信息通过进度条来提醒
 * @author Chajian
 */
public class NoteBar extends BaseBar{


    public NoteBar(String title, int maxProgress, BarColor barColor,String titleFormat) {
        this.title = title;
        this.maxProgress = maxProgress;
        this.currentProcess = (int) maxProgress;
        this.titleFormat = titleFormat;
        initBar();
    }

    public BossBar getBossBar() {
        return bossBar;
    }

    @Override
    void initBar() {
        namespacedKey = new NamespacedKey(MatchGame.getMatchGame(),"note");
        this.bossBar = Bukkit.createBossBar(namespacedKey,title,BarColor.RED,BarStyle.SOLID,BarFlag.DARKEN_SKY);
    }

    //递减bar存在的时间
    @Override
    public synchronized boolean decrease(){
        currentProcess --;
        setProgress(currentProcess);
        updateTitle();
        return currentProcess == 0;
    }

    @Override
    public void show(Player player) {
        bossBar.addPlayer(player);
        //将自己添加到bossbar管理池中
        MatchGame.getMatchGame().getBossbarRunnable().addNoteBar(this);
    }

}
