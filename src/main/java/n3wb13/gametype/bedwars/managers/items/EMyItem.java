package n3wb13.gametype.bedwars.managers.items;

import n3wb13.gametype.bedwars.guis.BedSettingGui;
import n3wb13.gametype.bedwars.guis.GuiSpawnSetting;
import n3wb13.gametype.bedwars.guis.MyGui;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum EMyItem {
    BED(0, Material.BED, ChatColor.RED + "Bed Setting", Arrays.asList(ChatColor.GRAY + "ベッドの位置を設定します"), new BedSettingGui()),
    SPAWN(1, Material.ARMOR_STAND, ChatColor.GREEN + "Spawn Setting", Arrays.asList(ChatColor.GRAY + "プレイヤーがスポーンする位置を設定します"), new GuiSpawnSetting()),
    EXIT(8, Material.BARRIER, ChatColor.GOLD + "Exit Edit", Arrays.asList(ChatColor.GRAY + "EditModeを終了します"), new MyGui());

    private int slot;
    private Material material;
    private String name;
    private List<String> lore;
    private MyGui myGui;

    EMyItem(int slot, Material material, String name, List<String> lore, MyGui myGui) {
        this.slot = slot;
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.myGui = myGui;
    }

    public int getSlot() {
        return slot;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public MyGui getMyGui() {
        return myGui;
    }
}
