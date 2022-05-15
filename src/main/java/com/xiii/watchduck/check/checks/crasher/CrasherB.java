package com.xiii.watchduck.check.checks.crasher;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import com.xiii.watchduck.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "Crasher B", category = Category.PLAYER)
public class CrasherB extends Check {

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        boolean exempt = isExempt(ExemptType.TELEPORT);
        if(data.getDistance(true) > 10 && !exempt) fail("Moved too fast between positions", "dis=" + data.getDistance(true));
        if(data.getDistance(true) > 10 && !exempt) packet.setCancelled(true);
    }
}
