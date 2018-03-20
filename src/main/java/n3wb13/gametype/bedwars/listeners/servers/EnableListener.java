package n3wb13.gametype.bedwars.listeners.servers;

import n3wb13.gametype.bedwars.events.server.UpdateEvent;
import n3wb13.gametype.bedwars.managers.listeners.IMyListener;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.scheduler.BukkitScheduler;

public class EnableListener implements Listener, IMyListener {

    @EventHandler
    public void onEnable(PluginEnableEvent event) {
        if (event.getPlugin() == bedWars.getServer().getPluginManager().getPlugin(bedWars.getName())) {
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncRepeatingTask(bedWars, new Runnable() {
                @Override
                public void run() {
                    Bukkit.getServer().getPluginManager().callEvent(new UpdateEvent(UpdateEvent.Type.PRE));
                    Bukkit.getServer().getPluginManager().callEvent(new UpdateEvent(UpdateEvent.Type.POST));
                }
            }, 0L, 1L);
        }
    }
}
