package com.xiii.watchduck.check.checks.crasher;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.chat.WrappedPacketInChat;
import io.github.retrooper.packetevents.packetwrappers.play.in.clientcommand.WrappedPacketInClientCommand;

@CheckInfo(name = "Crasher B", category = Category.PLAYER)
public class CrasherB extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.CHAT) {
            WrappedPacketInChat chat = new WrappedPacketInChat(packet.getNMSPacket());
            String message = chat.getMessage();
            if(String.valueOf(message).contains("${")) fail("Log4J (or similar) Exploit", "NaN (Protection Reasons)");
            if(String.valueOf(message).contains("${")) packet.setCancelled(true);
        }
        if(packet.getPacketId() == PacketType.Play.Client.CLIENT_COMMAND) {
            WrappedPacketInClientCommand cmd = new WrappedPacketInClientCommand(packet.getNMSPacket());
            String message = String.valueOf(cmd.getClientCommand());
            if(String.valueOf(message).contains("${")) fail("Log4J (or similar) Exploit", "NaN (Protection Reasons)");
            if(String.valueOf(message).contains("${")) packet.setCancelled(true);
        }
    }

}
