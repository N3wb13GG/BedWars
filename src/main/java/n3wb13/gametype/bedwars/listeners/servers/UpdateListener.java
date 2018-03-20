package n3wb13.gametype.bedwars.listeners.servers;

import n3wb13.gametype.bedwars.events.server.UpdateEvent;
import n3wb13.gametype.bedwars.managers.listeners.IMyListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class UpdateListener implements Listener, IMyListener {

    @EventHandler
    public void onUpdate(UpdateEvent event) {
        if(event.getType() == UpdateEvent.Type.PRE) {
            bedWars.teamManager.checkResigned();
        }
    }
}
