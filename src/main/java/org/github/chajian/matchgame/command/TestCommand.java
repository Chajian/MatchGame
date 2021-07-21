package org.github.chajian.matchgame.command;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.boss.BarColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
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
                showBarToPlayer(player);
            }
            else if(label.equals("gui")){
                //测试gui
                ChestGui chestGui = new ChestGui(5,"test");
                Pattern pattern = new Pattern(
                        "111111111",
                        "123000001",
                        "100000001",
                        "111111111"
                );
                PatternPane pane = new PatternPane(0, 0, 9, 4, pattern);
                pane.bindItem('1', new GuiItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE)));
                chestGui.addPane(pane);
                chestGui.show(player);
            }
            else if(label.equals("equit")){
                MatchGame.getMatchGame().getMatchLobby().leave(player);
            }
            else if(label.equals("score")){
                Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
                Objective objective1 = scoreboard.registerNewObjective("bbb","cccc",ChatColor.RED+"芜湖也");
                Objective objective2 = scoreboard.registerNewObjective("ghh","jjj",ChatColor.GOLD+"黄金诶!");
                Score score1 = objective1.getScore(ChatColor.GREEN+"sjdkfj");
                score1.setScore(78);
                objective1.setDisplaySlot(DisplaySlot.SIDEBAR);
                Score score2 = objective2.getScore(ChatColor.BLACK+"sjdk");
                score2.setScore(9990);
                objective2.setDisplaySlot(DisplaySlot.PLAYER_LIST);
                player.setScoreboard(scoreboard);
            }
        }


        return true;
    }

    public void showBarToPlayer(Player player){
        noteBar.getBossBar().addPlayer(player);
    }

    public void hideBarToPlayer(Player player){
        noteBar.getBossBar().removePlayer(player);
    }
}
