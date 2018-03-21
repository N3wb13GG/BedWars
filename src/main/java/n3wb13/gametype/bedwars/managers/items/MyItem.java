package n3wb13.gametype.bedwars.managers.items;

import n3wb13.gametype.bedwars.BedWars;
import n3wb13.gametype.bedwars.managers.guis.GuiManager;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class MyItem {

    protected BedWars bedWars = BedWars.getInstance();

    protected GuiManager guiManager = bedWars.guiManager;

    private final String name;

    private ItemStack itemStack;

    public MyItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    protected void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public void onItemUse(PlayerInteractEvent event) {
        return;
    }

    public void onDrop(PlayerDropItemEvent event) {
        return;
    }

    public void onItemSpawn(ItemSpawnEvent event) {
        return;
    }

    public void onInventoryClick(InventoryClickEvent event) {
        return;
    }
}
