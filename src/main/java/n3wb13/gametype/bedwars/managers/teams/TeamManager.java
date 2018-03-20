package n3wb13.gametype.bedwars.managers.teams;

import n3wb13.gametype.bedwars.managers.IManager;
import n3wb13.gametype.bedwars.managers.games.EGameProgres;
import n3wb13.gametype.bedwars.managers.players.PlayerData;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TeamManager implements IManager {

    private Map<String, MyTeam> teamList = new HashMap<>();
    private Map<PlayerData, MyTeam> playerInTeamList = new HashMap<>();

    public void createTeam() {
        for (ETeamType teamType : ETeamType.values()) {
            getTeams().put(teamType.getTeamName(), new MyTeam(teamType.getTeamName(), teamType.getTeamDisplayName(), teamType));

            Team team = scoreBoardManager.getScoreBoard().registerNewTeam(teamType.getTeamName());

            team.setDisplayName(teamType.getTeamDisplayName());
            team.setAllowFriendlyFire(false);
            team.setCanSeeFriendlyInvisibles(true);
            team.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
            team.setPrefix("" + teamType.getTeamColor());
        }
    }

    public void removeTeam() {
        for (ETeamType teamType : ETeamType.values()) {
        }
    }

    public void createPlayerTeamData(PlayerData playerData) {
        if (!playerInTeamList.containsKey(playerData)) {
            playerInTeamList.put(playerData, getTeams().get(ETeamType.NONE.getTeamName()));
            getTeams().get(ETeamType.NONE.getTeamName()).joinTeam(playerData);
            scoreBoardManager.getScoreBoard().getTeam(ETeamType.NONE.getTeamName()).addEntry(playerData.getPlayer().getName());
        }
    }

    public void onJoin(PlayerData playerData) {
        createPlayerTeamData(playerData);
    }

    public Map<String, MyTeam> getTeams() {
        return teamList;
    }

    public ArrayList<String> getTeamNames() {
        return new ArrayList<>(teamList.keySet());
    }

    public ETeamType recommentedTeam() {
        return ETeamType.NONE;
    }

    public MyTeam getTeam(PlayerData playerData) {
        return playerInTeamList.get(playerData);
    }

    public ETeamJoinDetails setTeam(PlayerData playerData, String team) {
        if (!getTeams().containsKey(team.toLowerCase())) return ETeamJoinDetails.NOT_EXIT;
        return setTeam(playerData, getTeams().get(team.toLowerCase()));
    }

    public ETeamJoinDetails setTeam(PlayerData playerData, ETeamType teamType) {
        if (!getTeams().containsKey(teamType.getTeamName())) return ETeamJoinDetails.NOT_EXIT;
        return setTeam(playerData, getTeams().get(teamType.getTeamName()));
    }

    public ETeamJoinDetails setTeam(PlayerData playerData, MyTeam team) {
        int min = 0;
        for (ETeamType teamType : ETeamType.values()) {
            if (team.getTeamType() != ETeamType.NONE && min > team.getTeamSize())
                min = team.getTeamSize();
        }
        min /= ETeamType.values().length - 1;

        MyTeam playerTeam = getTeam(playerData);

        if (((playerTeam.getTeamType() == ETeamType.NONE || gameManager.getGameProgres() == EGameProgres.WAIT) && team.getTeamType() != ETeamType.NONE && team.getTeamSize() <= min + 1)
            || (team.getTeamType() == ETeamType.NONE && playerTeam.getTeamType() != ETeamType.NONE && gameManager.getGameProgres() == EGameProgres.WAIT)) {
            playerTeam.leaveTeam(playerData);
            playerInTeamList.put(playerData, team);
            team.joinTeam(playerData);
            scoreBoardManager.getScoreBoard().getTeam(team.getName()).addEntry(playerData.getPlayer().getName());

            return ETeamJoinDetails.TRUE;
        } else if (gameManager.getGameProgres() != EGameProgres.WAIT)
            return ETeamJoinDetails.GAME_PROGRES;
        else if (team.getTeamSize() > min + 1)
            return ETeamJoinDetails.PLAYER_MANY;

        return ETeamJoinDetails.ERROR;
    }

    public void checkResigned() {
        for (PlayerData playerData : playerManager.getPlayerDatas()) {
            if (!playerData.getPlayer().isOnline() && getTeam(playerData).getTeamType() != ETeamType.NONE && !playerData.isResigned() && playerData.getLogoutTime().hasReached(500L)) {
                setTeam(playerData, ETeamType.NONE);
                playerData.setResigned(true);
            }
        }
    }
}
