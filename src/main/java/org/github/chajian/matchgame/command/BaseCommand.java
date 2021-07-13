package org.github.chajian.matchgame.command;

import lombok.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.github.chajian.matchgame.MatchGame;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础指令
 * 所有指令都继承BaseCommand
 * @author Chajian
 */
@Data
public abstract class BaseCommand{
    private static final String ADMIN_PERMISSION= "match.game.admin";
    private static final String OTHER_STARTS_PERMISSION = "match.game.otherstats";

    private String name;
    private String permission;
    private boolean allowConsole;

    public BaseCommand(String name, String permission, boolean allowConsole) {
        this.name = name;
        this.permission = permission;
        this.allowConsole = allowConsole;
        MatchGame.getMatchGame().getCommands().put(this.name,this);
    }

    public abstract boolean execute(CommandSender sender,List<String> args);

    /**
     * 权限检测
     * @param sender 指令发送者
     * @return
     */
    public boolean hasPermission(CommandSender sender){
        if(permission == null || "".equals(permission))
            return true;
        return sender.hasPermission(permission);
    }
    /**
     * @param sender 命令的发送者[方块，玩家]
     * @param command 被调用的指令[help ,version]
     * @param label 标签包含发送者的第一个单词
     * @param args 参数是其余部分用空格隔开[不包括label]
     * @return 执行正确返回true,否则返回false，并且会将plugin.yml中的usege发送给sender
     */
//    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
//        if (sender instanceof Player){
//            Player player = (Player) sender;
//        }
//        return true;
//    }


}
