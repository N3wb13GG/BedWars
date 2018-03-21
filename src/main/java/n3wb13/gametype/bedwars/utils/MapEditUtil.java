package n3wb13.gametype.bedwars.utils;

import n3wb13.gametype.bedwars.BedWars;
import n3wb13.gametype.bedwars.managers.players.PlayerData;
import n3wb13.gametype.bedwars.managers.teams.MyTeam;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class MapEditUtil {

    private static BedWars bedWars = BedWars.getInstance();

    public static void onEdit(PlayerData playerData, String mapName) {
        playerData.setEditMode(true);
        playerData.setEditingWorld(mapName);

        playerData.getPlayer().setGameMode(GameMode.CREATIVE);
        playerData.getPlayer().getInventory().clear();

        playerData.getPlayer().getInventory().setItem(0, bedWars.itemManager.getMyItem("bedsetting").getItemStack());
    }

    public void onExitEdit(PlayerData playerData) {
        playerData.setEditMode(false);
        playerData.setEditingWorld("");

        playerData.getPlayer().getInventory().clear();
        playerData.getPlayer().teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
    }

    public static void setBedPos(PlayerData playerData, MyTeam myTeam) {
        Block block = Utils.getLookBlock(playerData.getPlayer());
        if (Utils.isBedBlock(block)) {
            CustomConfig mapConfig = bedWars.mapManager.getMap(playerData.getEditingWorld()).getMapConfig();
            Location pos = block.getLocation();

            mapConfig.getConfig().set(myTeam.getName() + ".bedpos", pos.getX() + ", " + pos.getY() + ", " + pos.getX());
            mapConfig.saveConfig();
        }
    }
}
