package n3wb13.gametype.bedwars.listeners.players;

import n3wb13.gametype.bedwars.managers.listeners.IMyListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener, IMyListener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        itemManager.onInventoryClick(event);
        guiManager.onInventoryClick(event);

    }
}
