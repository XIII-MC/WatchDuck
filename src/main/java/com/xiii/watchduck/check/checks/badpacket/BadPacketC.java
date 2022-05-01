package com.xiii.watchduck.check.checks.badpacket;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import com.xiii.watchduck.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "BadPacket C", category = Category.PLAYER)
public class BadPacketC extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.ABILITIES) {
            boolean exempt = isExempt(ExemptType.FLYING);
            if(!exempt) fail("Sent abilities packet", "NaN");
        }
    }
}
