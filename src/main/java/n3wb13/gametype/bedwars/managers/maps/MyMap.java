package n3wb13.gametype.bedwars.managers.maps;

import n3wb13.gametype.bedwars.utils.CustomConfig;
import org.bukkit.World;

public class MyMap {

    private final World world;
    private final CustomConfig mapConfig;

    public MyMap(World world, CustomConfig mapConfig) {
        this.world = world;
        this.mapConfig = mapConfig;
    }

    public World getWorld() {
        return world;
    }

    public CustomConfig getMapConfig() {
        return mapConfig;
    }
}
