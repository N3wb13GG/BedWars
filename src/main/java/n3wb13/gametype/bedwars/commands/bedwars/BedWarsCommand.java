package n3wb13.gametype.bedwars.commands.bedwars;

import n3wb13.gametype.bedwars.commands.MyCommand;
import n3wb13.gametype.bedwars.commands.bedwars.subcommands.TestSubCommand;
import n3wb13.gametype.bedwars.utils.ConsoleLog;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class BedWarsCommand extends MyCommand {

    public BedWarsCommand(String name) {
        super(name, false);

        addSubCommand(new TestSubCommand());
    }

    public BedWarsCommand(String name, boolean isSubCommand) {
        super(name, isSubCommand);
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        sender.sendMessage(getName() + " MainCommand Execute");

        if(getSubCommandMap().containsKey(args[0]))
            getSubCommandMap().get(args[0]).execute(sender, alias, args);

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        sender.sendMessage(getName() + " MainCommand TabComplete");
        if(args.length <= 1)
            return getSubCommandNames();

        if(getSubCommandMap().containsKey(args[0]))
            getSubCommandMap().get(args[0]).tabComplete(sender, alias, args);

        return new ArrayList<>();
    }
}
