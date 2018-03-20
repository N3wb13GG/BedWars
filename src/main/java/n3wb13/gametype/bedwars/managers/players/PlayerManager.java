package n3wb13.gametype.bedwars.managers.players;

import n3wb13.gametype.bedwars.managers.IManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerManager implements IManager {

    private Map<Player, PlayerData> playerDatas = new HashMap<>();

    public Map<Player, PlayerData> getPlayerDataMap() {
        return playerDatas;
    }

    public ArrayList<PlayerData> getPlayerDatas() {
        return new ArrayList<>(playerManager.playerDatas.values());
    }

    public void createPlayerData(Player player) {
        if (!playerDatas.containsKey(player)) {
            PlayerData playerData = new PlayerData(player);
            playerDatas.put(player, playerData);
        }
    }

    public void addOnlinePLayers() {
        for (Player player : Bukkit.getOnlinePlayers()) onJoin(player);
    }

    public void onJoin(Player player) {
        player.setScoreboard(scoreBoardManager.getScoreBoard());

        createPlayerData(player);
        PlayerData playerData = getPlayerData(player);

        teamManager.onJoin(playerData);

        if (playerData.isResigned()) {
            playerData.setResigned(false);
            player.sendMessage(ChatColor.GRAY + "You have been removed from the team because you have been logout for long time.");
        }
    }

    public void onQuit(Player player) {
        PlayerData playerData = getPlayerData(player);
        playerData.getLogoutTime().reset();
    }

    public PlayerData getPlayerData(Player player) {
        return playerDatas.get(player);
    }
}
