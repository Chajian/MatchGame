package org.github.chajian.matchgame.gui;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 基本的Gui抽象类
 * @author Chajian
 */
public abstract class BaseGui {
    protected ChestGui chestGui;
    protected Pane pane;
    protected List<GuiItem> list = new ArrayList<>();

    abstract void initGui();
    abstract void initGuiItem();
    abstract void initPane();
    public abstract void showPlayer(Player player);
    void bindPaneClick(){
        pane.setOnClick(this::PaneClick);
    }

    //避免玩家作弊或者拖拽物品
    public void PaneClick(InventoryClickEvent event){
        if(event.getClick() != ClickType.LEFT)
            event.setCancelled(true);
        if(event.getAction() != InventoryAction.NOTHING)
            event.setCancelled(true);
    }

}
