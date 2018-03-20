package n3wb13.gametype.bedwars.commands.bedwars.subcommands;

import n3wb13.gametype.bedwars.commands.bedwars.BedWarsCommand;
import n3wb13.gametype.bedwars.utils.LogUtil;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

final public class TestSubCommand extends BedWarsCommand {

    public TestSubCommand() {
        this.setName("test");
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        LogUtil.log(getName() + " SubCommand Execute", sender);

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        LogUtil.log(getName() + " SubCommand TabComplete", sender);
        return new ArrayList<>();
    }
}
