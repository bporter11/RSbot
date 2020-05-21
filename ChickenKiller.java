package scripts;




import org.powerbot.script.Area;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Script.Manifest(name="Chicken Killer", description = "hi", properties = "author=brian; topic=999; client=4;")



public class ChickenKiller extends PollingScript<ClientContext> {
    //final static int CHICKEN_IDS[] = {1173, 1174};
    //final static int LOOT_IDS[] = {526, 2138};
    private static final Area LUMBRIDGE_COW_AREA = new Area(
            new Tile(3209, 3302, 0), // NE
            new Tile(3212, 3285, 0), // SE
            new Tile(3196, 3282, 0), // SW
            new Tile(3194, 3301, 0)
    );
    List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start() {
        taskList.add(new Dead(ctx));
        taskList.add(new Loot(ctx));
        taskList.add(new Combat(ctx));
        System.out.println("hello");
    }

    @Override
    public void stop() {

    }

    @Override
    public void poll() {
        for (Task task: taskList){
            if (task.activate()){
                task.execute(LUMBRIDGE_COW_AREA);
                break;

            }

        }
        
    }
}