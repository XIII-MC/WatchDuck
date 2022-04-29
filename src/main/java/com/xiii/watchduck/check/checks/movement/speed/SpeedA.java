package com.xiii.watchduck.check.checks.movement.speed;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.CheckInfo;
import com.xiii.watchduck.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import com.xiii.watchduck.check.Check;

@CheckInfo(name = "Speed A", category = Category.MOVEMENT)
public class SpeedA extends Check {

    double maxSpeed;
    double cSpeed;
    int groundTicks;

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {

        if(data.isOnGround()) groundTicks++;
        cSpeed = data.getDistance(true);

        if(groundTicks > 22) maxSpeed = 0.3;
        if(groundTicks < 22) maxSpeed = 0.35;

        if(data.lastice < 2000) {
            if(groundTicks > 22) maxSpeed = 0.3;
            if(groundTicks < 22) maxSpeed = 1;
        }

        if(data.lastslime < 2000) {
            if(groundTicks > 22) maxSpeed = 0.12;
            if(groundTicks < 22) maxSpeed = 0.8;
        }

        if(isExempt(ExemptType.WEB)) maxSpeed = 0.15;
        if(isExempt(ExemptType.LIQUID)) {
            maxSpeed = 0.28;
            if(data.getDepthStriderLevel() >= 1) maxSpeed = (data.getDepthStriderLevel() * 0.03) + 0.2;
        }

        if(data.lasthurt < 2000) {
            maxSpeed = 0.6;
            if(data.kblevel >= 1) maxSpeed = (data.kblevel * 0.95);
        }

        if(cSpeed > maxSpeed) fail("Moved too fast", "cs=" + cSpeed + "ms=" + maxSpeed);
        if(cSpeed <= maxSpeed) removeBuffer();
    }
}
