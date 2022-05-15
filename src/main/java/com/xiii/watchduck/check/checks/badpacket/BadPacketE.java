package com.xiii.watchduck.check.checks.badpacket;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;

@CheckInfo(name = "BadPacket E", category = Category.PLAYER)
public class BadPacketE extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (data.eatDelay < 1400) {
            fail("Ate too fast", "delay=" + data.eatDelay);
            data.eatDelay = 1400;
        }
    }
}

