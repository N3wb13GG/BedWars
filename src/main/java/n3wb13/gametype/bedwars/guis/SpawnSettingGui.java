package n3wb13.gametype.bedwars.guis;

import n3wb13.gametype.bedwars.managers.guis.MyGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class SpawnSettingGui extends MyGui {

    public SpawnSettingGui() {
        super("spawnsetting");

        Inventory inventory = Bukkit.createInventory(null, 9, ChatColor.GREEN + "Spawn Setting");

        super.setInventory(inventory);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
    }
}
