package n3wb13.gametype.bedwars.commands.bedwars;

import n3wb13.gametype.bedwars.managers.commands.MyCommand;
import n3wb13.gametype.bedwars.commands.bedwars.subcommands.InfoSubCommand;
import n3wb13.gametype.bedwars.commands.bedwars.subcommands.TestSubCommand;
import n3wb13.gametype.bedwars.utils.LogUtil;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class BedWarsCommand extends MyCommand {

    public BedWarsCommand(String name) {
        super(name);

        addSubCommand(new InfoSubCommand());
        addSubCommand(new TestSubCommand());
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        LogUtil.log(getName() + " MainCommand Execute", sender);

        if(args.length >= 1 && getSubCommandMap().containsKey(args[0]))
            getSubCommandMap().get(args[0]).execute(sender, alias, args);

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        LogUtil.log(getName() + " MainCommand TabComplete", sender);
        if(args.length <= 1)
            return getSubCommandNames();

        if(getSubCommandMap().containsKey(args[0]))
            getSubCommandMap().get(args[0]).tabComplete(sender, alias, args);

        return new ArrayList<>();
    }
}
