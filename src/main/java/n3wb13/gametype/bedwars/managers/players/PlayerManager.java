package n3wb13.gametype.bedwars.managers.players;

import n3wb13.gametype.bedwars.BedWars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerManager {

    private List<PlayerData> playerDatas = new ArrayList<>();

    public List<PlayerData> getPlayerDatas() {
        return playerDatas;
    }

    public PlayerData getPlayerData(Player player) {
        for(PlayerData playerData : playerDatas) {
            if (player.getUniqueId().equals(playerData.getUUID()))
                return playerData;
        }

        PlayerData playerData = new PlayerData(player);
        playerDatas.add(playerData);
        return playerData;
    }

    public void onJoin(Player player) {
        PlayerData playerData = getPlayerData(player);
        if (playerData.getResigned()) {
            playerData.setResigned(false);
            player.sendMessage(ChatColor.GRAY + "You have been removed from the team because you have been logout for long time.");
        }
    }

    public void onQuit(Player player) {
        PlayerData playerData = getPlayerData(player);
        playerData.getLogoutTime().reset();
    }
}
