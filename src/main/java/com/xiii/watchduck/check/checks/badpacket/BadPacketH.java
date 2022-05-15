package com.xiii.watchduck.check.checks.badpacket;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import org.bukkit.Bukkit;

@CheckInfo(name = "BadPacket H", category = Category.COMBAT)
public class BadPacketH extends Check {

    double lastDelay;

    public void onPacket(PacketPlayReceiveEvent packet) {
        //Bukkit.broadcastMessage("Delay=" + ((data.lastWindow - data.lastEntityUse) - lastDelay));
        if(packet.getPacketId() == PacketType.Play.Client.WINDOW_CLICK) data.lastWindow = System.currentTimeMillis();
        if(packet.getPacketId() == PacketType.Play.Client.USE_ENTITY) data.lastEntityUse = System.currentTimeMillis();
        lastDelay = data.lastWindow - data.lastEntityUse;

    }
}
