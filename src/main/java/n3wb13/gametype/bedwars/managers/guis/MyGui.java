package n3wb13.gametype.bedwars.managers.guis;

import n3wb13.gametype.bedwars.BedWars;
import n3wb13.gametype.bedwars.managers.commands.CommandManager;
import n3wb13.gametype.bedwars.managers.games.GameManager;
import n3wb13.gametype.bedwars.managers.items.ItemManager;
import n3wb13.gametype.bedwars.managers.listeners.ListenerManager;
import n3wb13.gametype.bedwars.managers.maps.MapManager;
import n3wb13.gametype.bedwars.managers.players.PlayerManager;
import n3wb13.gametype.bedwars.managers.scoreboards.ScoreBoardManager;
import n3wb13.gametype.bedwars.managers.teams.TeamManager;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class MyGui {

    protected BedWars bedWars = BedWars.getInstance();

    protected ListenerManager listenerManager = bedWars.listenerManager;
    protected CommandManager commandManager = bedWars.commandManager;

    protected GameManager gameManager = bedWars.gameManager;
    protected MapManager mapManager = bedWars.mapManager;
    protected ItemManager itemManager = bedWars.itemManager;
    protected ScoreBoardManager scoreBoardManager = bedWars.scoreBoardManager;
    protected PlayerManager playerManager = bedWars.playerManager;
    protected TeamManager teamManager = bedWars.teamManager;

    private final String name;

    private Inventory inventory;

    public MyGui(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    protected void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void onInventoryClick(InventoryClickEvent event) {
    }
}
