package org.github.chajian.matchgame.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 指令模块入口
 * @author YangLin
 */
public class CommandMain implements CommandExecutor {
    /**
     *
     * @param sender 命令的发送者[方块，玩家]
     * @param command 被调用的指令[help ,version]
     * @param label 标签包含发送者的第一个单词
     * @param args 参数是其余部分用空格隔开[不包括label]
     * @return 执行正确返回true,否则返回false，并且会将plugin.yml中的usege发送给sender
     */
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
        }
        return true;
    }
}
