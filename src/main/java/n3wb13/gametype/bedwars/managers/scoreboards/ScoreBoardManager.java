package n3wb13.gametype.bedwars.managers.scoreboards;

import n3wb13.gametype.bedwars.managers.IManager;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreBoardManager implements IManager {

    private Scoreboard myBoard;

    public void createScoreBoard() {
        myBoard = Bukkit.getScoreboardManager().getNewScoreboard();
    }

    public Scoreboard getScoreBoard() {
        return myBoard;
    }
}
