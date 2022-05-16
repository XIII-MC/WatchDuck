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
    Entity target;
    int targetedEntities;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.USE_ENTITY) {
            WrappedPacketInUseEntity ue = new WrappedPacketInUseEntity(packet.getNMSPacket());
            lastTarget = target == null ? ue.getEntity() : target;
            target = ue.getEntity();
            if(target != lastTarget) {
                targetedEntities++;
            }
            if(targetedEntities > 1)
                fail("Multi Aura", targetedEntities);

        }
        if(packet.getPacketId() == PacketType.Play.Client.POSITION || packet.getPacketId() == PacketType.Play.Client.POSITION_LOOK || packet.getPacketId() == PacketType.Play.Client.LOOK) {
            targetedEntities = 0;
        }
    }
}
