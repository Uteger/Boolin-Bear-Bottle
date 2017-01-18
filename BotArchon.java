package sprint_1;
import battlecode.common.*;
import java.util.ArrayList;

public class BotArchon extends Globals {
    private static int spawnCount = 0;
    private static int numTreesSeen = 0;
    private static ArrayList<MapLocation> seenNeutralTrees = new ArrayList<>();
    //private static int[] shakenNeutralTrees = new int[100];
    private static int closestUnshakenTree = 0;

    //private static int lastArchonLocationMessageRound;
    //private static int lastGlobalArchonLocationMessageRound;

    //private static MapLocation startingLocation = null;

    public static void loop() throws GameActionException {
        Globals.init(rc);
        /*
        for (int i = 0; i < 100; i++) {
            seenNeutralTrees[i] = usInitialArchonLocations[0];
        }
        */
        /*
        for (int i = 0; i < 100; i++) {
            shakenNeutralTrees[i] = 0;
        }
        */
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
        updateTreeLocation();
        if (1 > spawnCount) {
            trySpawn();
        }
        if (closestUnshakenTree < seenNeutralTrees.size()) {
            Nav.tryMoveInDirection(here.directionTo(seenNeutralTrees.get(closestUnshakenTree)));
            tryShake(seenNeutralTrees.get(closestUnshakenTree));
        } else {
            Nav.wander();
        }
        /*
        if (visibleEnemyRobots.length == 0) {
            //Nav.tryShakeNearbyTree();
        } else if (visibleEnemyRobots.length >= 1) {
            Nav.tryMoveInDirection((here.directionTo(visibleEnemyRobots[0].getLocation()).rotateLeftDegrees(100)));
        }
        */
    }

    private static void trySpawn() throws GameActionException {
        Direction dir = new Direction((float)Math.random() * 2 * (float)Math.PI);
        if (rc.isBuildReady()) {
            for (int i = 0; i < 100; i++) {
                if (rc.canHireGardener(dir)) {
                    rc.hireGardener(dir);
                    spawnCount++;
                    return;
                }
            }
        }
    }

    public static void updateTreeLocation() throws GameActionException {
        if (visibleNeutralTrees.length > 0) {
            for (int i = 0; i < visibleNeutralTrees.length; i++) {
                if (!hasTreeBeenSeen(visibleNeutralTrees[i].getLocation())) {
                    seenNeutralTrees.add(visibleNeutralTrees[i].getLocation());
                    numTreesSeen++;
                }
            }
        }
    }

    public static boolean hasTreeBeenSeen(MapLocation loc) throws GameActionException {
        for (int i = 0; i < numTreesSeen; i++) {
            if (seenNeutralTrees.get(i) == loc) {
                return true;
            }
        }
        return false;
    }

    public static boolean tryShake(MapLocation loc) throws GameActionException {
        if (rc.canShake(loc)) {
            rc.shake(loc);
            closestUnshakenTree++;
            return true;
        }
        return false;
    }
}
