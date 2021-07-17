package org.github.chajian.matchgame.data;

/**
 * 自定义变量
 * @author Chajian
 */
public class MatchVariable {
    /*可用变量*/
    String[] variable = new String[]{"playerName","gameName","joinedPlayer","gameMaxPlayer","kill","death","win","loser","winrate","integral"};

    /**
     * 将变量字符串转成目标数据
     * @param info
     * @return
     */
    public String replaceVariable(String info){
        String result = null;
        switch (info){
            case "playerName":

                break;

            case "gameName":

                break;
            case "joinedPlayer":

                break;
            case "gameMaxPlayer":

                break;
            case "kill":

                break;
            case "death":

                break;
            case "win":

                break;
            case "loser":

                break;
            case "winrate":

                break;
        }
        return result;
    }
}
