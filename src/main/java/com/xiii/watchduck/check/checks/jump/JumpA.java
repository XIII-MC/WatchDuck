package com.xiii.watchduck.check.checks.jump;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import com.xiii.watchduck.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "Jump A", category = Category.MOVEMENT)
public class JumpA extends Check {

    int jumpTicks;

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        boolean exempt = isExempt(ExemptType.FLYING, ExemptType.TELEPORT, ExemptType.WEB, ExemptType.SLAB, ExemptType.STAIRS, ExemptType.LIQUID, ExemptType.BLOCK_ABOVE, ExemptType.VELOCITY, ExemptType.PLACE);
        if(motionY > 0) {
            jumpTicks++;
            if(jumpTicks == 1) {
                if(motionY != 0.41999998688697815 && !exempt && !data.onLowBlock) fail("Invalid Jump", "my=" + motionY);
                if(motionY == 0.41999998688697815) removeBuffer();
            }
        } else jumpTicks = 0;
    }
}
