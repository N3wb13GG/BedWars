package n3wb13.gametype.bedwars.managers.listeners;

import n3wb13.gametype.bedwars.BedWars;
import n3wb13.gametype.bedwars.managers.IManager;
import n3wb13.gametype.bedwars.utils.LogUtil;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ListenerManager implements IManager {

    private List<Listener> myListeners = new ArrayList<>();

    public List<Listener> getMyListners() {
        return myListeners;
    }

    public void registerListeners() {
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
            .setScanners(new SubTypesScanner(false), new ResourcesScanner())
            .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
            .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(bedWars.getClass().getPackage().getName()))));
        Set list = reflections.getSubTypesOf(IMyListener.class);

        for (Object obj : list) {
            Class objClass = (Class) obj;
            try {
                if (objClass != BedWars.class) {
                    Listener listener = (Listener) objClass.newInstance();
                    myListeners.add(listener);
                    bedWars.getServer().getPluginManager().registerEvents(listener, bedWars);
                }
            } catch (Exception e) {
                //ここに入った時点でアウト
                LogUtil.log(ChatColor.RED + "Reflection Error! you should to server restart.");
            }
        }
    }
}
