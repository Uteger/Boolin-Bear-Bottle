package sprint_1;
import battlecode.common.*;

public class BotScout extends Globals {
    private static MapLocation startingLocation = null;

    public static void loop() throws GameActionException {
        while (true) {
            try {
                //Globals.update();
                turn();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Clock.yield();
        }
    }

    private static void turn() throws GameActionException {
        Direction dir = new Direction((float)Math.random() * 2 * (float)Math.PI);
        Nav.tryMoveInDirection(dir);
    }
}
