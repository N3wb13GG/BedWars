package n3wb13.gametype.bedwars.commands.bedwars.subcommands;

import n3wb13.gametype.bedwars.commands.bedwars.BedWarsCommand;
import n3wb13.gametype.bedwars.utils.LogUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

final public class InfoSubCommand extends BedWarsCommand {

    public InfoSubCommand() {
        this.setName("info");
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        LogUtil.log(ChatColor.GRAY + "------> " + ChatColor.RED + bedWars.getName() + ChatColor.GRAY + " <------", sender);
        LogUtil.log(ChatColor.GRAY + "Version: " + bedWars.getDescription().getVersion(), sender);
        LogUtil.log(ChatColor.GRAY + "Created by N3wb13", sender);

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return new ArrayList<>();
    }
}
