package com.xiii.watchduck.check.checks.badpacket;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import com.xiii.watchduck.utils.SampleList;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "BadPacket I", category = Category.PLAYER)
public class BadPacketI extends Check{

    long lastms = System.currentTimeMillis();
    long lastPosition = System.currentTimeMillis();
    double bal;
    double lastbal;
    SampleList<Double> balances = new SampleList<>(4);

    public void onPacket(PacketPlayReceiveEvent packet) {

        //Kind of useless check since BadPacket B already checks for FLYINGs, but keep it since it might detect faster
        if(packet.getPacketId() == PacketType.Play.Client.FLYING) {
            long diff = System.currentTimeMillis() - lastms;
            long diff2 = System.currentTimeMillis() - lastPosition;
            lastbal = bal;
            bal += 50 - diff;
            if(bal >= 5 && diff2 < 400) {
                balances.add(bal);
                if(balances.isCollected()) {
                    if(balances.getAverageDouble(balances) >= 50) {
                        fail("Basic Regen Check", "bal=" + bal);
                    } else {
                        removeBuffer();
                    }
                }
                bal = 0;
            }else {
            }

            lastms = System.currentTimeMillis();
        }else {
            if (packet.getPacketId() == PacketType.Play.Client.POSITION || packet.getPacketId() == PacketType.Play.Client.POSITION_LOOK || packet.getPacketId() == PacketType.Play.Client.LOOK) {
                if(bal < -100) {
                    bal = -20;
                }
                lastPosition = System.currentTimeMillis();
            }
        }


    }
}
