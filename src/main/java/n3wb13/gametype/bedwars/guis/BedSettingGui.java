package n3wb13.gametype.bedwars.guis;

import n3wb13.gametype.bedwars.BedWars;
import n3wb13.gametype.bedwars.managers.guis.MyGui;
import n3wb13.gametype.bedwars.managers.players.PlayerData;
import n3wb13.gametype.bedwars.managers.teams.ETeamType;
import n3wb13.gametype.bedwars.managers.teams.MyTeam;
import n3wb13.gametype.bedwars.utils.MapEditUtil;
import n3wb13.gametype.bedwars.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BedSettingGui extends MyGui {

    private List<MyTeam> teamItemMetas = new ArrayList<>();

    public BedSettingGui() {
        super(BedWars.getInstance().itemManager.getMyItem("bedsetting").getName());

        Inventory inventory = Bukkit.createInventory(null, 9, ChatColor.RED + "Bed Setting");

        ItemStack grass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 8);
        ItemMeta grassMeta = grass.getItemMeta();
        grassMeta.setDisplayName(" ");
        grass.setItemMeta(grassMeta);

        for (int i = 0; i < 9; i++)
            inventory.setItem(i, grass);

        int slot = 0;
        for (MyTeam myTeam : teamManager.getTeams().values()) {
            if (myTeam.getTeamType() != ETeamType.NONE) {
                ItemStack bed = new ItemStack(Material.BED, 1);
                ItemMeta bedMeta = bed.getItemMeta();
                bedMeta.setDisplayName(myTeam.getDisplayName() + "のベッドとして設定する");
                bed.setItemMeta(bedMeta);

                teamItemMetas.add(myTeam);
                inventory.setItem(slot, bed);
                slot++;
            }
        }

        super.setInventory(inventory);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);

        if (event.getCurrentItem() != null) {
            Player player = (Player) event.getWhoClicked();

            Block lookBlock = Utils.getLookBlock(player);
            if (teamItemMetas.size() > event.getRawSlot() && Utils.isBedBlock(lookBlock)) {
                PlayerData playerData = playerManager.getPlayerData(player);
                MapEditUtil.setBedPos(playerData, teamItemMetas.get(event.getSlot()));
            }
        }
    }
}
