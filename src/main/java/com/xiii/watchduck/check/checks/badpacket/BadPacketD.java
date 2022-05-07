package com.xiii.watchduck.check.checks.badpacket;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "BadPacket D", category = Category.PLAYER)
public class BadPacketD extends Check {

    double lastUse;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.BLOCK_PLACE) {
            if(lastUse - System.currentTimeMillis() > -70 && lastUse - System.currentTimeMillis() < -1) fail("Packet Spam", "delay=" + (lastUse - System.currentTimeMillis()));
            if(buffer > maxBuffer) packet.setCancelled(true);
            if(lastUse - System.currentTimeMillis() < -70) removeBuffer();
            lastUse = System.currentTimeMillis();
        }
    }
}
