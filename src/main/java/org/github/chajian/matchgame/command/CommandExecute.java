package org.github.chajian.matchgame.command;

import org.bukkit.command.CommandSender;

/**
 * 指令执行器接口
 * @author YangLin
 */
public interface CommandExecute {

    boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args);
}
