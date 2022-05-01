package com.xiii.watchduck.check.checks.killaura;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "KillAura B", category = Category.COMBAT)
public class KillAuraB extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() != PacketType.Play.Client.BLOCK_DIG) {
            boolean isPost = isPost(packet.getPacketId(), PacketType.Play.Client.USE_ENTITY);
            if (isPost) fail("Post Packets", "NaN");

        }
    }
}
