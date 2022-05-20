package com.xiii.watchduck.check.checks.chat;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.chat.WrappedPacketInChat;

import java.util.Arrays;
import java.util.List;

@CheckInfo(name = "Chat B", category = Category.PLAYER)
public class ChatB extends Check {

    private final List<String> messages = Arrays.asList("nigger", "nigga", "niggers", "niggas", "nigger's", "nigga's", "gger", "gga", "n$gger", "niggerino", "n!gger", "n.i.g.g.e.r", "n.i.g.g.a", "n.!.g.g.e.r", "n!gger", "n!gg3r", "nlgger", "nlgg3r", "n1gger", "n1gg3r", "niger", "n1ger", "n1gga", "nigers", "n1ggas", "ni99as", "ni9as", "ni99a", "ni9a", "ni99er", "ni9er", "ni99ers", "ni9ers", "eer", "gollywog", "coon", "cotton picker", "negro");

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.CHAT) {
            WrappedPacketInChat chat = new WrappedPacketInChat(packet.getNMSPacket());
            String message = chat.getMessage();
            String message2 = message.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "").replaceAll("\\s+", "");
            boolean found = false;
            for(String msg : messages) {
                message = message.toLowerCase();
                if (message.contains(msg)) {
                    if(msg.equals("eer")) {
                        if(message.contains("gg") && message.contains("n")) {
                            if (!found) {
                                fail("Swore", message);
                                packet.setCancelled(true);
                            }
                        }
                    } else {
                        if (!found) {
                            fail("Swore", message);
                            packet.setCancelled(true);
                        }
                    }
                    found = true;
                }
            }
            if(!found) {
                for(String msg : messages) {
                    if(message2.toLowerCase().contains(msg)) {
                        if(msg.equals("eer")) {
                            if(message.contains("gg") && message.contains("n")) {
                                fail("Swore", message);
                                packet.setCancelled(true);
                            }
                        } else {
                            fail("Swore", message);
                            packet.setCancelled(true);

                        }
                    }
                }

            }
        }
    }
}
