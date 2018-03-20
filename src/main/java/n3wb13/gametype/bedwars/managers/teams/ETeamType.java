package n3wb13.gametype.bedwars.managers.teams;

import org.bukkit.ChatColor;

public enum ETeamType {

    NONE("none", "None", ChatColor.GRAY),
    RED("red", "Red", ChatColor.RED),
    BLUE("blue", "Blue", ChatColor.BLUE);


    private String teamName;
    private String teamDisplayName;
    private ChatColor teamColor;

    ETeamType(String teamName, String teamDisplayName, ChatColor teamColor) {
        this.teamName = teamName;
        this.teamDisplayName = teamDisplayName;
        this.teamColor = teamColor;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamDisplayName() {
        return teamColor + teamDisplayName + ChatColor.RESET;
    }

    public ChatColor getTeamColor() {
        return teamColor;
    }
}
