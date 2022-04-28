package watchduck.check.checks.combat.TpAura;

import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import watchduck.check.Category;
import watchduck.check.Check;
import watchduck.check.CheckInfo;

@CheckInfo(name = "TpAura A", category = Category.MOVEMENT)
public class TpAuraA extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if (packet.getPacketId() == PacketType.Play.Client.USE_ENTITY) {
            data.sendMessage("DISTANCE=" + (data.getDeltaXZ() - data.getLastDeltaXZ()));
        }
    }
}
