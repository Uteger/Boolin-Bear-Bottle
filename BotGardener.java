package sprint_1;
import battlecode.common.*;

public class BotGardener extends Globals {
    private static int spawnCount = 0;
    private static boolean rooted = false;

    private static MapLocation startingLocation = null;

    public static void loop() throws GameActionException {
        try {
            Globals.init(rc);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if (!rooted) {
            if (!moveAwayFromGardeners()) {
                if (!rc.isCircleOccupiedExceptByThisRobot(here, GameConstants.BULLET_TREE_RADIUS * 5) && rc.onTheMap(here, GameConstants.BULLET_TREE_RADIUS * 5)) {
                    rooted = true;
                } else {
                    Nav.wander();
                }
            }
        }

        if (rooted) {
            plantTree();
            waterTrees();
            if (spawnCount == 0) {
                trySpawn(RobotType.SCOUT);
            } else if (spawnCount > 0) {
                trySpawn(RobotType.LUMBERJACK);
            }
            /*
            if (visibleAllyTrees.length < 2) {
                plantTree();
                if (visibleAllyTrees.length == 1) {
                    Nav.tryMoveInDirection(here.directionTo(visibleAllyTrees[0].getLocation()).rotateLeftDegrees(180));
                } else {
                    Nav.wander();
                }
            } else if (visibleAllyTrees.length >= 2) {
                int luckyTree = 0;
                for (luckyTree = 0; luckyTree < visibleAllyTrees.length; luckyTree++) {
                    if (visibleAllyTrees[luckyTree].getHealth() < 35) {
                        Nav.tryMoveInDirection(rc.getLocation().directionTo(visibleAllyTrees[luckyTree].getLocation()));
                        if (rc.canWater(visibleAllyTrees[luckyTree].getLocation())) {
                            rc.water(visibleAllyTrees[luckyTree].getLocation());
                        }
                        break;
                    }
                }
                if (!rc.hasMoved()) {

                Nav.tryMoveInDirection(here.directionTo(visibleNeutralTrees[0].getLocation()));
                if (rc.canShake(visibleNeutralTrees[0].getLocation())) {
                    rc.shake(visibleNeutralTrees[0].getLocation());
                }

                    if (rc.canPlantTree(here.directionTo(visibleAllyTrees[0].getLocation()).rotateLeftDegrees(90))) {
                        rc.plantTree(here.directionTo(visibleAllyTrees[0].getLocation()).rotateLeftDegrees(90));
                    }
                }
                if (rc.canBuildRobot(RobotType.LUMBERJACK, here.directionTo(visibleAllyTrees[0].getLocation()).rotateRightDegrees(90))) {
                    rc.buildRobot(RobotType.LUMBERJACK, here.directionTo(visibleAllyTrees[0].getLocation()).rotateRightDegrees(90));
                    spawnCount++;
                }
            }
            */
        }
    }

    private static boolean moveAwayFromGardeners() throws GameActionException {
        for (int i = 0; i < visibleAllyRobots.length; i++) {
            if (visibleAllyRobots[i].getType() == RobotType.GARDENER) {
                Nav.tryMoveInDirection(here.directionTo(visibleAllyRobots[i].getLocation()).rotateLeftDegrees(180));
                return true;
            }
        }
        return false;
    }

    private static boolean plantTree() throws GameActionException {
        Direction dir = here.directionTo(themCenterInitialArchons);
        for (int i = 0; i < 5; i++) {
            if (rc.canPlantTree(dir)) {
                rc.plantTree(dir);
                return true;
            }
            dir = dir.rotateLeftDegrees(60);
        }
        return false;
    }

    private static boolean waterTrees() throws GameActionException {
        for (int i = 0; i < rc.senseNearbyTrees(5, us).length; i++) {
            if (rc.senseNearbyTrees(5, us)[i].getHealth() < 25) {
                if (rc.canWater(rc.senseNearbyTrees(5, us)[i].getLocation())) {
                    rc.water(rc.senseNearbyTrees(5, us)[i].getLocation());
                    return true;
                }
            }
        }
        return false;
    }

    private static void trySpawn(RobotType typeToSpawn) throws GameActionException {
        if (rc.canBuildRobot(typeToSpawn, here.directionTo(themCenterInitialArchons).rotateRightDegrees(60))) {
            rc.buildRobot(typeToSpawn, here.directionTo(themCenterInitialArchons).rotateRightDegrees(60));
            spawnCount++;
        }
    }
}
