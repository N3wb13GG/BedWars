package n3wb13.gametype.bedwars.commands.bedwars.subcommands.games.subcommands;

import n3wb13.gametype.bedwars.commands.bedwars.subcommands.games.GameSubCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

final public class WaitSubCommand extends GameSubCommand {

    public WaitSubCommand() {
        this.setName("wait");
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        gameManager.onWait();
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return new ArrayList<>();
    }
}
