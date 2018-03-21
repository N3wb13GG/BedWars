package n3wb13.gametype.bedwars.managers.items;

import n3wb13.gametype.bedwars.guis.MyGui;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MyItem {

    private final int slot;
    private final ItemStack settingItem;
    private final EMyItem eMyItem;

    private MyGui myGui = new MyGui();

    public MyItem(EMyItem eMyItem) {
        this.eMyItem = eMyItem;
        slot = eMyItem.getSlot();
        settingItem = new ItemStack(eMyItem.getMaterial());
        ItemMeta meta = settingItem.getItemMeta();
        meta.setDisplayName(eMyItem.getName());
        meta.setLore(eMyItem.getLore());
        settingItem.setItemMeta(meta);

        myGui = eMyItem.getMyGui();
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getItemStack() {
        return settingItem;
    }

    public EMyItem getEMyItem() {
        return eMyItem;
    }

    public MyGui getMyGui() {
        return myGui;
    }

    protected Inventory inventory;

    public Inventory getInventory() {
        return inventory;
    }
}
