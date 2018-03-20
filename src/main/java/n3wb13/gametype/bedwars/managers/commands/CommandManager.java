package n3wb13.gametype.bedwars.managers.commands;

import n3wb13.gametype.bedwars.BedWars;
import n3wb13.gametype.bedwars.managers.IManager;
import n3wb13.gametype.bedwars.utils.LogUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CommandManager implements IManager {

    private List<MyCommand> myCommands = new ArrayList<>();

    public List<MyCommand> getMyCommands() {
        return myCommands;
    }

    public void registerCommands() {
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
            .setScanners(new SubTypesScanner(false), new ResourcesScanner())
            .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
            .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(bedWars.getClass().getPackage().getName()))));
        Set list = reflections.getSubTypesOf(MyCommand.class);

        for (Object obj : list) {
            Class objClass = (Class) obj;
            try {
                if (objClass.getSuperclass() == MyCommand.class) {
                    MyCommand myCommand = (MyCommand) objClass.newInstance();
                    myCommands.add(myCommand);

                    try {
                        final Field bCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

                        bCommandMap.setAccessible(true);
                        CommandMap commandMap = (CommandMap) bCommandMap.get(Bukkit.getServer());

                        commandMap.register(bedWars.getName(), myCommand);

                        registerSubCommands(myCommand);
                    } catch (Exception e) {
                        //ここに入った時点でアウト
                        LogUtil.log(ChatColor.RED + "Reflection Error! you should to server restart.");
                    }
                }
            } catch (Exception e) {
                //ここに入った時点でアウト
                LogUtil.log(ChatColor.RED + "Reflection Error! you should to server restart.");
            }
        }
    }

    private void registerSubCommands(MyCommand myCommand) {
        List<ClassLoader> classLoadersList1 = new LinkedList<ClassLoader>();
        classLoadersList1.add(ClasspathHelper.contextClassLoader());
        classLoadersList1.add(ClasspathHelper.staticClassLoader());

        Reflections reflections1 = new Reflections(new ConfigurationBuilder()
            .setScanners(new SubTypesScanner(false), new ResourcesScanner())
            .setUrls(ClasspathHelper.forClassLoader(classLoadersList1.toArray(new ClassLoader[0])))
            .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(BedWars.getInstance().getClass().getPackage().getName()))));
        Set list1 = reflections1.getSubTypesOf(myCommand.getClass());

        for (Object obj1 : list1) {
            Class objClass1 = (Class) obj1;
            try {
                if (objClass1.getSuperclass() == myCommand.getClass()) {
                    MyCommand subCommand = (MyCommand) objClass1.newInstance();

                    myCommand.addSubCommand(subCommand);

                    if (list1.size() > 0) registerSubCommands(subCommand);
                }
            } catch (Exception e) {
                //ここに入った時点でアウト
                LogUtil.log(ChatColor.RED + "Reflection Error! you should to server restart.");
            }
        }
    }
}
