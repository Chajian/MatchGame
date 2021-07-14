package org.github.chajian.matchgame.command;

import org.bukkit.Note;
import org.bukkit.boss.BarColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.github.chajian.matchgame.MatchGame;
import org.github.chajian.matchgame.bar.NoteBar;
import org.github.chajian.matchgame.gui.MainGui;

import java.util.List;

/**
 * 测试指令
 * -用于测试功能
 * @author Chajian
 *
 */
public class TestCommand extends BaseCommand {
    NoteBar noteBar;

    public TestCommand() {
        super("test", null, false);
    }

    @Override
    public boolean execute(CommandSender sender, List<String> args) {
        if (args.size() ==1){
            Player player = (Player) sender;
            String label = args.get(0);
            if (label.equals("show")) {
                noteBar = new NoteBar("测试",100, BarColor.RED,"请在%s秒内确认匹配");
                MatchGame.getMatchGame().getBossbarRunnable().addNoteBar(noteBar);
                showBarToPlayer(player);
            }
            else if(label.equals("gui")){
                MainGui mainGui = new MainGui();
                mainGui.getChestGui().show(player);
            }
        }


//        showBarToPlayer((Player)sender);
        return true;
    }

    public void showBarToPlayer(Player player){
        noteBar.getBossBar().addPlayer(player);
    }

    public void hideBarToPlayer(Player player){
        noteBar.getBossBar().removePlayer(player);
    }
}
