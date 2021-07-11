package org.github.chajian.matchgame;

import org.bukkit.plugin.java.JavaPlugin;
import org.github.chajian.matchgame.command.CommandMain;
import org.github.chajian.matchgame.listener.MyListener;

/**
 * 匹配插件入口
 * @author Chajian
 */
public class MatchGame extends JavaPlugin {



    @Override
    public void onDisable() {
        getLogger().info("匹配插件卸载成功!");
    }

    @Override
    public void onEnable() {
        //指令注册
        this.getCommand("match").setExecutor(new CommandMain());


        //注册监听
        getServer().getPluginManager().registerEvents(new MyListener(),this);
        getLogger().info("匹配插件加载成功!");
    }
}
