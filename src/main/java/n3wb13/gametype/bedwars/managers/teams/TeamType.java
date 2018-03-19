package n3wb13.gametype.bedwars.managers.teams;

import org.bukkit.ChatColor;

public enum TeamType {

    None("None", ChatColor.GRAY + "[None]"),
    Red("Red", ChatColor.RED + "[Red] "),
    Blue("Blue", ChatColor.BLUE + "[Blue] ");


    private String teamName;
    private String teamDisplayName;

    TeamType(String teamName, String teamDisplayName) {
        this.teamName = teamName;
        this.teamDisplayName = teamDisplayName;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamDisplayName() {
        return teamDisplayName;
    }
}
