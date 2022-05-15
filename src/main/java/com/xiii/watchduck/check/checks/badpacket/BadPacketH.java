package com.xiii.watchduck.check.checks.badpacket;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "BadPacket H", category = Category.COMBAT)
public class BadPacketH extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.WINDOW_CLICK) data.lastWindow = System.currentTimeMillis();
        if(packet.getPacketId() == PacketType.Play.Client.USE_ENTITY) data.lastEntityUse = System.currentTimeMillis();
        if(data.lastEntityUse - data.lastWindow < 300 && data.lastEntityUse - data.lastWindow > -200) fail("Packet Spam", "delay=" + (data.lastEntityUse - data.lastWindow));
        if(data.lastEntityUse - data.lastWindow > 300 && data.lastEntityUse - data.lastWindow < -200) removeBuffer();
        data.lastEntityUse = 1000;
        data.lastWindow = 0;
    }
}
