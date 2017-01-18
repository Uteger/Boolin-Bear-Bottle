package sprint_1;
import battlecode.common.*;

public class RobotPlayer extends Globals {

    public static void run(RobotController theRC) throws GameActionException {
        Globals.init(theRC);

        switch (rc.getType()) {
            case ARCHON:
                BotArchon.loop();
                break;
            case GARDENER:
                BotGardener.loop();
                break;
            /*
            case SOLDIER:
                BotSoldier.loop();
                break;
            */
            case LUMBERJACK:
                BotLumberjack.loop();
                break;
            /*
            case TANK:
                BotTank.loop();
                break;
            */
            case SCOUT:
                BotScout.loop();
                break;

        }
    }
}
