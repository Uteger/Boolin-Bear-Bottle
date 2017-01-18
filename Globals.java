package sprint_1;
import battlecode.common.*;
import java.util.ArrayList;

public class Globals {
    public static RobotController rc;
    public static MapLocation here;
    public static Team us;
    public static Team them;
    public static int myID;
    public static RobotType myType;
    public static float myVisionRadius;
    public static float myBulletVisionRadius;
    public static int roundNum;

    public static int numInitialArchons;
    public static MapLocation[] usInitialArchonLocations;
    public static MapLocation[] themInitialArchonLocations;
    public static MapLocation usCenterInitialArchons;
    public static MapLocation themCenterInitialArchons;
    public static MapLocation allCenterInitialArchons;

    public static RobotInfo[] visibleEnemyRobots;
    public static RobotInfo[] visibleAllyRobots;
    public static TreeInfo[] visibleEnemyTrees;
    public static TreeInfo[] visibleAllyTrees;
    public static TreeInfo[] visibleNeutralTrees;

    //public static ArrayList<MapLocation> seenNeutralTrees;

    /*
    public static int NUMTREES_CHANNEL;
    public static int numTreesFound;
    public static int ROUNDING_PRECISION;
    public static int minTreeLocationsChannel;
    public static int minShakenTreesChannel;
    */

    public static void init(RobotController theRC) throws GameActionException {
        rc = theRC;
        us = rc.getTeam();
        them = rc.getTeam().opponent();
        myID = rc.getID();
        myType = rc.getType();
        myVisionRadius = myType.sensorRadius;
        myBulletVisionRadius = myType.bulletSightRadius;

        usInitialArchonLocations = rc.getInitialArchonLocations(us);
        themInitialArchonLocations = rc.getInitialArchonLocations(them);
        numInitialArchons = usInitialArchonLocations.length;
        usCenterInitialArchons = new MapLocation(0,0);
        themCenterInitialArchons = new MapLocation(0,0);
        allCenterInitialArchons = new MapLocation(0,0);
        for (MapLocation a : usInitialArchonLocations) {
            usCenterInitialArchons = new MapLocation(usCenterInitialArchons.x + a.x,
                    usCenterInitialArchons.y + a.y);
        }
        for (MapLocation a : themInitialArchonLocations) {
            themCenterInitialArchons = new MapLocation(themCenterInitialArchons.x + a.x,
                    themCenterInitialArchons.y + a.y);
        }
        allCenterInitialArchons = new MapLocation(usCenterInitialArchons.x + themCenterInitialArchons.x,
                usCenterInitialArchons.y + themCenterInitialArchons.y);
        usCenterInitialArchons = new MapLocation((float)(1.0 / numInitialArchons) * usCenterInitialArchons.x,
                (float)(1.0 / numInitialArchons) * usCenterInitialArchons.y);
        themCenterInitialArchons = new MapLocation((float)(1.0 / numInitialArchons) * themCenterInitialArchons.x,
                (float)(1.0 / numInitialArchons) * themCenterInitialArchons.y);
        allCenterInitialArchons = new MapLocation((float)(0.5 / numInitialArchons) * allCenterInitialArchons.x,
                (float)(0.5 / numInitialArchons) * allCenterInitialArchons.y);

        here = rc.getLocation();

        //seenNeutralTrees = new ArrayList<>();

        /*
        ROUNDING_PRECISION = 1000;
        NUMTREES_CHANNEL = 0;
        minTreeLocationsChannel = 1000;
        minShakenTreesChannel = 3000;
        if (myID == 22 || myID == 23) {
            rc.broadcast(NUMTREES_CHANNEL, 0);
        }
        numTreesFound = rc.readBroadcast(NUMTREES_CHANNEL);
        */

        //ArrayList<MapLocation> seenNeutralTrees = new ArrayList<>();
    }

    /*
    public static MapLocation closestInitialEnemyArchonLocation() {
        MapLocation result = null;
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < themInitialArchonLocations.length; i++) {
            int dist = 5; //here.distanceTo(themInitialArchonLocations[i]);
            if (dist < minDist) {
                minDist = dist;
                result = themInitialArchonLocations[i];
            }
        }
        return result;
    }
    */

    public static void update() throws GameActionException {
        here = rc.getLocation();
        roundNum = rc.getRoundNum();
        visibleAllyRobots = rc.senseNearbyRobots(-1, us);
        visibleEnemyRobots = rc.senseNearbyRobots(-1, them);
        visibleAllyTrees = rc.senseNearbyTrees(-1, us);
        visibleEnemyTrees = rc.senseNearbyTrees(-1, them);
        visibleNeutralTrees = rc.senseNearbyTrees(-1, Team.NEUTRAL);
        //updateTreeLocation();
    }

    /*
    public static void updateTreeLocation() throws GameActionException {
        if (visibleNeutralTrees.length > 0) {
            for (int i = 0; i < visibleNeutralTrees.length; i++) {
                Object o = visibleNeutralTrees[i].getLocation();
                if (!seenNeutralTrees.contains(visibleNeutralTrees[i].getLocation())) {
                    rc.setIndicatorDot(usCenterInitialArchons,0,0,0);
                    seenNeutralTrees.add(visibleNeutralTrees[i].getLocation());
                }
            }
        }


            int x = Math.round(visibleNeutralTrees[i].getLocation().x * ROUNDING_PRECISION);
            int y = Math.round(visibleNeutralTrees[i].getLocation().y * ROUNDING_PRECISION);
            if (!isTreeAlreadyFound(x, y)) {
                rc.broadcast(numTreesFound * 2 + minTreeLocationsChannel, x);
                rc.broadcast(numTreesFound * 2 + minTreeLocationsChannel + 1, y);
                rc.broadcast(numTreesFound + minShakenTreesChannel, 0);
                numTreesFound++;
            }
        }

    }
    */

    /*
    public static boolean isTreeAlreadyFound(MapLocation loc) throws GameActionException {
        if (seenNeutralTrees.contains(loc)) {
            return true;
        }
        return false;

        for (int j = 0; j < numTreesFound * 2; j += 2) {
            if (x == rc.readBroadcast(j + minTreeLocationsChannel) &&
                    y == rc.readBroadcast(j + 1 + minTreeLocationsChannel)) {
                return true;
            }
        }
        return false;

    }
    */
}
