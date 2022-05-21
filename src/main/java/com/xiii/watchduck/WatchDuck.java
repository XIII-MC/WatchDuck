package com.xiii.watchduck;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import com.xiii.watchduck.data.Data;
import com.xiii.watchduck.listener.Event;
import com.xiii.watchduck.listener.PacketListener;
import com.xiii.watchduck.command.Command;
import com.xiii.watchduck.runnable.WebhookRunnable;
import com.xiii.watchduck.utils.ConfigUtils;
import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.settings.PacketEventsSettings;
import io.github.retrooper.packetevents.utils.server.ServerVersion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class WatchDuck extends JavaPlugin {

    public static WatchDuck instance;
    public PacketListener listener;
    public ConfigUtils configUtils;
    public WebhookClient client;
    public ArrayList<String> alertsToSend = new ArrayList<>();

    @Override
    public void onLoad() {
        PacketEvents.create(this);
        PacketEventsSettings settings = PacketEvents.get().getSettings();
        settings
                .fallbackServerVersion(ServerVersion.v_1_7_10)
                .compatInjector(false)
                .checkForUpdates(false);
        PacketEvents.get().load();
        PacketEvents.get().loadAsyncNewThread();
    }

    @Override
    public void onEnable() {
        instance = this;
        listener = new PacketListener();
        Data.data.clearDataBase();
        Bukkit.getPluginManager().registerEvents(new Event(), this);
        Bukkit.getConsoleSender().sendMessage("§8[§b§lWATCHDUCK§8] §fWatchDuck is now §aenabled§f!");
        Bukkit.getPluginCommand("watchduck").setExecutor(new Command());
        configUtils = new ConfigUtils(this);
        PacketEvents.get().init();
        PacketEvents.get().registerListener(listener);
        PacketEvents.get().getInjector().eject();
        PacketEvents.get().getInjector().inject();
        for(Player p : Bukkit.getOnlinePlayers()) {
            PacketEvents.get().getInjector().injectPlayer(p);
        }
        WebhookRunnable runnable = new WebhookRunnable();
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, runnable, 100, 100);
        if(configUtils.getBooleanFromConfig("config", "sendAlertsToDiscord", false)) {
            discordthing();
        }
    }

    public void discordthing() {
        if(configUtils.getStringFromConfig("config", "discordWebhookurl", "").equals("")) {
            Bukkit.getConsoleSender().sendMessage("§8[§b§lWATCHDUCK§8] §cPlease add the Webhook URL to the 'config.yml' and reload§f!");
            return;
        }
        WebhookClientBuilder builder = new WebhookClientBuilder(configUtils.getStringFromConfig("config", "discordWebhookurl", ""));
        builder.setThreadFactory((job) -> {
            Thread thread = new Thread(job);
            thread.setName("Webhook-Thread");
            thread.setDaemon(true);
            return thread;
        });

        this.client = builder.build();
    }


    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§8[§b§lWATCHDUCK§8] §fWatchDuck is now §cdisabled§f!");
        PacketEvents.get().terminate();
        Bukkit.getScheduler().cancelTasks(this);

    }

}
