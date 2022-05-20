package com.xiii.watchduck.check.checks.scaffold;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.blockplace.WrappedPacketInBlockPlace;
import io.github.retrooper.packetevents.utils.vector.Vector3i;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

@CheckInfo(name = "Scaffold A", category = Category.WORLD)
public class ScaffoldA extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.BLOCK_PLACE) {
            WrappedPacketInBlockPlace block = new WrappedPacketInBlockPlace(packet.getNMSPacket());
            Vector3i pos = block.getBlockPosition();
            Block blockunderblock = getBlock(new Location(data.to.getWorld(), pos.getX(), pos.getY() - 1, pos.getZ()));
            if(blockunderblock.getType() == Material.AIR) {
                if(pos.getY() == (data.player.getLocation().getY() - 1)) {
                    if(System.currentTimeMillis() - data.lastblockplace < 200) {
                        if(data.player.getLocation().getPitch() <= 90) fail("Impossible Pitch Angle", "pitch=" + data.player.getLocation().getPitch());
                    }
                }
            }
        }
    }
}