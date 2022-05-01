package com.xiii.watchduck.check.checks.killaura;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;

@CheckInfo(name = "KillAura A", category = Category.MOVEMENT)
public class KillAuraA extends Check {

    boolean cSwing;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.USE_ENTITY) {
            WrappedPacketInUseEntity ue = new WrappedPacketInUseEntity(packet.getNMSPacket());
            if (ue.getAction() == WrappedPacketInUseEntity.EntityUseAction.ATTACK)
                if (!cSwing) fail("Missing Packet", "NaN");
            cSwing = false;
        }
        if (packet.getPacketId() == PacketType.Play.Client.ARM_ANIMATION) {
            cSwing = true;
        }


    }
}
