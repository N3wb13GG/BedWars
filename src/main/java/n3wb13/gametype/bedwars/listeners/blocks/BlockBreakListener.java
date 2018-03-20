package n3wb13.gametype.bedwars.listeners.blocks;

import n3wb13.gametype.bedwars.managers.listeners.IMyListener;
import n3wb13.gametype.bedwars.utils.LogUtil;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener, IMyListener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.BED_BLOCK) {
            LogUtil.log("Bed Break!", event.getPlayer());
        }
    }
}
