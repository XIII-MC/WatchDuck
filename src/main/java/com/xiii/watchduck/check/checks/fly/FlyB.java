package com.xiii.watchduck.check.checks.fly;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import com.xiii.watchduck.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "Fly B", category = Category.MOVEMENT)
public class FlyB extends Check {

    int airTicks;
    int airLimit;
    boolean touchedSlime;

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        boolean exempt = isExempt(ExemptType.FLYING, ExemptType.SLIME2, ExemptType.TELEPORT, ExemptType.JOINED, ExemptType.INSIDE_VEHICLE, ExemptType.NEAR_VEHICLE, ExemptType.CLIMBABLE, ExemptType.LIQUID, ExemptType.PLACE, ExemptType.STAIRS, ExemptType.SLAB);
        airLimit = 6;
        if(isExempt(ExemptType.SLIME2)) {
            touchedSlime = true;
        }
        if(motionY > 0.05 && !exempt) airTicks++;
        if(isExempt(ExemptType.VELOCITY)) airLimit += 5;
        if(airTicks > airLimit && !exempt && !touchedSlime) fail("Added Airticks", "ticks=" + airTicks);
        if(motionY <= 0.05 && data.onSolidGround || data.ground2() || data.isOnGround() || data.playerGround) airTicks = 0;
        if(motionY <= 0) {
            touchedSlime = false;
        }
    }
}
