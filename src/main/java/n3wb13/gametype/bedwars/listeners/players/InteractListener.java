package n3wb13.gametype.bedwars.listeners.players;

import n3wb13.gametype.bedwars.managers.listeners.IMyListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener, IMyListener {

    @EventHandler
    public void onItemUse(PlayerInteractEvent event) {
        if (event.getItem() != null)
            itemManager.onItemUse(event);
    }
}
