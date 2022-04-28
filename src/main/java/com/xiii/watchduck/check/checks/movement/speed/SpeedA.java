package com.xiii.watchduck.check.checks.movement.speed;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.CheckInfo;
import com.xiii.watchduck.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import com.xiii.watchduck.check.Check;

@CheckInfo(name = "Speed A", category = Category.MOVEMENT)
public class SpeedA extends Check {

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        boolean exempt = isExempt(ExemptType.FLYING);


    }
}
