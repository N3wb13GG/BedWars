package n3wb13.gametype.bedwars.commands.bedwars.subcommands.games;

import n3wb13.gametype.bedwars.commands.bedwars.BedWarsCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class GameSubCommand extends BedWarsCommand {

    public GameSubCommand() {
        this.setName("game");
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (args.length >= 2 && getSubCommandMap().containsKey(args[1]))
            getSubCommandMap().get(args[1]).execute(sender, alias, args);

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        if (args.length <= 2)
            return getSubCommandNames();

        if (getSubCommandMap().containsKey(args[1]))
            return getSubCommandMap().get(args[1]).tabComplete(sender, alias, args);

        return new ArrayList<>();
    }
}
