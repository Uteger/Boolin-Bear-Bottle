package sprint_1;
import battlecode.common.*;

public class Nav extends Globals {
    public static boolean tryMoveInDirection(Direction dir) throws GameActionException {
        if (rc.canMove(dir)) {
            rc.move(dir);
            return true;
        }

        for (int i = 0; i < 8; i++) {
            Direction left = dir.rotateLeftDegrees((float) Math.random() * 90);
            if (rc.canMove(left)) {
                rc.move(left);
                return true;
            }

            Direction right = dir.rotateRightDegrees((float) Math.random() * 90);
            if (rc.canMove(right)) {
                rc.move(right);
                return true;
            }
        }

        return false;
    }

    public static void wander() throws GameActionException {
        for (int i = 0; i < 8; i++) {
            Direction dir = new Direction((float) Math.random() * 2 * (float) Math.PI);
            tryMoveInDirection(dir);
            if (rc.hasMoved()) {
                return;
            }
        }
    }

    /*
    public static boolean goShakeTrees() throws GameActionException {
        return true;
    }

    public static boolean tryShakeNearbyTree() throws GameActionException {
        for (int i = 0; i < visibleNeutralTrees.length; i++) {
            if (rc.canShake(visibleNeutralTrees[i].getLocation())) {
                if (!hasTreeBeenShaken(visibleNeutralTrees[i].getLocation().x, visibleNeutralTrees[i].getLocation().y)) {
                    rc.shake(visibleNeutralTrees[i].getLocation());
                }
            }
        }
        return false;
    }

    public static boolean hasTreeBeenShaken(float x, float y) throws GameActionException {
        for (int i = 0; i < numTreesFound; i++) {
            if (rc.readBroadcast(minTreeLocationsChannel + (2 * i)) == x * ROUNDING_PRECISION) {
                if (rc.readBroadcast(minTreeLocationsChannel + (2 * i) + 1) == y * ROUNDING_PRECISION) {
                    if (rc.readBroadcast(minShakenTreesChannel + i) == 0) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    */
}
