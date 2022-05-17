package com.xiii.watchduck.check.checks.crasher;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.chat.WrappedPacketInChat;
import io.github.retrooper.packetevents.packetwrappers.play.in.clientcommand.WrappedPacketInClientCommand;
import io.github.retrooper.packetevents.packetwrappers.play.in.tabcomplete.WrappedPacketInTabComplete;

@CheckInfo(name = "Crasher A", category = Category.PLAYER)
public class CrasherA extends Check {

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.CHAT) {
            WrappedPacketInChat chat = new WrappedPacketInChat(packet.getNMSPacket());
            String message = chat.getMessage();
            if(String.valueOf(message).contains("//calc for(i=0;i<256;i++)")) fail("Tried a WorldEdit exploit", "cmd=" + message);
            if(String.valueOf(message).contains("//calc for(i=0;i<256;i++)")) packet.setCancelled(true);
        }
        if(packet.getPacketId() == PacketType.Play.Client.CLIENT_COMMAND) {
            WrappedPacketInClientCommand cmd = new WrappedPacketInClientCommand(packet.getNMSPacket());
            String message = String.valueOf(cmd.getClientCommand());
            if(String.valueOf(message).contains("//calc for(i=0;i<256;i++)")) fail("Tried a WorldEdit exploit", "cmd=" + message);
            if(String.valueOf(message).contains("//calc for(i=0;i<256;i++)")) packet.setCancelled(true);
        }
        if(packet.getPacketId() == PacketType.Play.Client.TAB_COMPLETE) {
            WrappedPacketInTabComplete tab = new WrappedPacketInTabComplete((packet.getNMSPacket()));
            String tabcommand = tab.getText();
            if(String.valueOf(tabcommand).contains("for(i=0;i<256;i++)")) fail("Tried a WorldEdit exploit", "cmd=" + tabcommand);
            if(String.valueOf(tabcommand).contains("for(i=0;i<256;i++)")) packet.setCancelled(true);
        }
    }
}
