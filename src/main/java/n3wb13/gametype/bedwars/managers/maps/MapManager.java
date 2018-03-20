package n3wb13.gametype.bedwars.managers.maps;

import n3wb13.gametype.bedwars.managers.IManager;
import n3wb13.gametype.bedwars.managers.teams.ETeamType;
import n3wb13.gametype.bedwars.utils.CustomConfig;
import n3wb13.gametype.bedwars.utils.LogUtil;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapManager implements IManager {

    private Map<String, MyMap> mapList = new HashMap<>();

    private final String mapDirectory = "/Maps/";

    public void loadMaps() {
        File[] files = new File(bedWars.getDataFolder() + mapDirectory).listFiles();
        for (File file : files) {
            if (!file.exists())
                continue;
            else if (file.isDirectory()) {
                File file1 = new File(file + "/" + file.getName() + ".yml");
                File file2 = new File(file + "/" + file.getName() + "/");
                if (file1.exists() && file2.exists()) {
                    mapList.put(file.getName().toLowerCase(), new MyMap(Bukkit.createWorld(new WorldCreator(file + "/" + file.getName())), new CustomConfig(mapDirectory + file.getName() + "/" + file1.getName())));
                    LogUtil.log(ChatColor.GREEN + file.getName() + " Map is loaded");
                } else
                    LogUtil.log(ChatColor.RED + "Map Load Error! " + file.getName() + " does not exist map data.");
            }
        }
    }

    public EMapCreateDetails createMap(String name) {
        if (!mapList.containsKey(name.toLowerCase())) {
            CustomConfig tempConfig = new CustomConfig(mapDirectory + name.toLowerCase() + "/" + name.toLowerCase() + ".yml");
            tempConfig.getConfig().set("name", name);
            Location tempLoc = new Location(Bukkit.getWorld(name), 0, 0, 0, 0, 0);
            for (ETeamType teamType : ETeamType.values()) {
                if (teamType != ETeamType.NONE) {
                    tempConfig.getConfig().set(teamType.getTeamName() + ".bedpos.1", tempLoc.getX() + ", " + tempLoc.getY() + ", " + tempLoc.getX() + ", " + tempLoc.getYaw() + ", " + tempLoc.getPitch());
                    tempConfig.getConfig().set(teamType.getTeamName() + ".bedpos.2", tempLoc.getX() + ", " + tempLoc.getY() + ", " + tempLoc.getX() + ", " + tempLoc.getYaw() + ", " + tempLoc.getPitch());
                    tempConfig.getConfig().set(teamType.getTeamName() + ".spawnpos.1", tempLoc.getX() + ", " + tempLoc.getY() + ", " + tempLoc.getX() + ", " + tempLoc.getYaw() + ", " + tempLoc.getPitch());
                    tempConfig.getConfig().set(teamType.getTeamName() + ".spawnpos.2", tempLoc.getX() + ", " + tempLoc.getY() + ", " + tempLoc.getX() + ", " + tempLoc.getYaw() + ", " + tempLoc.getPitch());
                    tempConfig.getConfig().set(teamType.getTeamName() + ".spawnpos.3", tempLoc.getX() + ", " + tempLoc.getY() + ", " + tempLoc.getX() + ", " + tempLoc.getYaw() + ", " + tempLoc.getPitch());
                }
            }

            tempConfig.saveConfig();

            File mapFile = new File(bedWars.getDataFolder() + mapDirectory + "/" + name.toLowerCase() + "/" + name.toLowerCase());
            if (!mapFile.exists()) {
                return EMapCreateDetails.MAP_FILE_NOT_EXIST;
            }

            mapList.put(name.toLowerCase(), new MyMap(Bukkit.createWorld(new WorldCreator(bedWars.getDataFolder() + mapDirectory + "/" + name.toLowerCase() + "/" + name.toLowerCase())), tempConfig));
            return EMapCreateDetails.TRUE;
        }

        return EMapCreateDetails.EXIST;
    }

    public Map<String, MyMap> getMaps() {
        return mapList;
    }

    public ArrayList<String> getMapNames() {
        return new ArrayList<>(mapList.keySet());
    }
}
