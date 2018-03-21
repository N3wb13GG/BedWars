package n3wb13.gametype.bedwars.managers.items;

import n3wb13.gametype.bedwars.BedWars;
import n3wb13.gametype.bedwars.managers.IManager;
import n3wb13.gametype.bedwars.utils.LogUtil;
import org.bukkit.ChatColor;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.*;

public class ItemManager implements IManager {

    private Map<String, MyItem> myItems = new HashMap<>();
    private Map<ItemMeta, MyItem> myItemMetas = new HashMap<>();

    public void registerItems() {
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
            .setScanners(new SubTypesScanner(false), new ResourcesScanner())
            .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
            .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(bedWars.getClass().getPackage().getName()))));
        Set list = reflections.getSubTypesOf(MyItem.class);

        for (Object obj : list) {
            Class objClass = (Class) obj;
            try {
                if (objClass != BedWars.class) {
                    MyItem myItem = (MyItem) objClass.newInstance();

                    myItems.put(myItem.getName(), myItem);
                    myItemMetas.put(myItem.getItemStack().getItemMeta(), myItem);
                }
            } catch (Exception e) {
                //ここに入った時点でアウト
                LogUtil.log(ChatColor.RED + "Reflection Error! you should to server restart.");
            }
        }
    }

    public Map<String, MyItem> getMyItems() {
        return myItems;
    }

    public MyItem getMyItem(String name) {
        return myItems.get(name.toLowerCase());
    }

    public void onInventoryClick(InventoryClickEvent event) {
        MyItem myItem = null;
        if (event.getCurrentItem() != null && myItemMetas.containsKey(event.getCurrentItem().getItemMeta()))
            myItem = myItemMetas.get(event.getCurrentItem().getItemMeta());
        else if (event.getCursor() != null && myItemMetas.containsKey(event.getCursor().getItemMeta()))
            myItem = myItemMetas.get(event.getCursor().getItemMeta());

        if (myItem != null)
            myItem.onInventoryClick(event);
    }

    public void onDrop(PlayerDropItemEvent event) {
        ItemMeta itemMeta = event.getItemDrop().getItemStack().getItemMeta();
        if (myItemMetas.containsKey(itemMeta)) {
            MyItem myItem = myItemMetas.get(itemMeta);

            myItem.onDrop(event);
        }
    }

    public void onItemSpawn(ItemSpawnEvent event) {
        ItemMeta itemMeta = event.getEntity().getItemStack().getItemMeta();
        if (myItemMetas.containsKey(itemMeta)) {
            MyItem myItem = myItemMetas.get(itemMeta);

            myItem.onItemSpawn(event);
        }
    }

    public void onItemUse(PlayerInteractEvent event) {
        ItemMeta itemMeta = event.getItem().getItemMeta();
        if (myItemMetas.containsKey(itemMeta)) {
            MyItem myItem = myItemMetas.get(itemMeta);

            myItem.onItemUse(event);
        }
    }
}
