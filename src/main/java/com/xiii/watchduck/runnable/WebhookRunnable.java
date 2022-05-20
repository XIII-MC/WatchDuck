package com.xiii.watchduck.runnable;

import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import com.xiii.watchduck.WatchDuck;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;

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
                    String url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrJAuo__T2HVu437O2FK1oevPtWRBcpBaG5w&usqp=CAU";

                    if (!WatchDuck.instance.alertsToSend.isEmpty()) {
                        WebhookEmbed embed = new WebhookEmbedBuilder().setColor(Color.RED.getRGB()).setImageUrl(url).setTitle(new WebhookEmbed.EmbedTitle("Watchduck", "https://discord.gg/norules")).setDescription(string.toString()).build();
                        builder.addEmbeds(embed);
                        WatchDuck.instance.alertsToSend.clear();
                        WatchDuck.instance.client.send(builder.build());
                    }
                }
            }

        }

    }
}
