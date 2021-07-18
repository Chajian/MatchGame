package org.github.chajian.matchgame.game;

import org.bukkit.Bukkit;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scoreboard.ScoreboardManager;
import org.github.chajian.matchgame.MatchGame;
import org.github.chajian.matchgame.bar.BaseBar;
import org.github.chajian.matchgame.data.define.MatchModel;
import org.github.chajian.matchgame.gui.BaseGui;
import org.screamingsandals.bedwars.api.BedwarsAPI;

import java.util.HashMap;

/**
 * 匹配大厅
 * @author Chajian
 * 进入条件:当玩家点击匹配按钮，就将玩家记录在匹配大厅，玩家在匹配大厅并不影响其正常游戏
 * 匹配逻辑
 *
 * 无脑模式:
 * 当人数满足x人条件就大批量向玩家发送是否进入游戏提示
 * 如果玩家同意就立马将其传送到对应游戏的竞技场，并根据胜率分配队伍
 *
 * 有脑模式:
 * 将游戏大厅的玩家分组，把水平相对差不多的玩家放入同一组。
 * 当某一组达到x人条件就直接开启游戏
 * 否则超时了就合并n组并将他们按照战力排放位置
 *
 * 组队模式:
 * 通过指令或其他可以输入字符串的页面进行组队申请
 * 如果玩家同意了就组建队伍成功。
 * 当队长点击匹配按钮时，默认整支队伍都进行了匹配，并且将队伍放置匹配大厅中
 * 如果超时了并且没有其他队伍，就将队伍放置到尽量同水平的游戏中
 *
 *
 *
 *
 */
public class MatchLobby {
    boolean supportBedWar;
    boolean supportPushCar;
    //参与匹配的玩家
    static HashMap<String,MatchPool> poolHashMap = new HashMap<String,MatchPool>();
    //游戏模式
    static MatchModel model = MatchModel.VIOLENT;


    public MatchLobby() {
        init();
        //生成对应的匹配池
        if(supportBedWar){
            MatchPool matchPool = new MatchPool("bedwar");
            poolHashMap.put(matchPool.getGameId(),matchPool);
        }
    }

    //初始化
    public void init(){
        checkGame();
    }
    public void join(Player player,String gameId){
        if(existGame(gameId)){
            poolHashMap.get(gameId).joinPlayer(player);
        }
    }
    public void leave(Player player){

    }
    /**
     * 匹配开始
     */
    public void start(){

    }

    /**
     * 判断小游戏是否存在
     * @param gameName 小游戏名
     * @return
     */
    public boolean existGame(String gameName){
        return poolHashMap.get(gameName) != null;
    }

    /**
     * 检查是否支持小游戏
     */
    public void checkGame(){
        PluginManager pluginManager = MatchGame.getMatchGame().getServer().getPluginManager();
        //检测是否支持小游戏
        String bedwars = "BedWars";
        String pushcar = "PushCar";
        if(pluginManager.isPluginEnabled(bedwars))
            supportBedWar = true;
        //检测是否支持推车
        if(pluginManager.isPluginEnabled(pushcar))
            supportPushCar = true;
    }

    public boolean isSupportBedWar() {
        return supportBedWar;
    }

    public boolean isSupportPushCar() {
        return supportPushCar;
    }
}
