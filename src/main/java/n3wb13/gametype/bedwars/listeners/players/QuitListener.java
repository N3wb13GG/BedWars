package n3wb13.gametype.bedwars.listeners.players;

import n3wb13.gametype.bedwars.managers.listeners.IMyListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener, IMyListener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        playerManager.onQuit(player);
    }
}
