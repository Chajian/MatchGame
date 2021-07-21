package org.github.chajian.matchgame.game;

import lombok.Data;
import org.apache.ibatis.session.SqlSession;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Player;
import org.github.chajian.matchgame.bar.BaseBar;
import org.github.chajian.matchgame.bar.NoteBar;
import org.github.chajian.matchgame.board.BaseScore;
import org.github.chajian.matchgame.board.LobbyScoreBoard;
import org.github.chajian.matchgame.data.IntegralPO;
import org.github.chajian.matchgame.data.config.Configurator;
import org.github.chajian.matchgame.data.define.PoolStatus;
import org.github.chajian.matchgame.data.mysql.MySqlManager;
import org.github.chajian.matchgame.game.api.GameApi;
import org.github.chajian.matchgame.mapper.IntegralMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 匹配池
 * @author Chajian
 */
@Data
public class MatchPool {
    /*玩家*/
    private List<Player> players = new ArrayList<Player>();
    /*池子名*/
    private String name;
    /*游戏id*/
    private String gameId;
    /*记分板*/
    private BaseScore baseScore;
    private GameApi gameApi;
    /*最大人数*/
    private int maxPlayer = 16;
    /*最小开始条件*/
    private int minPlayer = 2;
    /*超时*/
    private int timeout = 10;
    /*匹配状态*/
    private PoolStatus status;
    /*在匹配阶段设置的取消匹配时间*/
    private int varifyTime = 10;
    private int currentCount = 0;
    private IntegralMapper integralMapper;
    private SqlSession sqlSession;
    private Map<String,Object> configInfo;


    public MatchPool(Map<String,Object> gameInfo) {
        this.configInfo = gameInfo;
        init();
    }

    /**
     * 开始匹配
     * - 分配玩家和队伍
     */
    public void startMatch(){

    }

    //更新scoreBoard
    public synchronized void updateBoard(Player player){
        baseScore.showByStatus(player,status, (Map<String, Object>) configInfo.get("board"));
    }
    //更新进度条
    public synchronized void updateBar(Player player){
        Map<String,Object> body = (Map<String, Object>) configInfo.get("bar");
        BaseBar baseBar;
        switch (status){
            case WAITING:
                baseBar = new NoteBar("温馨提示",10, BarColor.PINK, (String) body.get(status.name()));
                baseBar.show(player);
                break;

            case BEFORE_START:
                baseBar = new NoteBar("匹配即将开始",varifyTime,BarColor.GREEN,(String) body.get(status.name()));
                baseBar.show(player);
                break;

            case START:
                baseBar = new NoteBar("匹配开始!",3,BarColor.RED,(String) body.get(status.name()));
                baseBar.show(player);
                break;

            case ENDING:

                break;
        }

    }

    /*初始化*/
    public void init(){
        //初始化基本信息
        try {
            this.gameId = (String) checkAndGet("gameId");
            this.name = (String) checkAndGet("name");
            this.maxPlayer = (int)checkAndGet("maxPlayer");
            this.minPlayer = (int)checkAndGet("minPlayer");
            this.timeout = (int)checkAndGet("timeout");
            this.varifyTime = (int)checkAndGet("varifyTime");
        } catch (Exception e) {
            e.printStackTrace();
        }
        currentCount = timeout;
        status = PoolStatus.WAITING;
        //初始化scoreboard
//        baseBar = new NoteBar(name,100, BarColor.BLUE,"剩余%s秒将开始!");
        baseScore = new LobbyScoreBoard();
        players = new ArrayList<>();
        //初始化mapper
        try {
            sqlSession = MySqlManager.getMySqlManager().getSqlSession();
            integralMapper = sqlSession.getMapper(IntegralMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //匹配插件运行
    public synchronized void start(){
        switch (status){
            case WAITING:
                //当满足直接匹配的条件
                if(players.size() >= minPlayer && currentCount <= 0){
                    //更新状态和时间，并更新board和进度条
                    status = PoolStatus.BEFORE_START;
                    currentCount = varifyTime;
                    //更新玩家的提示信息
                    players.forEach(player -> {
                        updateBoard(player);
                        updateBar(player);
                    });
                }
                else if(players.size() == maxPlayer){
                    currentCount = 0;
                }
                else if(players.size() >= minPlayer){
                    currentCount--;
                }



                break;

            case BEFORE_START:
                if(currentCount == 0 && players.size()>= minPlayer ){
                    status = PoolStatus.START;
                    //更新玩家的提示信息
                    players.forEach(player -> {
                        updateBoard(player);
                        updateBar(player);
                    });
                }
                else if(players.size()>=minPlayer){
                    currentCount --;
                }
                else {
                    status =PoolStatus.WAITING;
                    currentCount = varifyTime;
                }




                break;

            case START:
                //做一些匹配的操作




                break;

            case ENDING:

                break;
        }


    }

    /**
     * 进行匹配
     */
    public void MatchGame(){

    }





    /**
     * 玩家加入匹配
     * @param player
     */
    public void joinPlayer(Player player){
        if(!containPlayer(player)&&players.size()<maxPlayer) {
            //判断积分表中是否有这个用户
            IntegralPO integralPO = integralMapper.selectByPlayerAndGameId(player.getName(),this.gameId);
            if(integralPO == null) {
                IntegralPO integralPO1 = new IntegralPO();
                integralPO1.setPlayerName(player.getName());
                integralPO1.setGameId(this.gameId);
                integralMapper.registerIntegral(integralPO1);
                sqlSession.commit();
                try {
                    MySqlManager.getMySqlManager().getSqlSessionFactory().openSession().commit();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            players.add(player);
            updateBar(player);
            updateBoard(player);
            player.sendMessage("您已加入匹配!");
        }
        else{
            player.sendMessage("您已经加入了一场匹配请先退出");
        }
    }

    /**
     * 玩家离开
     * @param player
     */
    public void leavePlayer(Player player){
        if (containPlayer(player)){
            players.remove(player);
            baseScore.hide(player);
            player.sendMessage("您已经离开了!");
        }
    }

    /**
     * 检测并获取配置
     * @param path 检测地址
     * @return
     * @throws Exception
     */
    public Object checkAndGet(String path) throws Exception {
        Object o = configInfo.get(path);
        if (o == null)
            throw new Exception("pool配置错误");
        else
            return o;
    }

    /**
     * 获取玩家是否存在列表中
     * @param player 玩家名
     * @return
     */
    public boolean containPlayer(Player player){
        for(Player player1:players){
            if(player1.getName().equals(player.getName())){
                return true;
            }
        }
        return false;
    }

}
