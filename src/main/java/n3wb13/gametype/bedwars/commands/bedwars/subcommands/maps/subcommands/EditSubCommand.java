package n3wb13.gametype.bedwars.commands.bedwars.subcommands.maps.subcommands;

import n3wb13.gametype.bedwars.commands.bedwars.subcommands.maps.MapSubCommand;
import n3wb13.gametype.bedwars.utils.MapEditUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class EditSubCommand extends MapSubCommand {

    public EditSubCommand() {
        this.setName("edit");
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (args.length >= 3 && mapManager.getMaps().containsKey(args[2]))
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.teleport(mapManager.getMap(args[2].toLowerCase()).getWorld().getSpawnLocation());
                MapEditUtil.onEdit(playerManager.getPlayerData(player), args[2].toLowerCase());
            }

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return mapManager.getMapNames();
    }
}
