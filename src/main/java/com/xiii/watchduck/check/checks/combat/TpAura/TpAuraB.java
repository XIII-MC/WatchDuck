package com.xiii.watchduck.check.checks.combat.TpAura;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import com.xiii.watchduck.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "TpAura B", category = Category.MOVEMENT)
public class TpAuraB extends Check {

    double lastms;
    boolean didntmove = false;
    boolean hit = false;
    boolean cancel;

    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        boolean exempt = isExempt(ExemptType.JOINED);

        if(exempt) return;
        if(motionX != 0 || motionY != 0 || motionZ != 0) {
            double ms = System.currentTimeMillis();
            double ratio = ms - lastms;
            if(ratio < 5) {
                if(didntmove && hit) {
                    fail("Invalid Teleport", ratio);
                    debug("ratio=" +  ratio + " buffer=" + buffer);
                    cancel = true;
                    didntmove = false;
                    hit = false;
                } else {
                    didntmove = false;
                    if(hit) {
                        removeBuffer();
                    }
                    hit = false;

                }
            } else {
                if(hit) {
                    removeBuffer();
                }
                hit = false;
            }

        } else {
            didntmove = true;
            lastms = System.currentTimeMillis();
        }
    }

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.USE_ENTITY) {
            hit = true;
            if(cancel) {
                packet.setCancelled(true);
                cancel = false;
            }
        }
    }
}
