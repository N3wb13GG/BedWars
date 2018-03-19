package n3wb13.gametype.bedwars;

import n3wb13.gametype.bedwars.commands.bedwars.BedWarsCommand;
import n3wb13.gametype.bedwars.managers.listeners.MyListener;
import n3wb13.gametype.bedwars.managers.players.PlayerManager;
import n3wb13.gametype.bedwars.managers.teams.TeamManager;
import n3wb13.gametype.bedwars.utils.LogUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.event.Listener;

import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public final class BedWars extends JavaPlugin implements Listener {

    public static BedWars instance;

    public PlayerManager playerManager;
    public TeamManager teamManager;

    @Override
    public void onEnable() {
        InstanceInit(); //インスタンス生成
        registerListener(); //リフレクションでのリスナー登録

        try {
            final Field bCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bCommandMap.get(Bukkit.getServer());

            commandMap.register(BedWars.instance.getName(), new BedWarsCommand("bedwars"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void InstanceInit() {
        instance = this;

        playerManager = new PlayerManager();
        teamManager = new TeamManager();
    }

    private void registerListener() {
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(this.getClass().getPackage().getName()))));
        Set list = reflections.getSubTypesOf(MyListener.class); //子がListenerの物を探す

        for (Object obj : list) {
            Class objClass = (Class) obj;
            try {
                if(objClass != BedWars.class){ //BedWarsは既に登録されてるため、それ以外を通す
                    Listener listener = (Listener) objClass.newInstance(); //インスタンス生成
                    getServer().getPluginManager().registerEvents(listener, this); //リスナー登録
                }
            } catch (Exception e) {
                //ここに入った時点でアウト
                LogUtil.log(ChatColor.RED + "Reflection Error! you should to server restart.");
            }
        }
    }
}
