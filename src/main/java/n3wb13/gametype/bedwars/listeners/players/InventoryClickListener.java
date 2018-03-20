package n3wb13.gametype.bedwars.listeners.players;

import n3wb13.gametype.bedwars.managers.listeners.IMyListener;
import n3wb13.gametype.bedwars.managers.players.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener, IMyListener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        PlayerData playerData = playerManager.getPlayerData(player);

        event.setCancelled(mapEditManager.onInventoryClick(playerData, event.getCurrentItem(), event.getCursor()));
    }
}
