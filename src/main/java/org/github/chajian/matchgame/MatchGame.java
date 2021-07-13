package org.github.chajian.matchgame;

import lombok.Data;
import lombok.Getter;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.github.chajian.matchgame.command.BaseCommand;
import org.github.chajian.matchgame.command.HandlerCommand;
import org.github.chajian.matchgame.command.HelpCommand;
import org.github.chajian.matchgame.command.TestCommand;
import org.github.chajian.matchgame.listener.MyListener;
import org.github.chajian.matchgame.runnable.BossBarRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 匹配插件入口
 * @author Chajian
 */
public class MatchGame extends JavaPlugin {
    private static MatchGame matchGame;
    private HashMap<String,BaseCommand> commands;//指令集
    private BossBarRunnable bossbarRunnable;

    @Override
    public void onDisable() {
        getLogger().info("匹配插件卸载成功!");
    }

    @Override
    public void onEnable() {
        matchGame = this;
        commands = new HashMap<>();

        //注册监听
        getServer().getPluginManager().registerEvents(new MyListener(),this);

        //指令注册
        HandlerCommand handlerCommand = new HandlerCommand();
        new HelpCommand();
        new TestCommand();
        this.getCommand("match").setExecutor(handlerCommand);

        //初始化NoteBar线程
        bossbarRunnable = new BossBarRunnable();
        bossbarRunnable.runTaskTimer(this,20L,20L);
        getLogger().info("匹配插件加载成功!");
    }


    public static MatchGame getMatchGame() {
        if(matchGame != null)
            return matchGame;
        return null;
    }

    public HashMap<String, BaseCommand> getCommands() {
        return commands;
    }

    public BossBarRunnable getBossbarRunnable() {
        return bossbarRunnable;
    }
}
