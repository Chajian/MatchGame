package org.github.chajian.matchgame.command;

import org.bukkit.Note;
import org.bukkit.boss.BarColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.github.chajian.matchgame.MatchGame;
import org.github.chajian.matchgame.bar.NoteBar;

import java.util.List;

/**
 * 测试指令
 * -用于测试功能
 * @author Chajian
 *
 */
public class TestCommand extends BaseCommand {
    NoteBar noteBar = new NoteBar("测试",100, BarColor.RED,"请在%s秒内确认匹配");

    public TestCommand() {
        super("test", null, false);
        MatchGame.getMatchGame().getBossbarRunnable().addNoteBar(noteBar);

    }

    @Override
    public boolean execute(CommandSender sender, List<String> args) {
        if (args.size() ==1){
            Player player = (Player) sender;
            String label = args.get(0);
            if (label.equals("show"))
                showBarToPlayer(player);
            else if(label.equals("close"))
                hideBarToPlayer(player);
        }


        showBarToPlayer((Player)sender);
        return true;
    }

    public void showBarToPlayer(Player player){
        noteBar.getBossBar().addPlayer(player);
    }

    public void hideBarToPlayer(Player player){
        noteBar.getBossBar().removePlayer(player);
    }
}
