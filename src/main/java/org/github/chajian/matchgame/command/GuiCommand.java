package org.github.chajian.matchgame.command;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.github.chajian.matchgame.data.config.Configurator;
import org.github.chajian.matchgame.gui.BaseGui;
import org.github.chajian.matchgame.gui.MainGui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 打开窗口的指令
 */
public class GuiCommand extends BaseCommand{
    HashMap<String, BaseGui> baseGuiHashMap = new HashMap<>();;

    public GuiCommand() {
        super("gui",OTHER_STARTS_PERMISSION , false);
    }

    @Override
    public boolean execute(CommandSender sender, List<String> args) {
        if(args.size()==0&&sender instanceof Player) {
            initGui();
            Player player = (Player) sender;
            baseGuiHashMap.get("main").showPlayer(player);
        }
        return true;
    }

    public void initGui(){
        if(baseGuiHashMap.get("main") == null||baseGuiHashMap.size()<1) {
            MainGui mainGui = new MainGui();
            baseGuiHashMap.put("main", mainGui);
        }
    }
}
