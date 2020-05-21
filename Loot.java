package scripts;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;
import org.powerbot.script.rt4.Player;

import java.util.Random;

public class Loot extends Task<ClientContext> {
    public Loot(ClientContext ctx) {
        super(ctx);
    }
    Random random = new Random();
    int randomInt = random.nextInt(50);
    Player me = ctx.players.local();

    //final static int bones = 526;
    //final static int chicken = 2138;




    @Override
    public boolean activate() {
        return (!me.inCombat() && !ctx.inventory.isFull() &&
                 !ctx.groundItems.select().id(1739).isEmpty());
    }

    @Override
    public void execute(Area area) {

         GroundItem cowHide = ctx.groundItems.within(area).id(1739).nearest().poll();
         if (!cowHide.inViewport()) {
             ctx.camera.pitch(30 + random.nextInt(30));
             ctx.camera.turnTo(cowHide);
         }
         else
             ctx.camera.pitch(30 + random.nextInt(30));;
         cowHide.interact("Take", "Cowhide");
         Condition.wait(()->!cowHide.valid(),1000);






    }
}
