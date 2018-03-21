package n3wb13.gametype.bedwars;

import n3wb13.gametype.bedwars.managers.commands.CommandManager;
import n3wb13.gametype.bedwars.managers.games.GameManager;
import n3wb13.gametype.bedwars.managers.guis.GuiManager;
import n3wb13.gametype.bedwars.managers.items.ItemManager;
import n3wb13.gametype.bedwars.managers.listeners.ListenerManager;
import n3wb13.gametype.bedwars.managers.maps.MapManager;
import n3wb13.gametype.bedwars.managers.players.PlayerManager;
import n3wb13.gametype.bedwars.managers.scoreboards.ScoreBoardManager;
import n3wb13.gametype.bedwars.managers.teams.TeamManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class BedWars extends JavaPlugin implements Listener {

    private static BedWars instance;

    public ListenerManager listenerManager;
    public CommandManager commandManager;

    public GameManager gameManager;
    public MapManager mapManager;
    public ItemManager itemManager;
    public GuiManager guiManager;
    public ScoreBoardManager scoreBoardManager;
    public PlayerManager playerManager;
    public TeamManager teamManager;

    public static BedWars getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        InstanceInit(); //インスタンス生成

        listenerManager.registerListeners(); //リスナー登録
        commandManager.registerCommands(); //コマンド登録

        scoreBoardManager.createScoreBoard();
        teamManager.createTeam(); //チーム作成
        mapManager.loadMaps();
        itemManager.registerItems();
        guiManager.registerGuis();
        playerManager.addOnlinePLayers(); //リロード対策
    }

    private void InstanceInit() {
        instance = this;

        listenerManager = new ListenerManager();
        commandManager = new CommandManager();

        gameManager = new GameManager();
        mapManager = new MapManager();
        itemManager = new ItemManager();
        guiManager = new GuiManager();
        scoreBoardManager = new ScoreBoardManager();
        playerManager = new PlayerManager();
        teamManager = new TeamManager();
    }
}
