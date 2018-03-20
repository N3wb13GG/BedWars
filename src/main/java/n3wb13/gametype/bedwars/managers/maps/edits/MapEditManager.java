package n3wb13.gametype.bedwars.managers.maps.edits;

import n3wb13.gametype.bedwars.managers.IManager;
import n3wb13.gametype.bedwars.managers.players.PlayerData;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class MapEditManager implements IManager {

    private Map<ESettingItem, ItemStack> settingItems = new HashMap<>();

    public void registerSettingItems() {
        for (ESettingItem settingItem : ESettingItem.values()) {

            ItemStack tempItem = settingItem.getItemStack();
            ItemMeta meta = tempItem.getItemMeta();
            meta.setDisplayName(settingItem.getName());
            meta.setLore(settingItem.getLore());
            tempItem.setItemMeta(meta);

            settingItems.put(settingItem, tempItem);
        }
    }

    public void onEdit(PlayerData playerData) {
        playerData.setEditMode(true);

        playerData.getPlayer().setGameMode(GameMode.CREATIVE);
        playerData.getPlayer().getInventory().clear();
        for (ESettingItem settingItem : settingItems.keySet()) {
            playerData.getPlayer().getInventory().setItem(settingItem.getSlot(), settingItems.get(settingItem));
        }
    }

    public void onExitEdit(PlayerData playerData) {
        playerData.setEditMode(false);

        playerData.getPlayer().getInventory().clear();
    }

    public boolean onInventoryClick(PlayerData playerData, ItemStack currentItem, ItemStack cursorItem) {
        if (playerData.isEditMode()) {
            if (settingItems.containsValue(currentItem) || settingItems.containsValue(cursorItem)) {
                playerData.getPlayer().updateInventory();
                playerData.getPlayer().setItemOnCursor(new ItemStack(Material.AIR));
                return true;
            }
        }

        return false;
    }
}
