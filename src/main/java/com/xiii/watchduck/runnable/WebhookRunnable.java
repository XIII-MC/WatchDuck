package com.xiii.watchduck.runnable;

import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import com.xiii.watchduck.WatchDuck;
import io.github.retrooper.packetevents.PacketEvents;
import sun.nio.cs.ext.MacCentralEurope;

import java.awt.*;
import java.time.Clock;
import java.time.ZoneId;

public class WebhookRunnable implements Runnable {


    @Override
    public void run() {
        if(WatchDuck.instance.configUtils.getBooleanFromConfig("config", "sendAlertsToDiscord", false)) {
            if(!WatchDuck.instance.configUtils.getStringFromConfig("config", "discordWebhookurl", "").equals("")) {
                if(WatchDuck.instance.client != null) {
                    WebhookMessageBuilder builder = new WebhookMessageBuilder();
                    StringBuilder string = new StringBuilder();
                    for (String s : WatchDuck.instance.alertsToSend) {
                        string.append(s).append("\n");

                    }
                    //String url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrJAuo__T2HVu437O2FK1oevPtWRBcpBaG5w&usqp=CAU";

                    if (!WatchDuck.instance.alertsToSend.isEmpty()) {
                        //.setImageUrl(url)
                        WebhookEmbed embed = new WebhookEmbedBuilder().setColor(Color.CYAN.getRGB()).setAuthor(new WebhookEmbed.EmbedAuthor("WatchDuck Alerts", "", "")).setFooter(new WebhookEmbed.EmbedFooter("TPS: " + PacketEvents.get().getServerUtils().getTPS() + " • " + java.time.LocalDate.now(ZoneId.of("GMT+02:00")) + " (" + java.time.LocalTime.now(ZoneId.of("GMT+02:00")) + " CEST)", "")).setDescription(string.toString()).build();
                        builder.addEmbeds(embed);
                        WatchDuck.instance.alertsToSend.clear();
                        WatchDuck.instance.client.send(builder.build());
                    }
                }
            }

        }

    }
}
