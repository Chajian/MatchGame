package org.github.chajian.matchgame.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import javax.swing.*;
import java.util.List;

/**
 * 帮组指令
 * @author Chajian
 */
public class HelpCommand extends BaseCommand {



    public HelpCommand() {
        super("help", null, true);
    }

    public boolean execute(CommandSender sender, List<String> args) {
        if (sender instanceof  Player){
            sendHelp((Player) sender);
        }
        else if(sender instanceof ConsoleCommandSender){

        }
        return true;
    }

    public void sendHelp(Player player){
        player.sendMessage("--------"+"匹配插件"+"--------");
        player.sendMessage("match join 打开匹配界面");

    }
}
