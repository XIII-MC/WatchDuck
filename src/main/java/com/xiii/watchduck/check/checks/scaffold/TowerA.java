package com.xiii.watchduck.check.checks.scaffold;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import com.xiii.watchduck.exempt.ExemptType;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import org.bukkit.Bukkit;

@CheckInfo(name = "Tower A", category = Category.WORLD)
public class TowerA extends Check {

    int bCount;

    public void onPacket(PacketPlayReceiveEvent packet) {
        boolean exempt = isExempt(ExemptType.CLIMBABLE, ExemptType.FLYING);
        if (packet.getPacketId() == PacketType.Play.Client.BLOCK_PLACE) {
            if (data.motionY > 0.2) {
                bCount++;
                if(bCount > 6 && !exempt) fail("Placed too many blocks", "bCount=" + bCount);
                if(bCount > 6 && !exempt) packet.setCancelled(true);
            } else bCount = 0;

        }
    }
}