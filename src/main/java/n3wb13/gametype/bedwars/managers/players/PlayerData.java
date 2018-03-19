package n3wb13.gametype.bedwars.managers.players;

import n3wb13.gametype.bedwars.utils.TimeHelper;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {

    private Player player;
    private UUID uuid;

    private boolean resigned = false;
    private TimeHelper logoutTime = new TimeHelper();

    public PlayerData(Player player) {
        this.player = player;
        uuid = player.getUniqueId();
    }

    public Player getPlayer() {
        return player;
    }

    public UUID getUUID() {
        return uuid;
    }

    public boolean getResigned() {
        return  resigned;
    }

    public void setResigned(boolean resigned) {
        this.resigned = resigned;
    }

    public TimeHelper getLogoutTime() {
        return logoutTime;
    }
}
