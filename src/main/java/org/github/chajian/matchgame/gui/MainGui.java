package org.github.chajian.matchgame.gui;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.github.chajian.matchgame.MatchGame;

import java.io.File;
import java.io.InputStream;

/**
 * 匹配插件
 * @author Chajian
 */
public class MainGui {

    ChestGui chestGui;
    GuiItem borderItem;
    GuiItem bedwarItem;
    PatternPane pane;

    public MainGui() {
        initGui();
        initPane();
        initGuiItem();

    }

    public void initGui(){
        chestGui = new ChestGui(6,"游戏大厅");
    }

    public void initPane(){
        Pattern pattern = new Pattern(
                "111111111",
                "120000001",
                "100000001",
                "111111111"
        );
        pane = new PatternPane(0, 0, 9, 4, pattern);
        chestGui.addPane(pane);
    }

    public void initGuiItem(){
        //初始化ItemStack
        ItemStack bedwarStack = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta bedwarMeta = bedwarStack.getItemMeta();
        bedwarMeta.setDisplayName("起床战争");
        bedwarStack.setItemMeta(bedwarMeta);
        ItemStack borderStack = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta itemMeta = borderStack.getItemMeta();
        itemMeta.setDisplayName("边框");
        //初始化GuiItem
        borderItem = new GuiItem(borderStack);
        bedwarItem = new GuiItem(bedwarStack);
        //点击事件
        borderItem.setAction(this::clickBorder);
        bedwarItem.setAction(this::clickBedWar);
        //绑定
        pane.bindItem('1', borderItem);
        pane.bindItem('2',bedwarItem);
    }

    public void clickBorder(InventoryClickEvent event){
        if(event.getClick() != ClickType.LEFT)
            event.setCancelled(true);
        else{

        }
    }

    public void clickBedWar(InventoryClickEvent event){
        if(event.getClick() != ClickType.LEFT)
            event.setCancelled(true);
        else{

        }
    }

    public ChestGui getChestGui() {
        return chestGui;
    }


}
