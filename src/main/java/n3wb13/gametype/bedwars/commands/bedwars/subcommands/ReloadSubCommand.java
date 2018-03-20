package n3wb13.gametype.bedwars.commands.bedwars.subcommands;

import n3wb13.gametype.bedwars.commands.bedwars.BedWarsCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

final public class ReloadSubCommand extends BedWarsCommand {

    public ReloadSubCommand() {
        this.setName("reload");
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        mapManager.loadMaps();

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return new ArrayList<>();
    }
}
