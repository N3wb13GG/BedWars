package n3wb13.gametype.bedwars.utils;

import n3wb13.gametype.bedwars.BedWars;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class LogUtil {

    public static void log(Object log) {
        ConsoleCommandSender console = BedWars.instance.getServer().getConsoleSender();
        console.sendMessage(((String)log).replaceAll("&([0-9a-fk-or])", "\u00a7$1"));
    }

    public static void log(Object log, CommandSender sender) {
        if (sender instanceof Player) {
            sender.sendMessage((String)log);
        } else {
            ConsoleCommandSender console = BedWars.instance.getServer().getConsoleSender();
            console.sendMessage(((String) log).replaceAll("&([0-9a-fk-or])", "\u00a7$1"));
        }
    }
}
