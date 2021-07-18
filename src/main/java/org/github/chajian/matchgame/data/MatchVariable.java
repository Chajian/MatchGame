package org.github.chajian.matchgame.data;

import org.github.chajian.matchgame.data.mysql.MySqlManager;
import org.github.chajian.matchgame.game.api.BedwarsApi;
import org.github.chajian.matchgame.game.api.GameApi;
import org.github.chajian.matchgame.mapper.GameRecordMapper;
import org.github.chajian.matchgame.mapper.IntegralMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义变量
 * @author Chajian
 */
public class MatchVariable {
    public static MatchVariable matchVariable;

    /*可用变量*/
    public String[] variable = new String[]{"playerName","gameName","joinedPlayer","gameMaxPlayer","kill","death","wins","losers","winrate","integral","rank"};
    IntegralMapper integralMapper;
    GameRecordMapper gameRecordMapper;
    GameApi gameApi;

    private MatchVariable(String type) throws IOException {
        switch (type){
            case "bedwar":
                gameApi = new BedwarsApi();
                break;
        }
        init();
    }

    //初始化
    public void init() throws IOException {
        integralMapper = MySqlManager.getMySqlManager().getSqlSessionFactory().openSession().getMapper(IntegralMapper.class);
        gameRecordMapper = MySqlManager.getMySqlManager().getSqlSessionFactory().openSession().getMapper(GameRecordMapper.class);
    }

    /**
     * 通过游戏名获取信息
     * @param info 关键词
     * @param playerName 玩家名
     * @param gameName 游戏名
     * @return
     */
    public String replaceVariableByGameName(String info,String playerName,String gameName){
        String result = null;
        switch (info){
            case "joinedPlayer":
                result = String.valueOf(gameApi.getJoinedPlayer(gameName));
                break;

            case "gameMaxPlayer":
                result = String.valueOf(gameApi.getGameMaxPlayer(gameName));
                break;
            case "kill":
                break;
            case "death":

                break;
        }

        return result;
    }

    /**
     * 通过游戏id获取信息
     * @param info 关键词
     * @param playerName 玩家名
     * @param gameId 游戏id
     * @return
     */
    public String replaceVariableByGameId(String info,String playerName,String gameId){
        String result = null;
        IntegralPO integralPO = integralMapper.selectByPlayerAndGameId(playerName,gameId);
        switch (info){
            case "playerName":
                result = playerName;
                break;

            case "gameName":
                result = gameId;
                break;
            case "win":
                result = String.valueOf(integralPO.getWins());
                break;
            case "loser":
                result = String.valueOf(integralPO.getLosers());
                break;
            case "winrate":
                result = String.valueOf(integralPO.getWins()/(integralPO.getWins()+integralPO.getLosers()));
                break;
        }
        return result;
    }

    //提取关键词
    public String fetchFeat(String s){
        String rule = "%.*%";
        Pattern p = Pattern.compile(rule);
        Matcher matcher = p.matcher(s);
        if(matcher.find())
            return matcher.group();
        return null;
    }

    //将变量替换成相应的数据
    public String replace(String s,String playerName,String gameName){
        //提取关键词
        String feat = fetchFeat(s).replaceAll("%","");
        if(Arrays.asList(variable).contains(feat)){
            String result = s.replaceAll(feat,replaceVariableByGameId(feat,playerName,gameName));
            if (result == null)
                return s.replaceAll(feat,replaceVariableByGameName(feat,playerName,gameName));
            return result;
        }
        return null;
    }

    public static MatchVariable getMatchVariable(String type) {
        if (matchVariable == null) {
            try {
                matchVariable = new MatchVariable(type);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return matchVariable;
    }
}
