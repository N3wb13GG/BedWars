package n3wb13.gametype.bedwars.items.mapsettings;

import n3wb13.gametype.bedwars.managers.items.MyItem;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class BedSettingItem extends MyItem {

    private final String displayName = ChatColor.RED + "Bed Setting";
    private final List<String> lore = Arrays.asList(ChatColor.GRAY + "ベッドの位置を設定します");

    public BedSettingItem() {
        super("bedsetting");

        ItemStack itemStack = new ItemStack(Material.BED);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        super.setItemStack(itemStack);
    }

    @Override
    public void onItemUse(PlayerInteractEvent event) {
        event.setCancelled(true);
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.BED_BLOCK)
            event.getPlayer().openInventory(guiManager.getMyGui(getName()).getInventory());
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);
        ((Player) event.getWhoClicked()).updateInventory();
    }

    @Override
    public void onDrop(PlayerDropItemEvent event) {
        event.setCancelled(true);
        event.getPlayer().updateInventory();
    }

    @Override
    public void onItemSpawn(ItemSpawnEvent event) {
        event.setCancelled(true);
    }
}
