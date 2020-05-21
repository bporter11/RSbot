package scripts;

import org.powerbot.script.Area;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.ClientAccessor;

public abstract class Task<C extends ClientContext> extends ClientAccessor {
    public Task(C ctx) {
        super(ctx);
    }
    public abstract boolean activate();

    public abstract void execute(Area area);
}
