package n3wb13.gametype.bedwars.managers.games;

import n3wb13.gametype.bedwars.managers.IManager;

public class GameManager implements IManager {

    private EGameProgres gameProgres = EGameProgres.WAIT;

    public void onWait() {
        gameProgres = EGameProgres.WAIT;
    }

    public void onStart() {
        gameProgres = EGameProgres.START;
    }

    public void onEnd() {
        gameProgres = EGameProgres.END;
    }

    public EGameProgres getGameProgres() {
        return gameProgres;
    }
}
