package org.github.chajian.matchgame.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * 命令执行器
 * @author YangLin
 *
 */
public class CommandHandler implements CommandExecutor {

    //存放指令执行器的哈希表
    private static HashMap<String,CommandExecute> COMMANDS = new HashMap<String, CommandExecute>();

    public static void register(String name,CommandExecute commandExecute){
        COMMANDS.put(name,commandExecute);
    }

    public static boolean exists(String name){
        return COMMANDS.containsKey(name);
    }
    public static CommandExecute getExecutor(String name){
        return COMMANDS.get(name);
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }
}
