package com.xiii.watchduck.listener;

import com.xiii.watchduck.data.Data;
import com.xiii.watchduck.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.*;
import com.xiii.watchduck.WatchDuck;

public class Event implements Listener {

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(WatchDuck.instance, () -> {
            Data.data.registerUser(e.getPlayer());
            PlayerData data = Data.data.getUserData(e.getPlayer());
            if (e.getCause() != PlayerTeleportEvent.TeleportCause.UNKNOWN) {
                if (System.currentTimeMillis() - data.joined < 800) {
                    data.tpafterjoin = System.currentTimeMillis();
                }
                if (System.currentTimeMillis() - data.wasDead < 300) {
                    data.tpafterded = System.currentTimeMillis();
                }
                data.lastTeleport = System.currentTimeMillis();

            } else {
                if (e.getTo().distanceSquared(e.getFrom()) < 0.05) {
                    data.weirdTeleport = System.currentTimeMillis();
                }
            }
        });
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDeath(PlayerDeathEvent e) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(WatchDuck.instance, () -> {
            Data.data.registerUser(e.getEntity());
            PlayerData data = Data.data.getUserData(e.getEntity());
            data.isDead = true;
            data.wasDead = System.currentTimeMillis();
        });
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(WatchDuck.instance, () -> {
            Data.data.registerUser(e.getPlayer());
            PlayerData data = Data.data.getUserData(e.getPlayer());
            data.wasDead = System.currentTimeMillis();
        });
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(WatchDuck.instance, () -> {
            PlayerData data = Data.data.getUserData(e.getPlayer());
            data.lastblockplace = System.currentTimeMillis();
            data.blockplaced = e.getBlock();
        });
    }

    @EventHandler
    public void onDMG(EntityDamageEvent e) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(WatchDuck.instance, () -> {
            if (e.getEntity() instanceof Player) {
                Player p = (Player) e.getEntity();
                Data.data.registerUser(p);
                PlayerData data = Data.data.getUserData(p);
                if(e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION || e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK || e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE || e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)
                    data.validVelocityHit = true;
                else
                    data.validVelocityHit = false;
                data.lasthurt = System.currentTimeMillis();
                data.lastvelocity = System.currentTimeMillis();
                if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
                    data.entityhit = System.currentTimeMillis();


                if (e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION || e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)
                    data.lasthurtother = System.currentTimeMillis();
            }

        });
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(WatchDuck.instance, () -> {
            PlayerData data = Data.data.getUserData(e.getPlayer());
            data.lastUse = System.currentTimeMillis();
        });
    }

    @EventHandler
    public void onShoot(ProjectileLaunchEvent e) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(WatchDuck.instance, () -> {
            if (e.getEntity() instanceof Arrow) {
                Arrow arrow = (Arrow) e.getEntity();
                if (arrow.getShooter() != null && arrow.getShooter() instanceof Player) {
                    Player player = (Player) arrow.getShooter();
                    PlayerData data = Data.data.getUserData(player);
                        data.lastShootDelay = System.currentTimeMillis() - data.lastUse;
                        data.shootDelay = System.currentTimeMillis() - data.lastShoot;
                        data.lastShoot = System.currentTimeMillis();
                }
            }
        });
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(WatchDuck.instance, () -> {
            PlayerData data = Data.data.getUserData(e.getPlayer());
                data.eatDelay = System.currentTimeMillis() - data.lastUse;
                if (data.eatDelay < 1400) e.setCancelled(true);
                data.lastEat = System.currentTimeMillis();
        });
    }

    @EventHandler
    public void onEntityDMG(EntityDamageByEntityEvent e) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(WatchDuck.instance, () -> {
            if (e.getEntity() instanceof Player) {
                Player p = (Player) e.getEntity();
                Data.data.registerUser(p);
                PlayerData data = Data.data.getUserData(p);
                data.lasthurtother = System.currentTimeMillis();
                data.lastvelocity = System.currentTimeMillis();
                //if(e.getDamager() instanceof Player || e.getDamager() instanceof Mob) {
                //    if (((LivingEntity) e.getDamager()).getEquipment().getItemInMainHand().containsEnchantment(Enchantment.KNOCKBACK))
                //        data.kblevel = ((LivingEntity) e.getDamager()).getEquipment().getItemInMainHand().getEnchantmentLevel(Enchantment.KNOCKBACK);

                //    if (((LivingEntity) e.getDamager()).getEquipment().getItemInMainHand().containsEnchantment(Enchantment.ARROW_KNOCKBACK))
                //        data.kblevel = ((LivingEntity) e.getDamager()).getEquipment().getItemInMainHand().getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK);
                //}
            }
        });
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(WatchDuck.instance, () -> {
            Data.data.registerUserJoin(e.getPlayer());
            PlayerData data = Data.data.getUserData(e.getPlayer());
            data.joined = System.currentTimeMillis();
            data.join = 0;
        });

    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBreak(BlockBreakEvent e) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(WatchDuck.instance, () -> {
            Data.data.registerUser(e.getPlayer());
            PlayerData data = Data.data.getUserData(e.getPlayer());
            data.brokenblock = e.getBlock();
            if(e.isCancelled()) {
                data.cancel = 0;
            }
        });

    }
}
