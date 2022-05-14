package com.xiii.watchduck.check.checks.vclip;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import com.xiii.watchduck.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "vClip B", category = Category.MOVEMENT)
public class vClipB extends Check {

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        boolean exempt = isExempt(ExemptType.TELEPORT, ExemptType.LIQUID, ExemptType.WEB, ExemptType.GROUND, ExemptType.JOINED, ExemptType.INSIDE_VEHICLE, ExemptType.FLYING);
        if(!exempt && !data.onLowBlock) {
            if (Math.abs(motionY - data.predymotion) > 1.35 && (System.currentTimeMillis() - data.lasthurt > 1400)) {
                fail("Teleported Downwards", Math.abs(motionY - data.predymotion));
            } else removeBuffer();
        } else removeBuffer();
    }
}
