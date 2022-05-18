package com.xiii.watchduck.check.checks.scaffold;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.blockplace.WrappedPacketInBlockPlace;
import io.github.retrooper.packetevents.utils.vector.Vector3i;

@CheckInfo(name = "Scaffold B", category = Category.WORLD)
public class ScaffoldB extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.BLOCK_PLACE) {
            WrappedPacketInBlockPlace block = new WrappedPacketInBlockPlace(packet.getNMSPacket());
            Vector3i pos = block.getBlockPosition();
            data.sendMessage("x=" + Math.abs(pos.getX() - data.player.getLocation().getX()) + " z=" + Math.abs(pos.getZ() - data.player.getLocation().getZ()));
        }
    }
}
