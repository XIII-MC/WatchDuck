package com.xiii.watchduck.check.checks.badpacket;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "BadPacket J", category = Category.PLAYER)
public class BadPacketJ extends Check {

    int pCount;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.POSITION || packet.getPacketId() == PacketType.Play.Client.LOOK || packet.getPacketId() == PacketType.Play.Client.POSITION_LOOK) {
            pCount++;
            if(pCount > 0) buffer = 0;
            if(data.getDistance(false) <= 0.1) buffer = 0;
        } else {
            if(packet.getPacketId() == PacketType.Play.Client.FLYING) {
                pCount = -1;
            }
        }
        if(pCount <= 0 && data.getDistance(false) > 0.1) fail("Packet Spam", "pCount=" + pCount);
    }
}
