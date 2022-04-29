package com.xiii.watchduck.check.checks.combat.killaura;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "KillAura B", category = Category.COMBAT)
public class KillAuraB extends Check {

    String lastPacket;
    String cPacket;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.ARM_ANIMATION) lastPacket = cPacket;
        if(packet.getPacketId() == PacketType.Play.Client.USE_ENTITY) lastPacket = cPacket;
        if(packet.getPacketId() == PacketType.Play.Client.ARM_ANIMATION) cPacket = "ARM_ANIMATION";
        if(packet.getPacketId() == PacketType.Play.Client.USE_ENTITY) cPacket = "USE_ENTITY";
        if(String.valueOf(lastPacket).contains(cPacket)) fail("Missing ARM_ANIMATION packet", "lP=" + lastPacket + "cP" + cPacket);
    }
}
