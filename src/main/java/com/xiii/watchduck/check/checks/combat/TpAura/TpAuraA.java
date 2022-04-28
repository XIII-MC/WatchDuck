package com.xiii.watchduck.check.checks.combat.TpAura;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import com.xiii.watchduck.check.Check;

@CheckInfo(name = "TpAura A", category = Category.MOVEMENT)
public class TpAuraA extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.USE_ENTITY) {
            if(data.getLastDeltaXZSqrt() - data.getDeltaXZSqrt() >= 2 || data.getLastDeltaXZSqrt() - data.getDeltaXZSqrt() == 1.0) fail("Invalid Teleport", data.getLastDeltaXZSqrt() - data.getDeltaXZSqrt());
            if(data.getLastDeltaXZSqrt() - data.getDeltaXZSqrt() >= 2 || data.getLastDeltaXZSqrt() - data.getDeltaXZSqrt() == 1.0) packet.setCancelled(true);
        }
    }
}
