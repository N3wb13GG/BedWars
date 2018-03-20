package n3wb13.gametype.bedwars.managers.teams;

import n3wb13.gametype.bedwars.managers.players.PlayerData;

import java.util.ArrayList;
import java.util.List;

public class MyTeam {

    private final String name;
    private final String displayName;
    private final ETeamType teamType;

    private int teamSize = 0;

    private List<PlayerData> playerList = new ArrayList<>();

    public MyTeam(String name, String displayName, ETeamType teamType) {
        this.name = name;
        this.displayName = displayName;
        this.teamType = teamType;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ETeamType getTeamType() {
        return teamType;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public List<PlayerData> getPlayerList() {
        return playerList;
    }

    public void joinTeam(PlayerData playerData) {
        playerList.add(playerData);
        this.teamSize++;
    }

    public void leaveTeam(PlayerData playerData) {
        playerList.remove(playerData);
        this.teamSize--;
    }
}
