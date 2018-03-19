package n3wb13.gametype.bedwars.managers.teams;

import n3wb13.gametype.bedwars.BedWars;
import n3wb13.gametype.bedwars.managers.players.PlayerData;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;

import static org.bukkit.Bukkit.getScoreboardManager;

public class TeamManager {

    private HashMap<PlayerData, TeamType> playerInTeamList = new HashMap<>();
    private HashMap<TeamType, Integer> teamSize = new HashMap<>();

    public TeamManager() {
        for (TeamType teamType : TeamType.values()) {
            teamSize.put(teamType, 0);

            Scoreboard board = getScoreboardManager().getMainScoreboard();

            Team team = board.getTeam(teamType.getTeamName());
            if(team == null)
                team = board.registerNewTeam(teamType.getTeamName());

            team.setDisplayName(teamType.getTeamDisplayName());
            team.setAllowFriendlyFire(false);
            team.setCanSeeFriendlyInvisibles(true);
            team.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
            team.setPrefix(teamType.getTeamDisplayName());
        }
    }

    public TeamType recommentedTeam() {
        return TeamType.Red;
    }

    public TeamType getTeam(PlayerData playerData) {
       if (!playerInTeamList.containsKey(playerData))
           setTeam(playerData, TeamType.None);

       return playerInTeamList.get(playerData);
    }

    public boolean setTeam(PlayerData playerData, TeamType team) {
        int min = 0;
        for (TeamType teamType : TeamType.values()) {
            if(team != TeamType.None && min > teamSize.get(teamType))
                 min = teamSize.get(teamType);
        }
        min /= TeamType.values().length -1;

        if(team != TeamType.None && teamSize.get(team) <= min + 1) {
            playerInTeamList.put(playerData, team);
            Scoreboard board = getScoreboardManager().getMainScoreboard();
            board.getTeam(team.getTeamName()).addEntry(playerData.getPlayer().getName());

            return true;
        } else if(team == TeamType.None) {
            playerInTeamList.put(playerData, team);
            Scoreboard board = getScoreboardManager().getMainScoreboard();
            board.getTeam(team.getTeamName()).addEntry(playerData.getPlayer().getName());

            return true;
        }

        return false;
    }

    public void checkResigned() {
        for(PlayerData playerData : BedWars.instance.playerManager.getPlayerDatas()) {
            if (!playerData.getPlayer().isOnline() && getTeam(playerData) != TeamType.None && !playerData.getResigned() && playerData.getLogoutTime().hasReached(500L)) {
                setTeam(playerData, TeamType.None);
                playerData.setResigned(true);
            }
        }
    }
}
