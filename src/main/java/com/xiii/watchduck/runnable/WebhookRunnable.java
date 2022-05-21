package com.xiii.watchduck.runnable;

import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import com.xiii.watchduck.WatchDuck;
import io.github.retrooper.packetevents.PacketEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import sun.nio.cs.ext.MacCentralEurope;

import java.awt.*;
import java.time.Clock;
import java.time.ZoneId;
import java.util.HashMap;

public class WebhookRunnable implements Runnable {

    private HashMap<String, HashMap<String, Integer>> flags = new HashMap<>();

    @Override
    public void run() {
        if(WatchDuck.instance.configUtils.getBooleanFromConfig("config", "sendAlertsToDiscord", false)) {
            if(!WatchDuck.instance.configUtils.getStringFromConfig("config", "discordWebhookurl", "").equals("")) {
                if(WatchDuck.instance.client != null) {
                    WebhookMessageBuilder builder = new WebhookMessageBuilder();
                    StringBuilder string = new StringBuilder();
                    string.append("```");
                    for (String s : WatchDuck.instance.alertsToSend) {
                        String[] args = s.split(" ");
                        Player player = Bukkit.getPlayer(args[0]);
                        addFlag(player.getName(), args[2]);
                    }
                    for (String s : WatchDuck.instance.alertsToSend) {
                        String[] args = s.split(" ");
                        Player player = Bukkit.getPlayer(args[0]);
                        if(getFlags(player.getName(), args[2]) > 0) {
                            string.append(s + " (x" + getFlags(player.getName(), args[2]) + ")").append("\n");
                            removeFlag(player.getName(), args[2]);
                        }

                    }
                    string.append("```");
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


    public int getFlags(String plname, String type) {
        if (flags.get(plname) == null) return 0;
        return flags.get(plname).getOrDefault(type, 0);
    }
    public void removeFlag(String plname, String type) {
        if (flags.get(plname) == null) return;
        flags.get(plname).remove(type);
    }

    public void addFlag(String plname, String check) {
        HashMap<String, Integer> inner = flags.get(plname);
        if(inner == null)
            inner = new HashMap<>();
        inner.put(check, inner.getOrDefault(check, 0) + 1);
        flags.put(plname, inner);
    }
}
