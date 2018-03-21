package n3wb13.gametype.bedwars.guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiSpawnSetting extends MyGui {

    public GuiSpawnSetting() {
        inventory = Bukkit.createInventory(null, 9, ChatColor.RED + "Bed Setting");
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
    }
}
