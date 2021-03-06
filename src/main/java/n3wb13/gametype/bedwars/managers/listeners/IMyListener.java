package n3wb13.gametype.bedwars.managers.listeners;

import n3wb13.gametype.bedwars.BedWars;
import n3wb13.gametype.bedwars.managers.commands.CommandManager;
import n3wb13.gametype.bedwars.managers.games.GameManager;
import n3wb13.gametype.bedwars.managers.guis.GuiManager;
import n3wb13.gametype.bedwars.managers.items.ItemManager;
import n3wb13.gametype.bedwars.managers.maps.MapManager;
import n3wb13.gametype.bedwars.managers.players.PlayerManager;
import n3wb13.gametype.bedwars.managers.scoreboards.ScoreBoardManager;
import n3wb13.gametype.bedwars.managers.teams.TeamManager;

public interface IMyListener {

    BedWars bedWars = BedWars.getInstance();

    ListenerManager listenerManager = bedWars.listenerManager;
    CommandManager commandManager = bedWars.commandManager;

    GameManager gameManager = bedWars.gameManager;
    MapManager mapManager = bedWars.mapManager;
    ItemManager itemManager = bedWars.itemManager;
    GuiManager guiManager = bedWars.guiManager;
    ScoreBoardManager scoreBoardManager = bedWars.scoreBoardManager;
    PlayerManager playerManager = bedWars.playerManager;
    TeamManager teamManager = bedWars.teamManager;
}
