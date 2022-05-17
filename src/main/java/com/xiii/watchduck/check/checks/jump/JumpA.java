package com.xiii.watchduck.check.checks.jump;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import com.xiii.watchduck.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import org.bukkit.potion.PotionEffectType;

@CheckInfo(name = "Jump A", category = Category.MOVEMENT)
public class JumpA extends Check {

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        boolean step = mathOnGround(motionY) && mathOnGround(data.from.getY());
        boolean jumped = motionY > 0 && data.from.getY() % (1D/64) == 0 && !data.playerGround && !step;
        double expectedJumpMotion = 0.42F + (data.getPotionEffectAmplifier(PotionEffectType.JUMP) * 0.1F);
        boolean exempt = isExempt(ExemptType.INSIDE_VEHICLE, ExemptType.VELOCITY, ExemptType.CLIMBABLE, ExemptType.FLYING, ExemptType.SLIME, ExemptType.BLOCK_ABOVE, ExemptType.PISTON, ExemptType.LIQUID, ExemptType.NEAR_VEHICLE, ExemptType.TELEPORT, ExemptType.WEB, ExemptType.TRAPDOOR);
        double diff = Math.abs(motionY - expectedJumpMotion);
        if(jumped && !exempt) {
            if (diff > 0.2 && motionY < expectedJumpMotion) {
                fail("Invalid Jump", "my=" + motionY);
            }else removeBuffer();
        }
    }
}
