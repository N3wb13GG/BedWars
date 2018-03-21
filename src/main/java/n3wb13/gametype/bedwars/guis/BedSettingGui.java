package n3wb13.gametype.bedwars.guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BedSettingGui extends MyGui {

    public BedSettingGui() {
        inventory = Bukkit.createInventory(null, 9, ChatColor.RED + "Bed Setting");
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
    }
}
