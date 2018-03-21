package n3wb13.gametype.bedwars.managers.items;

import n3wb13.gametype.bedwars.managers.IManager;
import n3wb13.gametype.bedwars.managers.players.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class ItemManager implements IManager {

    private Map<ItemMeta, MyItem> myItemMetas = new HashMap<>();
    private Map<EMyItem, MyItem> myItems = new HashMap<>();
    private Map<Inventory, MyItem> myGuis = new HashMap<>();

    public void registerSettingItems() {
        for (EMyItem settingItem : EMyItem.values()) {
            MyItem myItem = new MyItem(settingItem);
            myItemMetas.put(myItem.getItemStack().getItemMeta(), myItem);
            myItems.put(settingItem, myItem);
            myGuis.put(myItem.getMyGui().getInventory(), myItem);
        }
    }

    private EMyItem getSettingItem(ItemStack item) {
        if (item != null) {
            if (myItemMetas.containsKey(item.getItemMeta())) {
                return myItemMetas.get(item.getItemMeta()).getEMyItem();
            }
        }

        return null;
    }

    public void onEdit(PlayerData playerData) {
        playerData.setEditMode(true);

        playerData.getPlayer().setGameMode(GameMode.CREATIVE);
        playerData.getPlayer().getInventory().clear();
        for (MyItem myItem : myItems.values()) {
            playerData.getPlayer().getInventory().setItem(myItem.getSlot(), myItem.getItemStack());
        }
    }

    public void onExitEdit(PlayerData playerData) {
        playerData.setEditMode(false);

        playerData.getPlayer().getInventory().clear();
        playerData.getPlayer().teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
    }

    public boolean onInventoryClick(PlayerData playerData, ItemStack currentItem, ItemStack cursorItem) {
        if (playerData.isEditMode()) {
            EMyItem temp = getSettingItem(currentItem);
            EMyItem myItem = temp != null ? temp : getSettingItem(cursorItem);

            if (myItem != null) {
                playerData.getPlayer().updateInventory();
                playerData.getPlayer().setItemOnCursor(new ItemStack(Material.AIR));

                return true;
            }
        }

        return false;
    }

    public boolean onDrop(PlayerData playerData, ItemStack item) {
        if (playerData.isEditMode()) {
            EMyItem myItem = getSettingItem(item);
            if (myItem != null) {
                playerData.getPlayer().updateInventory();
                playerData.getPlayer().getInventory().setItem(myItems.get(myItem).getSlot(), myItems.get(myItem).getItemStack());
                return true;
            }
        }

        return false;
    }

    public boolean onItemUse(PlayerData playerData, ItemStack item, Block clickedBlock) {
        if (playerData.isEditMode()) {
            EMyItem myItem = getSettingItem(item);
            if (myItem != null) {
                if (myItem == EMyItem.EXIT) onExitEdit(playerData);
                else playerData.getPlayer().openInventory(myItems.get(myItem).getMyGui().getInventory());

                return true;
            }
        }

        return false;
    }

    public boolean onItemSpawn(ItemStack itemStack) {
        EMyItem settingItem = getSettingItem(itemStack);
        if (settingItem != null) {
            return true;
        }

        return false;
    }
}
