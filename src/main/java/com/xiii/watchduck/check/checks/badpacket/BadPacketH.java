package com.xiii.watchduck.check.checks.badpacket;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import org.bukkit.Bukkit;

@CheckInfo(name = "BadPacket H", category = Category.COMBAT)
public class BadPacketH extends Check {

    double lastWindowTime;
    double lastUseEntityTime;

    public void onPacket(PacketPlayReceiveEvent packet) {
        //
        if(packet.getPacketId() == PacketType.Play.Client.WINDOW_CLICK) lastWindowTime = System.currentTimeMillis();
        if(packet.getPacketId() == PacketType.Play.Client.USE_ENTITY) lastUseEntityTime = System.currentTimeMillis();
        //lastDelay = data.lastWindow - data.lastEntityUse;
        double delay = lastUseEntityTime - lastWindowTime;
        if(packet.getPacketId() == PacketType.Play.Client.USE_ENTITY) {
            if(delay < 50) {
                fail("Armor Breaker", delay);
            }
        }

    }
}
