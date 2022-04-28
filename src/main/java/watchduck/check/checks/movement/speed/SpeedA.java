package watchduck.check.checks.movement.speed;

import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import watchduck.check.Category;
import watchduck.check.Check;
import watchduck.check.CheckInfo;
import watchduck.exempt.ExemptType;

@CheckInfo(name = "Speed A", category = Category.MOVEMENT)
public class SpeedA extends Check {

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        boolean exempt = isExempt(ExemptType.FLYING);


    }
}
