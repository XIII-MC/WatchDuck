package com.xiii.watchduck.command;

import com.xiii.watchduck.WatchDuck;
import com.xiii.watchduck.data.Data;
import com.xiii.watchduck.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.xiii.watchduck.check.Check;

public class Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s,String[] strings) {
        if(commandSender instanceof Player) {
            Player sender = (Player)commandSender;
            if(sender.hasPermission("watchduck.command.reload") || sender.hasPermission("watchduck.*") || sender.hasPermission("watchduck.command.*") || sender.hasPermission("watchduck.command.alerts") || sender.hasPermission("watchduc.command.debug")|| sender.isOp()) {
                if (command.getName().equalsIgnoreCase("watchduck")) {
                    if (strings[0].equalsIgnoreCase("reload")) {
                        if(sender.hasPermission("watchduck.command.reload")) {
                            sender.sendMessage("§b§lWatchDuck §8»§f Reloading WatchDuck, please wait...");
                            WatchDuck.instance.configUtils.reloadConfigs();
                            Data.data.clearDataBase();
                            sender.sendMessage("§b§lWatchDuck §8»§f WatchDuck has been reloaded!");
                            return true;
                        } else {
                            sender.sendMessage("§b§lWatchDuck §8»§c Missing permission node '§4watchduck.command.reload§c'");
                        }
                    } else if (strings[0].equalsIgnoreCase("alerts")) {
                        if(sender.hasPermission("watchduck.command.alerts")) {
                            PlayerData data = Data.data.getUserData(sender);
                            data.alertstoggled = !data.alertstoggled;
                            if (data.alertstoggled) {
                                sender.sendMessage("§b§lWatchDuck §8»§f §fAlert output §aenabled§f!");
                            } else {
                                sender.sendMessage("§b§lWatchDuck §8»§f §fAlert output §cdisabled§f!");
                            }
                            return true;
                        } else {
                            sender.sendMessage("§b§lWatchDuck §8»§c Missing permission node '§4watchduck.command.alerts§c'");
                        }
                    } else if (!strings[0].equalsIgnoreCase("debug") || !strings[0].equalsIgnoreCase("reload") || !strings[0].equalsIgnoreCase("alerts") || strings[0].equalsIgnoreCase("help")) {
                        sender.sendMessage("§8§m------------------------------------");
                        sender.sendMessage("§b/watchduck §fhelp §8| §fShow this message.");
                        sender.sendMessage("§b/watchduck §falerts §8 | §fToggle alerts output.");
                        sender.sendMessage("§b/watchduck §fdebug <CheckCheckType> §8| §fToggle debug for a certain check.");
                        sender.sendMessage("§b/watchduck §freload §8| §fReloads WatchDuck's config files.");
                        sender.sendMessage("§8§m------------------------------------");
                    } else if (strings[0].equalsIgnoreCase("debug")) {
                        if(sender.hasPermission("watchduck.command.debug")) {
                            if (strings.length > 1) {
                                if (strings.length < 3) {
                                    PlayerData data = Data.data.getUserData(sender);
                                    for (Check c : data.checks) {
                                        String checkname = c.name.replace(" ", "");
                                        if (strings[1].equalsIgnoreCase(checkname)) {
                                            c.isdebugging = !c.isdebugging;
                                            if (c.isdebugging) {
                                                sender.sendMessage("§b§lWatchDuck §8»§f §fDebugging output §aenabled§f for §a" + c.name + "!");
                                            } else {
                                                sender.sendMessage("§b§lWatchDuck §8»§f §fDebugging output §cdisabled§f for §a" + c.name + "!");
                                            }
                                        }
                                    }
                                } else {
                                    Player target = Bukkit.getPlayer(strings[2]);
                                    if (target != null) {
                                        PlayerData data = Data.data.getUserData(target);
                                        for (Check c : data.checks) {
                                            String checkname = c.name.replace(" ", "");
                                            if (strings[1].equalsIgnoreCase(checkname)) {
                                                c.isdebugging = !c.isdebugging;
                                                if (c.isdebugging) {
                                                    c.debugtoplayers.add(sender);
                                                    sender.sendMessage("§b§lWatchDuck §8»§f §fDebugging output §aenabled§f for §a" + c.name + "!");
                                                } else {
                                                    c.debugtoplayers.remove(sender);
                                                    sender.sendMessage("§b§lWatchDuck §8»§f §fDebugging output §cdisabled§f for §a" + c.name + "!");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            sender.sendMessage("§b§lWatchDuck §8»§c Missing permission node '§4watchduck.command.debug§c'");
                        }
                        return true;

                    }
                }
            }
            else {
                sender.sendMessage("Unknown command. Type \"/help\" for help.");
            }
        }

        return false;
    }
}
