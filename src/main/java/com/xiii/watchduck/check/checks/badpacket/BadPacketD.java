package com.xiii.watchduck.check.checks.badpacket;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import org.bukkit.Bukkit;

@CheckInfo(name = "BadPacket D", category = Category.PLAYER)
public class BadPacketD extends Check {

    int aUse;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.USE_ITEM) {
            aUse++;
            Bukkit.broadcastMessage(data.player + "Sent USE_ITEM x" + aUse);
        } else aUse = 0;
    }
}
