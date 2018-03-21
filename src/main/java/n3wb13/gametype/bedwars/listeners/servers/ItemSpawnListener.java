package n3wb13.gametype.bedwars.listeners.servers;

import n3wb13.gametype.bedwars.managers.listeners.IMyListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;

public class ItemSpawnListener implements Listener, IMyListener {

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
        itemManager.onItemSpawn(event);
    }
}
