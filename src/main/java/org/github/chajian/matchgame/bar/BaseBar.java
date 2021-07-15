package org.github.chajian.matchgame.bar;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;

/**
 *基础的递减Bar
 * @author Chajian
 */
public abstract class BaseBar {
    /*标题*/
    String title;
    /*bar*/
    BossBar bossBar;
    /*标题格式*/
    String titleFormat;
    /*唯一命名空间*/
    NamespacedKey namespacedKey;
    /*当前的进度条剩余时间*/
    int currentProcess;
    /*进度条一共存在时间*/
    double maxProgress;
    /*每次process递减的值*/
    double once;
    //初始化bar
    abstract void initBar();
    //递减
    abstract boolean decrease();
    void setProgress(int num){
        once = 1/maxProgress;
        bossBar.setProgress(once*num);
    }
    //更新Title
    void updateTitle(){
        String title = String.format(titleFormat,currentProcess);
        bossBar.setTitle(title);
    }
    /**
     * 销毁bar
     */
    public synchronized void destory(){
        bossBar.removeAll();
        bossBar.setVisible(false);
        Bukkit.removeBossBar(namespacedKey);
    }
}
