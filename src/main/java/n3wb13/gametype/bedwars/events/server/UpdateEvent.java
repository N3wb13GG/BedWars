package n3wb13.gametype.bedwars.events.server;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UpdateEvent extends Event {

    public enum Type {
        PRE,
        POST;
    }

    private Type type;

    public UpdateEvent(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    private static final HandlerList handlers = new HandlerList();

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
