package n3wb13.gametype.bedwars.commands.teams.subcommands;

import n3wb13.gametype.bedwars.commands.teams.TeamCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

final public class LeaveSubCommand extends TeamCommand {

    public LeaveSubCommand() {
        this.setName("leave");
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return bedWars.teamManager.getTeamNames();
    }
}
