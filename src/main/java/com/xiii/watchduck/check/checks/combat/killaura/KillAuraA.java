package com.xiii.watchduck.check.checks.combat.killaura;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "KillAura A", category = Category.MOVEMENT)
public class KillAuraA extends Check {

    double armTime;
    double flyingTime;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.POSITION && packet.getPacketId() == PacketType.Play.Client.ARM_ANIMATION) {
            if (data.getPlayer().isFlying()) {
                flyingTime = System.currentTimeMillis() * 1000;
                double pTime1 = armTime - flyingTime;
                double pTime2 = (System.currentTimeMillis() * 1000) - flyingTime;
                double pTime3 = Math.abs(pTime1 - pTime2);
                armTime--;
                if(pTime1 < 10 && pTime3 > 10) fail("Packets are out of order", "pT1=" + pTime1 + "pT2=" + pTime2 + "pT3=" + pTime3);
            if(packet.getPacketId() == PacketType.Play.Client.ARM_ANIMATION) armTime = System.currentTimeMillis() * 1000;

            }
        }
    }
}
