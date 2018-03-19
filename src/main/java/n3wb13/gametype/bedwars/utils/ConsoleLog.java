package n3wb13.gametype.bedwars.utils;

import n3wb13.gametype.bedwars.BedWars;
import org.bukkit.command.ConsoleCommandSender;

public class ConsoleLog {

    public static void log(Object log) {
        ConsoleCommandSender console = BedWars.instance.getServer().getConsoleSender();
        console.sendMessage(((String)log).replaceAll("&([0-9a-fk-or])", "\u00a7$1"));
    }
}
