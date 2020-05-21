package scripts;


import org.powerbot.script.Area;
import org.powerbot.script.rt4.BasicQuery;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;

public class Dead extends Task<ClientContext> {
    public Dead(ClientContext ctx) {
        super(ctx);
    }
    public static int[] gear = {1115, 1725, 1323};

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(gear).isEmpty();
    }

    @Override
    public void execute(Area area) {
        ctx.inventory.select().id(gear).action("Wear");
        ctx.inventory.select().id(gear).action("Wield");
    }
}
