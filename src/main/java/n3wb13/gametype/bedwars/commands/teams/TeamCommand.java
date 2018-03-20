package n3wb13.gametype.bedwars.commands.teams;

import n3wb13.gametype.bedwars.managers.commands.MyCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class TeamCommand extends MyCommand {

    public TeamCommand() {
        super("team");
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if(args.length >= 1 && getSubCommandMap().containsKey(args[0]))
            getSubCommandMap().get(args[0]).execute(sender, alias, args);

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        if(args.length <= 1)
            return getSubCommandNames();

        if(getSubCommandMap().containsKey(args[0]))
            return getSubCommandMap().get(args[0]).tabComplete(sender, alias, args);

        return new ArrayList<>();
    }
}
