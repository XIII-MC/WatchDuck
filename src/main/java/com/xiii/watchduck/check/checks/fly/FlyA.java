package com.xiii.watchduck.check.checks.fly;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import com.xiii.watchduck.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "Fly A", category = Category.MOVEMENT)
public class FlyA extends Check {

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        boolean exempt = isExempt(ExemptType.FLYING, ExemptType.SLIME, ExemptType.TELEPORT, ExemptType.JOINED, ExemptType.INSIDE_VEHICLE, ExemptType.NEAR_VEHICLE, ExemptType.CLIMBABLE);
        if(data.inAir && !exempt) {
            if (Math.abs(data.motionY - data.predymotion) > 0.001) fail("Prediction Unfollowed", "my=" + motionY + " pred=" + data.predymotion);
            if (Math.abs(data.motionY - data.predymotion) < 0.001) removeBuffer();
        }
    }
}
