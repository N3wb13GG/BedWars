package n3wb13.gametype.bedwars.listeners.players;

import n3wb13.gametype.bedwars.managers.listeners.IMyListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItemListener implements Listener, IMyListener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        itemManager.onDrop(event);
    }
}
