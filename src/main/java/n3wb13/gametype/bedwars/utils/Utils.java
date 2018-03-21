package n3wb13.gametype.bedwars.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.Set;

public class Utils {

    public static Block getLookBlock(Player player) {
        return player.getTargetBlock((Set<Material>) null, 0);
    }

    public static Block getBedNeighbor(Block head) {
        if (Utils.isBedBlock(head.getRelative(BlockFace.EAST))) {
            return head.getRelative(BlockFace.EAST);
        } else if (Utils.isBedBlock(head.getRelative(BlockFace.WEST))) {
            return head.getRelative(BlockFace.WEST);
        } else if (Utils.isBedBlock(head.getRelative(BlockFace.SOUTH))) {
            return head.getRelative(BlockFace.SOUTH);
        } else {
            return head.getRelative(BlockFace.NORTH);
        }
    }

    public static boolean isBedBlock(Block isBed) {
        if (isBed == null) {
            return false;
        }

        return (isBed.getType() == Material.BED || isBed.getType() == Material.BED_BLOCK);
    }
}
