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

    private final List<String> messages = Arrays.asList("nigger", "nigga", "gger", "gga");

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.CHAT || packet.getPacketId() == PacketType.Play.Client.CLIENT_COMMAND) {
            WrappedPacketInChat chat = new WrappedPacketInChat(packet.getNMSPacket());
            String message = chat.getMessage();
            if(messages.contains(message)) {
                fail("Swore", message);
                packet.setCancelled(true);
            }
        }
    }
}
