package sprint_1;
import battlecode.common.*;

public class BotLumberjack extends Globals {
    private static MapLocation startingLocation = null;

    public static void loop() throws GameActionException {
        while (true) {
            try {
                Globals.update();
                turn();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Clock.yield();
        }
    }

    private static void turn() throws GameActionException {
        RobotInfo[] bots = rc.senseNearbyRobots();
        for (RobotInfo b : bots) {
            if (b.getTeam() != rc.getTeam() && rc.canStrike()) {
                rc.strike();
                Direction chase = rc.getLocation().directionTo(b.getLocation());
                if (rc.canMove(chase)) {
                    rc.move(chase);
                }
                break;
            }
        }
        TreeInfo[] trees = rc.senseNearbyTrees(-1, Team.NEUTRAL);
        for (TreeInfo t : trees) {
            if (rc.canChop(t.getLocation())) {
                rc.chop(t.getLocation());
                break;
            }
        }
        if (! rc.hasAttacked()) {
            Nav.wander();
        }
    }
}
