package org.github.chajian.matchgame;

import lombok.NonNull;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;
import org.github.chajian.matchgame.command.*;
import org.github.chajian.matchgame.data.mysql.MySqlManager;
import org.github.chajian.matchgame.game.MatchLobby;
import org.github.chajian.matchgame.listener.MyListener;
import org.github.chajian.matchgame.mapper.IntegralMapper;
import org.github.chajian.matchgame.runnable.BossBarRunnable;

import java.io.IOException;
import java.util.HashMap;

/**
 * 匹配插件入口
 * @author Chajian
 */
public class MatchGame extends JavaPlugin {
    private static MatchGame matchGame;
    private HashMap<String,BaseCommand> commands;//指令集
    private MatchLobby matchLobby;
    private BossBarRunnable bossbarRunnable;
    private BukkitAudiences adventure;
    private MySqlManager mySqlManager;

    public @NonNull BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }

    @Override
    public void onDisable() {
        bossbarRunnable.cancel();
        bossbarRunnable = null;
        commands = null;
        //关闭adventure
        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
        getLogger().info("匹配插件卸载成功!");
    }

    @Override
    public void onEnable() {
        matchGame = this;
        commands = new HashMap<>();
        //注册数据库
        try {
            mySqlManager = MySqlManager.getMySqlManager();
            IntegralMapper integralMapper = mySqlManager.getSqlSessionFactory().openSession().getMapper(IntegralMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //注册大厅
        matchLobby = new MatchLobby();
        //注册监听
        getServer().getPluginManager().registerEvents(new MyListener(),this);
        //初始化NoteBar线程
        bossbarRunnable = new BossBarRunnable();
        bossbarRunnable.runTaskTimer(this,20L,20L);
        //指令注册
        HandlerCommand handlerCommand = new HandlerCommand();
        new HelpCommand();
        new TestCommand();
        new GuiCommand();
        this.getCommand("match").setExecutor(handlerCommand);
        //开启adventure支持ui
        this.adventure = BukkitAudiences.create(this);

        getLogger().info("匹配插件加载成功!");
    }


    public static MatchGame getMatchGame() {
        if(matchGame != null)
            return matchGame;
        return null;
    }

    public MatchLobby getMatchLobby() {
        return matchLobby;
    }

    public HashMap<String, BaseCommand> getCommands() {
        return commands;
    }

    public BossBarRunnable getBossbarRunnable() {
        return bossbarRunnable;
    }
}
