package n3wb13.gametype.bedwars.commands.bedwars.subcommands;

import n3wb13.gametype.bedwars.commands.bedwars.BedWarsCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class TestSubCommand extends BedWarsCommand {

    public TestSubCommand() {
        super("test", true);
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        sender.sendMessage(getName() + " SubCommand Execute");

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        sender.sendMessage(getName() + " SubCommand TabComplete");
        return new ArrayList<>();
    }
}
