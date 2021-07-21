package org.github.chajian.matchgame.gui;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.github.chajian.matchgame.MatchGame;
import org.github.chajian.matchgame.data.config.Configurator;

import java.util.List;
import java.util.Map;

/**
 * 匹配窗口
 * @author Chajian
 */
public class MainGui extends BaseGui {

    public MainGui() {
        initGui();
        initPane();
        initGuiItem();

    }
    @Override
    public void initGui(){
        chestGui = new ChestGui(5,"游戏大厅");
    }
    @Override
    public void initPane(){
        Pattern pattern = new Pattern(
                "111111111",
                "123000001",
                "100000001",
                "111111111"
        );
        pane = new PatternPane(0, 0, 9, 4, pattern);
        bindPaneClick();
    }
    @Override
    public void initGuiItem(){

        //通过config生成GuiItem
        if(MatchGame.getMatchGame().getMatchLobby().isSupportBedWar()||MatchGame.getMatchGame().getMatchLobby().isSupportPushCar())
            readConfig();
        chestGui.addPane(pane);
    }


    public void clickBedWar(InventoryClickEvent event){
        if(event.getClick() != ClickType.LEFT)
            event.setCancelled(true);
        else{
            Player player = (Player) event.getWhoClicked();
            //加入匹配
            MatchGame.getMatchGame().getMatchLobby().join(player,"bedwar");
            player.sendMessage("点击了匹配起床哦");
        }
    }

    public ChestGui getChestGui() {
        return chestGui;
    }

    public void readConfig(){
        List<Map<?,?>> list = Configurator.getConfigurator().getConfig().getMapList("gui.main");
        for(int i = 0 ; i < list.size() ; i++){
            Map<?,?> items = (Map<?,Object>) list.get(i);
            ItemStack itemStack = new ItemStack(Material.getMaterial((String) items.get("itemType"),false));
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName((String) items.get("title"));
            itemMeta.setLore((List<String>) items.get("lore"));
            itemStack.setItemMeta(itemMeta);
            GuiItem guiItem = new GuiItem(itemStack);
            int on = Integer.parseInt((String)items.get("itemon"));
            char item_on = (char) (48+on);
            //绑定起床点击事件
            if(items.get("gameId")!=null && items.get("gameId").equals("bedwar"))
                guiItem.setAction(this::clickBedWar);
            //绑定PatternPane的位置和pane
            ((PatternPane)pane).bindItem(item_on,guiItem);
        }
    }

    @Override
    public void showPlayer(Player player) {
        chestGui.show(player);
    }
}
