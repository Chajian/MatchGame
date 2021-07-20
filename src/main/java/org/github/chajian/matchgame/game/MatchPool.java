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
import org.github.chajian.matchgame.data.define.PoolStatus;
import org.github.chajian.matchgame.data.mysql.MySqlManager;
import org.github.chajian.matchgame.game.api.GameApi;
import org.github.chajian.matchgame.mapper.IntegralMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    /*进度条*/
    private BaseBar baseBar;
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


    public MatchPool(String gameId,int maxPlayer) {
        init(gameId,maxPlayer);
    }

    /**
     * 开始匹配
     * - 分配玩家和队伍
     */
    public void startMatch(){

    }

    //更新scoreBoard
    public synchronized void updateBoard(Player player){
        baseScore.showByStatus(player,status);
//        switch (status){
//            case WAITING:
//
//                break;
//
//            case BEFORE_START:
//
//                break;
//
//            case START:
//
//                break;
//
//            case ENDING:
//
//                break;
//        }
    }
    //更新进度条
    public synchronized void updateBar(Player player){
        switch (status){
            case WAITING:
                baseBar = new NoteBar("温馨提示",10, BarColor.PINK,"在等待匹配期间，并不会影响您正常游戏哦!");
                baseBar.show(player);
                break;

            case BEFORE_START:
                baseBar = new NoteBar("匹配即将开始",varifyTime,BarColor.GREEN,"匹配还有%s秒即将开始，请做好准备!");
                baseBar.show(player);
                break;

            case START:
                baseBar = new NoteBar("匹配开始!",3,BarColor.RED,"匹配已经开始！");
                baseBar.show(player);
                break;

            case ENDING:

                break;
        }

    }

    /*初始化*/
    public void init(String type,int maxPlayer){
        this.gameId = type;
        switch (type){
            case "bedwar":
                name = "起床战争";

                break;

            case "pushcar":

                break;
        }
        this.maxPlayer = maxPlayer;
        //初始化进度条和scoreboard
        baseBar = new NoteBar("起床战争",100, BarColor.BLUE,"剩余%s秒将开始!");
        baseScore = new LobbyScoreBoard();
        //初始化状态
        status = PoolStatus.WAITING;
        //通过配置文件读取pool设置
        players = new ArrayList<>();
        currentCount = timeout;
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
//                if(players.size() >= minPlayer){
//                    currentCount--;
//                }
//                else if(players.size() == maxPlayer){
//                    currentCount = 0;
//                }
//                else if (players.size() >= minPlayer && currentCount <= 0){
//                    //更新状态和时间，并更新board和进度条
//                    status = PoolStatus.BEFORE_START;
//                    currentCount = varifyTime;
//                    //更新玩家的提示信息
//                    players.forEach(player -> {
//                        updateBoard(player);
//                        updateBar(player);
//                    });
//                }


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

//                if(players.size()>=minPlayer){
//                    currentCount --;
//                }
//                else if(currentCount == 0 && players.size()>= minPlayer){
//                    status = PoolStatus.START;
//                    //更新玩家的提示信息
//                    players.forEach(player -> {
//                        updateBoard(player);
//                        updateBar(player);
//                    });
//                }
//                //如果匹配中途有人退出,并且导致无法满足最低开启条件
//                else{
//                    status =PoolStatus.WAITING;
//                    currentCount = varifyTime;
//                }


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
