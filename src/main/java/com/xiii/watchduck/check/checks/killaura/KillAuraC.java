package com.xiii.watchduck.check.checks.killaura;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

@CheckInfo(name = "KillAura C", category = Category.COMBAT)
public class KillAuraC extends Check {

    Entity lastTarget;
    int targetedEntities;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.USE_ITEM) {
            WrappedPacketInUseEntity ue = new WrappedPacketInUseEntity(packet.getNMSPacket());
            if(lastTarget == null) lastTarget = ue.getEntity();
            if(ue.getEntity() != lastTarget) {
                ++targetedEntities;
            }
            lastTarget = ue.getEntity();
            if(targetedEntities > 1) {
               // Bukkit.broadcastMessage("TargetCount: " + targetedEntities);
            }
        }
        if(packet.getPacketId() == PacketType.Play.Client.POSITION || packet.getPacketId() == PacketType.Play.Client.POSITION_LOOK || packet.getPacketId() == PacketType.Play.Client.LOOK) {
            targetedEntities = 0;
        }
    }
}
