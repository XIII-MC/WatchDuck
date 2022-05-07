package com.xiii.watchduck.check.checks.badpacket;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "BadPacket B", category = Category.PLAYER)
public class BadPacketB extends Check {

    int pCount;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.FLYING) {
            pCount++;
        } else {
            pCount = 0;
        }
        if(pCount > 42) fail("Packet spam", "pCount=" + pCount);
        if(pCount > 42) packet.setCancelled(true);

    }
}