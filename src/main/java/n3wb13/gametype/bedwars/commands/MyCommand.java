package n3wb13.gametype.bedwars.commands;

import n3wb13.gametype.bedwars.BedWars;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCommand extends BukkitCommand {

    BedWars bedWars = BedWars.instance;

    public final boolean isSubCommand;
    private Map<String, MyCommand> subCommands = new HashMap<>();

    public MyCommand(String name, boolean isSubCommand) {
        super(name);

        this.isSubCommand = isSubCommand;
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return null;
    }

    public void addSubCommand(MyCommand subCommand) {
        if(!subCommands.containsKey(subCommand)) {
            subCommands.put(subCommand.getName(), subCommand);
        }
    }

    public Map<String, MyCommand> getSubCommandMap() {
        return subCommands;
    }

    public ArrayList<MyCommand> getSubCommands() {
        return new ArrayList<>(subCommands.values());
    }

    public ArrayList<String> getSubCommandNames() {
        return new ArrayList<>(subCommands.keySet());
    }
}
