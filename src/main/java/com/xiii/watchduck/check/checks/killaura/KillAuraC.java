package com.xiii.watchduck.check.checks.killaura;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "KillAura C", category = Category.COMBAT)
public class KillAuraC extends Check {

    int cCount;

    public void onPacket(PacketPlayReceiveEvent packet) {
    }
}
