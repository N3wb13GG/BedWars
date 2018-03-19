package n3wb13.gametype.bedwars.listeners.players;

import n3wb13.gametype.bedwars.BedWars;
import n3wb13.gametype.bedwars.listeners.MyListener;
import n3wb13.gametype.bedwars.managers.players.PlayerData;
import n3wb13.gametype.bedwars.managers.teams.TeamType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener, MyListener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        bedWars.teamManager.setTeam(bedWars.playerManager.getPlayerData(player), TeamType.Blue);

        bedWars.playerManager.onJoin(player);
    }
}
