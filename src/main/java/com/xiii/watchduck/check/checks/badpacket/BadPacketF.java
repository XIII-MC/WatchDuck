package com.xiii.watchduck.check.checks.badpacket;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import com.xiii.watchduck.utils.SampleList;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.event.impl.PacketPlaySendEvent;
import io.github.retrooper.packetevents.packettype.PacketType;

@CheckInfo(name = "BadPacket F", category = Category.WORLD)
public class BadPacketF extends Check {

    long lastms;
    long lastdiff;
    long currentms;
    double bal;
    boolean wasfirst;
    boolean notmoving;
    int tickslower;

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.POSITION_LOOK || packet.getPacketId() == PacketType.Play.Client.POSITION || packet.getPacketId() == PacketType.Play.Client.LOOK) {
            lastms = currentms;
            currentms = System.currentTimeMillis();
            long diff = currentms - lastms;

            if(notmoving) {
                if(diff < 1100 && diff > 900) {
                    diff = 51;
                }
                tickslower = 0;
            } else {
                //data.sendMessage("mhhh: " + diff);
                if(bal < -80) {
                    bal--;
                }
                if(bal < -400) {
                    bal-= 2;
                    tickslower++;
                    buffer = 0;
                    if(diff > 400) {
                        tickslower = 0;
                    }
                    if(tickslower > 30) {
                        bal = -30;
                    }
                    //data.sendMessage("dont work: " + diff);

                } else
                {
                    tickslower = 0;
                }
            }
            if(!wasfirst) {
                wasfirst = true;
                diff = 100;
            }

            bal += 50 - diff;

            lastdiff = diff;
            //Bukkit.broadcastMessage( data.name + " no flag: " + bal + " "+ diff);
            if(bal >= 3) {
                fail("Sent More Move Packets", bal); // 1.4 add 1
                bal = 0;
            } else {
                removeBuffer(); // 0.1
            }
        }


    }



    public void onPacketSend(PacketPlaySendEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Server.POSITION) {
            bal -= 50;
        }
    }


    public void onMove(PacketPlayReceiveEvent packet, double motionX, double motionY, double motionZ, double lastmotionX, double lastmotionY, double lastmotionZ, float deltaYaw, float deltaPitch, float lastdeltaYaw, float lastdeltaPitch) {
        notmoving = motionX == 0 && motionY == 0 && motionZ == 0 && deltaPitch == 0 && deltaYaw == 0;
    }

}
