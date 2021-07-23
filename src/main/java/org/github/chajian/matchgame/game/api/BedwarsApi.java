package org.github.chajian.matchgame.game.api;

import org.bukkit.entity.Player;
import org.screamingsandals.bedwars.api.BedwarsAPI;
import org.screamingsandals.bedwars.api.Team;
import org.screamingsandals.bedwars.api.game.Game;

import java.util.List;

/**
 * 起床战争api
 * @author Chajian
 */
public class BedwarsApi implements GameApi {
    BedwarsAPI bedwarsAPI;

    public BedwarsApi() {
        this.bedwarsAPI = BedwarsAPI.getInstance();
    }

    @Override
    public void joinGame(Player player,String gameName) {
        Game game = bedwarsAPI.getGameByName(gameName);
        game.selectPlayerRandomTeam(player);
    }

    @Override
    public void joinGame(Player player, String team, String gameName) {
        Game game = bedwarsAPI.getGameByName(gameName);
        Team team1 = game.getTeamFromName(team);
        game.selectPlayerTeam(player,team1);
    }

    @Override
    public int getJoinedPlayer(String gameName) {
        Game game = bedwarsAPI.getGameByName(gameName);
        return game.getConnectedPlayers().size();
    }

    @Override
    public int getGameMaxPlayer(String gameName) {
        Game game = bedwarsAPI.getGameByName(gameName);
        return game.getMaxPlayers();
    }

    @Override
    public int getKills(String gameName, Player player) {
        return 0;
    }

    @Override
    public int getDeaths(String gameName, Player player) {
        return 0;
    }

    @Override
    public int getGameTeams(String gameName) {
        Game game = bedwarsAPI.getGameByName(gameName);
        return game.getAvailableTeams().size();
    }

    @Override
    public Object getGame(String gameName) {
        return bedwarsAPI.getGameByName(gameName);
    }

    @Override
    public Object getTeam(String gameName) {
        Game game = bedwarsAPI.getGameByName(gameName);
        return game.getAvailableTeams();
    }

    @Override
    public List<Game> getGames() {
        return bedwarsAPI.getGames();
    }
}
