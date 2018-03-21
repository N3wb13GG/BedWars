package n3wb13.gametype.bedwars.managers.guis;

import n3wb13.gametype.bedwars.BedWars;
import n3wb13.gametype.bedwars.managers.IManager;
import n3wb13.gametype.bedwars.utils.LogUtil;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.*;

public class GuiManager implements IManager {
    private Map<String, MyGui> myGuis = new HashMap<>();
    private Map<Inventory, MyGui> myGuiInventorys = new HashMap<>();

    public void registerGuis() {
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
            .setScanners(new SubTypesScanner(false), new ResourcesScanner())
            .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
            .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(bedWars.getClass().getPackage().getName()))));
        Set list = reflections.getSubTypesOf(MyGui.class);

        for (Object obj : list) {
            Class objClass = (Class) obj;
            try {
                if (objClass != BedWars.class) {
                    MyGui myGui = (MyGui) objClass.newInstance();

                    myGuis.put(myGui.getName(), myGui);
                    myGuiInventorys.put(myGui.getInventory(), myGui);
                }
            } catch (Exception e) {
                //ここに入った時点でアウト
                LogUtil.log(ChatColor.RED + "Reflection Error! you should to server restart.");
            }
        }
    }

    public Map<String, MyGui> getMyGuis() {
        return myGuis;
    }

    public MyGui getMyGui(String name) {
        return myGuis.get(name.toLowerCase());
    }

    public MyGui getMyGui(Inventory inventory) {
        return myGuiInventorys.get(inventory);
    }

    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory() != null) {
            MyGui myGui = getMyGui(event.getInventory());

            if (myGui != null)
                myGui.onInventoryClick(event);
        }
    }
}
