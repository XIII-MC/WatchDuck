package com.xiii.watchduck.check.checks.vclip;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import com.xiii.watchduck.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import org.bukkit.potion.PotionEffectType;

@CheckInfo(name = "vClip A", category = Category.MOVEMENT)
public class vClipA extends Check {

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        boolean exempt = isExempt(ExemptType.FLYING, ExemptType.SLIME, ExemptType.TELEPORT, ExemptType.JOINED, ExemptType.VELOCITY, ExemptType.SLAB, ExemptType.STAIRS);
        if(motionY > .5634 + (data.player.hasPotionEffect(PotionEffectType.JUMP) ? ((data.player.getPotionEffect(PotionEffectType.JUMP).getAmplifier() + 1) * 0.1) : 0) && !exempt) fail("Teleported Upwards", motionY);
    }
}
