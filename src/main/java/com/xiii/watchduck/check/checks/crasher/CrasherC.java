package com.xiii.watchduck.check.checks.crasher;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "Crasher C", category = Category.PLAYER)
public class CrasherC extends Check {

    int pCount;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.POSITION) {
            pCount++;
            if(pCount > 60) fail("Packet Spam", "pCount=" + pCount);
            if(pCount > 60) packet.setCancelled(true);
        } else pCount = 0;
    }

}
