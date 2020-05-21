package scripts;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.BasicQuery;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;
import org.powerbot.script.rt4.Player;

import java.util.Random;

public class Combat extends Task<ClientContext> {
    Player me = ctx.players.local();
    final private int COW_IDS[] = {2794, 2793, 2790, 2791, 2792};
    Random random = new Random();

    public Combat(ClientContext ctx)
    {
        super(ctx);
    }
    
    @Override
    public boolean activate() {
        return  !me.inCombat() && !me.interacting().name().equals(COW_IDS) &&
        ctx.npcs.select().select(npc->npc.interacting().equals(me)).isEmpty();
    }

    @Override
    public void execute(Area area) {
         Npc cow = ctx.npcs
                 .select()
                 .name("Cow")
                 .nearest()
                 .limit(5)
                 .select(npc -> !npc.interacting().valid() &&
                         npc.healthPercent() > 0 &&
                         npc.tile().matrix(ctx).reachable()).poll();

        if (cow.inViewport()){
            ctx.camera.pitch(30 +random.nextInt(30));
            ctx.camera.turnTo(cow);
        }
        else
            ctx.camera.pitch(30 +random.nextInt(30));

        cow.interact("Attack");

        Condition.wait(()->!me.interacting().name().equals("Chicken"),1000);
        }
}
