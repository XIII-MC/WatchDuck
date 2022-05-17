package com.xiii.watchduck.check.checks.chat;

import com.xiii.watchduck.check.Category;
import com.xiii.watchduck.check.Check;
import com.xiii.watchduck.check.CheckInfo;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.chat.WrappedPacketInChat;

import java.util.Arrays;
import java.util.List;

@CheckInfo(name = "Chat A", category = Category.PLAYER)
public class ChatA extends Check {
    private final List<String> messages = Arrays.asList("can I have some tittie pics?", "do you wanna be above or below?",
            "I am gonna be pounding you 24/7", "I am gonna send you something okay? no sharing :wink:", "I am fine below or above",
            "you are gonna be riding this dick all night", "oh I am creaming just looking at you", "I want to make you cum.",
            "my balls are gonna be dry tonight thanks to you", "I am gonna relieve you all night", "daddy is ready.",
            "you will be screaming my name tonight", "fly up here and you can have as much as you want",
            "lick it off like that, until I ram your mouth.", "daddy wants your mouth on all of this tonight",
            "I bet you like daddy pounding you so hard that your knees give out and drag your face f");

    public void onPacket(PacketPlayReceiveEvent packet) {
        if(packet.getPacketId() == PacketType.Play.Client.CHAT || packet.getPacketId() == PacketType.Play.Client.CLIENT_COMMAND) {
            WrappedPacketInChat chat = new WrappedPacketInChat(packet.getNMSPacket());
            String message = chat.getMessage();
            if(messages.contains(message)) {
                fail("AutoGroomer", message);
                packet.setCancelled(true);
            }
            if(message.contains("/")) {
                for(String msg : messages) {
                    if(message.contains(msg)) {
                        fail("AutoGroomer", message);
                        packet.setCancelled(true);
                    }
                }
            }
        }
    }
}
