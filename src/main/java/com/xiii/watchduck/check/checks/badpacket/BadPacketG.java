package com.xiii.watchduck.check.checks.badpacket;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "BadPacket G", category = Category.WORLD)
public class BadPacketG extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.BLOCK_PLACE) {
            boolean isPost = isPost(packet.getPacketId(), PacketType.Play.Client.USE_ENTITY);
            if(isPost) fail("Sent post packet", "NaN");
            if(isPost) packet.setCancelled(true);
        }
    }
}
