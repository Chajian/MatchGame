package org.github.chajian.matchgame.command;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.github.chajian.matchgame.MatchGame;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * 命令执行器
 * @author Chajian
 *
 */
public class HandlerCommand implements CommandExecutor {

    /**
     *
     * @param sender 发送者
     * @param command
     * @param label 插件主指令
     * @param args 指令选项
     * @return
     */
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0){
            args = new String[]{"help"};
        }

        String cmd = args[0];
        ArrayList<String> arguments = new ArrayList<String>(Arrays.asList(args));
        arguments.remove(0);
        BaseCommand bCommand = MatchGame.getMatchGame().getCommands().get(cmd.toLowerCase());

        if(bCommand == null)
            sender.sendMessage("未知的命令!");

        if(sender instanceof ConsoleCommandSender)
            if(!bCommand.isAllowConsole())
                sender.sendMessage("§c终端无法使用这个命令!");

        if(!bCommand.hasPermission(sender))
            sender.sendMessage("您没有权限!");

        boolean result = bCommand.execute(sender,arguments);

        if(!result)
            sender.sendMessage("未知的使用方法!");

        return true;
    }

}
