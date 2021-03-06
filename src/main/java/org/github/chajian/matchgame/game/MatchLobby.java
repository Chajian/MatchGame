package org.github.chajian.matchgame.game;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;
import org.github.chajian.matchgame.MatchGame;
import org.github.chajian.matchgame.data.config.Configurator;
import org.github.chajian.matchgame.data.define.MatchModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

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
public class MatchLobby extends BukkitRunnable {
    boolean supportBedWar;
    boolean supportPushCar;
    //参与匹配的玩家
    HashMap<String,MatchPool> poolHashMap = new HashMap<String,MatchPool>();
    //游戏模式
    MatchModel model = MatchModel.VIOLENT;


    public MatchLobby() {
        init();
    }

    //初始化
    public void init(){
        //检测是否支持对应的小游戏
        checkGame();
        //生成对应的匹配池
        readConfig();
    }
    //加入匹配
    public void join(Player player,String gameId){
        if(existGame(gameId)){
            poolHashMap.get(gameId).joinPlayer(player);
        }
    }
    //离开匹配
    public void leave(Player player){
        String gameName = getGameNameByPlayer(player);
        if (gameName != null){
            poolHashMap.get(gameName).leavePlayer(player);
        }
    }

    /**
     * 通过玩家获取游戏名
     * @param player 玩家
     * @return
     */
    public String getGameNameByPlayer(Player player){
        AtomicReference<String> game = new AtomicReference<>();
        poolHashMap.forEach((s, matchPool) -> {
            if (matchPool.containPlayer(player))
                game.set(s);
        });
        return game.get();
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

    public HashMap<String, MatchPool> getPoolHashMap() {
        return poolHashMap;
    }

    //管理Pool
    @Override
    public void run() {
        poolHashMap.forEach((s, matchPool) -> {
            matchPool.start();
        });
    }

    /**
     * 读取config配置
     */
    public void readConfig(){
        List<Object> list = (List<Object>) Configurator.getConfigurator().getConfig().getList("games");
        //根据配置生成Pool
        for(int i = 0 ; i < list.size() ; i++){
            Map<String,Object> map = (Map<String, Object>) list.get(i);
            String lobbyType = (String) map.get("gameId");
            switch (lobbyType){
                case "bedwar":
                    if(supportBedWar){
                        MatchPool matchPool = new MatchPool(map);
                        poolHashMap.put(lobbyType,matchPool);
                    }
                    break;

                case "pushcar":
                    if(supportPushCar){
                        MatchPool matchPool = new MatchPool(map);
                        poolHashMap.put(lobbyType,matchPool);
                    }
                    break;
            }
        }
    }
}
