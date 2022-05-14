package com.xiii.watchduck.check.checks.invalid;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import com.xiii.watchduck.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "Invalid A", category = Category.MOVEMENT)
public class InvalidA extends Check {

    double maxSpeed;

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        boolean exempt = isExempt(ExemptType.TELEPORT, ExemptType.JOINED, ExemptType.FLYING, ExemptType.INSIDE_VEHICLE);
        maxSpeed = 0.68;
        if(isExempt(ExemptType.VELOCITY)) maxSpeed += 0.52;
        if(data.getDistance(true) > maxSpeed && !exempt) fail("Moved too fast", "mS=" + maxSpeed + " cS=" + data.getDistance(true));
    }
}
