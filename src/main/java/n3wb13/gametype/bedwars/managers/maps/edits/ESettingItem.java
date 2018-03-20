package n3wb13.gametype.bedwars.managers.maps.edits;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public enum ESettingItem {
    BED(0, new ItemStack(Material.BED), ChatColor.RED + "Bed Settings", Arrays.asList(ChatColor.GRAY + "ベッドの位置を設定します")),
    SPAWN(1, new ItemStack(Material.ARMOR_STAND), ChatColor.GREEN + "Spawn Settings", Arrays.asList(ChatColor.GRAY + "プレイヤーがスポーンする位置を設定します")),
    EXIT(2, new ItemStack(Material.BARRIER), ChatColor.GOLD + "Exit Edit", Arrays.asList(ChatColor.GRAY + "EditModeを終了します"));

    private int slot;
    private ItemStack itemStack;
    private String name;
    private List<String> lore;

    ESettingItem(int slot ,ItemStack itemStack, String name, List<String> lore) {
        this.slot = slot;
        this.itemStack = itemStack;
        this.name = name;
        this.lore = lore;
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }
}
